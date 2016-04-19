/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package br.net.mirante.singular.form.wicket.mapper;

import br.net.mirante.singular.form.mform.SInstance;
import br.net.mirante.singular.form.mform.basic.ui.SPackageBasic;
import br.net.mirante.singular.form.mform.basic.view.SView;
import br.net.mirante.singular.form.wicket.IWicketComponentMapper;
import br.net.mirante.singular.form.wicket.WicketBuildContext;
import br.net.mirante.singular.form.wicket.behavior.DisabledClassBehavior;
import br.net.mirante.singular.form.wicket.behavior.InvisibleIfNullOrEmptyBehavior;
import br.net.mirante.singular.form.wicket.enums.ViewMode;
import br.net.mirante.singular.form.wicket.model.AtributoModel;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSContainer;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSControls;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSLabel;
import br.net.mirante.singular.util.wicket.output.BOutputPanel;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ClassAttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.feedback.ErrorLevelFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.LabeledWebMarkupContainer;
import org.apache.wicket.model.IModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static br.net.mirante.singular.util.wicket.util.Shortcuts.$b;
import static br.net.mirante.singular.util.wicket.util.Shortcuts.$m;

public abstract class ControlsFieldComponentAbstractMapper implements IWicketComponentMapper {

    final static HintKey<Boolean> NO_DECORATION = (HintKey<Boolean>) () -> false;

    protected WicketBuildContext          ctx;
    protected SView                       view;
    protected BSContainer                 bodyContainer;
    protected BSControls                  formGroup;
    protected IModel<? extends SInstance> model;
    protected IModel<String>              labelModel;

    protected abstract Component appendInput();

    protected abstract String getReadOnlyFormattedText(IModel<? extends SInstance> model);

    protected Component appendReadOnlyInput() {
        final SInstance    mi   = model.getObject();
        final BOutputPanel comp = new BOutputPanel(mi.getName(), $m.ofValue(getReadOnlyFormattedText(model)));
        formGroup.appendTag("div", comp);
        return comp;
    }

    public void buildView(WicketBuildContext ctx) {

        this.ctx = ctx;
        this.model = ctx.getModel();
        this.labelModel = new AtributoModel<>(model, SPackageBasic.ATR_LABEL);
        this.view = ctx.getView();
        this.bodyContainer = ctx.getExternalContainer();

        final boolean                hintNoDecoration      = ctx.getHint(NO_DECORATION);
        final IFeedbackMessageFilter feedbackMessageFilter = new ErrorLevelFeedbackMessageFilter(FeedbackMessage.WARNING);
        final BSContainer<?>         container             = ctx.getContainer();
        final AtributoModel<String>  subtitle              = new AtributoModel<>(model, SPackageBasic.ATR_SUBTITLE);
        final ViewMode               viewMode              = ctx.getViewMode();
        final BSLabel                label                 = new BSLabel("label", labelModel);

        this.formGroup = container.newFormGroup();

        label.add(DisabledClassBehavior.getInstance());
        label.setVisible(!hintNoDecoration);
        label.add($b.onConfigure(c -> {
                    if (StringUtils.isEmpty(labelModel.getObject())) {
                        c.setVisible(false);
                    }
                }
        ));

        formGroup.appendLabel(label);
        formGroup.newHelpBlock(subtitle)
                .add($b.classAppender("hidden-xs"))
                .add($b.classAppender("hidden-sm"))
                .add($b.classAppender("hidden-md"))
                .add(InvisibleIfNullOrEmptyBehavior.getInstance());

        final Component input;

        if (viewMode.isEdition()) {
            input = appendInput();
            formGroup.appendFeedback(formGroup, feedbackMessageFilter);
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
            for (FormComponent fc : findAjaxComponents(input)) {
                ctx.configure(this, fc);
            }
        } else {
            input = appendReadOnlyInput();
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

    protected FormComponent[] findAjaxComponents(Component input) {
        if (input instanceof FormComponent) {
            return new FormComponent[]{(FormComponent) input};
        } else if (input instanceof MarkupContainer) {
            List<FormComponent> formComponents = new ArrayList<>();
            ((MarkupContainer) input).visitChildren((component, iVisit) -> {
                if (component instanceof FormComponent) {
                    formComponents.add((FormComponent) component);
                    iVisit.dontGoDeeper();
                }
            });
            return formComponents.toArray(new FormComponent[formComponents.size()]);
        } else {
            return new FormComponent[0];
        }

    }
}