/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.business;

import br.com.acae.eva.auth.dao.PermissionDAO;
import br.com.acae.eva.auth.dao.ProfileDAO;
import br.com.acae.eva.model.Permission;
import br.com.acae.eva.model.Profile;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author vitor
 */
@RequestScoped
public class ProfileBusiness {
    @Inject private ProfileDAO profileDAO;
    @Inject private PermissionDAO permissionDAO;
    
    public boolean hasPermission(final Profile profile, final String ruleName) {
        if (profile != null)
            return profileDAO.findPermission(profile.getId(), ruleName).count() > 0;
        else
            return false;
    }
    
    public Profile find(final String name) {
        return profileDAO.findByNameEqual(name);
    }
    
    public List<String> getBlockedViews() {
        return permissionDAO.findBlockedViews();
    }
    
    public List<Permission> getPagePermissions(final Profile profile) {
        if (profile != null)
            return profileDAO.findPagePermissions(profile.getId());
        else
            return new ArrayList<>();
    }
}