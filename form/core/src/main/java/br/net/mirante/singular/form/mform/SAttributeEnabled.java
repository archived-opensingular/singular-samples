/*
 * Copyright (c) 2016, Mirante and/or its affiliates. All rights reserved.
 * Mirante PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package br.net.mirante.singular.form.mform;

import java.util.function.Function;

import br.net.mirante.singular.form.mform.basic.ui.AtrBasic;
import br.net.mirante.singular.form.mform.basic.ui.AtrBootstrap;
import br.net.mirante.singular.form.mform.calculation.SimpleValueCalculation;
import br.net.mirante.singular.form.mform.core.AtrCore;
import br.net.mirante.singular.form.mform.core.annotation.AtrAnnotation;

/**
 * Representa um entidade habilitada para ter atributos lidos ou alterados.
 * Tipicamente os atributos são de uma instância, tipo ou um proxy de atributos.
 *
 * @author Daniel C. Bordin
 */
public interface SAttributeEnabled {

    default <V> void setAttributeCalculation(AtrRef<?, ?, V> atr, SimpleValueCalculation<V> value) {
        getDictionary().loadPackage(atr.getPackageClass());
        setAttributeCalculation(atr.getNameFull(), null, value);
    }

    <V> void setAttributeCalculation(String attributeFullName, String subPath, SimpleValueCalculation<V> value);

    default <V> void setAttributeValue(AtrRef<?, ?, V> atr, V value) {
        getDictionary().loadPackage(atr.getPackageClass());
        setAttributeValue(atr.getNameFull(), null, value);
    }

    default <V> void setAttributeValue(SAttribute defAttribute, Object value) {
        setAttributeValue(defAttribute.getName(), null, value);
    }

    default void setAttributeValue(String attributeName, Object value) {
        setAttributeValue(attributeName, null, value);
    }

    void setAttributeValue(String attributeFullName, String subPath, Object value);

    <V> V getAttributeValue(String attributeFullName, Class<V> resultClass);

    default <T> T getAttributeValue(AtrRef<?, ?, ?> atr, Class<T> resultClass) {
        getDictionary().loadPackage(atr.getPackageClass());
        return getAttributeValue(atr.getNameFull(), resultClass);
    }

    default <V> V getAttributeValue(AtrRef<?, ?, V> atr) {
        getDictionary().loadPackage(atr.getPackageClass());
        return getAttributeValue(atr.getNameFull(), atr.getValueClass());
    }

    default Object getAttributeValue(String attributeFullName) {
        return getAttributeValue(attributeFullName, null);
    }

    SDictionary getDictionary();

    /**
     * Transforma o tipo ou instância atual de acordo com a função de
     * mapeamento.
     */
    public <TR> TR as(Function<SAttributeEnabled, TR> wrapper);

    /** Retorna o leitor de atributos básicos para o tipo ou instância atual. */
    public default AtrBasic asAtrBasic() {
        return as(AtrBasic::new);
    }

    /**
     * Retorna o leitor de atributos de Bootstrap para o tipo ou instância
     * atual.
     */
    public default AtrBootstrap asAtrBootstrap() {
        return as(AtrBootstrap::new);
    }

    /** Retorna o leitor de atributos core para o tipo ou instância atual. */
    public default AtrCore asAtrCore() {
        return as(AtrCore::new);
    }

    /**
     * Retorna o leitor de atributos de anotação para o tipo ou instância atual.
     */
    public default AtrAnnotation asAtrAnnotation() {
        return as(AtrAnnotation::new);
    }

}
