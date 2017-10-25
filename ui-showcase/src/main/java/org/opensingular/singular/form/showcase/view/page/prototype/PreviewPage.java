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

import org.apache.wicket.Page;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.lang.Bytes;
import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SDictionary;
import org.opensingular.form.SIComposite;
import org.opensingular.form.SIList;
import org.opensingular.form.SType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.STypeList;
import org.opensingular.form.context.SFormConfig;
import org.opensingular.form.view.SViewListByTable;
import org.opensingular.form.wicket.component.SingularFormWicket;
import org.opensingular.form.wicket.model.SInstanceRootModel;
import org.opensingular.form.wicket.panel.SingularFormPanel;
import org.opensingular.lib.commons.base.SingularUtil;
import org.opensingular.singular.form.showcase.view.template.ShowcaseTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.function.Consumer;

public class PreviewPage extends ShowcaseTemplate {

    private SInstanceRootModel<SIComposite> model;
    private Page backpage;

    @Inject
    @Named("formConfigWithDatabase")
    private SFormConfig<String> singularFormConfig;

    public PreviewPage(SInstanceRootModel<SIComposite> model, Page backpage) {
        this.model = model;
        this.backpage = backpage;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnDomReadyHeaderItem.forScript("$('#_menuItemPrototype').addClass('active');"));
    }


    @Override
    protected void onInitialize() {
        super.onInitialize();
        SingularFormWicket<?> enclosing = new SingularFormWicket<>("just-a-form");
        enclosing.setMultiPart(true);
        enclosing.setFileMaxSize(Bytes.MAX);
        enclosing.setMaxSize(Bytes.MAX);
        SingularFormPanel panel = new SingularFormPanel("singular-panel");
        panel.setInstanceFromType(() -> new TypeBuilder(PreviewPage.this.model.getObject()).createRootType());
        enclosing.add(panel);
        queue(enclosing);
        queue(new Link("cancel-btn") {
            @Override
            public void onClick() {
                setResponsePage(backpage);
            }
        });
    }

    @Override
    protected IModel<String> getContentTitle() {
        return new ResourceModel("label.content.title", "");
    }

    @Override
    protected IModel<String> getContentSubtitle() {
        return new ResourceModel("label.content.title", "");
    }

}

class TypeBuilder {

    private final PackageBuilder pkg;
    private SIComposite metaInformation;
    private long id;

    TypeBuilder(SIComposite metaInformation) {
        this.metaInformation = metaInformation;
        pkg = createPackage();
    }

    private PackageBuilder createPackage() {
        SDictionary dictionary = SDictionary.create();
        dictionary.loadPackage(SPackagePrototype.class);
        return dictionary.createNewPackage("org.opensingular.form.preview");
    }

    public STypeComposite<? extends SIComposite> createRootType() {
        STypeComposite<?> root = pkg.createCompositeType("root");
        SIList children = (SIList) metaInformation.getField(STypePrototype.CHILDREN_FIELD);
        root.asAtr().label(metaInformation.getValueString(STypePrototype.NAME));
        addChildFieldsIfAny(root, children);
        return root;
    }

    private void addChildFieldsIfAny(STypeComposite<? extends SIComposite> root, SIList children) {
        if (!children.isEmptyOfData()) {
            for (SIComposite f : (List<SIComposite>) children.getValues()) {
                addField(root, f);
            }
        }
    }

    private void addField(STypeComposite<? extends SIComposite> root, SIComposite descriptor) {
        String type = descriptor.getValueString(STypePrototype.TYPE);
        SType<?> typeOfField = root.getDictionary().getType(type);

        SType<?> fieldType = addFieldType(root, descriptor, typeOfField);
        addAttributesIfAny(descriptor, fieldType);
        addCompositeFieldsIfNeeded(descriptor, typeOfField, fieldType);
    }

    private SType<?> addFieldType(STypeComposite<? extends SIComposite> root, SIComposite descriptor, SType<?> typeOfField) {
        String name = descriptor.getValueString(STypePrototype.NAME);
        String genName = generateJavaIdentifier(name);
        if (isList(descriptor)) {
            return addListFieldType(root, typeOfField, name, genName);
        } else {
            return addSimpleOrCompositeFieldType(root, typeOfField, name, genName);
        }
    }

    private SType<?> addListFieldType(STypeComposite<? extends SIComposite> root, SType<?> typeOfField, String name, String genName) {
        STypeList fieldType = addAppropriateListFieldType(root, typeOfField, genName);
        fieldType.asAtr().label(name);
        fieldType.withView(SViewListByTable::new);
        return fieldType.getElementsType();
    }

    private STypeList addAppropriateListFieldType(STypeComposite<? extends SIComposite> root, SType<?> typeOfField, String genName) {
        if (typeOfField.isComposite()) {
            return root.addFieldListOfComposite(genName, "sub_" + genName);
        } else {
            return root.addFieldListOf(genName, typeOfField);
        }
    }

    private SType<?> addSimpleOrCompositeFieldType(STypeComposite<? extends SIComposite> root, SType<?> typeOfField, String name, String genName) {
        return root.addField(genName, typeOfField)
                .asAtr().label(name).getType();
    }

    private String generateJavaIdentifier(String name) {
        id++;
        String javaIdentifier = SingularUtil.convertToJavaIdentity(name, true);
        if (javaIdentifier.isEmpty()) {
            return "id" + id;
        }
        return javaIdentifier + id;
    }

    private void addAttributesIfAny(SIComposite descriptor, SType<?> fieldType) {
        addAttributeIfExists(descriptor.getValueInteger(STypePrototype.TAMANHO_CAMPO), fieldType.asAtrBootstrap()::colPreference);
        addAttributeIfExists(descriptor.getValueInteger(STypePrototype.TAMANHO_MAXIMO), fieldType.asAtr()::maxLength);
        addAttributeIfExists(descriptor.getValueInteger(STypePrototype.TAMANHO_INTEIRO_MAXIMO), fieldType.asAtr()::integerMaxLength);
        addAttributeIfExists(descriptor.getValueInteger(STypePrototype.TAMANHO_DECIMAL_MAXIMO), fieldType.asAtr()::fractionalMaxLength);
        addAttributeIfExists(descriptor.getValueBoolean(STypePrototype.CAMPO_OBRIGATORIO), fieldType.asAtr()::required);

    }

    private <T> void addAttributeIfExists(T value, Consumer<T> attributeConsumer) {
        if (value != null) {
            attributeConsumer.accept(value);
        }
    }

    private boolean isList(SIComposite descriptor) {
        return notNull(descriptor.getValueBoolean(STypePrototype.IS_LIST), false);
    }

    private boolean notNull(Boolean v, boolean defaultValue) {
        return v == null ? defaultValue : v;
    }

    private void addCompositeFieldsIfNeeded(SIComposite descriptor,
                                            SType<?> typeOfField,
                                            SType<?> fieldType) {
        if (typeOfField.isComposite()) {
            SIList children = (SIList) descriptor.getField(STypePrototype.FIELDS);
            addChildFieldsIfAny((STypeComposite<? extends SIComposite>) fieldType, children);
        }
    }

}
