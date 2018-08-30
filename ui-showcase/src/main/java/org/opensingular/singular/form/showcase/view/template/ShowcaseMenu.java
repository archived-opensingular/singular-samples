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

package org.opensingular.singular.form.showcase.view.template;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

import javax.inject.Inject;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.string.StringValue;
import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.lib.wicket.util.menu.MetronicMenu;
import org.opensingular.lib.wicket.util.menu.MetronicMenuGroup;
import org.opensingular.lib.wicket.util.menu.MetronicMenuItem;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.singular.form.showcase.component.ShowCaseTable;
import org.opensingular.singular.form.showcase.component.ShowCaseType;
import org.opensingular.singular.form.showcase.view.page.ComponentPage;
import org.opensingular.singular.form.showcase.view.page.form.crud.CrudPage;
import org.opensingular.singular.form.showcase.view.page.prototype.PrototypeListPage;
import org.opensingular.singular.form.showcase.view.page.studio.StudioHomePage;
import org.opensingular.singular.form.showcase.view.page.wicket.WicketHomePage;

public class ShowcaseMenu extends Panel {

    private static final long serialVersionUID = 7622791136418841943L;

    @Inject
    private ShowCaseTable     showCaseTable;

    private ShowCaseType      showCaseType;

    public ShowcaseMenu(String id) {
        this(id, null);
    }

    public ShowcaseMenu(String id, ShowCaseType showCaseType) {
        super(id);
        this.showCaseType = showCaseType;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(buildMenu());
    }

    private ShowCaseType retrieveShowcaseTypeFromRequest() {
        if (showCaseType != null)
            return showCaseType;
        StringValue tipoValue = getPage().getPageParameters().get(ShowCaseType.SHOWCASE_TYPE_PARAM);
        return ShowCaseType.valueOf(tipoValue.toString(ShowCaseType.FORM.toString()));
    }

    private static Function<? super String, ? extends MetronicMenuItem> menuFunction(ShowCaseType showCaseType) {
        return name -> new MetronicMenuItem(null, name, () -> new ComponentPage(showCaseType, ShowCaseType.buildPageParameters(name.toLowerCase(), showCaseType)));
    }

    private MetronicMenu buildMenu() {
        MetronicMenu menu = new MetronicMenu("menu");

        switch (retrieveShowcaseTypeFromRequest()) {
            case FORM:
                menu.addItem(new MetronicMenuItem(DefaultIcons.ROCKET, "Demo", CrudPage.class, ShowCaseType.buildPageParameters(ShowCaseType.FORM)));
                menu.addItem(new MetronicMenuItem(DefaultIcons.PENCIL, "FormBuilder", PrototypeListPage.class, ShowCaseType.buildPageParameters(ShowCaseType.FORM)));
                addItems(menu, ShowCaseType.FORM, false);
                break;
            case STUDIO:
                menu.addItem(new MetronicMenuItem(DefaultIcons.MAP, "Studio", StudioHomePage.class, ShowCaseType.buildPageParameters(ShowCaseType.STUDIO)));
                addItems(menu, ShowCaseType.STUDIO, false);
                break;
            case WICKET_UTILS:
                menu.addItem(new MetronicMenuItem(DefaultIcons.PUZZLE, "Wicket Utils", WicketHomePage.class, ShowCaseType.buildPageParameters(ShowCaseType.WICKET_UTILS)));
                addItems(menu, ShowCaseType.WICKET_UTILS, false);
                break;
            default:
                addItems(menu, ShowCaseType.FORM, true);
        }

        MetronicMenuGroup relationalPersistence = new MetronicMenuGroup(Icon.of("fa fa-database"), "Relational Persistence");
        relationalPersistence.addItem(new MetronicMenuItem(null, "Simple", "/relationalpersistence/simple"));
        relationalPersistence.addItem(new MetronicMenuItem(null, "Many to One", "/relationalpersistence/manytoone"));
        relationalPersistence.addItem(new MetronicMenuItem(null, "Many to Many", "/relationalpersistence/manytomany"));
        menu.addItem(relationalPersistence);

        return menu;
    }

    private void addItems(MetronicMenu menu, ShowCaseType showCaseType, boolean emptyGroups) {
        Collection<ShowCaseTable.ShowCaseGroup> groups = (emptyGroups) ? Collections.emptySet() : showCaseTable.getGroups(showCaseType);
        Function<? super String, ? extends MetronicMenuItem> menuFunction = menuFunction(showCaseType);
        groups.forEach(group -> {
            final MetronicMenuGroup showCaseGroup = new MetronicMenuGroup(group.getIcon(), group.getGroupName());
            group.getItens().stream()
                .map(ShowCaseTable.ShowCaseItem::getComponentName)
                .map(menuFunction)
                .forEach(showCaseGroup::addItem);
            menu.addItem(showCaseGroup);
        });
    }
}
