package br.net.mirante.singular.bamclient.portlet;


import java.io.Serializable;

import br.net.mirante.singular.bamclient.enums.EndpointType;

public class DataEndpoint implements Serializable {

    private EndpointType endpointType;
    private String processAbbreviation;
    private String dashboardViewName;
    private String url;


    public String getProcessAbbreviation() {
        return processAbbreviation;
    }

    public void setProcessAbbreviation(String processAbbreviation) {
        this.processAbbreviation = processAbbreviation;
    }

    public String getDashboardViewName() {
        return dashboardViewName;
    }

    public void setDashboardViewName(String dashboardViewName) {
        this.dashboardViewName = dashboardViewName;
    }

    public EndpointType getEndpointType() {
        return endpointType;
    }

    public void setEndpointType(EndpointType endpointType) {
        this.endpointType = endpointType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static DataEndpoint rest(String processAbbreviation, String dashboardViewName) {
        final DataEndpoint endpoint = new DataEndpoint();
        endpoint.endpointType = EndpointType.REST;
        endpoint.processAbbreviation = processAbbreviation;
        endpoint.dashboardViewName = dashboardViewName;
        return endpoint;
    }

    public static DataEndpoint local(String url) {
        final DataEndpoint endpoint = new DataEndpoint();
        endpoint.endpointType = EndpointType.LOCAL;
        endpoint.url = url;
        return endpoint;
    }
}