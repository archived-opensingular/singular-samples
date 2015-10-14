package br.net.mirante.singular.flow.core.defaults;

import br.net.mirante.singular.flow.core.ITaskPageStrategy;
import br.net.mirante.singular.flow.core.MUser;
import br.net.mirante.singular.flow.core.TaskInstance;
import br.net.mirante.singular.flow.util.view.WebRef;

public class NullPageStrategy implements ITaskPageStrategy {

    @Override
    public WebRef getPageFor(TaskInstance taskInstance, MUser user) {
        return null;
    }

}