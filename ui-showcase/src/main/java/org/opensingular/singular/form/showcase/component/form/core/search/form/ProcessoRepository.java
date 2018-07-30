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
package org.opensingular.singular.form.showcase.component.form.core.search.form;

import org.opensingular.form.SInstance;
import org.opensingular.form.provider.ProviderContext;

import java.util.ArrayList;
import java.util.List;

public class ProcessoRepository {

    private static final List<Processo> PROCESSOS;

    static {
        PROCESSOS = new ArrayList<>();

        Processo systemd = new Processo(1L, "systemd");

        Processo network = new Processo(2L, "network-manager");
        Processo accounts = new Processo(3L, "accounts-daemon");
        network.addSubProcesso(accounts);
        accounts.addSubProcesso(new Processo(4L, "daemon"));
        systemd.addSubProcesso(network);

        Processo chromium = new Processo(5L, "chromium");
        chromium.addSubProcesso(new Processo(6L, "chromium-sandbox"));

        systemd.addSubProcesso(chromium);

        Processo lightdm = new Processo(7L, "ligthdm");

        Processo xorg = new Processo(8L, "Xorg");
        Processo sh = new Processo(9L, "sh");
        xorg.addSubProcesso(sh);
        sh.addSubProcesso(new Processo(10L, "ssh-agent"));

        lightdm.addSubProcesso(xorg);

        Processo xfce = new Processo(11L, "xfce-session");
        xfce.addSubProcesso(new Processo(12L, "thunar"));
        xfce.addSubProcesso(new Processo(13L, "nm-applet"));
        xfce.addSubProcesso(new Processo(14L, "thunderbird"));

        Processo panel = new Processo(15L, "xfce4-panel");
        panel.addSubProcesso(new Processo(16L, "panel-10-pulses"));
        panel.addSubProcesso(new Processo(17L, "panel-2-actions"));
        panel.addSubProcesso(new Processo(18L, "panel-6-tray"));

        xfce.addSubProcesso(panel);
        lightdm.addSubProcesso(xfce);

        systemd.addSubProcesso(lightdm);

        Processo idea = new Processo(19L, "idea");
        Processo java = new Processo(20L, "java");
        java.addSubProcesso(new Processo(21L, "standalone"));
        idea.addSubProcesso(java);

        systemd.addSubProcesso(idea);

        Processo journal = new Processo(22L, "xfsettingsd");
        Processo appfinder = new Processo(23L, "xfce4-appfinder");
        appfinder.addSubProcesso(new Processo(24L, "finder"));
        journal.addSubProcesso(appfinder);
        journal.addSubProcesso(new Processo(25L, "rambox"));

        Processo logind = new Processo(25L, "systemd-logind");
        Processo loginctl = new Processo(26L, "loginctl");
        loginctl.addSubProcesso(new Processo(27L, "login-agent"));
        logind.addSubProcesso(loginctl);

        PROCESSOS.add(systemd);
        PROCESSOS.add(journal);
        PROCESSOS.add(logind);
    }

    public List<Processo> list(ProviderContext<SInstance> context) {
        return PROCESSOS;
    }
}
