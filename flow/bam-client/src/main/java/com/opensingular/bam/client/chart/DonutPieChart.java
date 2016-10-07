/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.opensingular.bam.client.chart;

import com.opensingular.bam.client.builder.amchart.AmPieChartBuilder;

public class DonutPieChart extends PieChart {

    public DonutPieChart() {
    }

    public DonutPieChart(String categoryProperty, String valueProperty) {
        super(categoryProperty, valueProperty);
    }

    @Override
    protected void addOthersConfigs(AmPieChartBuilder chartBuilder) {
        chartBuilder.innerRadius(40);
    }
}
