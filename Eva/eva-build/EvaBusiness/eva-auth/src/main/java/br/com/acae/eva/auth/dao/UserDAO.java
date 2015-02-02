/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.dao;

import br.com.acae.eva.model.User;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@Repository
public interface UserDAO extends EntityRepository<User, Long> {
    
    @Transactional
    User findByLoginEqualAndPasswordEqual(final String login, final String password);
}
