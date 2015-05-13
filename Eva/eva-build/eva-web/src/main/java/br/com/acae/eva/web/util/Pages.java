/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.util;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

/**
 *
 * @author vitor
 */
@MessageBundle
public interface Pages {
    
    @MessageTemplate("login")
    String loginPage();
    @MessageTemplate("index")
    String mainPage();
}
