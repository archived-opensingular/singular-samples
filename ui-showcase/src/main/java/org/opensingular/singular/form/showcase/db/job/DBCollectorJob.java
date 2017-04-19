package org.opensingular.singular.form.showcase.db.job;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.opensingular.lib.commons.util.Loggable;
import org.opensingular.singular.form.showcase.db.SessionDataSource;
import org.springframework.stereotype.Component;

/**
 * 
 * O DBCollectorJob é responsável por remover os bancos de dados gerados a mais
 * de um dia.
 *
 */
@Component("DBCollectorJob")
public class DBCollectorJob implements Loggable {

    public void run() {

        File folderDB = new File(SessionDataSource.DATABASE_FOLDER);
        File[] files = folderDB.listFiles();
        for (File file : files) {
            getLogger().trace("**** Verificando arquivo {}", file.getPath());
            try {
                BasicFileAttributes att = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                LocalDateTime creationDateTime = LocalDateTime.ofInstant(att.creationTime().toInstant(),
                        ZonedDateTime.now().getZone());
                long hours = 24;
                LocalDateTime expirationDateTime = LocalDateTime.now().minusHours(hours);

                if (file.getName().endsWith("db") && creationDateTime.isBefore(expirationDateTime)) {
                    getLogger().info("O Arquivo ({}) foi criado a mais de {} horas será deletado: {}", file.getName(),
                            hours, creationDateTime);
                    if (!file.delete()) {
                        getLogger().info("Não foi possivel deletar o arquivo: {}", file);
                    }
                    ;
                }

            } catch (IOException e) {
                getLogger().error("Erro ao processar o Job para limpar arquivos do banco h2", e);
            }
        }
    }

}
