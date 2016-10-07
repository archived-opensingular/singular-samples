/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.lib.wicket.util.bootstrap.layout;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.border.Border;

import static org.opensingular.lib.wicket.util.util.WicketUtils.$b;

public class BSWellBorder extends Border {

    public final static String LARGE = "padding: 12px";
    public final static String SMALL = "padding: 6px;";

    public BSWellBorder(String id) {
        this(id, null);
    }

    public BSWellBorder(String id, String sizeClass) {
        super(id);
        addToBorder(buildWell(sizeClass));
    }

    private WebMarkupContainer buildWell(String sizeClass) {
        return new WebMarkupContainer("well") {
            @Override
            protected void onInitialize() {
                super.onInitialize();
                add($b.attrAppender("style", sizeClass, StringUtils.SPACE));
            }
        };
    }

    public static BSWellBorder large(String id) {
        return new BSWellBorder(id, LARGE);
    }

    public static BSWellBorder small(String id) {
        return new BSWellBorder(id, SMALL);
    }

}
