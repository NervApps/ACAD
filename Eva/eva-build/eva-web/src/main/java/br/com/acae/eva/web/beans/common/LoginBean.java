/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.beans.common;

import br.com.acae.eva.connector.exception.BusinessException;
import br.com.acae.eva.connector.service.LoginService;
import br.com.acae.eva.model.User;
import br.com.acae.eva.web.beans.util.Controller;
import br.com.acae.eva.web.context.UserLogged;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor Ribeiro de Oliveira
 */
@Named @RequestScoped
public class LoginBean extends Controller {    
    
    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Inject private LoginService service;
    @Inject private UserLogged logged;
    
    @PostConstruct
    public void init() {
        login = new String();
        password = new String();
    }
    
    public String doLogin() {
        try {
            final User obj = service.getUser(login, password);
            logged.login(obj);
        } catch (BusinessException e) {
            showMessage(e);
        }
        
        return "index?faces-redirect=true";
    }
    
    public String doLogout() {
        logged.logout();
        return "index?faces-redirect=true";
    }
}