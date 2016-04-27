package br.net.mirante.singular.exemplos.notificacaosimplificada.form.vocabulario;

import br.net.mirante.singular.exemplos.notificacaosimplificada.domain.Farmacopeia;
import br.net.mirante.singular.form.mform.SIComposite;
import br.net.mirante.singular.form.mform.SIList;
import br.net.mirante.singular.form.mform.SInfoType;
import br.net.mirante.singular.form.mform.STypeComposite;
import br.net.mirante.singular.form.mform.TypeBuilder;
import br.net.mirante.singular.form.mform.basic.view.SViewAutoComplete;
import br.net.mirante.singular.form.mform.core.STypeInteger;
import br.net.mirante.singular.form.mform.core.STypeString;

import static br.net.mirante.singular.exemplos.notificacaosimplificada.form.vocabulario.SPackageVocabularioControlado.dominioService;

@SInfoType(spackage = SPackageVocabularioControlado.class)
public class STypeFarmacopeia extends STypeComposite<SIComposite> {

    public STypeString descricao;
    public STypeInteger id;

    @Override
    protected void onLoadType(TypeBuilder tb) {
        super.onLoadType(tb);
        id = this.addFieldInteger("id");
        descricao = this.addFieldString("descricao");
        {

            this
                    .asAtrBasic()
                    .required()
                    .label("Farmacopéia")
                    .asAtrBootstrap()
                    .colPreference(4);
            this.setView(SViewAutoComplete::new);

            this.withSelectionFromProvider(descricao, (ins, filter) -> {
                final SIList<?> list = ins.getType().newList();
                for (Farmacopeia lc : dominioService(ins).listFarmacopeias()) {
                    final SIComposite c = (SIComposite) list.addNew();
                    c.setValue(id, lc.getId());
                    c.setValue(descricao, lc.getDescricao());
                }
                return list;
            });

        }
    }

}