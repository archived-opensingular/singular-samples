package org.opensingular.singular.form.showcase.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.id.CompositeNestedGeneratedValueGenerator.GenerationContextLocator;
import org.opensingular.lib.commons.util.Loggable;
import org.opensingular.lib.support.spring.util.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	    getLogger().info("****** Destruiu a sessao !!!! " + se.getSession().getId());
	    //getLogger().info("***** max "+se.getSession().getMaxInactiveInterval());

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
            byte[] result = IOUtils.toByteArray(classLoader.getResourceAsStream("/db/singulardb.mv.db"));
            
            //cria pasta caso nao existir
            new File("db").mkdirs();
            
            File file = new File(String.format("db/singulardb_%s.mv.db", se.getSession().getId()));
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
        final File folder = new File("db");
        final File[] files = folder.listFiles((File dir, String name)->name.startsWith(String.format("singulardb_%s", se.getSession().getId())));

        for ( final File file : files ) {
            if ( !file.delete() ) {
                getLogger().error("Can't remove " + file.getAbsolutePath());
            }
        }
    }

    
}
