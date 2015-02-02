/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.context;

import br.com.acae.eva.connector.exception.BusinessException;
import javax.enterprise.event.Observes;

/**
 *
 * @author Vitor
 */
public class BusinessExceptionHandler {
    
    public void saveLog(@Observes BusinessException e) {
        System.out.println("Salvando o log");
    }
    
    public void showError(@Observes BusinessException e) {
        System.out.println("Salvando o log");
    }
    
}
