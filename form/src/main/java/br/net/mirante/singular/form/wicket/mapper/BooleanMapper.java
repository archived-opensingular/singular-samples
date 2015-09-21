package br.net.mirante.singular.form.wicket.mapper;

import static br.net.mirante.singular.util.wicket.util.Shortcuts.*;
import static org.apache.commons.lang3.StringUtils.*;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.IModel;

import br.net.mirante.singular.form.mform.MInstancia;
import br.net.mirante.singular.form.mform.basic.ui.MPacoteBasic;
import br.net.mirante.singular.form.mform.basic.view.MView;
import br.net.mirante.singular.form.wicket.IWicketComponentMapper;
import br.net.mirante.singular.form.wicket.WicketBuildContext;
import br.net.mirante.singular.form.wicket.model.MInstanciaValorModel;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSControls;
import br.net.mirante.singular.util.wicket.bootstrap.layout.BSLabel;

public class BooleanMapper implements IWicketComponentMapper {
    @Override
    public void buildView(WicketBuildContext ctx, MView view, IModel<? extends MInstancia> model) {
        String label = trimToEmpty(model.getObject().as(MPacoteBasic.aspect()).getLabel());
        BSControls formGroup = ctx.getContainer()
            .newComponent(BSControls::new);
        IModel<String> labelModel = $m.ofValue(label);
        formGroup.appendLabel(new BSLabel("label", ""));
        formGroup.appendCheckbox(
            new CheckBox(model.getObject().getNome(), new MInstanciaValorModel<>(model)),
            labelModel);
        formGroup.appendFeedback();
    }
}