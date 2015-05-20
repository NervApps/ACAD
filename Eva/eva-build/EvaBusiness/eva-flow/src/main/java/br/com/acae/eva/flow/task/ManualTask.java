/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task;

import br.com.acae.eva.model.TaskDef;
import br.com.acae.eva.model.TaskInstance;
import br.com.acae.eva.model.enums.TaskState;
import java.util.List;

/**
 *
 * @author Vitor
 */
public abstract class ManualTask extends AutomaticTask {

    @Override
    public void start(TaskInstance instance) {
        final List<TaskDef> previous = orderDAO.getPrevious(instance.getTaskDef());
        final List pending = instanceDAO.getPendingInProcess(previous, instance.getProcess());
        
        if (pending.isEmpty()) {
            updateState(instance, TaskState.DOING);
        }
    }
}