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

package org.opensingular.form.io.definition;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import org.opensingular.form.SIList;
import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SDictionary;
import org.opensingular.form.SFormUtil;
import org.opensingular.form.SScopeBase;
import org.opensingular.form.SType;
import org.opensingular.form.STypeComposite;
import org.opensingular.form.SingularFormException;
import com.google.common.collect.Lists;

import org.opensingular.lib.commons.internal.function.SupplierUtil;

/**
 * Transforma a definição de um tipo ou mesmo de um pacote inteiro em uma
 * estrutura de dados equivalente. Tipicamente essa estrutura é utilizada para
 * persistir a definição do tipo ou mesmo transmitir pela rede.
 * 
 * @author Daniel C. Bordin
 */
public class SFormDefinitionPersistenceUtil {

    private final static Supplier<STypePersistenceArchive> typePresistenceArchive = SupplierUtil.cached(
            () -> createTypePersistence());

    private SFormDefinitionPersistenceUtil() {}

    public static SIPersistenceArchive toArchive(SType<?> type) {
        ContextArchive ctx = new ContextArchive(type.getDictionary());

        ctx.getArchive().setRootTypeName(type.getName());
        ensureType(ctx, type);

        return ctx.getArchive();
    }


    private static void ensureType(ContextArchive ctx, SType<?> type) {
        while (type.getParentScope() instanceof SType) {
            type = (SType<?>) type.getParentScope();
        }
        if (!ctx.isNecessaryToArchive(type) || ctx.isAlreadyArchived(type)) {
            return;
        }
        SIPersistenceType pType = ctx.createTypeInPackage(type);
        writeType(ctx, pType, type);
    }

    private static void writeType(ContextArchive ctx, SIPersistenceType pType, SType<?> type) {
        pType.setSuperType(ctx.translateImport(type.getSuperType()));
        
        ensureType(ctx, type.getSuperType());

        if (type instanceof STypeComposite) {
            //TODO (por Daniel Bordin) O código abaixo ainda precisa resolver a questão de field que foram extendido
            // e tiveram apenas uma atributo alterado
            for (SType<?> localField : ((STypeComposite<?>) type).getFieldsLocal()) {
                SIPersistenceType pMember = pType.addMember(localField.getNameSimple());
                writeType(ctx, pMember, localField);
            }
        }
    }

    public static SType<?> fromArchive(SIPersistenceArchive persistenceArchive) {
        ContextUnarchive ctx = new ContextUnarchive(persistenceArchive);
        Lists.reverse(persistenceArchive.getPackages().getChildren()).forEach(ctx::createNewPackage);

        for (SIPersistencePackage pPackage : Lists.reverse(persistenceArchive.getPackages().getChildren())) {
            PackageBuilder pkg = ctx.getPackage(pPackage.getPackageName());
            for (SIPersistenceType pType : Lists.reverse(pPackage.getTypes().getChildren())) {
                SType<?> superType = resolveSuperType(ctx, pkg.getPackage(), pType);
                SType<?> newType = pkg.createType(pType.getSimpleName(), superType);
                readType(ctx, newType, pType);
            }
        }
        return ctx.getDictionary().getType(persistenceArchive.getRootTypeName());
    }


    private static void readType(ContextUnarchive ctx, SType<?> newType, SIPersistenceType pType) {
        if (newType instanceof STypeComposite) {
            readMembers(ctx, (STypeComposite<?>) newType, pType.getMembers());
        }
    }

    private static SType<?> resolveSuperType(ContextUnarchive ctx, SScopeBase scopeNewType, SIPersistenceType pType) {
        String superTypeName = ctx.translateTypeName(pType.getSuperType());
        SType<?> superType = ctx.getDictionary().getTypeOptional(superTypeName);
        if(superType == null) {
            throw new SingularFormException("Ao ler o tipo '" + scopeNewType.getName() + "." + pType.getSimpleName()
                    + "' não foi encontrado a definição do seu tipo '" + superTypeName + "' nas definições sendo importadas.");
        }
        return superType;
    }

    private static void readMembers(ContextUnarchive ctx, STypeComposite<?> newComposite, SIList<SIPersistenceType> members) {
        for(SIPersistenceType member : members) {
            SType<?> fieldType = resolveSuperType(ctx, newComposite, member);
            SType<?> newField = newComposite.addField(member.getSimpleName(), fieldType);
            readType(ctx, newField, member);
        }
        
    }

    /**
     * Cria o tipo e dicionário necessário para o tipo da estrutura de dados de
     * persistência da definição.
     */
    private static STypePersistenceArchive createTypePersistence() {
        return SDictionary.create().getType(STypePersistenceArchive.class);
    }

    private static class ContextArchive {

        private final SIPersistenceArchive pArchive;
        private final Map<String, SIPersistencePackage> packages = new HashMap<>();
        private final Map<String, SIPersistenceType> types = new HashMap<>();
        private final Set<String> imports = new HashSet<>();

        public ContextArchive(SDictionary dictionary) {
            this.pArchive = typePresistenceArchive.get().newInstance();
            prepareDefaultImports(dictionary);
        }

        private void prepareDefaultImports(SDictionary dictionary) {
            dictionary.getType(SType.class).getPackage().getLocalTypes().forEach(type -> imports.add(type.getName()));
        }

        public SIPersistenceArchive getArchive() {
            return pArchive;
        }

        public boolean isNecessaryToArchive(SType<?> type) {
            return !SFormUtil.isSingularBuiltInType(type);
        }

        public SIPersistenceType createTypeInPackage(SType<?> type) {
            SIPersistencePackage pkg = packages.get(type.getPackage().getName());
            if (pkg == null) {
                pkg = pArchive.addPackage(type.getPackage().getName());
                packages.put(type.getPackage().getName(), pkg);
            }
            SIPersistenceType pType = pkg.addType(type.getNameSimple());
            types.put(type.getName(), pType);
            return pType;
        }

        public boolean isAlreadyArchived(SType<?> type) {
            return types.containsKey(type.getName());
        }

        public String translateImport(SType<?> superType) {
            String nameFull = superType.getName();
            return imports.contains(nameFull) ? superType.getNameSimple() : nameFull;
        }
    }

    private static class ContextUnarchive {

        private final SDictionary dictionary = SDictionary.create();
        private final Map<String, PackageBuilder> pkgs = new HashMap<>();
        private final Map<String, String> imports = new HashMap<>();

        private final SIPersistenceArchive pArchive;
        private final Map<String, SIPersistencePackage> packages = new HashMap<>();
        private final Map<String, SIPersistenceType> types = new HashMap<>();

        public ContextUnarchive(SIPersistenceArchive pArchive) {
            this.pArchive = pArchive;
            prepareDefaultImports(dictionary);
        }

        public SDictionary getDictionary() {
            return dictionary;
        }

        public PackageBuilder getPackage(String packageName) {
            return pkgs.get(packageName);
        }

        public void createNewPackage(SIPersistencePackage pPackage) {
            pkgs.put(pPackage.getPackageName(), dictionary.createNewPackage(pPackage.getPackageName()));
        }

        private void prepareDefaultImports(SDictionary dictionary) {
            for (SType<?> type : dictionary.getType(SType.class).getPackage().getLocalTypes()) {
                imports.put(type.getNameSimple(), type.getName());
            }
        }

        public String translateTypeName(String superTypeName) {
            if (superTypeName.indexOf('.') == -1) {
                String expandedName = imports.get(superTypeName);
                if (expandedName != null) {
                    return expandedName;
                }
            }
            return superTypeName;
        }
    }
}
