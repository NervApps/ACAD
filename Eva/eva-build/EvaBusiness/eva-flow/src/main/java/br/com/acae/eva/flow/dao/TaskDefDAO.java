/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.dao;

import br.com.acae.eva.model.TaskDef;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;

/**
 *
 * @author Vitor
 */
@Repository
public interface TaskDefDAO extends EntityRepository<TaskDef, Long> {
    
    @Query(singleResult = SingleResultType.OPTIONAL)
    TaskDef findByNameEqual(final String name);
}
