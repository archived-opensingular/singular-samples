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

package org.opensingular.singular.form.showcase.view.page;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SourceCodeProcessorTest {
    private String mockCodigoFonte(){
        StringBuilder builder = new StringBuilder();

        builder.append("/*\n");
        builder.append(" * XXXX Comments to be supressed\n");
        builder.append(" */\n");
        builder.append("package org.example;\n");
        builder.append("\n");
        builder.append("import org.opensingular.form.SIComposite;\n");
        builder.append("import org.opensingular.form.SInfoType;\n");
        builder.append("\n");

        builder.append("/** javadoc do arquivo\n")
                .append(" * Linha 1\n")
                .append("* Linha 2\n")
            .append("end javadoc\n")
            .append("* @author Daniel\n")
            .append("*/\n")
            .append("public class MockClass implements Serializable{\n")
            .append("/**\n")
            .append("String mock\n")
            .append("*/\n")
            .append("public String mockString;\n")
            .append("public Integer mockInteger;\n")

            .append("@CaseItem \n")
            .append("@Resource de teste \n")
            .append("public MockClass(){}\n")

            .append("//@destacar:bloco\n")
            .append("public string getMockString(){\n")
            .append("return mockString;\n")
            .append("}\n")
            .append("//@destacar:fim\n")

            .append("// @destacar:bloco\n")
            .append("public string getMockInteger(){\n")
            .append("return mockInteger;\n")
            .append("}\n")
            .append("// @destacar:fim\n")

            .append("//TODO verificar se nao mostra")
            .append("//@formatter\n")
            .append("public void emptyMethod(){}\n")

            .append("//@destacar\n")
            .append("// empty line\n")

            .append("// @destacar\n")
            .append("// empty line2\n")

            .append("}\n")
            ;

        return builder.toString();
    }

    @Test
    public void testProcessadorCodigoFonte(){
        SourceCodeProcessor sourceCodeProcessor = new SourceCodeProcessor(mockCodigoFonte());

        assertThat(sourceCodeProcessor.getLinesToBeHighlighted()).hasSize(8);
        assertThat(sourceCodeProcessor.getJavadoc()).isEqualTo(" Linha 1\n Linha 2\nend javadoc\n");
        assertThat(sourceCodeProcessor.getResultSourceCode()).isNotNull().startsWith("package ");
    }

    @Test
    public void testRemoveInternalImports() {
        StringBuilder code = new StringBuilder();
        code.append("package org.example;\n");
        code.append("\n");
        code.append("import org.opensingular.form.SIComposite;\n");
        code.append("import org.opensingular.singular.form.showcase.component.CaseItem;\n");
        code.append("import org.opensingular.singular.form.showcase.component.Group;\n");
        code.append("import org.opensingular.singular.form.showcase.component.Resource;\n");
        code.append("import org.opensingular.form.SInfoType;\n");
        code.append("\n");
        code.append("public class Test {}");

        StringBuilder exp = new StringBuilder();
        exp.append("package org.example;\n");
        exp.append("\n");
        exp.append("import org.opensingular.form.SIComposite;\n");
        exp.append("import org.opensingular.form.SInfoType;\n");
        exp.append("\n");
        exp.append("public class Test {}");

        SourceCodeProcessor processor = new SourceCodeProcessor(code.toString());
        assertThat(processor.getResultSourceCode()).isEqualTo(exp.toString());
    }

    @Test
    public void testRemoveHiddenBlock() {
        StringBuilder code = new StringBuilder();
        code.append("package org.example;\n");
        code.append("\n");
        code.append("//hidden:begin\n");
        code.append("import org.opensingular.form.SIComposite;\n");
        code.append("//hidden:end\n");
        code.append("\n");
        code.append("public class Test {}");

        StringBuilder exp = new StringBuilder();
        exp.append("package org.example;\n");
        exp.append("\n");
        exp.append("public class Test {}");

        SourceCodeProcessor processor = new SourceCodeProcessor(code.toString());
        assertThat(processor.getResultSourceCode()).isEqualTo(exp.toString());
    }

    @Test
    public void testRemoveHiddenComment() {
        StringBuilder code = new StringBuilder();
        code.append("package org.example;\n");
        code.append("\n");
        code.append("/*hidden*/\n");
        code.append(" /*hidden*/\n");
        code.append("/*hidden*/A\n");
        code.append(" /*hidden*/B\n");
        code.append(" C/*hidden*/\n");
        code.append("D /*hidden*/\n");
        code.append("E/*hidden*/\n");
        code.append("\n");
        code.append("public class Test {}");

        StringBuilder exp = new StringBuilder();
        exp.append("package org.example;\n");
        exp.append("\n");
        exp.append("public class Test {}");

        SourceCodeProcessor processor = new SourceCodeProcessor(code.toString());
        assertThat(processor.getResultSourceCode()).isEqualTo(exp.toString());
    }

    @Test
    public void testRemoveCaseAnnotationSimple() {
        StringBuilder code = new StringBuilder();
        code.append("package org.example;\n");
        code.append("\n");
        code.append("@CaseItem()\n");
        code.append("public class Test {}");

        StringBuilder exp = new StringBuilder();
        exp.append("package org.example;\n");
        exp.append("\n");
        exp.append("public class Test {}");

        SourceCodeProcessor processor = new SourceCodeProcessor(code.toString());
        assertThat(processor.getResultSourceCode()).isEqualTo(exp.toString());
    }

    @Test
    public void testRemoveCaseAnnotationMultiLine() {
        StringBuilder code = new StringBuilder();
        code.append("package org.example;\n");
        code.append("\n");
        code.append("@CaseItem(\n");
        code.append(")\n");
        code.append("public class Test {}");

        StringBuilder exp = new StringBuilder();
        exp.append("package org.example;\n");
        exp.append("\n");
        exp.append("public class Test {}");

        assertThat(new SourceCodeProcessor(code.toString()).getResultSourceCode()).isEqualTo(exp.toString());
    }

    @Test
    public void testRemoveCaseAnnotationMultiParathesis() {
        StringBuilder code = new StringBuilder();
        code.append("package org.example;\n");
        code.append("\n");
        code.append("@CaseItem(componentName = \"Basic\", resources = @Resource(CaseInputCorePackage.class))\n");
        code.append("public class Test {}");

        StringBuilder exp = new StringBuilder();
        exp.append("package org.example;\n");
        exp.append("\n");
        exp.append("public class Test {}");

        assertThat(new SourceCodeProcessor(code.toString()).getResultSourceCode()).isEqualTo(exp.toString());
    }
}
