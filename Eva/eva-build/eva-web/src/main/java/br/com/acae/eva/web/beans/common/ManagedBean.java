/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.beans.common;

import br.com.acae.eva.web.util.Message;
import br.com.acae.eva.web.util.MessageType;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Vitor
 */
public abstract class ManagedBean {
    
    protected void info(final String title, final String detail) {
        final Message msg = new Message(MessageType.INFO, title, detail);
        msg.show();
    }
    
    protected void info(final String title) {
        final Message msg = new Message(MessageType.INFO, title);
        msg.show();
    }
    
    protected void warn(final String title, final String detail) {
        final Message msg = new Message(MessageType.WARN, title, detail);
        msg.show();
    }
    
    protected void warn(final String title) {
        final Message msg = new Message(MessageType.WARN, title);
        msg.show();
    }
    
    protected void error(final String title, final String detail) {
        final Message msg = new Message(MessageType.ERROR, title, detail);
        msg.show();
    }
    
    protected void error(final String title) {
        final Message msg = new Message(MessageType.ERROR, title);
        msg.show();
    }
    
    protected String redirect(final String page) {
        return page.concat("?faces-redirect=true");
    }
    
    protected void putParam(final String paramName, final Object value) {
        getFlash().put(paramName, value);
    }
    
    protected <T> T getParam(final String paramName, final Class<T> type) {
        return (T) getFlash().get(type);
    }
    
    private ExternalContext getExternalContext() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        return ctx.getExternalContext();
    }
    
    private Flash getFlash() {
        return getExternalContext().getFlash();
    }
}