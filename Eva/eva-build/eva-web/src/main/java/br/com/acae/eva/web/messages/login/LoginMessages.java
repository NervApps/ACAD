/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.messages.login;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

/**
 *
 * @author vitor
 */
@MessageBundle
public interface LoginMessages {
    
    @MessageTemplate("{user.create}")
    String created();
    @MessageTemplate("{user.create.error}")
    String errorCreate();
    @MessageTemplate("{user.password.unmatch}")
    String unmatchPassword();
    @MessageTemplate("{user.login.invalid}")
    String invalidLogin();
    @MessageTemplate("{user.login.invalid.detail}")
    String invalidLoginDetail(final String detail);
}