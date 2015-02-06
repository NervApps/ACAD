/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task;

import br.com.acae.eva.flow.dao.TaskInstanceDAO;
import br.com.acae.eva.flow.dao.TaskOrderDAO;
import br.com.acae.eva.flow.task.qualifier.KeepHistory;
import br.com.acae.eva.flow.task.qualifier.NextTask;
import br.com.acae.eva.model.TaskDef;
import br.com.acae.eva.model.TaskInstance;
import br.com.acae.eva.model.enums.TaskState;
import java.util.List;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
public abstract class AutomaticTask implements Task {
    
    @Inject protected TaskInstanceDAO instanceDAO;
    @Inject protected TaskOrderDAO orderDAO;
    @Inject @NextTask protected Event<TaskInstance> nextTasks;
    @Inject @KeepHistory protected Event<TaskInstance> keepHistory;

    @Override
    @Transactional
    public TaskInstance create(final TaskDef def) {
        TaskInstance instance = new TaskInstance();
        instance.setState(TaskState.NEW);
        instance.setTaskDef(def);
        instance = instanceDAO.save(instance);
        
        return updateState(instance, TaskState.TODO);
    }

    @Override
    public void start(TaskInstance instance) {
        final List<TaskDef> previous = orderDAO.getPrevious(instance.getTaskDef());
        final List pending = instanceDAO.getPendingInProcess(previous, instance.getProcess());
        
        if (pending.isEmpty()) {
            updateState(instance, TaskState.DOING);
            doing(instance);
            done(instance);
        }
    }

    @Override
    public void done(final TaskInstance instance) {
        updateState(instance, TaskState.DONE);
        nextTasks.fire(instance);
    }

    @Override
    public void error(final TaskInstance instance) {
        updateState(instance, TaskState.FAILED);
    }

    @Override
    public void block(final TaskInstance instance) {
        updateState(instance, TaskState.BLOCKED);
    }

    @Override
    public void cancel(final TaskInstance instance) {
        updateState(instance, TaskState.CANCELED);
    }
    
    @Transactional
    protected TaskInstance updateState(final TaskInstance instance, final TaskState state) {
        instance.setState(state);
        keepHistory.fire(instance);
        return instanceDAO.saveAndFlushAndRefresh(instance);
    }
}
