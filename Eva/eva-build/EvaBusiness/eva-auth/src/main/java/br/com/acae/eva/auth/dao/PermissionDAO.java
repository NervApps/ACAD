/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.dao;

import br.com.acae.eva.model.Permission;
import java.util.List;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author vitor
 */
@Repository
public interface PermissionDAO extends EntityRepository<Permission, Long> {
    
    @Query(value = "select p.toView from Permission p where p.toView is not null")
    List<String> findBlockedViews();
}
