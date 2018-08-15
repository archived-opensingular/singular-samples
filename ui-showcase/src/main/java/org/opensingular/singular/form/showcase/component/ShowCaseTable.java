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

package org.opensingular.singular.form.showcase.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.apache.wicket.Component;
import org.opensingular.form.STypeComposite;
import org.opensingular.lib.commons.base.SingularException;
import org.opensingular.lib.commons.base.SingularUtil;
import org.opensingular.lib.commons.scan.SingularClassPathScanner;
import org.opensingular.lib.commons.ui.Icon;
import org.opensingular.lib.wicket.util.resource.DefaultIcons;
import org.opensingular.singular.form.showcase.ShowCaseException;
import org.opensingular.singular.form.showcase.component.form.xsd.XsdCaseSimple;
import org.opensingular.singular.form.showcase.component.form.xsd.XsdCaseSimple2;
import org.opensingular.studio.core.definition.StudioDefinition;
import org.springframework.stereotype.Service;

@Service
public class ShowCaseTable {

    private final Map<String, ShowCaseGroup> formGroups   = new LinkedHashMap<>();
    private final Map<String, ShowCaseGroup> studioGroups = new LinkedHashMap<>();
    private final Map<String, ShowCaseGroup> wicketGroups = new LinkedHashMap<>();

    private final Map<Group, List<Class<?>>> casePorGrupo = new EnumMap<>(Group.class);

    public ShowCaseTable() {

        Set<Class<?>> annotated = SingularClassPathScanner.get().findClassesAnnotatedWith(CaseItem.class, ShowCaseTable.class.getPackage().getName());
        for (Class<?> aClass : annotated) {
            final CaseItem annotation = aClass.getAnnotation(CaseItem.class);
            List<Class<?>> classes = casePorGrupo.get(annotation.group());
            if (classes == null) {
                classes = new ArrayList<>();
            }
            classes.add(aClass);
            casePorGrupo.put(annotation.group(), classes);
        }

        // @formatter:off
        Stream.of(Group.values()).forEach(this::addGroup);

        addGroup("XSD", DefaultIcons.CODE, ShowCaseType.FORM)
                .addCase(new XsdCaseSimple())
                .addCase(new XsdCaseSimple2());

        addGroup(Group.STUDIO_PERSISTENCE);
        //@formatter:on
    }

