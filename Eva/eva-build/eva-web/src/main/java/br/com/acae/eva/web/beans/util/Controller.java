/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.beans.util;

import br.com.acae.eva.connector.exception.BusinessException;
import br.com.acae.eva.web.util.Message;
import br.com.acae.eva.web.util.MessageType;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
public abstract class Controller {
    
    @Inject @Any private Event<BusinessException> event;
    
    protected void showMessage(final BusinessException e) {
        showMessage(e, null);
    }
    
    protected void showMessage(final String title, final BusinessException detail) {
        final Message message = new Message(MessageType.ERROR, title);
        message.setDetail(detail.getWebServiceTranslatedMessage());
        
        putMessage(message);
        event.fire(detail);
    }
    
    protected void showMessage(final BusinessException e, final String detail) {
        final Message message = new Message(MessageType.ERROR, e.getWebServiceTranslatedMessage());
        message.setDetail(detail);
        
        putMessage(message);
        event.fire(e);
    }
    
    private void putMessage(final Message message) {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final ExternalContext ext = ctx.getExternalContext();
        final Flash flash = ext.getFlash();
        flash.put("message", message);
    }
}