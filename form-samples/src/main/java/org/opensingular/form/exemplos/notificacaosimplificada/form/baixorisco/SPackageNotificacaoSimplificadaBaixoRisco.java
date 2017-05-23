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

package org.opensingular.form.exemplos.notificacaosimplificada.form.baixorisco;

import org.opensingular.form.PackageBuilder;
import org.opensingular.form.SInfoPackage;
import org.opensingular.form.SInstance;
import org.opensingular.form.SPackage;
import org.opensingular.form.exemplos.notificacaosimplificada.form.SPackageNotificacaoSimplificada;
import org.opensingular.form.exemplos.notificacaosimplificada.service.DominioService;

@SInfoPackage(name = SPackageNotificacaoSimplificadaBaixoRisco.PACOTE)
public class SPackageNotificacaoSimplificadaBaixoRisco extends SPackage {

    public static final String PACOTE = "mform.peticao.notificacaosimplificada.baixorisco";

    public static DominioService dominioService(SInstance ins) {
        return ins.getDocument().lookupServiceOrException(DominioService.class);
    }

    @Override
    protected void onLoadPackage(PackageBuilder pb) {
        super.onLoadPackage(pb);
        pb.loadPackage(SPackageNotificacaoSimplificada.class);
        pb.createType(STypeNotificacaoSimplificadaBaixoRisco.class);
    }
}
