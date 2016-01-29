package br.net.mirante.singular.form.mform.basic.view;

import br.net.mirante.singular.form.mform.SType;
import br.net.mirante.singular.form.mform.STypeLista;

public class MPanelListaView extends MView {

    private boolean permiteAdicaoDeLinha   = true;
    private boolean permiteInsercaoDeLinha = false;
    private boolean permiteExclusaoDeLinha = true;

    @Override
    public boolean aplicavelEm(SType<?> tipo) {
        return tipo instanceof STypeLista;
    }

    public boolean isPermiteAdicaoDeLinha() {
        return permiteAdicaoDeLinha;
    }
    public boolean isPermiteExclusaoDeLinha() {
        return permiteExclusaoDeLinha;
    }
    public boolean isPermiteInsercaoDeLinha() {
        return permiteInsercaoDeLinha;
    }
    public MPanelListaView withAdicaoDeLinha() {
        return setPermiteAdicaoDeLinha(true);
    }
    public MPanelListaView withExclusaoDeLinha() {
        return setPermiteExclusaoDeLinha(true);
    }
    public MPanelListaView withInsercaoDeLinha() {
        return setPermiteInsercaoDeLinha(true);
    }
    public MPanelListaView setPermiteAdicaoDeLinha(boolean permiteAdicaoDeLinha) {
        this.permiteAdicaoDeLinha = permiteAdicaoDeLinha;
        return this;
    }
    public MPanelListaView setPermiteExclusaoDeLinha(boolean permiteExclusaoDeLinha) {
        this.permiteExclusaoDeLinha = permiteExclusaoDeLinha;
        return this;
    }
    public MPanelListaView setPermiteInsercaoDeLinha(boolean permiteInsercaoDeLinha) {
        this.permiteInsercaoDeLinha = permiteInsercaoDeLinha;
        return this;
    }
}
