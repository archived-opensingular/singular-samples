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

package org.opensingular.requirementsamplemodule.report;

import org.opensingular.form.SIComposite;
import org.opensingular.lib.commons.table.ColumnType;
import org.opensingular.lib.commons.table.TablePopulator;
import org.opensingular.lib.commons.table.TableTool;
import org.opensingular.lib.commons.views.ViewGenerator;
import org.opensingular.lib.commons.views.ViewOutputFormat;
import org.opensingular.requirementsamplemodule.report.filter.STypeSimpleReportFilter;
import org.opensingular.server.commons.form.report.AbstractSingularFormReport;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

public class SimpleReport extends AbstractSingularFormReport<SIComposite> {

    @Inject
    private SimpleReportService simpleReportService;

    @Override
    public String getReportName() {
        return "Relatório Simples";
    }

    @Override
    public Class<STypeSimpleReportFilter> getFilterType() {
        return STypeSimpleReportFilter.class;
    }

    @Override
    public ViewGenerator getViewGenerator() {
        TableTool table = new TableTool();
        table.addColumn(ColumnType.STRING, "Código");
        table.addColumn(ColumnType.STRING, "Nome");
        table.addColumn(ColumnType.STRING, "Descrição");
        TablePopulator populator = table.createSimpleTablePopulator();
        for (SimpleDTO simpleDTO : simpleReportService.listSimpleData(getFilterValue())) {
            TablePopulator tablePopulator = populator.insertLine();
            tablePopulator.setValue(0, simpleDTO.getCodigo());
            tablePopulator.setValue(1, simpleDTO.getNome());
            tablePopulator.setValue(2, simpleDTO.getDescricao());
        }
        return table;
    }

    @Override
    public List<ViewOutputFormat> getEnabledExportFormats() {
        return Collections.singletonList(ViewOutputFormat.EXCEL);
    }


}