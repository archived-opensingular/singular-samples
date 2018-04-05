package org.opensingular.singular.form.showcase.component.form.core.search;

import org.opensingular.form.SIComposite;
import org.opensingular.form.SInstance;
import org.opensingular.form.provider.*;

import java.util.List;

public class ProcessoProvider implements TreeProvider<Processo> {

    private static final ProcessoRepository repository = new ProcessoRepository();

    @Override
    public List<Processo> loadChildren(Processo node) {
        return node.getSubProcessos();
    }

    @Override
    public List<Processo> load(ProviderContext<SInstance> context) {
        return repository.list(context);
    }
}
