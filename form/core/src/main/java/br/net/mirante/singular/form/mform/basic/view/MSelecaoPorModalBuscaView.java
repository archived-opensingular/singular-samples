package br.net.mirante.singular.form.mform.basic.view;

import br.net.mirante.singular.form.mform.MTipo;
import br.net.mirante.singular.form.mform.options.MSelectionableType;

@SuppressWarnings("serial")
public class MSelecaoPorModalBuscaView extends MView {

    private String tituloModal;

    @Override
    public boolean aplicavelEm(MTipo<?> tipo) {
        return tipo instanceof MSelectionableType;
    }

    public MSelecaoPorModalBuscaView setTituloModal(String tituloModal){
        this.tituloModal = tituloModal;
        return this;
    }


}
