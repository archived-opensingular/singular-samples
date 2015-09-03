package br.net.mirante.singular.form.wicket;

import org.apache.wicket.model.IModel;

import br.net.mirante.singular.form.mform.MInstancia;
import br.net.mirante.singular.form.mform.MTipo;
import br.net.mirante.singular.form.mform.io.MformPersistenciaXML;
import br.net.mirante.singular.form.util.xml.MElement;

public abstract class MInstanciaRaizModel<I extends MInstancia> implements IModel<I> {
    private MElement    serial;
    private transient I object;
    private String      nomeTipo;
    public MInstanciaRaizModel() {}
    public MInstanciaRaizModel(I object) {
        setObject(object);
    }
    protected abstract MTipo<I> getTipoRaiz(String nomeTipo);
    protected MTipo<I> getTipoRaiz() {
        if (this.nomeTipo == null)
            return null;
        return getTipoRaiz(this.nomeTipo);
    }
    protected I hydrate(MTipo<I> tipoRaiz, MElement xml) {
        I instancia = tipoRaiz.novaInstancia();
        if (xml != null)
            MformPersistenciaXML.fromXML(tipoRaiz, instancia, xml);
        return instancia;
    }
    protected MElement dehydrate(I raiz) {
        return MformPersistenciaXML.toXML(raiz);
    }
    @Override
    public I getObject() {
        if (this.object == null) {
            this.object = hydrate(getTipoRaiz(), this.serial);
        }
        return this.object;
    }
    @Override
    public void setObject(I object) {
        this.serial = dehydrate(object);
        this.object = object;
        this.nomeTipo = (object == null) ? null : object.getMTipo().getNome();
    }
    @Override
    public void detach() {
        this.serial = dehydrate(this.object);
    }
}