    public ShowCaseItem findCaseItemByComponentName(String name) {
        return getGroups().stream()
            .map(ShowCaseGroup::getItens)
            .flatMap(Collection::stream)
            .filter(f -> name.equalsIgnoreCase(f.getComponentName()))
            .findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    private void addGroup(Group groupEnum) {
        final ShowCaseGroup group = addGroup(groupEnum.getName(), groupEnum.getIcone(), groupEnum.getTipo());

        final List<Class<?>> classes = casePorGrupo.get(groupEnum);
        if (classes == null) {
            return;
        }
        for (Class<?> caseClass : classes) {
            final CaseItem caseItem = caseClass.getAnnotation(CaseItem.class);
            CaseBase<?> caseBase;
            if (STypeComposite.class.isAssignableFrom(caseClass)) {
                caseBase = new CaseBaseForm((Class<? extends STypeComposite<?>>) caseClass);
            } else if (StudioDefinition.class.isAssignableFrom(caseClass)) {
                caseBase = new CaseBaseStudio((Class<? extends StudioDefinition>) caseClass);
            } else if (Component.class.isAssignableFrom(caseClass)) {
                caseBase = new CaseBaseWicket((Class<? extends Component>) caseClass);
            } else {
                throw new ShowCaseException("Apenas classes que estendem o tipo " + STypeComposite.class.getName() +
                    " podem ser anotadas com @" + CaseItem.class.getSimpleName());
            }
            caseBase.setShowCaseType(group.getTipo());
            caseBase.setComponentName(caseItem.componentName());
            caseBase.setSubCaseName(caseItem.subCaseName());
            caseBase.setAnnotationMode(caseItem.annotation());

            if (!caseItem.customizer().isInterface()) {
                createInstance(caseItem).customize(caseBase);
            }
            for (Resource resource : caseItem.resources()) {
                Optional<ResourceRef> resourceRef;
                if (resource.extension().isEmpty()) {
                    resourceRef = ResourceRef.forSource(resource.value());
                } else {
                    resourceRef = ResourceRef.forClassWithExtension(resource.value(), resource.extension());
                }
                resourceRef.ifPresent(resourceRef1 -> caseBase.getAdditionalSources().add(resourceRef1));
            }
            group.addCase(caseBase);
        }
    }

    private CaseCustomizer createInstance(CaseItem caseItem) {
        try {
            return caseItem.customizer().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw SingularException.rethrow(e);
        }
    }

    private ShowCaseGroup addGroup(String groupName, Icon icon, ShowCaseType tipo) {
        return getGroupMap(tipo).computeIfAbsent(groupName, n -> new ShowCaseGroup(n, icon, tipo));
    }

    public Collection<ShowCaseGroup> getGroups() {
        final List<ShowCaseGroup> groups = new ArrayList<>(formGroups.values());
        groups.addAll(studioGroups.values());
        groups.addAll(wicketGroups.values());
        return groups;
    }

    private Map<String, ShowCaseGroup> getGroupMap(ShowCaseType showCaseType) {
        switch (showCaseType) {
            case FORM:
                return formGroups;
            case STUDIO:
                return studioGroups;
            case WICKET_UTILS:
                return wicketGroups;
            default:
                return Collections.emptyMap();
        }
    }

    public Collection<ShowCaseGroup> getGroups(ShowCaseType showCaseType) {
        return getGroupMap(showCaseType).values();
    }

    public static class ShowCaseGroup implements Serializable {

        private final String                    groupName;
        private final Icon                      icon;
        private final ShowCaseType              tipo;

        private final Map<String, ShowCaseItem> itens = new TreeMap<>();

        public ShowCaseGroup(String groupName, Icon icon, ShowCaseType tipo) {
            this.groupName = groupName;
            this.icon = icon;
            this.tipo = tipo;
        }

        public String getGroupName() {
            return groupName;
        }

        public <T extends CaseBase<?>> ShowCaseGroup addCase(Class<T> classCase) {
            try {
                return addCase(classCase.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw SingularUtil.propagate(e);
            }
        }

        private ShowCaseGroup addCase(CaseBase<?> c) {
            ShowCaseItem item = itens.computeIfAbsent(c.getComponentName(), k -> new ShowCaseItem(c.getComponentName(), c.getShowCaseType()));
            item.addCase(c);
            return this;
        }

        public Collection<ShowCaseItem> getItens() {
            return itens.values();
        }

        public Icon getIcon() {
            return icon;
        }

        public ShowCaseType getTipo() {
            return tipo;
        }
    }

    public static class ShowCaseItem implements Serializable {

        private final String            componentName;

        private final List<CaseBase<?>> cases = new ArrayList<>();

        private ShowCaseType            showCaseType;

        public ShowCaseItem(String componentName, ShowCaseType showCaseType) {
            this.componentName = componentName;
            this.showCaseType = showCaseType;
        }

        public String getComponentName() {
            return componentName;
        }

        public void addCase(CaseBase<?> c) {
            cases.add(c);
        }

        public List<CaseBase<?>> getCases() {
            cases.sort((case1, case2) -> case1.getSubCaseName().compareToIgnoreCase(case2.getSubCaseName()));

            CaseBase<?> caseBaseDefault = cases.stream().filter(ins -> "Default".equalsIgnoreCase(ins.getSubCaseName())).findFirst().orElse(null);
            if (caseBaseDefault != null) {
                cases.remove(caseBaseDefault);
                cases.add(0, caseBaseDefault);
            }
            return cases;
        }

        public ShowCaseType getShowCaseType() {
            return showCaseType;
        }
    }
}
