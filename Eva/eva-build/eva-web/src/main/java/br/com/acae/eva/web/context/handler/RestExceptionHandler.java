/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.context.handler;

import br.com.acae.eva.web.messages.rest.MessagesTranslator;
import javax.ws.rs.WebApplicationException;
import org.apache.deltaspike.core.api.provider.BeanProvider;

/**
 *
 * @author Vitor
 */
public class RestExceptionHandler extends ContextExceptionHandler {

    @Override
    protected Class<WebApplicationException> type() {
        return WebApplicationException.class;
    }

    @Override
    protected String getTranslateMessage(Throwable ex) {
        final WebApplicationException e = (WebApplicationException) ex;
        final MessagesTranslator msg = BeanProvider.getContextualReference(MessagesTranslator.class);
        return msg.translate(e);
    }
}