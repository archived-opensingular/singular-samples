package org.opensingular.requirementsamplemodule.flow;

import org.apache.wicket.model.IModel;
import org.opensingular.form.wicket.enums.ViewMode;
import org.opensingular.requirement.module.persistence.entity.form.RequirementEntity;
import org.opensingular.requirement.module.service.RequirementInstance;
import org.opensingular.requirement.module.wicket.view.form.AbstractFormPage;
import org.opensingular.requirement.module.wicket.view.form.FormPageExecutionContext;
import org.opensingular.requirement.module.wicket.view.form.SimpleMessageFlowConfirmModal;
import org.opensingular.requirement.module.wicket.view.util.ActionContext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

import static org.opensingular.lib.wicket.util.util.WicketUtils.$m;

public class SampleFormPage extends AbstractFormPage<RequirementEntity, RequirementInstance> {

    public SampleFormPage(@Nullable ActionContext context) {
        super(context);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }

    @Override
    protected IModel<String> getContentTitle() {
        return $m.get(() -> getSingularFormPanel().getRootTypeSubtitle());
    }

    @Nonnull
    @Override
    protected Optional<String> getIdentifier() {
        return getRequirementOptional()
                .map(RequirementInstance::getCod)
                .map(Object::toString);
    }

    @Override
    protected ViewMode getViewMode(FormPageExecutionContext formPageConfig) {
        return ViewMode.EDIT;
    }

    @Override
    protected SimpleMessageFlowConfirmModal<RequirementEntity, RequirementInstance> getSimpleMessageFLowConfirmModal(
            String id, String transitionName, AbstractFormPage<RequirementEntity, RequirementInstance> formPage) {
        if ("Solicitar ajustes".equalsIgnoreCase((transitionName))) {
           return new SimpleMessageFlowConfirmModal(id, transitionName, formPage, false, "mensagem");
        }
        return super.getSimpleMessageFLowConfirmModal(id, transitionName, formPage);
    }

}

