/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task.impl;

import br.com.acae.eva.flow.qualifier.Doing;
import br.com.acae.eva.flow.qualifier.Start;
import br.com.acae.eva.flow.task.AutomaticTask;
import br.com.acae.eva.flow.task.qualifier.AlocarPorta;
import br.com.acae.eva.model.TaskInstance;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class AutomaticAllocation extends AutomaticTask {
    
    @Override
    public void start(@Observes @Start @AlocarPorta TaskInstance task) {
        super.start(task);
    }
    
    @Override
    public void doing(@Observes @Doing @AlocarPorta TaskInstance instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
