/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.common.beans;

import com.nerv.eva.core.cached.CachedUser;
import com.nerv.eva.core.persistence.dao.UserDAO;
import com.nerv.eva.core.persistence.entity.User;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.Getter;

/**
 *
 * @author Vitor Ribeiro de Oliveira
 */
@Named @RequestScoped
public class LoginBean {    
    @Getter private User user;
    @Inject private CachedUser cached;
    @Inject private UserDAO dao;
    
    @PostConstruct
    public void init() {
        user = new User();
    }
    
    public String doLogin() {
        return "index?faces-redirect=true";
    }
    
    public String doLogout() {
        invalidate();
        cached.clear();
        return "index?faces-redirect=true";
    }
    
    public boolean isAdmin() {
        if (cached.isActive())
            return "Administrador".equals(cached.getUser().getProfile().getDescr());
        else
            return false;
    }
    
    private void invalidate() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final ExternalContext ext = ctx.getExternalContext();
        final HttpSession session = (HttpSession) ext.getSession(false);
        session.invalidate();
    }
}