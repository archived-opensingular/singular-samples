/*
 * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
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

package org.opensingular.form.wicket.helpers;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.opensingular.lib.commons.test.AssertionsBase;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Classe base de apoio a construção de assertivas para componentes Wicket.
 *
 * @author Daniel Bordin on 12/02/2017.
 */
public abstract class AssertionsWComponentBase<T extends Component, SELF extends AssertionsWComponentBase<T, SELF>>
        extends AssertionsBase<T, SELF> {

    public AssertionsWComponentBase(T c) {
        super(c);
    }

    @Override
    protected String errorMsg(String msg) {
        if (getTarget() == null) {
            return "null : " + msg;
        }
        return getTarget().getPageRelativePath() + " : " + msg;
    }

    /**
     * Busca um sub componente do componente atual com o ID informado e retonar o resultado. O resultado pode
     * conter um valor null senão for encontrado o componente.
     */
    @Nonnull
    public final AssertionsWComponent getSubCompomentWithId(String componentId) {
        return findSubComponent(component -> componentId.equals(component.getId())).isNotNull();
    }

    /**
     * Busca um sub componente do componente atual que atenda ao critério informado. Para no primeiro que atender. O
     * resultado pode conter um valor null senão for encontrado o componente.
     */
    @Nonnull
    public final AssertionsWComponent findSubComponent(Predicate<Component> predicate) {
        isNotNull();
        return new AssertionsWComponent(findSubComponent(getTarget(), predicate));
    }

    private Component findSubComponent(Component parent, Predicate<Component> predicate) {
        if (parent instanceof MarkupContainer) {
            for (Component component : (MarkupContainer) parent) {
                if (predicate.test(component)) {
                    return component;
                }
                Component result = findSubComponent(component, predicate);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /**
     * Retornar uma lista com todos os sub componentes do atual que forem da classe informada. A lista pode ser de
     * tamanho zero.
     */
    @Nonnull
    public final <TT extends Component> AssertionsWComponentList getSubComponents(@Nonnull Class<TT> targetClass) {
        List<TT> result = Collections.emptyList();
        if (getTarget() instanceof MarkupContainer) {
            result = TestFinders.findTag((MarkupContainer) getTarget(), targetClass);
        }
        return new AssertionsWComponentList<>(result);
    }

    /**
     * Verifica se o componente é um TextField e sendo retorna um assertiva específica para esse caso. Dispara exception
     * senão for um TextField Wicket.
     */
    @Nonnull
    public AssertionsWTextField asTextField() {
        return new AssertionsWTextField(getTarget(TextField.class));
    }
}