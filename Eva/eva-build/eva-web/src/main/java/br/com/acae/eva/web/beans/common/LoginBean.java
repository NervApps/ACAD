/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.beans.common;

import br.com.acae.eva.connector.RestClient;
import br.com.acae.eva.connector.qualifier.Json;
import br.com.acae.eva.connector.hosts.AuthHosts;
import br.com.acae.eva.connector.hosts.FlowHosts;
import br.com.acae.eva.model.User;
import br.com.acae.eva.util.Hasher;
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
public class LoginBean extends ManagedBean {    
    
    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Getter @Setter private String email;
    @Getter @Setter private String password2;
    
    @Inject private UserLogged logged;
    @Inject private AuthHosts authHost;
    @Inject private FlowHosts flowHost;
    @Inject @Json private RestClient rest;
    @Inject private Hasher hasher;
    
    @PostConstruct
    public void init() {
        login = new String();
        password = new String();
    }
    
    public String doLogin() {
        final Map<String, String> params = new HashMap<>();
        params.put("user", login);
        params.put("taskId", hasher.sha1Hash(password));

        final User obj = rest.get(flowHost.runTask(), params, User.class);
        logged.login(obj);
        
        return "index?faces-redirect=true";
    }
    
    public void create() {
        final User user = new User();
        user.setLogin(login);
        user.setPassword(hasher.sha1Hash(password));
        user.setEmail(email);
        
        if (rest.post(authHost.newUser(), user) != null)
            info("Usuário criado com sucesso");
        else
            error("Erro ao criar usuário", "Contate o administrador");
    }
    
    public String doLogout() {
        logged.logout();
        return "index?faces-redirect=true";
    }
}