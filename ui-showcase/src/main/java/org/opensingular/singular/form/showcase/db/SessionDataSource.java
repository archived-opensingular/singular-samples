/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opensingular.singular.form.showcase.db;

import com.google.common.io.Files;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.opensingular.lib.commons.util.Loggable;
import org.opensingular.singular.form.showcase.db.job.DBCollectorJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Datasource capaz de criar um pool de datasources para cada sessão http.<br>
 * Os banco são criado dentro da uma pasta "db" e ao encerrar a sessão serão
 * apagados.<br>
 * <br>
 * Existe o job {@link DBCollectorJob} que é executado a cada 12h, que verifica
 * se o arquivo do banco tem mais que 24h de criado, caso seja positivo, o
 * arquivo é deletado.
 */
public class SessionDataSource extends HikariDataSource implements Loggable {

    private static final String INITIAL_DB_RESOURCE_PATH = "db/singulardb.mv.db";

    private static final File baseDir = Files.createTempDir();
    private static Map<String, HikariDataSource> internalPoolDS = new ConcurrentHashMap<String, HikariDataSource>();
    private static ThreadLocal<String> sessionIdHolder = new ThreadLocal<String>();

    public SessionDataSource() {
        super();
    }

    public SessionDataSource(String sessionId) {
        this();
    }

    public static void clearSessionId() {
        sessionIdHolder.remove();
    }

    private static String getSessionId() {
        return (sessionIdHolder.get() != null) ? sessionIdHolder.get() : "0";
    }

    public static void setSessionId(String sessionId) {
        sessionIdHolder.set(sessionId);
    }

    private static void generateDB(String sessionId) {
        final ClassLoader classLoader = SessionDataSource.class.getClassLoader();
        try (//
             FileOutputStream fos = new FileOutputStream(getDatabaseMvFile(sessionId)); //
             InputStream initialDB = classLoader.getResourceAsStream(INITIAL_DB_RESOURCE_PATH); //
        ) {

            IOUtils.copy(initialDB, fos);
            fos.flush();

        } catch (FileNotFoundException e) {
            LoggerFactory.getLogger(SessionDataSource.class).error("Arquivo embarcado do h2 não encontrado", e);
        } catch (IOException e) {
            LoggerFactory.getLogger(SessionDataSource.class).error("Erro ao gerar o arquivo do banco h2 ", e);
        }
    }

    private static void destroyDB(String sessionId) {
        final String filenamePrefix = String.format("singulardb_%s", sessionId);
        final File[] files = baseDir.listFiles((File dir, String name) -> name.startsWith(filenamePrefix));

        for (final File file : files) {
            if (!file.delete()) {
                LoggerFactory.getLogger(SessionDataSource.class)
                        .error("O arquivo do banco de dados não pode ser deletado {}", file.getAbsolutePath());
            }
        }
    }

    /**
     * Coleta de lixo: remove os arquivos de banco que não são alterados a mais de 24 horas.
     */
    public static void gc() {
        final Logger logger = LoggerFactory.getLogger(SessionDataSource.class);

        final long hours = 24;
        final LocalDateTime expirationDateTime = LocalDateTime.now().minusHours(hours);
        final ZoneId zone = ZonedDateTime.now().getZone();

        final File[] files = baseDir.listFiles((dir, name) -> name.endsWith("db"));
        for (File file : files) {
            logger.trace("**** Verificando arquivo {}", file.getPath());

            final Instant instant = Instant.ofEpochMilli(file.lastModified());
            final LocalDateTime lastModified = LocalDateTime.ofInstant(instant, zone);

            if (lastModified.isBefore(expirationDateTime)) {
                logger.info("O Arquivo ({}) não é alterado a mais de {} horas será deletado: {}",
                        file.getName(), hours, lastModified);

                if (!file.delete())
                    logger.info("Não foi possivel deletar o arquivo: {}", file);
            }
        }
    }

    private static File getDatabaseMvFile(String sessionId) {
        return new File(baseDir, String.format("/singulardb_%s.mv.db", sessionId));//NOSONAR
    }

    private static String getDatabasePath(String sessionId) {
        return String.format("%s/singulardb_%s", baseDir.getAbsolutePath(), sessionId);
    }

    @Override
    public Connection getConnection() throws SQLException {

        String sessionId = getSessionId();

        if (sessionId != null) {
            if (!internalPoolDS.containsKey(sessionId)) {
                getLogger().info("criou um novo Banco para a sessionId {}", sessionId);
                internalPoolDS.put(sessionId, createNewDb(sessionId));
            }

            getLogger().info("Utilizou o DS da SesionID {}", sessionId);

            return internalPoolDS.get(sessionId).getConnection();
        } else {
            getLogger().info("Não tem sessão HTTP criada ! Utiliza o datasource original");
            return super.getConnection();
        }
    }

    public HikariDataSource createNewDb(String sessionId) {
        final String dbOptions = StringUtils.substringAfter(this.getJdbcUrl(), ";");

        generateDB(sessionId);

        final HikariDataSource ddss = new HikariDataSource();
        String newDbUrl = "jdbc:h2:file:" + getDatabasePath(sessionId) + ";" + dbOptions;
        System.out.println(newDbUrl);
        ddss.setJdbcUrl(newDbUrl);
        ddss.setDriverClassName("org.h2.Driver");
        ddss.setUsername("sa");
        ddss.setPassword("sa");
        return ddss;
    }

    public void removeDB(String sessionId) {
        HikariDataSource hikariDataSource = internalPoolDS.get(sessionId);
        try {
            if (hikariDataSource != null) {
                hikariDataSource.close();
            }
        } catch (Exception e) {
            getLogger().error(e.getMessage(), e);
            getLogger().error("Erro ao fechar a conexão com o banco");
        }
        internalPoolDS.remove(sessionId);
        destroyDB(sessionId);
        getLogger().info("Removeu o DS da SesionID {}", sessionId);
    }

}
