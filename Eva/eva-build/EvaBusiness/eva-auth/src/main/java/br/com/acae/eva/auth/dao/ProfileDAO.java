/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.dao;

import br.com.acae.eva.model.Permission;
import br.com.acae.eva.model.Profile;
import java.util.List;
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
    
    @Query(value = "select pm from Profile p join p.permissions pm "
                 + "where p.id = ?1 "
                 + "and pm.toView = ?2")
    QueryResult findPagePermission(final Long profileId, final String page);
    
    @Query(singleResult = SingleResultType.OPTIONAL)
    Profile findByNameEqual(final String name);
    
    @Query(value = "select pm from Profile p join p.permissions pm "
                 + "where p.id = ?1 "
                 + "and pm.toView is not null")
    List<Permission> findPagePermissions(final Long profileId);
}