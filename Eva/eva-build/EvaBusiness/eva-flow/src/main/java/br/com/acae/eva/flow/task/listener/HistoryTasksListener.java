/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task.listener;

import br.com.acae.eva.connector.hosts.AuthHosts;
import br.com.acae.eva.flow.dao.HistoryDAO;
import br.com.acae.eva.flow.dao.TaskInstanceDAO;
import br.com.acae.eva.flow.task.qualifier.KeepHistory;
import br.com.acae.eva.model.History;
import br.com.acae.eva.model.TaskInstance;
import br.com.acae.eva.model.enums.HistoryObjectType;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class HistoryTasksListener {
    
    @Inject private TaskInstanceDAO taskDAO;
    @Inject private HistoryDAO historyDAO;
    @Inject private AuthHosts hosts;
    
    @Transactional
    public void keepHistory(@Observes @KeepHistory TaskInstance instance) {
        final TaskInstance previous = taskDAO.findBy(instance.getId());
        final History entity = new History();
        entity.setObjectType(HistoryObjectType.TASK_INSTANCE);
        entity.setObjectId(instance.getId());
        entity.setFrom(previous.getState().name());
        entity.setTo(instance.getState().name());
        entity.setExecutedBy(instance.getExecutedBy());
        entity.setExecutedIn(new Date());
        historyDAO.save(entity);
    }
}
