/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adiciona meta informações ao pacote, que podem ser lidas sem força a carga do
 * pacote inteiro.
 *
 * @author Daniel C. Bordin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface SInfoPackage {

    /**
     * Permite informar o nome do pacote.
     */
    public String name() default "";
}
