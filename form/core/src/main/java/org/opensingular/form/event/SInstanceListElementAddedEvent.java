/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.event;

import org.opensingular.form.SIList;
import org.opensingular.form.SInstance;

public class SInstanceListElementAddedEvent extends SInstanceStructureChangeEvent {

    private final SInstance addedInstance;
    private final int       index;

    public SInstanceListElementAddedEvent(SIList<? extends SInstance> source, SInstance addedInstance, int index) {
        super(source);
        this.addedInstance = addedInstance;
        this.index = index;
    }

    public SInstance getAddedInstance() {
        return addedInstance;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return super.toString() + "[" + getIndex() + "] += " + getAddedInstance();
    }
}
