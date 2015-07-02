/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.context.handler;

import javax.enterprise.event.Observes;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Vitor
 */
public abstract class ContextExceptionHandler {
    
    public void handle(@Observes FacesException e) {
        Throwable cause = e.getCause();
        
        while(cause != null && !type().isInstance(cause)) {
            cause = cause.getCause();
        }
        
        if (cause != null) {
            final String message = getTranslateMessage(cause);
            putInContext(message);
        }
    }
    
    private void putInContext(final String message) {
        final FacesMessage msg = new FacesMessage(message);
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        
        final FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, msg);
    }
    
    protected abstract Class<? extends Throwable> type();
    
    protected String getTranslateMessage(final Throwable exception) {
        return exception.getMessage();
    }
}
