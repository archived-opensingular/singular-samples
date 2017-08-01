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
package org.opensingular.singular.form.showcase.db.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.io.IOUtils;
import org.opensingular.lib.commons.util.Loggable;
import org.opensingular.lib.support.spring.util.ApplicationContextProvider;
import org.opensingular.singular.form.showcase.db.IdSessionLocator;
import org.opensingular.singular.form.showcase.db.SessionDataSource;

/**
 * 
 * Listener para criar e destruir banco de dados h2.
 *
 */
@WebListener
public class DataSourceSessionListener implements HttpSessionListener, Loggable {

	 
	public DataSourceSessionListener() {
	}

    @Override
	public void sessionCreated(HttpSessionEvent se) {
        getLogger().info(String.format("****** (%s) Criou uma sessao !!!! id=> %s", se.getSession().getMaxInactiveInterval(),
                se.getSession().getId()));
		generateDB(se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	    getLogger().info("****** Destruiu a sessao !!!! {}", se.getSession().getId());

	    //remove do map
	    SessionDataSource sds =  ApplicationContextProvider.get().getBean("dataSource", SessionDataSource.class);
	    sds.removeDB(se.getSession().getId());

	    //remove do threadLocal
		IdSessionLocator.get().remove();
		
		//remove arquivo
		destroyDB(se);
	}
	
    private void generateDB(HttpSessionEvent se) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            byte[] result = IOUtils.toByteArray(classLoader.getResourceAsStream("/"+SessionDataSource.DATABASE_FOLDER+"/singulardb.mv.db"));
            
            //cria pasta caso nao existir
            new File(SessionDataSource.DATABASE_FOLDER).mkdirs();
            
            File file = new File(String.format(SessionDataSource.DATABASE_FOLDER+"/singulardb_%s.mv.db", se.getSession().getId()));//NOSONAR
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(result);
            fos.close();
            
        } catch (FileNotFoundException e) {
            getLogger().error("Arquivo embarcado do h2 não encontrado", e);
        } catch (IOException e) {
            getLogger().error("Erro ao gerar o arquivo do banco h2 ", e);
        }
    }
    
    private void destroyDB(HttpSessionEvent se){
        final File folder = new File(SessionDataSource.DATABASE_FOLDER);
        final File[] files = folder.listFiles((File dir, String name)->name.startsWith(String.format("singulardb_%s", se.getSession().getId())));

        for ( final File file : files ) {
            if ( !file.delete() ) {
                getLogger().error("O arquivo do banco de dados não pode ser deletado {}", file.getAbsolutePath());
            }
        }
    }

    
}
