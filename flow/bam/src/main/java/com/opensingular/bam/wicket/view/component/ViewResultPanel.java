/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.opensingular.bam.wicket.view.component;


import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.opensingular.bam.client.portlet.PortletConfig;
import com.opensingular.bam.client.portlet.PortletContext;

public abstract class ViewResultPanel<T extends PortletConfig> extends Panel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewResultPanel.class);

    private IModel<T> config;

    private IModel<PortletContext> context;

    public ViewResultPanel(String id, IModel<T> config, IModel<PortletContext> context) {
        super(id);
        this.config = config;
        this.context = context;
    }

    public IModel<T> getConfig() {
        return config;
    }

    public IModel<PortletContext> getContext() {
        return context;
    }

    public String getSerializedContext() {
        try {
            return new ObjectMapper().writeValueAsString(context.getObject());
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

}

