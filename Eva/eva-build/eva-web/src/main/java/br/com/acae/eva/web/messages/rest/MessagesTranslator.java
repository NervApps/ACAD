/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.messages.rest;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Vitor
 */
@ApplicationScoped
public class MessagesTranslator implements Serializable {
    
    private ResourceBundle httpCodes;
    
    @PostConstruct
    public void init() {
        httpCodes = ResourceBundle.getBundle("http-codes");
    }
    
    public String translate(final WebApplicationException e) {
        return httpCodes.getString(String.valueOf(e.getResponse().getStatus()));
    }
}