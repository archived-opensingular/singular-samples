package org.opensingular.singular.form.showcase.db.job;

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
        SessionDataSource.gc();
    }

}
