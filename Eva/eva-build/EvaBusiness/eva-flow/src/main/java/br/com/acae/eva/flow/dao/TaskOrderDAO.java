/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.dao;

import br.com.acae.eva.model.TaskDef;
import br.com.acae.eva.model.TaskOrder;
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
public interface TaskOrderDAO extends EntityRepository<TaskOrder, Long> {
    
    @Transactional
    @Query(value = "SELECT t.previous FROM TaskOrder t WHERE t.task = ?1")
    List<TaskDef> getPrevious(final TaskDef task);
    
    @Transactional
    @Query(value = "SELECT t.task FROM TaskOrder t WHERE t.previous = ?1")
    List<TaskDef> getNexts(final TaskDef task);
}