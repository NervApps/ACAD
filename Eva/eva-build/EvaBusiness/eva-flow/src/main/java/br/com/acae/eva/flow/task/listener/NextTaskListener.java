/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task.listener;

import br.com.acae.eva.flow.dao.TaskInstanceDAO;
import br.com.acae.eva.flow.dao.TaskOrderDAO;
import br.com.acae.eva.flow.qualifier.NextTask;
import br.com.acae.eva.model.ProcessInstance;
import br.com.acae.eva.model.TaskDef;
import br.com.acae.eva.model.TaskInstance;
import java.util.List;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
public class NextTaskListener {
    
    @Inject private TaskOrderDAO orderDAO;
    @Inject private TaskInstanceDAO instanceDAO;
    
    @Transactional
    public void executeNextTaks(@Observes @NextTask TaskInstance instance) {
        final List<TaskDef> nexts = orderDAO.getNexts(instance.getTaskDef());
        if (!nexts.isEmpty()) {
            final ProcessInstance process = instance.getProcess();
            final List<TaskDef> pending = instanceDAO.getPendingInProcess(nexts, process);
            if (!pending.isEmpty()) {
                //TODO: chamar o método run da classe da tarefa
            }
        }
    }
    
}
