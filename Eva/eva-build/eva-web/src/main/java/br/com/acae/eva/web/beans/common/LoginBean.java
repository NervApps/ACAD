/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.beans.common;

import br.com.acae.eva.connector.RestClient;
import br.com.acae.eva.connector.qualifier.Json;
import br.com.acae.eva.connector.hosts.RestHosts;
import br.com.acae.eva.model.User;
import br.com.acae.eva.web.context.UserLogged;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor Ribeiro de Oliveira
 */
@Named @RequestScoped
public class LoginBean {    
    
    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Inject private UserLogged logged;
    @Inject private RestHosts host;
    @Inject @Json private RestClient rest;
    
    @PostConstruct
    public void init() {
        login = new String();
        password = new String();
    }
    
    public String doLogin() {
        final Map<String, String> params = new HashMap<>();
        params.put("login", login);
        params.put("password", password);

        final User obj = rest.get(host.login(), params, User.class);
        logged.login(obj);
        
        return "index?faces-redirect=true";
    }
    
    public String doLogout() {
        logged.logout();
        return "index?faces-redirect=true";
    }
}