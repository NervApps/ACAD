/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.context.validator;

import br.com.acae.eva.web.messages.login.LoginMessages;
import java.util.Arrays;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

/**
 *
 * @author Vitor
 */
@Named("usernameValidator") 
@RequestScoped
public class UsernameValidator {
    @Inject private LoginMessages messages;
    private final String[] notAllowed = {"admin"};
    
    public void validate(FacesContext ctx, UIComponent comp, Object value) {
        if (value != null) {
            final String login = (String) value;
            for (final String str : notAllowed) {
                if (login.toLowerCase().contains(str))
                    throw new ValidationException(buildMessage());
            }
        }
    }
    
    public String buildMessage() {
        final StringBuilder sb = new StringBuilder();
        return sb.append(messages.invalidLogin())
                 .append(". ")
                 .append(messages.invalidLogin(Arrays.toString(notAllowed)))
                 .toString();
    }
}