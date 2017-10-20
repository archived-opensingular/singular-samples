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

package org.opensingular.singular.form.showcase.view.page.prototype;

import org.apache.commons.lang3.tuple.Pair;
import org.opensingular.form.SDictionary;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.SType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.converter.SInstanceConverter;
import org.opensingular.form.type.core.SIString;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeDate;
import org.opensingular.form.type.core.STypeDateTime;
import org.opensingular.form.type.core.STypeDecimal;
import org.opensingular.form.type.core.STypeInteger;
import org.opensingular.form.type.core.STypeMonetary;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.type.core.attachment.STypeAttachment;
import org.opensingular.form.type.country.brazil.STypeCEP;
import org.opensingular.form.type.country.brazil.STypeCNPJ;
import org.opensingular.form.type.country.brazil.STypeCPF;
import org.opensingular.form.type.country.brazil.STypeTelefoneNacional;
import org.opensingular.form.type.util.STypeEMail;
import org.opensingular.form.type.util.STypeLatitudeLongitude;
import org.opensingular.form.type.util.STypePersonName;
import org.opensingular.form.type.util.STypeYearMonth;
import org.opensingular.form.view.SViewListByMasterDetail;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

@SInfoType(spackage = SPackagePrototype.class, name = "metaForm")
public class STypePrototype extends STypeComposite<SIComposite> {
    private static final SDictionary dictionary = SDictionary.create();

    public STypeString name;
    public STypeList<STypeComposite<SIComposite>, SIComposite> childFields;

    public static final String  CHILDREN = "childFields",
            NAME                      = "name",
            TYPE                      = "type",
            IS_LIST                   = "isList",
            TAMANHO_CAMPO             = "tamanhoCampo",
            CAMPO_OBRIGATORIO         = "obrigatorio",
            TAMANHO_MAXIMO            = "tamanhoMaximo",
            TAMANHO_INTEIRO_MAXIMO    = "tamanhoInteiroMaximo",
            TAMANHO_DECIMAL_MAXIMO    = "tamanhoDecimalMaximo",
            FIELDS                    = "fields";
    public static final String NAME_FIELD = "name";

    private STypeInteger tamanhoCampo;
    private STypeBoolean obrigatorio;


    @Override
    protected void onLoadType(@Nonnull org.opensingular.form.TypeBuilder tb) {
        name = this.addFieldString(NAME_FIELD);
        name.asAtr().label("Nome")
                .asAtr().required();

        childFields = this.addFieldListOfComposite(CHILDREN, "field");

        childFields.asAtr().label("Campos");

        STypeComposite<SIComposite> fieldType = childFields.getElementsType();

        STypeString nome = fieldType.addFieldString(NAME);
        nome.asAtr().label("Nome")
                .asAtr().required()
                .asAtrBootstrap().colPreference(3);

        STypeString type = fieldType.addFieldString(TYPE);
        type.asAtr().label("Tipo")
                .asAtr().required()
                .asAtrBootstrap().colPreference(2);

        List<Pair> typesList = new ArrayList<>();
        populateOptions(typesList);

        type.selectionOf(Pair.class)
                .id("${left}")
                .display("${right}")
                .converter(new SInstanceConverter<Pair, SIString>() {
                    @Override
                    public void fillInstance(SIString ins, Pair obj) {
                        ins.setValue(obj.getLeft());
                    }

                    @Override
                    public Pair toObject(SIString ins) {
                        return typesList
                                .stream()
                                .filter(p -> p.getLeft().equals(ins.getValue()))
                                .findFirst().orElse(null);
                    }
                })
                .simpleProviderOf(typesList.toArray(new Pair[]{}));

        fieldType.addFieldBoolean(IS_LIST)
                .withRadioView()
                .withDefaultValueIfNull(Boolean.FALSE)
                .asAtr().label("Múltiplo").getType().asAtrBootstrap().colPreference(2);

        addAttributeFields(fieldType, type);

        childFields.withView(new SViewListByMasterDetail()
                        .col(nome)
                        .col(type)
                        .col(tamanhoCampo)
                        .col(obrigatorio)
        );

        addFields(fieldType, type);
    }

