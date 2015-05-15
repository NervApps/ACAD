/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.messages.rest;

import java.io.Serializable;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.message.Message;
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
        return parse(template);
    }
    
    public String translate(final int httpCode, final Serializable... params) {
        final String template = "{"+httpCode+".param}";
        return parse(template, params);
    }
    
    public String parse(final String template, final Serializable... params) {
        Message message = context.messageSource(source)
                                 .message()
                                 .template(template);
        
        if (params != null && params.length > 0)
            return message.argument(params)
                          .toString();
        else
            return message.toString();
    }
}