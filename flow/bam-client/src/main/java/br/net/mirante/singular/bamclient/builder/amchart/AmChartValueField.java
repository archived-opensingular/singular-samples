package br.net.mirante.singular.bamclient.builder.amchart;

import java.io.Serializable;

public class AmChartValueField implements Serializable {

    private String propertyName;
    private String title;
    private String suffix;

    public AmChartValueField(String propertyName, String title) {
        this(propertyName, title, "");
    }

    public AmChartValueField(String propertyName, String title, String suffix) {
        this.propertyName = propertyName;
        this.title = title;
        this.suffix = suffix;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getTitle() {
        return title;
    }

    public String getSuffix() {
        return suffix;
    }
}
