/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package br.net.mirante.singular.bam.wicket.view.template;

import static br.net.mirante.singular.util.wicket.util.WicketUtils.$b;
import static br.net.mirante.singular.util.wicket.util.WicketUtils.$m;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

public class Header extends Panel {

    private boolean withTogglerButton;
    private boolean withTopAction;
    private boolean withSideBar;

    public Header(String id) {
        super(id);
        this.withTogglerButton = true;
        this.withTopAction = true;
        this.withSideBar = false;
    }

    public Header(String id, boolean withTogglerButton,  boolean withTopAction, boolean withSideBar) {
        super(id);
        this.withTogglerButton = withTogglerButton;
        this.withTopAction = withTopAction;
        this.withSideBar = withSideBar;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new WebMarkupContainer("togglerButton")
                .add($b.attrAppender("class", "hide", " ", $m.ofValue(!withTogglerButton))));
        if (withTopAction) {
            add(new TopAction("_TopAction"));
        } else {
            add(new WebMarkupContainer("_TopAction"));
        }
        add(new TopMenu("_TopMenu", withSideBar));

    }
}
