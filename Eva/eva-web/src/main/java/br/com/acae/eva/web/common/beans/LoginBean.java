/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.common.beans;

import br.com.nerv.eva.core.web.mb.util.ManagedBean;
import br.com.nerv.eva.core.web.stereotypes.Model;
import com.nerv.eva.core.cached.CachedUser;
import com.nerv.eva.core.enums.Severity;
import com.nerv.eva.core.exceptions.DAOException;
import com.nerv.eva.core.persistence.dao.UserDAO;
import com.nerv.eva.core.persistence.entity.User;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.Getter;
import org.joda.time.DateTime;

/**
 *
 * @author Vitor Ribeiro de Oliveira
 */
@Model
public class LoginBean extends ManagedBean {    
    @Getter private User user;
    @Inject private CachedUser cached;
    @Inject private UserDAO dao;
    
    @PostConstruct
    public void init() {
        user = new User();
    }
    
    public String doLoginTest() {
        return redirect("index");
    }
    
    public String doLogin() {
        User usr = dao.getUser(user.getLogin(), user.getPassword());

        if (usr == null) {
            addMessage("Usuário não encontrado", Severity.WARN);
            cached.clear();
        } else if (usr.isUserActive()) {            
            try {
                DateTime dt = new DateTime();
                if (usr.getFirstLogin() == null) {
                    usr.setFirstLogin(dt.toDate());    
                }

                usr.setLastLogin(dt.toDate());
                
                cached.put(dao.update(usr), true);

                return redirect("index");
            } catch (DAOException e) {
                addMessage("Erro ao efetuar login", e);
            }
        } else {
            addMessage("Usuário inativo", "Contate o administrador", Severity.ERROR);
            cached.clear();
        }
        
        return null;
    }
    
    public String doLogout() {
        cached.clear();
        return redirect("login");
    }
    
    public boolean isAdmin() {
        if (cached.isActive())
            return "Administrador".equals(cached.getUser().getProfile().getDescr());
        else
            return false;
    }
    
    public void changePassword() {
        User usr = dao.getUser(cached.getUser().getLogin());
        
        if (usr != null) {
            usr.setPassword(user.getPassword());

            try {
                dao.updatePassword(usr);
                addMessage("Senha alterada com sucesso");
            } catch (DAOException e) {
                addMessage("Erro ao atualizar a senha", e);
            }
        } else
            addMessage("Usuário não encontrado", Severity.WARN);
    }
}