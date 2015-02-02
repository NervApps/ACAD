/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.beans.util;

import br.com.acae.eva.web.util.Message;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@Named @RequestScoped
public class MessageBean {
    
    @Getter private List<Message> messages;
    
    @PostConstruct
    public void init() {
        messages = new ArrayList<>();
    }
    
    public void putMessage(final Message message) {
        messages.add(message);
    }
}