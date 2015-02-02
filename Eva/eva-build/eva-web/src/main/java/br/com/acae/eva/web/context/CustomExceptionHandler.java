/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.context;

import br.com.acae.eva.web.messages.MessagesTranslator;
import br.com.acae.eva.web.util.Message;
import br.com.acae.eva.web.util.MessageType;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.ws.rs.WebApplicationException;
import org.apache.deltaspike.core.api.provider.BeanProvider;

/**
 *
 * @author Vitor
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {
    private final ExceptionHandler wrapped;
 
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }
 
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
 
    @Override
    public void handle() throws FacesException {
        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            final ExceptionQueuedEvent event = i.next();
            final ExceptionQueuedEventContext context =
                    (ExceptionQueuedEventContext) event.getSource();
 
            final FacesException t = (FacesException) context.getException();
            
            try {
                Throwable cause = t.getCause();
                while (cause != null && !(cause instanceof WebApplicationException)) {
                    cause = cause.getCause();
                }
                
                if (cause != null) {
                    final WebApplicationException ex = (WebApplicationException) cause;
                    putMessage(ex);
                }
            } finally {
                i.remove();
            }
        }
        getWrapped().handle();
    }
    
    private void putMessage(final WebApplicationException ex) {
        final MessagesTranslator msg = BeanProvider.getContextualReference(MessagesTranslator.class);
        final Message message = new Message(MessageType.ERROR, msg.translate(ex));
        
        final ExternalContext ext = getExternalContext();
        final Flash flash = ext.getFlash();
        flash.put("message", message);
    }
    
    private ExternalContext getExternalContext() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        return ctx.getExternalContext();
    }
}
