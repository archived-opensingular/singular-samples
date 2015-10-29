package br.net.mirante.singular.form.mform;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.net.mirante.singular.form.mform.MTipoComposto.FieldMapOfRecordType;

public class MIComposto extends MInstancia implements ICompositeInstance {

    private FieldMapOfRecordInstance fields;

    @Override
    public MTipoComposto<?> getMTipo() {
        return (MTipoComposto<?>) super.getMTipo();
    }

    @Override
    public Object getValor() {
        return getCampos();
    }

    @Override
    public boolean isEmptyOfData() {
        return fields == null || fields.stream().allMatch(i -> i.isEmptyOfData());
    }

    public Collection<MInstancia> getCampos() {
        return (fields == null) ? Collections.emptyList() : fields.getFields();
    }

    @Override
    public Collection<MInstancia> getChildren() {
        return getCampos();
    }

    @Override
    public Stream<? extends MInstancia> stream() {
        return fields == null ? Stream.empty() : fields.stream();
    }

    @Override
    public MInstancia getCampo(String path) {
        return getCampo(new LeitorPath(path));
    }

    @Override
    final MInstancia getCampoLocal(LeitorPath leitor) {
        int fieldIndex = findIndexTrecho(leitor);
        MInstancia instancia = (fields == null) ? null : fields.getByIndex(fieldIndex);
        if (instancia == null) {
            instancia = createField(fieldIndex);
        }
        return instancia;
    }

    @Override
    final MInstancia getCampoLocalSemCriar(LeitorPath leitor) {
        int fieldIndex = findIndexTrecho(leitor);
        return (fields == null) ? null : fields.getByIndex(fieldIndex);
    }

    public <T extends MInstancia> T getFilho(MTipo<T> tipoPai) {
        throw new RuntimeException("Método não implementado");
    }

    @Override
    public final void setValor(String pathCampo, Object valor) {
        setValor(new LeitorPath(pathCampo), valor);
    }

    private FieldMapOfRecordType getFieldsDef() {
        return getMTipo().getFieldsConsolidated();
    }

    @Override
    void setValor(LeitorPath leitorPath, Object valor) {
        int fieldIndex = findIndexTrecho(leitorPath);
        MInstancia instancia = (fields == null) ? null : fields.getByIndex(fieldIndex);
        if (instancia == null) {
            if (valor == null) {
                return;
            }
            instancia = createField(fieldIndex);
        }
        if (leitorPath.isUltimo()) {
            if (valor == null) {
                fields.remove(fieldIndex);
            } else {
                instancia.setValor(valor);
            }
        } else {
            instancia.setValor(leitorPath.proximo(), valor);
        }
    }

    private MInstancia createField(int fieldIndex) {
        MInstancia instancia;
        MTipo<?> tipoCampo = getFieldsDef().getByIndex(fieldIndex);
        instancia = tipoCampo.newInstance(getDocument());
        instancia.setPai(this);
        if (fields == null) {
            fields = new FieldMapOfRecordInstance(getFieldsDef());
        }
        fields.set(fieldIndex, instancia);
        return instancia;
    }

    private int findIndexTrecho(LeitorPath leitor) {
        if (leitor.isIndice()) {
            throw new SingularFormException(leitor.getTextoErro(this, "Não é uma lista"));
        }
        int fieldIndex = getFieldsDef().findIndex(leitor.getTrecho());
        if (fieldIndex == -1) {
            throw new SingularFormException(leitor.getTextoErro(this, "Não é um campo definido"));
        }
        return fieldIndex;
    }

    @Override
    public final <T extends Object> T getValor(String pathCampo, Class<T> classeDestino) {
        return getValor(new LeitorPath(pathCampo), classeDestino);
    }

    @Override
    final <T extends Object> T getValorWithDefaultIfNull(LeitorPath leitor, Class<T> classeDestino) {
        if (fields != null) {
            MInstancia instancia = fields.getByIndex(findIndexTrecho(leitor));
            if (instancia != null) {
                return instancia.getValorWithDefaultIfNull(leitor.proximo(), classeDestino);
            }
        }
        MTipo<?> tipo = MFormUtil.resolverTipoCampo(getMTipo(), leitor);
        return tipo.getValorAtributoOrDefaultValueIfNull(classeDestino);
    }

    private final static class FieldMapOfRecordInstance {

        private final MInstancia[] instances;

        public FieldMapOfRecordInstance(FieldMapOfRecordType fieldsDef) {
            this.instances = new MInstancia[fieldsDef.size()];
        }

        public MInstancia getByIndex(int fieldIndex) {
            return instances[fieldIndex];
        }

        public void remove(int fieldIndex) {
            instances[fieldIndex] = null;
        }

        public void set(int fieldIndex, MInstancia instance) {
            instances[fieldIndex] = instance;
        }

        public List<MInstancia> getFields() {
            return stream().collect(Collectors.toList());
        }

        public Stream<MInstancia> stream() {
            return Arrays.stream(instances).filter(i -> i != null);
        }
    }
}
