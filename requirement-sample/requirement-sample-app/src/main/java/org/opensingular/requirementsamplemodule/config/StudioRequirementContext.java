package org.opensingular.requirementsamplemodule.config;

import org.opensingular.lib.commons.base.SingularProperties;
import org.opensingular.server.commons.config.IServerContext;

public enum StudioRequirementContext implements IServerContext {

    REQUIREMENT("/requirement/*", "singular.requirement"),
    WORKLIST("/worklist/*", "singular.worklist"),
    ADMINISTRATION("/administration/*", "singular.administration"),
    ROOT("/*", "singular.root");

    private final String contextPath;
    private final String propertiesBaseKey;

    StudioRequirementContext(String defaultPath, String propertiesBaseKey) {
        this.propertiesBaseKey = propertiesBaseKey;
        String key  = propertiesBaseKey + ".context";
        String path = SingularProperties.get().getProperty(key);
        if (path == null || path.length() <= 0) {
            path = defaultPath;
        }
        if (!path.endsWith("/*")) {
            if (path.endsWith("*")) {
                path = path.substring(0, path.length() - 2) + "/*";
            } else if (path.endsWith("/")) {
                path += "*";
            } else {
                path += "/*";
            }
        }
        this.contextPath = path;
    }


    @Override
    public String getPropertiesBaseKey() {
        return propertiesBaseKey;
    }

    @Override
    public String getName() {
        return this.name();
    }

    /**
     * O contexto no formato aceito por servlets e filtros
     *
     * @return
     */
    @Override
    public String getContextPath() {
        return contextPath;
    }

    /**
     * Conversao do formato aceito por servlets e filtros (contextPath) para java regex
     *
     * @return
     */
    @Override
    public String getPathRegex() {
        return getContextPath().replaceAll("\\*", ".*");
    }

    /**
     * Conversao do formato aceito por servlets e filtros (contextPath) para um formato de url
     * sem a / ao final.
     *
     * @return
     */
    @Override
    public String getUrlPath() {
        String path = getContextPath().replace("*", "").replace(".", "").trim();
        return path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
    }

}