    private void populateOptions(List<Pair> list) {

        list.add(Pair.of(typeName(STypeAttachment.class), "Anexo"));
        list.add(Pair.of(typeName(STypeYearMonth.class), "Ano/Mês"));
        list.add(Pair.of(typeName(STypeBoolean.class), "Booleano"));
        list.add(Pair.of(typeName(STypeComposite.class), "Composto"));
        list.add(Pair.of(typeName(STypeCEP.class), "CEP"));
        list.add(Pair.of(typeName(STypeCPF.class), "CPF"));
        list.add(Pair.of(typeName(STypeCNPJ.class), "CNPJ"));
        list.add(Pair.of(typeName(STypeDate.class), "Data"));
        list.add(Pair.of(typeName(STypeDateTime.class), "Data/Hora"));
        list.add(Pair.of(typeName(STypeEMail.class), "Email"));
        list.add(Pair.of(typeName(STypeLatitudeLongitude.class), "Latitude/Longitude"));
        list.add(Pair.of(typeName(STypeMonetary.class), "Monetário"));
        list.add(Pair.of(typeName(STypePersonName.class), "Nome Pessoa"));
        list.add(Pair.of(typeName(STypeInteger.class), "Número"));
        list.add(Pair.of(typeName(STypeDecimal.class), "Número Decimal"));
        list.add(Pair.of(typeName(STypeString.class), "Texto"));
        list.add(Pair.of(typeName(STypeTelefoneNacional.class), "Telefone Nacional"));
    }

    private String typeName(Class<? extends SType> typeClass) {
        return dictionary.getType(typeClass).getName();
    }

    private void addAttributeFields(STypeComposite<SIComposite> fieldType, STypeString type) {
        tamanhoCampo = fieldType.addFieldInteger(TAMANHO_CAMPO);
        tamanhoCampo.asAtr().label("Colunas").maxLength(12)
                .getType().asAtrBootstrap().colPreference(2);

        obrigatorio = fieldType.addFieldBoolean(CAMPO_OBRIGATORIO);
        obrigatorio.withRadioView().asAtr().label("Obrigatório").getType().asAtrBootstrap().colPreference(2);

        fieldType.addFieldInteger(TAMANHO_MAXIMO)
                .asAtrBootstrap().colPreference(2)
                .getType().asAtr().label("Tamanho Máximo")
                .visible(
                        (instance) -> {
                            Optional<String> optType = instance.findNearestValue(type, String.class);
                            if (!optType.isPresent()) return false;
                            return optType.get().equals(typeName(STypeInteger.class));
                        }
                );

        Predicate<SInstance> ifDecimalPredicate = (instance) -> {
            Optional<String> optType = instance.findNearestValue(type, String.class);
            if (!optType.isPresent()) return false;
            return optType.get().equals(typeName(STypeDecimal.class));
        };

        fieldType.addFieldInteger(TAMANHO_INTEIRO_MAXIMO)
                .asAtrBootstrap().colPreference(2)
                .getType().asAtr()
                .label("Tamanho Inteiro")
                .visible(ifDecimalPredicate);

        fieldType.addFieldInteger(TAMANHO_DECIMAL_MAXIMO)
                .asAtrBootstrap().colPreference(2)
                .getType().asAtr()
                .label("Tamanho Decimal")
                .visible(ifDecimalPredicate);
    }

    private void addFields(STypeComposite<SIComposite> fieldType, STypeString type) {
        STypeList<STypeComposite<SIComposite>, SIComposite> fields =
                fieldType.addFieldListOf(FIELDS, fieldType);
        fields.asAtr().label("Campos")
                .getType().withView(SViewListByMasterDetail::new)
                .asAtr().exists(
                        (instance) -> {
                            SInstance t = instance.getParent().getField("type");
                            return Objects.equals(t.getValue(), typeName(STypeComposite.class));
                        })
                .asAtr().dependsOn(type);
    }
}
