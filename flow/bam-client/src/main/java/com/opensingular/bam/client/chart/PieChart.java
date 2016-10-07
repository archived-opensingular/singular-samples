/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.opensingular.bam.client.chart;

import com.opensingular.bam.client.builder.SingularChartBuilder;
import com.opensingular.bam.client.builder.amchart.AmPieChartBuilder;

public class PieChart implements SingularChart {

    private String valueProperty;
    private String categoryProperty;

    public PieChart() {
    }

    public PieChart(String categoryProperty, String valueProperty) {
        this.valueProperty = valueProperty;
        this.categoryProperty = categoryProperty;
    }

    @Override
    public String getDefinition() {
        final AmPieChartBuilder chartBuilder = new SingularChartBuilder()
                .newPieChart()
                .startDuration(0.5)
                .startEffect("easeOutSine")
                .angle(12)
                .balloonText("[[title]]<br><span style='font-size:14px'><b>[[value]]</b> ([[percents]]%)</span>")
                .depth3D(15)
                .labelRadius(30)
                .minRadius(50)
                .titleField(categoryProperty)
                .valueField(valueProperty);

        addOthersConfigs(chartBuilder);

        return chartBuilder.finish();
    }


    protected void addOthersConfigs(AmPieChartBuilder chartBuilder) {
    }

    public String getValueProperty() {
        return valueProperty;
    }

    public void setValueProperty(String valueProperty) {
        this.valueProperty = valueProperty;
    }

    public String getCategoryProperty() {
        return categoryProperty;
    }

    public void setCategoryProperty(String categoryProperty) {
        this.categoryProperty = categoryProperty;
    }
}
