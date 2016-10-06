/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.singular.flow.core.view;

import java.io.Serializable;

public class ModalViewDef implements Serializable {

    private int width = -1;
    private int height = -1;

    public static ModalViewDef of(int width, int height) {
        return new ModalViewDef(width, height);
    }

    private ModalViewDef(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public ModalViewDef setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public ModalViewDef setHeight(int height) {
        this.height = height;
        return this;
    }

}