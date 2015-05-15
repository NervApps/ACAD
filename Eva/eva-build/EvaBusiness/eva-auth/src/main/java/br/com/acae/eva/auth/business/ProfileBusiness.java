/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.business;

import br.com.acae.eva.auth.dao.ProfileDAO;
import br.com.acae.eva.model.Profile;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author vitor
 */
@RequestScoped
public class ProfileBusiness {
    @Inject private ProfileDAO profileDAO;
    
    public boolean hasPermission(final Profile profile, final String ruleName) {
        if (profile != null)
            return profileDAO.findPermission(profile.getId(), ruleName).count() > 0;
        else
            return false;
    }
    
    public Profile find(final String name) {
        return profileDAO.findByNameEqual(name);
    }
}