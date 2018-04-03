package org.opensingular.singular.form.showcase.component.form.core.search;

import org.opensingular.form.SInstance;
import org.opensingular.form.provider.Config;
import org.opensingular.form.provider.FilteredProvider;
import org.opensingular.form.provider.ProviderContext;

import java.util.List;

public class ProcessoProvider implements FilteredProvider<Processo> {

    private ProcessoRepository repository = new ProcessoRepository();

    @Override
    public void configureProvider(Config cfg) {
        cfg.getFilter().addFieldString("nome").asAtr().label("Nome").asAtrBootstrap().colPreference(6);
        cfg.result()
                .addColumn("nome", "Nome");
    }

    @Override
    public List<Processo> load(ProviderContext<SInstance> context) {
        //@destacar
        return repository.get(context.getFilterInstance());
    }

}
