package org.opensingular.singular.form.showcase;

import org.opensingular.lib.commons.base.SingularException;

/**
 * @author Daniel C. Bordin
 * @since 2018-07-27
 */
public class ShowCaseException extends SingularException {

    public ShowCaseException() {
    }

    public ShowCaseException(String msg) {
        super(msg);
    }

    public ShowCaseException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
