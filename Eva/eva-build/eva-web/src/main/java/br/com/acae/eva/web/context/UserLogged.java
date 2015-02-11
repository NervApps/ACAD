/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.context;

import br.com.acae.eva.model.User;
import br.com.acae.eva.web.qualifiers.LoggedIn;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vitor
 */
@SessionScoped
public class UserLogged implements Serializable {
    
    private User user;
    
    @Produces @LoggedIn @Named("logged")
    public User getUser() {
        return user;
    }
    
    public void login(final User user) {
        this.user = user;
    }
    
    public void logout() {
        login(null);
        invalidate();
    }
    
    public boolean isActive() {
        return getUser() != null;
    }
    
    public boolean isInactive() {
        return !isActive();
    }
    
    private void invalidate() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final ExternalContext ext = ctx.getExternalContext();
        final HttpSession session = (HttpSession) ext.getSession(false);
        session.invalidate();
    }
}