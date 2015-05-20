/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task.business;

import br.com.acae.eva.flow.dao.ProcessInstanceDAO;
import br.com.acae.eva.model.ProcessInstance;
import br.com.acae.eva.model.Project;
import br.com.acae.eva.model.enums.ProcessDefinition;
import br.com.acae.eva.model.enums.State;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author vitor
 */
@RequestScoped
public class ProcessBusiness {
    @Inject private ProcessInstanceDAO dao;
    
    public ProcessInstance create(final ProcessDefinition def, 
                                  final State state, final Project project) {
        final ProcessInstance instance = new ProcessInstance(def, state, project);
        return save(instance);
    }
    
    public ProcessInstance createSubProcess(final ProcessDefinition def, 
                    final State state, final ProcessInstance parent) {
        final Project project = parent.getProject();
        final ProcessInstance instance = new ProcessInstance(def, state, project);
        instance.setParent(parent);
        return save(instance);
    }
    
    public ProcessInstance find(Long processInsanceId) {
        return dao.findBy(processInsanceId);
    }
    
    @Transactional
    private ProcessInstance save(final ProcessInstance instance) {
        return dao.save(instance);
    }
}