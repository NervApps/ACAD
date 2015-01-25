/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.connector;

import br.com.acae.eva.connector.exception.BusinessException;
import com.nerv.eva.core.persistence.entity.User;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class LoginService {
    
    private static final String PATH = "http://eva-auth:8080/eva-auth/rest";
    @Inject private JsonRestClient rest;
    
    public User getUser(final String login, final String password) throws BusinessException {
        final Map<String, String> params = new HashMap<>();
        params.put("login", login);
        params.put("password", password);
        
        final String endpoint = PATH.concat("/login/doLogin");
        
        try {
            return rest.get(endpoint, params, User.class);    
        } catch (WebApplicationException e) {
            throw new BusinessException(e);
        }
    }
    
    public boolean exists(final User user) throws BusinessException {
        return getUser(user.getLogin(), user.getPassword()) != null;
    }
}