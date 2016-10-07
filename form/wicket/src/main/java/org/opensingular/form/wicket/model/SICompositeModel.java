/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.wicket.model;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInstance;
import org.apache.wicket.Component;
import org.apache.wicket.model.IComponentInheritedModel;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IWrapModel;

public class SICompositeModel<T extends SIComposite>
    extends AbstractSInstanceModel<T>
    implements IComponentInheritedModel<T> {

    private Object target;

    public SICompositeModel(Object target) {
        this.target = target;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getObject() {
        return (T) ((target instanceof IModel<?>)
            ? ((IModel<?>) target).getObject()
            : target);
    }
    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public void detach() {
        if (target instanceof IDetachable) {
            ((IDetachable) target).detach();
        }
    }

    public String propertyExpression(Component owner) {
        return owner.getId();
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <W> IWrapModel<W> wrapOnInheritance(Component component) {
        return new AttachedCompoundPropertyModel(component);
    }
    public <S extends SInstance> IModel<S> bind(String property) {
        return new SInstanceFieldModel<S>(this, property);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((target == null) ? 0 : target.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SICompositeModel<?> other = (SICompositeModel<?>) obj;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        return true;
    }

    private class AttachedCompoundPropertyModel<C extends SInstance> extends AbstractSInstanceCampoModel<C>
        implements IWrapModel<C> {
        private final Component owner;
        public AttachedCompoundPropertyModel(Component owner) {
            super(SICompositeModel.this);
            this.owner = owner;
        }
        @Override
        protected String propertyExpression() {
            return SICompositeModel.this.propertyExpression(owner);
        }
        @Override
        public IModel<T> getWrappedModel() {
            return SICompositeModel.this;
        }

        @Override
        public void detach() {
            super.detach();
            SICompositeModel.this.detach();
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((owner == null) ? 0 : owner.hashCode());
            return result;
        }
        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (!super.equals(obj))
                return false;
            if (getClass() != obj.getClass())
                return false;
            AttachedCompoundPropertyModel<?> other = (AttachedCompoundPropertyModel<?>) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (owner == null) {
                if (other.owner != null)
                    return false;
            } else if (!owner.equals(other.owner))
                return false;
            return true;
        }
        private SICompositeModel<?> getOuterType() {
            return SICompositeModel.this;
        }
    }
}
