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

package org.opensingular.singular.form.showcase.component.internal.layout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInfoType;
import org.opensingular.form.SInstance;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.TypeBuilder;
import org.opensingular.form.decorator.action.SIcon;
import org.opensingular.form.decorator.action.SInstanceAction;
import org.opensingular.form.decorator.action.SInstanceAction.Preview;
import org.opensingular.form.type.core.STypeBoolean;
import org.opensingular.form.type.core.STypeString;
import org.opensingular.form.view.SViewBooleanByRadio;
import org.opensingular.form.view.SViewTextArea;
import org.opensingular.form.wicket.enums.AnnotationMode;
import org.opensingular.lib.commons.lambda.IFunction;
import org.opensingular.singular.form.showcase.component.CaseItem;
import org.opensingular.singular.form.showcase.component.Group;
import org.opensingular.singular.form.showcase.component.Resource;
import org.opensingular.singular.form.showcase.component.form.custom.CaseCustomPackage;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Anotações e comentários associados a elementos de um form
 */
@CaseItem(componentName = "Field Alignment", group = Group.INTERNAL, annotation = AnnotationMode.EDIT, //
    resources = @Resource(CaseCustomPackage.class), //
    actionProviders = CaseFieldAlignmentSType.CustomActionsProvider.class)
@SInfoType(spackage = CaseCustomPackage.class, name = "Layout")
public class CaseFieldAlignmentSType extends STypeComposite<SIComposite> {

    public STypeString  row11, row12, row13;
    public STypeString  row21, row22, row23;
    public STypeString  row31, row32, row33;
    public STypeString  row41, row42, row43;
    public STypeString  row51;
    public STypeBoolean row52, row53;
    public STypeBoolean row6;

    /*
     * Observe que as anotações só estão disponíveis quando devidamente configuradas no
     * contexto.
     */

    @Override
    protected void onLoadType(@Nonnull TypeBuilder tb) {
        row11 = this.addFieldString("row11", true);
        row12 = this.addFieldString("row12", true);
        row13 = this.addFieldString("row13", true);
        row21 = this.addFieldString("row21", true);
        row22 = this.addFieldString("row22", true);
        row23 = this.addFieldString("row23", true);
        row31 = this.addFieldString("row31", true);
        row32 = this.addFieldString("row32", true);
        row33 = this.addFieldString("row33", true);
        row41 = this.addFieldString("row41", true);
        row42 = this.addFieldString("row42", true);
        row43 = this.addFieldString("row43", true);
        row51 = this.addFieldString("row51", true);
        row52 = this.addFieldBoolean("row52", true);
        row53 = this.addFieldBoolean("row53", true);
        row6 = this.addFieldBoolean("row6", true);

        row11.asAtr().label("Row [1,1]").asAtrBootstrap().colPreference(4);
        row12.asAtr().label("Row [1,2]").asAtrBootstrap().colPreference(4);
        row13.asAtr().label("Row [1,3]").asAtrBootstrap().colPreference(4);
        row21.asAtr().label("Row [2,1]").asAtrBootstrap().colPreference(4);
        row22.asAtr().label("Row [2,2]").asAtrBootstrap().colPreference(4);
        row23.asAtr().label("Row [2,3]").asAtrBootstrap().colPreference(4);
        row31.asAtr().label("Row [3,1]").asAtrBootstrap().colPreference(4);
        row32.asAtr().label("Row [3,2]").asAtrBootstrap().colPreference(4);
        row33.asAtr().label("Row [3,3]").asAtrBootstrap().colPreference(4);
        row41.asAtr().label("Row [4,1]").asAtrBootstrap().colPreference(4);
        row42.asAtr().label("Row [4,2]").asAtrBootstrap().colPreference(4);
        row43.asAtr().label("Row [4,3]").asAtrBootstrap().colPreference(4);
        row51.asAtr().label("Row [5,1]").asAtrBootstrap().colPreference(4);
        row52.asAtr().label("Row [5,2]").asAtrBootstrap().colPreference(4);
        row53.asAtr().label("Row [5,3]").asAtrBootstrap().colPreference(4);
        row6.asAtr().label("Row [6]").asAtrBootstrap().colPreference(12);

        row11.withView(new SViewTextArea());

        row13.asAtr()
            .label("Long long long long long long long long long long long long long long long long label");

        row22.asAtr()
            .subtitle("Long long long long long long long long long long long long long long long long subtitle");
        row33.asAtr()
            .label("Long long long long long long long long long long long long long long long long label")
            .subtitle("Long long long long long long long long long long long long long long long long subtitle");

        row41.asAtr()
            .label("Long long long long long long long long long long long long long long long long label")
            .subtitle("Short subtitle");
        row43.asAtr()
            .label("Short label")
            .subtitle("Long long long long long long long long long long long long long long long long subtitle");

        row51.asAtr().subtitle("Short subtitle");

        row52.selectionOf(TRUE, FALSE)
            .withView(new SViewBooleanByRadio().horizontalLayout())
                .asAtrProvider().displayFunction(it -> (TRUE.equals(it)) ? "yep" : "nope");

        row53.asAtr().label("Long long long long long long long long long long long long long long long long label");

        row6.asAtr().label("Single checkbox in row");
    }

    public static class CustomActionsProvider implements IFunction<SInstance, List<SInstanceAction>> {
        @Override
        public List<SInstanceAction> apply(SInstance instance) {
            return (instance instanceof SIComposite)
                ? Collections.emptyList()
                : Arrays.asList(
                //@formatter:off
                    createAction("arrow-circle-up"   , "Up"),
                    createAction("arrow-circle-up"   , "Up"),
                    createAction("arrow-circle-down" , "Down"),
                    createAction("arrow-circle-down" , "Down"),
                    createAction("arrow-circle-left" , "Left"),
                    createAction("arrow-circle-right", "Right"),
                    createAction("arrow-circle-left" , "Left"),
                    createAction("arrow-circle-right", "Right"),
                    createAction("bold"              , "B"),
                    createAction("amazon"            , "A"),
                    createAction("play"              , "Start"));
                //@formatter:on
        }

        private static SInstanceAction createAction(String icon, String msg) {
            return new SInstanceAction(SInstanceAction.ActionType.NORMAL)
                .setIcon(SIcon.resolve(icon))
                .setPreview(new Preview()
                    .setMessage("<h1 class='text-center'>" + msg + "</h1>")
                    .setFormat("html"));
        }
    }

}
