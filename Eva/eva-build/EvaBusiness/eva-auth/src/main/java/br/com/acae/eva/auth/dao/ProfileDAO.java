/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.dao;

import br.com.acae.eva.model.Profile;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryResult;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;

/**
 *
 * @author Vitor
 */
@Repository
public interface ProfileDAO extends EntityRepository<Profile, Long> {
    
    @Query(value = "select pm from Profile p join p.permissions pm "
                 + "where p.id = ?1 "
                 + "and pm.name = ?2")
    QueryResult findPermission(final Long profileId, final String ruleName);
    
    @Query(singleResult = SingleResultType.OPTIONAL)
    Profile findByNameEqual(final String name);
}
