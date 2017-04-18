package org.opensingular.singular.form.showcase.db;

public class IdSessionLocator {

    private static IdSessionLocator idSessionLocator;

    private ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private IdSessionLocator() {

    }

    public static IdSessionLocator get() {
        if (idSessionLocator == null) {
            idSessionLocator = new IdSessionLocator();
        }
        return idSessionLocator;
    }

    public void setSessionId(String sessionId) {
        threadLocal.set(sessionId);
    }

    public String getSessionId() {
        return threadLocal.get();
    }

    public void remove() {
        threadLocal.remove();
    }
}
