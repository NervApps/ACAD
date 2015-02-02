/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.connector.exception;

import java.util.ResourceBundle;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


/**
 *
 * @author Vitor
 */
public class BusinessException extends Exception {
    
    private static final String HTTP_CODES = "br.com.acae.eva.messages.http-codes.properties";
    private final ResourceBundle bundle;

    public BusinessException(WebApplicationException cause) {
        super(cause);
        bundle = ResourceBundle.getBundle(HTTP_CODES);
    }
    
    public String getWebServiceTranslatedMessage() {
        final WebApplicationException ex = (WebApplicationException) getCause();
        final Response resp = ex.getResponse();
        
        return bundle.getString(String.valueOf(resp.getStatus()));
    }
}