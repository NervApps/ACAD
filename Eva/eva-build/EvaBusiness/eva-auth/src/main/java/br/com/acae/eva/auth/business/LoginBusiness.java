/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.business;

import br.com.acae.eva.auth.dao.UserDAO; 
import br.com.acae.eva.exception.BusinessException;
import br.com.acae.eva.exception.StackTrace;
import br.com.acae.eva.model.User;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author vitor
 */
@RequestScoped
public class LoginBusiness {
    @Inject private UserDAO dao;
    
    @Transactional
    public void create(final User user) throws BusinessException {
        if (exists(user.getLogin())) {
            throw new BusinessException("Usuário já existe");
        } else {
            dao.save(user);
        }
    }
    
    public boolean exists(final String login) {
        return get(login) != null;
    }
    
    public User get(final String login) {
        return dao.findByLoginEqual(login);
    }
    
    public User get(final String login, final String password) {
        return dao.findByLoginEqualAndPasswordEqual(login, password);
    }
}
