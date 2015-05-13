/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.beans.common;

import br.com.acae.eva.connector.RestClient;
import br.com.acae.eva.connector.qualifier.Json;
import br.com.acae.eva.connector.hosts.AuthHosts;
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
    
    @Inject private UserLogged logged;
    @Inject private AuthHosts authHost;
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
        params.put("password", hasher.sha1Hash(password));

        final User obj = rest.get(authHost.login(), params, User.class);
        logged.login(obj);
        
        return redirect("index");
    }
    
    public void create() {
        final User user = new User();
        user.setLogin(login);
        user.setPassword(hasher.sha1Hash(password));
        user.setEmail(email);
        
        if (rest.post(authHost.newUser(), user) != null)
            info("Usu�rio criado com sucesso");
        else
            error("Erro ao criar usu�rio", "Contate o administrador");
    }
    
    public String doLogout() {
        logged.logout();
        return redirect("login");
    }
}