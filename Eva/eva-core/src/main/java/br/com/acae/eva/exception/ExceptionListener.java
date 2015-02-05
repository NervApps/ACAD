/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.exception;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

/**
 *
 * @author Vitor
 */
@Stateless
public class ExceptionListener {
    private static final Logger logger = Logger.getLogger("ExceptionLogger");
    
    @Asynchronous
    public void logException(@Observes @StackTrace(printStackTrace = false) Exception ex) {
        logger.log(Level.SEVERE, "Exception occurred: {0}", ex.getMessage());
    }
    
    @Asynchronous
    public void showStackTrace(@Observes @StackTrace(printStackTrace = true) Exception ex) {
        logException(ex);
        ex.printStackTrace(System.err);
    }
}