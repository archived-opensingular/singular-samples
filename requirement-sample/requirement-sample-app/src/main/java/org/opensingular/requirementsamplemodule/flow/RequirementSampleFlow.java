/*
 *
 *  * Copyright (C) 2016 Singular Studios (a.k.a Atom Tecnologia) - www.opensingular.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.opensingular.requirementsamplemodule.flow;

import org.opensingular.requirementsamplemodule.RequirementsampleModule;
import org.opensingular.flow.core.DefinitionInfo;
import org.opensingular.flow.core.ITaskDefinition;
import org.opensingular.flow.core.FlowInstance;
import org.opensingular.flow.core.defaults.PermissiveTaskAccessStrategy;
import org.opensingular.requirement.module.flow.builder.RequirementFlowBuilder;
import org.opensingular.requirement.module.wicket.view.form.FormPage;
import org.opensingular.requirement.module.flow.builder.RequirementFlowDefinition;

import javax.annotation.Nonnull;

import static org.opensingular.requirementsamplemodule.flow.RequirementSampleFlow.RequirementSampleTasks.*;


@DefinitionInfo(RequirementsampleModule.REQUIREMENT_SAMPLE)
public class RequirementSampleFlow extends RequirementFlowDefinition<FlowInstance> {

    public enum RequirementSampleTasks implements ITaskDefinition {

        ANALISAR("Analisar"),
        APROVADO("Aprovado"),
        REPROVADO("Reprovado"),
        A("A"),
        B("B"),
        C("C"),
        D("D"),
        E("E"),
        F("F"),
        G("G"),
        H("H"),
        I("I"),
        J("J"),
        K("K"),
        L("L"),
        M("M"),
        N("N"),
        O("O"),
        P("P"),
        Q("Q"),
        ;

        private String taskName;

        RequirementSampleTasks(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String getName() {
            return taskName;
        }
    }

    public RequirementSampleFlow() {
        super(FlowInstance.class);
        this.setName(RequirementsampleModule.REQUIREMENT_SAMPLE, "Sample");

    }


    @Override
    protected void buildFlow(@Nonnull RequirementFlowBuilder builder) {

        builder.addHumanTask(ANALISAR)
                .uiAccess(new PermissiveTaskAccessStrategy())
                .withExecutionPage(FormPage.class);

        builder.addEndTask(REPROVADO);
        builder.addEndTask(APROVADO);


        builder.setStartTask(ANALISAR);
        
//        builder.addJavaTask(A).call(ctx -> {});
//        builder.addJavaTask(B).call(ctx -> {});
//        builder.addJavaTask(C).call(ctx -> {});
//        builder.addJavaTask(D).call(ctx -> {});
//        builder.addJavaTask(E).call(ctx -> {});
//        builder.addJavaTask(F).call(ctx -> {});
//        builder.addJavaTask(G).call(ctx -> {});
//        builder.addJavaTask(H).call(ctx -> {});
//        builder.addJavaTask(I).call(ctx -> {});
//        builder.addJavaTask(J).call(ctx -> {});
//        builder.addJavaTask(K).call(ctx -> {});
//        builder.addJavaTask(L).call(ctx -> {});
//        builder.addJavaTask(M).call(ctx -> {});
//        builder.addJavaTask(N).call(ctx -> {});
//        builder.addJavaTask(O).call(ctx -> {});
//        builder.addJavaTask(P).call(ctx -> {});
//        builder.addJavaTask(Q).call(ctx -> {});

        builder.from(ANALISAR).go("Aprovar", APROVADO);
        builder.from(ANALISAR).go("Reprovar", REPROVADO);
        
//        builder.from(ANALISAR).go(A);
//        builder.from(ANALISAR).go(B);
//        builder.from(ANALISAR).go(C);
//        builder.from(ANALISAR).go(D);
//        builder.from(ANALISAR).go(E);
//        builder.from(ANALISAR).go(F);
//        builder.from(ANALISAR).go(G);
//        builder.from(ANALISAR).go(H);
//        builder.from(ANALISAR).go(I);
//        builder.from(ANALISAR).go(J);
//        builder.from(ANALISAR).go(K);
//        builder.from(ANALISAR).go(L);
//        builder.from(ANALISAR).go(M);
//        builder.from(ANALISAR).go(N);
//        builder.from(ANALISAR).go(O);
//        builder.from(ANALISAR).go(P);
//        builder.from(ANALISAR).go(Q);
//        
//        builder.from(A).go(B);
//        builder.from(B).go(C);
//        builder.from(C).go(D);
//        builder.from(D).go(E);
//        builder.from(E).go(F);
//        builder.from(F).go(G);
//        builder.from(G).go(H);
//        builder.from(H).go(I);
//        builder.from(I).go(J);
//        builder.from(J).go(K);
//        builder.from(K).go(L);
//        builder.from(L).go(M);
//        builder.from(M).go(N);
//        builder.from(N).go(O);
//        builder.from(O).go(P);
//        builder.from(P).go(Q);
//        builder.from(Q).go(A);
//        builder.from(A).go(APROVADO);
//        builder.from(B).go(APROVADO);
//        builder.from(C).go(APROVADO);
//        builder.from(D).go(APROVADO);
//        builder.from(E).go(APROVADO);
//        builder.from(F).go(APROVADO);
//        builder.from(G).go(APROVADO);
//        builder.from(H).go(APROVADO);
//        builder.from(I).go(APROVADO);
//        builder.from(J).go(APROVADO);
//        builder.from(K).go(APROVADO);
//        builder.from(L).go(APROVADO);
//        builder.from(M).go(APROVADO);
//        builder.from(N).go(APROVADO);
//        builder.from(O).go(APROVADO);
//        builder.from(P).go(APROVADO);
//        builder.from(Q).go(APROVADO);
    }

}