/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.context;

import br.com.acae.eva.connector.exception.BusinessException;
import java.io.Serializable;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;

/**
 *
 * @author Vitor
 */
@Singleton
public class BusinessExceptionHandler implements Serializable {
    
    @Asynchronous
    public void saveLog(@Observes BusinessException e) throws InterruptedException {
        System.out.println("Salvando o log: " + e.getWebServiceTranslatedMessage());
    }
}