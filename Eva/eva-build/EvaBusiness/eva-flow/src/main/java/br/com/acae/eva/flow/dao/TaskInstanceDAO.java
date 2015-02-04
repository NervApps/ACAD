/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.dao;

import br.com.acae.eva.model.ProcessInstance;
import br.com.acae.eva.model.TaskDef;
import br.com.acae.eva.model.TaskInstance;
import java.util.List;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@Repository
public interface TaskInstanceDAO extends EntityRepository<TaskInstance, Long> {
    
    @Transactional
    @Query(value = "SELECT t.taskDef FROM TaskInstance t "
                 + "WHERE t.taskDef in (?1) "
                 + "AND t.process = ?2 "
                 + "AND t.taskState not in ('FINISHED', 'FAILED')")
    List<TaskDef> getPendingInProcess(final List<TaskDef> tasks, final ProcessInstance process);
    
}
