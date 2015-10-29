package br.net.mirante.singular.flow.core.defaults;

import br.net.mirante.singular.flow.core.ProcessNotifier;
import br.net.mirante.singular.flow.core.ExecucaoMTask;
import br.net.mirante.singular.commons.util.log.Loggable;
import br.net.mirante.singular.flow.core.AbstractProcessNotifiers;
import br.net.mirante.singular.flow.core.ExecutionContext;
import br.net.mirante.singular.flow.core.MUser;
import br.net.mirante.singular.flow.core.ProcessInstance;
import br.net.mirante.singular.flow.core.TaskHistoricLog;
import br.net.mirante.singular.flow.core.TaskInstance;

import java.util.List;

public class NullNotifier implements ProcessNotifier {

    @Override
    public void notifyUserTaskRelocation(TaskInstance taskInstance, MUser responsibleUser, MUser userToNotify, MUser allocatedUser, MUser removedUser) {

    }

    @Override
    public void notifyUserTaskAllocation(TaskInstance taskInstance, MUser responsibleUser, MUser userToNotify, MUser allocatedUser, MUser removedUser, String justification) {

    }

    @Override
    public void notifyStartToResponsibleUser(TaskInstance taskInstance, ExecutionContext execucaoTask) {

    }

    @Override
    public void notifyStartToInterestedUser(TaskInstance taskInstance, ExecutionContext execucaoTask) {

    }

    @Override
    public <X extends MUser> void notifyLogToUsers(TaskHistoricLog taskHistoricLog, List<X> usersToNotify) {

    }

    @Override
    public void notifyStateUpdate(ProcessInstance instanciaProcessoMBPM) {

    }

}
