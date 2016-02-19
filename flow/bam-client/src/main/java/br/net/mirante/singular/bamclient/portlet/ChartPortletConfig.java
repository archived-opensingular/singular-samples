package br.net.mirante.singular.bamclient.portlet;

import br.net.mirante.singular.bamclient.chart.SingularChart;

public abstract class ChartPortletConfig<T extends ChartPortletConfig<T>> extends PortletConfig<T> {

    private DataEndpoint dataEndpoint;
    private SingularChart chart;

    public ChartPortletConfig() {
    }

    public ChartPortletConfig(DataEndpoint dataEndpoint, SingularChart chart) {
        this.dataEndpoint = dataEndpoint;
        this.chart = chart;
    }

    public ChartPortletConfig(DataEndpoint dataEndpoint, SingularChart chart,
                              BamDashboardView dashboardView) {
        this(dataEndpoint, chart);
        setTitle(dashboardView.getTitle());
        setSubtitle(dashboardView.getSubtitle());
        setPortletSize(dashboardView.getPortletSize());

    }

    public SingularChart getChart() {
        return chart;
    }

    public T setChart(SingularChart chart) {
        this.chart = chart;
        return self();
    }

    public DataEndpoint getDataEndpoint() {
        return dataEndpoint;
    }

    public T setDataEndpoint(DataEndpoint dataEndpoint) {
        this.dataEndpoint = dataEndpoint;
        return self();
    }
}
