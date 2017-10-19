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

package org.opensingular.singular.form.showcase.view.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.opensingular.form.wicket.util.SourceCodeProcessor;
import org.opensingular.lib.wicket.util.util.WicketUtils;

import java.util.List;

public class ItemCodePanel extends Panel {

    public ItemCodePanel(String id, IModel<String> code, String extension) {
        super(id);
        final SourceCodeProcessor pcf = new SourceCodeProcessor(code.getObject());
        add(new Label("code", pcf.getResultSourceCode())
                .add(WicketUtils.$b.classAppender(getSyntaxHighlighterConfig(pcf.getLinesToBeHighlighted(), extension))));
    }

    private String getSyntaxHighlighterConfig(List<Integer> linesToBeHighlighted, String extension) {
        StringBuilder config = new StringBuilder();
        if ("xsd".equalsIgnoreCase(extension)) {
            extension = "xml";
        }
        config.append(String.format("brush: %s;", extension));

        if (!linesToBeHighlighted.isEmpty()) {
            config.append(" highlight: [");
            linesToBeHighlighted.forEach(l -> {
                config.append(l);
                if (linesToBeHighlighted.indexOf(l) != linesToBeHighlighted.size() - 1) {
                    config.append(", ");
                }
            });
            config.append("]; ");
        }

        return config.toString();
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
    }

}
