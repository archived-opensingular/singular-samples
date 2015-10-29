package br.net.mirante.singular.flow.core.entity;

import java.util.Date;
import java.util.List;

public interface IEntityProcessVersion extends IEntityByCod {

    IEntityProcessDefinition getProcessDefinition();

    Date getVersionDate();

    void setVersionDate(Date date);

    List<? extends IEntityTaskVersion> getVersionTasks();

    default String getDefinitionClassName() {
        return getProcessDefinition().getDefinitionClassName();
    }

    default String getAbbreviation() {
        return getProcessDefinition().getKey();
    }

    default String getName() {
        return getProcessDefinition().getName();
    }

    default IEntityCategory getCategory() {
        return getProcessDefinition().getCategory();
    }

    default IEntityTaskVersion getTaskVersion(String abbreviation) {
        for (IEntityTaskVersion situacao : getVersionTasks()) {
            if (situacao.getAbbreviation().equalsIgnoreCase(abbreviation)) {
                return situacao;
            }
        }
        return null;
    }
}
