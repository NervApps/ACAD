/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.context;

import br.com.acae.eva.connector.RestClient;
import br.com.acae.eva.connector.hosts.AuthHosts;
import br.com.acae.eva.connector.qualifier.Json;
import br.com.acae.eva.model.Permission;
import br.com.acae.eva.model.User;
import br.com.acae.eva.util.RestListWrapper;
import br.com.acae.eva.web.qualifiers.LoggedIn;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@SessionScoped
public class UserLogged implements Serializable {
    @Inject @Json private RestClient rest;
    @Inject private AuthHosts hosts;
    @Getter private final List<Permission> permissions = new ArrayList<>();
    private User user;
    
    @Produces @LoggedIn @Named("logged")
    public User getUser() {
        return user;
    }
    
    public void login(final User user) {
        this.user = user;
        if (this.user != null) {
            loadPermissions();
        }
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
    
    public boolean isAbleToView(final String page) {
        for (final Permission p : permissions) {
            if (page.equals(p.getToView()))
                return true;
        }
        return false;
    }
    
    private void invalidate() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final ExternalContext ext = ctx.getExternalContext();
        final HttpSession session = (HttpSession) ext.getSession(false);
        session.invalidate();
    }
    
    private void loadPermissions() {
        final Map<String, String> params = new HashMap<>();
        params.put("user", user.getLogin());
        
        final RestListWrapper wrapper = rest.get(hosts.pagePermissions(), params, RestListWrapper.class);
        final List values = wrapper.getValues();
        if (!values.isEmpty()) {
            permissions.addAll(values);
        }
    }
}