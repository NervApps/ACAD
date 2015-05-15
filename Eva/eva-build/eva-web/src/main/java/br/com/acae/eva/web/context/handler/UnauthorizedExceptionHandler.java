/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.context.handler;

import javax.ws.rs.NotAuthorizedException;

/**
 *
 * @author Vitor
 */
public class UnauthorizedExceptionHandler extends ContextExceptionHandler {
    
    @Override
    protected Class<NotAuthorizedException> type() {
        return NotAuthorizedException.class;
    }
}