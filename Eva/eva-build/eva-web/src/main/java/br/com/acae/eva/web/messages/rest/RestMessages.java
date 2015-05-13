/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.messages.rest;

import javax.inject.Inject;
import org.apache.deltaspike.core.api.message.MessageContext;

/**
 *
 * @author Vitor
 */
public class RestMessages {
    private final String source = RestMessages.class.getCanonicalName();
    @Inject private MessageContext context;
    
    public String translate(final int httpCode) {
        final String template = "{"+httpCode+"}";
        return context.messageSource(source)
                      .message()
                      .template(template)
                      .toString();
    }
}