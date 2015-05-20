/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task.business;

import br.com.acae.eva.flow.dao.TaskDefDAO;
import br.com.acae.eva.flow.dao.TaskInstanceDAO;
import br.com.acae.eva.flow.task.qualifier.Doing;
import br.com.acae.eva.flow.task.qualifier.Start;
import br.com.acae.eva.flow.task.TaskNames;
import br.com.acae.eva.model.ProcessInstance;
import br.com.acae.eva.model.TaskDef;
import br.com.acae.eva.model.TaskInstance;
import br.com.acae.eva.model.enums.TaskState;
import br.com.acae.eva.task.qualifier.Done;
import java.lang.annotation.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class TaskBusiness {
    
    private static final Logger logger = Logger.getLogger("TaskBusiness");
    @Inject private Event<TaskInstance> event;
    @Inject private TaskInstanceDAO dao;
    @Inject private TaskDefDAO defDAO;
    
    @Transactional
    public TaskInstance create(final TaskDef def, final ProcessInstance process) {
        TaskInstance instance = new TaskInstance();
        instance.setState(TaskState.TODO);
        instance.setTaskDef(def);
        instance.setProcess(process);
        return dao.save(instance);
    }
    
    public void run(final TaskInstance task) {
        dispatch(task, new AnnotationLiteral<Start>() {});
    }
    
    public void execute(final TaskInstance task) {
        dispatch(task, new AnnotationLiteral<Doing>() {});
    }
    
    public void finalize(final TaskInstance task) {
        dispatch(task, new AnnotationLiteral<Done>() {});
    }
    
    public TaskDef getTask(final String taskName) {
        return defDAO.findByNameEqual(taskName);
    }
    
    public TaskInstance find(final Long taskId) {
        return dao.findBy(taskId);
    }
    
    private void dispatch(final TaskInstance task, final Annotation method) {
        final TaskDef def = task.getTaskDef();
        final TaskNames t = TaskNames.getByTaskDef(def);
        if (t != null)
            event.select(method).select(t.getQualifier()).fire(task);
        else
            logger.log(Level.WARNING, "Class for task: {0} not found", def.getName());
    }
}
