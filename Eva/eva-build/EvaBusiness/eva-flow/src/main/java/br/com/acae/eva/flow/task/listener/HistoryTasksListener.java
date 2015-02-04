/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task.listener;

import br.com.acae.eva.connector.hosts.AuthHosts;
import br.com.acae.eva.flow.context.ActiveUser;
import br.com.acae.eva.flow.dao.TaskInstanceDAO;
import br.com.acae.eva.flow.qualifier.History;
import br.com.acae.eva.model.TaskInstance;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@Stateless
public class HistoryTasksListener {
    
    @Inject private TaskInstanceDAO taskDAO;
    @Inject private AuthHosts hosts;
    
    @Asynchronous
    @Transactional
    public void keepHistory(@Observes @History TaskInstance instance, ActiveUser active) {
        final TaskInstance find = taskDAO.findBy(instance.getId());
    }
}