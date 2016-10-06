/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.opensingular.bam.client.builder.amchart;

import com.opensingular.bam.client.builder.JSONBuilderContext;

public class AmSerialChartBuilder extends AmChartBuilder<AmSerialChartBuilder> {

    public AmSerialChartBuilder(JSONBuilderContext JSONBuilderContext) {
        super(JSONBuilderContext);
        context.getJsonWriter().key("type").value("serial");
    }

    @Override
    public AmSerialChartBuilder self() {
        return this;
    }
}