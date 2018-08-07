/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opensingular.singular.form.showcase.component.form.custom.comment;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.STypeAttachmentList;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.wicket.enums.AnnotationMode;
/*hidden*/import org.opensingular.singular.form.showcase.component.CaseItem;
/*hidden*/import org.opensingular.singular.form.showcase.component.Group;
/*hidden*/import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import javax.annotation.Nonnull;

/**
 * Anotações e comentários associados a elementos de um form
 */
/*hidden*/@CaseItem(componentName = "Annotation", group = Group.CUSTOM, annotation = AnnotationMode.EDIT,
/*hidden*/        resources = {@Resource(PageWithAnnotation.class), @Resource(STypeCliente.class), @Resource(STypeEndereco.class),
/*hidden*/                @Resource(STypeRequest.class), @Resource(STypeId.class), @Resource(CaseCustomPackage.class)})
@SInfoType(spackage = CaseCustomPackage.class, name = "Annotation")
public class CaseAnnotationSType extends STypeComposite<SIComposite> {

    public STypeCliente        cliente;
    public STypeEndereco       endereco;
    public STypeRequest        request;
    public STypeId             id;
    public STypeAttachmentList anexoMultiplo;
    public STypeCPF            cpf;

    /*
     * Observe que as anotações só estão disponíveis quando devidamente configuradas no
     * contexto.
     */

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        id = this.addField("id", STypeId.class);
        cliente = this.addField("cliente", STypeCliente.class);
        endereco = this.addField("endereco", STypeEndereco.class);
        request = this.addField("request", STypeRequest.class);
        anexoMultiplo = this.addFieldListOfAttachment("anexoMultiplo", "anexo");
        cpf = this.addField("cpf", STypeCPF.class);

        id
                .asAtr().label("Identificador")
                .asAtrAnnotation().setAnnotated();

        cliente
                .asAtr().label("Dados do cliente")
                //@destacar
                .asAtrAnnotation().setAnnotated() // Usará o rótulo do campo para a anotação
                .asAtrBootstrap().colPreference(6);

        endereco.asAtr().label("Endereço do cliente")
                .asAtrBootstrap().colPreference(6);

        request.asAtr().label("Dados do pedido")
                //@destacar
                .asAtrAnnotation().setAnnotated().label("Observações Finais"); //Permite definir seu pŕoprio rótulo

        anexoMultiplo
                .asAtr().label("Anexos de qualquer coisa")
                .asAtrAnnotation().setAnnotated();

        cpf.asAtrAnnotation().setAnnotated();

        this.asAtr().label("Pedido");
    }
}
