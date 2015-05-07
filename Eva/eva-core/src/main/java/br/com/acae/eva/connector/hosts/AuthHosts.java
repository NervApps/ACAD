/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.connector.hosts;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageTemplate;

/**
 *
 * @author Vitor
 */
@MessageBundle
public interface AuthHosts {
    
    @MessageTemplate("http://localhost:8080/eva-auth/rest/auth/login")
    String login();
    @MessageTemplate("http://localhost:8080/eva-auth/rest/auth")
    String getUser();
    @MessageTemplate("http://localhost:8080/eva-auth/rest/auth/create")
    String newUser();
    @MessageTemplate("http://localhost:8080/eva-auth/rest/login/admin")
    String admin();
}