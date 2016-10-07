/*
 * Copyright (c) 2016, Singular and/or its affiliates. All rights reserved.
 * Singular PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.opensingular.form.exemplos.notificacaosimplificada.domain.corporativo;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.opensingular.form.exemplos.notificacaosimplificada.domain.Schemas;


@Entity
@Table(schema = Schemas.DBCORPORATIVO, name = "TB_PESSOA_JURIDICA")
@DiscriminatorValue("J")
@PrimaryKeyJoinColumn(name = "ID_PESSOA_JURIDICA", referencedColumnName = "ID_PESSOA")
public class PessoaJuridicaNS extends PessoaNS {

    @Column(name = "NO_RAZAO_SOCIAL")
    private String razaoSocial;

    @Column(name = "NO_FANTASIA")
    private String nomeFantasia;

    @Column(name = "NU_CNPJ")
    private String cnpj;

    @Override
    public String getNome() {
        return razaoSocial;
    }

    @Override
    public String getCpfCnpj() {
        return cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
