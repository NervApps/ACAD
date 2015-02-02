/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.connector.service;

import br.com.acae.eva.connector.service.hosts.RestHosts;
import br.com.acae.eva.connector.RestClient;
import br.com.acae.eva.connector.exception.BusinessException;
import br.com.acae.eva.connector.qualifier.Json;
import br.com.acae.eva.model.User;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class LoginService {
    
    @Inject private RestHosts host;
    @Inject @Json private RestClient rest;
    
    public User getUser(final String login, final String password) {
        final Map<String, String> params = new HashMap<>();
        params.put("login", login);
        params.put("password", password);
        
        final String endpoint = host.auth().concat("doLogin");
        
        try {
            return rest.get(endpoint, params, User.class);    
        } catch (WebApplicationException e) {
            throw new BusinessException(e);
        }
    }
    
    public boolean exists(final String user, final String password) {
        return getUser(user, password) != null;
    }
}