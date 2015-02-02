/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.beans.common;

import br.com.acae.eva.web.util.Message;
import br.com.acae.eva.web.util.MessageType;

/**
 *
 * @author Vitor
 */
public abstract class ManagedBean {
    
    protected void infoMessage(final String title, final String detail) {
        final Message msg = new Message(MessageType.INFO, title, detail);
        msg.show();
    }
    
    protected void infoMessage(final String title) {
        final Message msg = new Message(MessageType.INFO, title);
        msg.show();
    }
    
    protected void warnMessage(final String title, final String detail) {
        final Message msg = new Message(MessageType.WARN, title, detail);
        msg.show();
    }
    
    protected void warnMessage(final String title) {
        final Message msg = new Message(MessageType.WARN, title);
        msg.show();
    }
    
    protected void errorMessage(final String title, final String detail) {
        final Message msg = new Message(MessageType.ERROR, title, detail);
        msg.show();
    }
    
    protected void errorMessage(final String title) {
        final Message msg = new Message(MessageType.ERROR, title);
        msg.show();
    }
}