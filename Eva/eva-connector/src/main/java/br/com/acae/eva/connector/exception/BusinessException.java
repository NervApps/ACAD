/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.connector.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


/**
 *
 * @author Vitor
 */
public class BusinessException extends Exception {

    public BusinessException(WebApplicationException cause) {
        super(cause);
    }
    
    public String getWebServiceTranslatedMessage() {
        final WebApplicationException ex = (WebApplicationException) getCause();
        final Response resp = ex.getResponse();
        
        if (Status.NOT_FOUND.getStatusCode() == resp.getStatus()) {
            return "Não encontrado";
        } else {
            return "Erro interno no servidor. Contate o adminstrador";
        }
    }
}