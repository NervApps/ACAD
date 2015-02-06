/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task.listener;

import br.com.acae.eva.flow.task.qualifier.Doing;
import br.com.acae.eva.flow.task.qualifier.Start;
import br.com.acae.eva.flow.task.TaskNames;
import br.com.acae.eva.model.TaskDef;
import br.com.acae.eva.model.TaskInstance;
import br.com.acae.eva.model.User;
import java.lang.annotation.Annotation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class TaskExecutorDispatcher {
    
    private static final Logger logger = Logger.getLogger("TaskExecutorDispatcher");
    @Inject private Event<TaskInstance> event;
    
    public void run(final TaskInstance task) {
        dispatch(task, new AnnotationLiteral<Start>() {});
    }
    
    public void execute(final TaskInstance task) {
        dispatch(task, new AnnotationLiteral<Doing>() {});
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
