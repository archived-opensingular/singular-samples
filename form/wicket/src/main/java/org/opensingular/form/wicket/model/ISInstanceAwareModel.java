/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.wicket.model;

import org.opensingular.form.SInstance;
import org.apache.wicket.model.IModel;

import java.util.Optional;

public interface ISInstanceAwareModel<T> extends IModel<T> {
    SInstance getMInstancia();

    static <X> Optional<ISInstanceAwareModel<X>> optionalCast(IModel<X> model){
        if(model != null && ISInstanceAwareModel.class.isAssignableFrom(model.getClass())){
            return Optional.of((ISInstanceAwareModel<X>) model);
        } else {
            return Optional.empty();
        }
    }

    static IModel<SInstance> getInstanceModel(ISInstanceAwareModel<?> model) {
        return new ISInstanceAwareModel<SInstance>() {
            public SInstance getObject() {
                return getMInstancia();
            }
            public SInstance getMInstancia() {
                return model.getMInstancia();
            }
            @Override
            public void setObject(SInstance object) {}
            @Override
            public void detach() {}
        };
    }
}
