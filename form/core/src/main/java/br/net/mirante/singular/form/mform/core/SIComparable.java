package br.net.mirante.singular.form.mform.core;

import br.net.mirante.singular.form.mform.SISimple;

public abstract class SIComparable<TIPO_NATIVO extends Comparable<TIPO_NATIVO>> extends SISimple<TIPO_NATIVO> {

    protected SIComparable() {
    }

    public int compareTo(SIComparable<TIPO_NATIVO> outro) {
        return compareTo(outro.getValor());
    }

    public int compareTo(TIPO_NATIVO outro) {
        return getValor().compareTo(outro);
    }

    public boolean isAfter(SIComparable<TIPO_NATIVO> outro) {
        return compareTo(outro) > 0;
    }

    public boolean isBefore(SIComparable<TIPO_NATIVO> outro) {
        return compareTo(outro) < 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof SIComparable) {
            final SIComparable other = (SIComparable) obj;
            if (this.getValor() != null && other.getValor() != null) {
                return compareTo(other) == 0;
            } else {
                return this.getValor() == null && other.getValor() == null;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (getValor() != null) {
            return getValor().hashCode();
        } else {
            return super.hashCode();
        }
    }
}