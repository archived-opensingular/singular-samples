/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package br.net.mirante.singular.form.wicket.mapper;

import static br.net.mirante.singular.util.wicket.util.Shortcuts.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ClassAttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.LabeledWebMarkupContainer;
import org.apache.wicket.model.IModel;

import br.net.mirante.singular.form.SInstance;
import br.net.mirante.singular.form.type.basic.SPackageBasic;
import br.net.mirante.singular.form.validation.IValidationError;
import br.net.mirante.singular.form.wicket.ISValidationFeedbackHandlerListener;
import br.net.mirante.singular.form.wicket.IWicketComponentMapper;
import br.net.mirante.singular.form.wicket.SValidationFeedbackHandler;
import br.net.mirante.singular.form.wicket.WicketBuildContext;
import br.net.mirante.singular.form.wicket.behavior.DisabledClassBehavior;
import br.net.mirante.singular.form.wicket.behavior.InvisibleIfNullOrEmptyBehavior;
import br.net.mirante.singular.form.wicket.enums.ViewMode;
import br.net.mirante.singular.form.wicket.feedback.SValidationFeedbackCompactPanel;
import br.net.mirante.singular.form.wicket.model.AttributeModel;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSContainer;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSControls;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSLabel;
import br.net.mirante.singular.util.wicket.output.BOutputPanel;

public abstract class AbstractControlsFieldComponentMapper implements IWicketComponentMapper {

    final static HintKey<Boolean> NO_DECORATION = (HintKey<Boolean>) () -> false;

    protected abstract Component appendInput(WicketBuildContext ctx, BSControls formGroup, IModel<String> labelModel);

    protected abstract String getReadOnlyFormattedText(IModel<? extends SInstance> model);

    protected Component appendReadOnlyInput(WicketBuildContext ctx, BSControls formGroup, IModel<String> labelModel) {
        final IModel<? extends SInstance> model = ctx.getModel();
        final SInstance mi = model.getObject();
        final BOutputPanel comp = new BOutputPanel(mi.getName(), $m.ofValue(getReadOnlyFormattedText(model)));
        formGroup.appendTag("div", comp);
        return comp;
    }

    public void buildView(WicketBuildContext ctx) {

        final IModel<? extends SInstance> model = ctx.getModel();
        final IModel<String> labelModel = new AttributeModel<>(model, SPackageBasic.ATR_LABEL);

        final boolean hintNoDecoration = ctx.getHint(NO_DECORATION);
        final BSContainer<?> container = ctx.getContainer();
        final AttributeModel<String> subtitle = new AttributeModel<>(model, SPackageBasic.ATR_SUBTITLE);
        final ViewMode viewMode = ctx.getViewMode();
        final BSLabel label = new BSLabel("label", labelModel);
        final BSControls formGroup = container.newFormGroup();

        label.add(DisabledClassBehavior.getInstance());
        label.setVisible(!hintNoDecoration);
        label.add($b.onConfigure(c -> {
            if (ctx.isTitleInBlock()) {
                c.setVisible(false);
            } else if (StringUtils.isEmpty(labelModel.getObject())) {
                c.setVisible(false);
            }
        }));

        formGroup.appendLabel(label);
        formGroup.newHelpBlock(subtitle)
            .add($b.classAppender("hidden-xs"))
            .add($b.classAppender("hidden-sm"))
            .add($b.classAppender("hidden-md"))
            .add(InvisibleIfNullOrEmptyBehavior.getInstance());

        final Component input;

        if (viewMode.isEdition()) {
            input = appendInput(ctx, formGroup, labelModel);
            SValidationFeedbackCompactPanel feedback = new SValidationFeedbackCompactPanel("feedback", ctx.getContainer());
            SValidationFeedbackHandler.bindTo(ctx.getContainer())
                .addInstanceModel(model)
                .addListener(new ISValidationFeedbackHandlerListener() {
                    @Override
                    public void onFeedbackChanged(SValidationFeedbackHandler handler, Optional<AjaxRequestTarget> target, Component container, Collection<SInstance> baseInstances, Collection<IValidationError> oldErrors, Collection<IValidationError> newErrors) {
                        if (target.isPresent())
                            target.get().add(feedback);
                    }
                });

            formGroup.appendFeedback(feedback);
            formGroup.add(new ClassAttributeModifier() {
                @Override
                protected Set<String> update(Set<String> oldClasses) {
                    if (model.getObject().getAttributeValue(SPackageBasic.ATR_DEPENDS_ON_FUNCTION) != null) {
                        oldClasses.add("dependant-input-group");
                    }
                    return oldClasses;
                }
            });
            input.add(DisabledClassBehavior.getInstance());
            input.add($b.onConfigure(c -> label.add(new ClassAttributeModifier() {
                @Override
                protected Set<String> update(Set<String> oldClasses) {
                    if (model.getObject().getAttributeValue(SPackageBasic.ATR_REQUIRED)) {
                        oldClasses.add("singular-form-required");
                    } else {
                        oldClasses.remove("singular-form-required");
                    }
                    return oldClasses;
                }
            })));
            for (FormComponent<?> fc : findAjaxComponents(input)) {
                ctx.configure(this, fc);
            }
        } else {
            input = appendReadOnlyInput(ctx, formGroup, labelModel);
        }

        if (ctx.annotation().enabled()) {
            if (input.getDefaultModel() != null) {
                ctx.updateAnnotations(input, (SInstance) input.getDefaultModel().getObject());
            }
        }

        if ((input instanceof LabeledWebMarkupContainer) && (((LabeledWebMarkupContainer) input).getLabel() == null)) {
            ((LabeledWebMarkupContainer) input).setLabel(labelModel);
        }
    }

    protected FormComponent<?>[] findAjaxComponents(Component input) {
        if (input instanceof FormComponent) {
            return new FormComponent[] { (FormComponent<?>) input };
        } else if (input instanceof MarkupContainer) {
            List<FormComponent<?>> formComponents = new ArrayList<>();
            ((MarkupContainer) input).visitChildren((component, iVisit) -> {
                if (component instanceof FormComponent) {
                    formComponents.add((FormComponent<?>) component);
                    iVisit.dontGoDeeper();
                }
            });
            return formComponents.toArray(new FormComponent[formComponents.size()]);
        } else {
            return new FormComponent[0];
        }

    }

    /**
     * Filtra os eventos, disparando somente um
     * <p>
     * quando um blur acontecer, verifica se um change está agendado se não agenda um blur
     * quando um change acontecer, verifica se um blur está agendado e renive dando prioridade ao change
     * <p>
     * a verificação é adicionada nas 2 pontas porque quando exite mascara o blur acontece antes do change
     * quando não tem, acontece o contrario.
     *
     * @param comp
     */
    @Override
    public void adjustJSEvents(Component comp) {
        comp.add(new SingularEventsHandlers(SingularEventsHandlers.FUNCTION.ADD_TEXT_FIELD_HANDLERS));
    }
}