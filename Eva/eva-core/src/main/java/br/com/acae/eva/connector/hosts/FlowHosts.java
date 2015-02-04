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
public interface FlowHosts {
    
    @MessageTemplate("http://localhost:8080/eva-flow/rest/task/run")
    String runTask();
    @MessageTemplate("http://localhost:8080/eva-flow/rest/task/execute")
    String executeTask();
}