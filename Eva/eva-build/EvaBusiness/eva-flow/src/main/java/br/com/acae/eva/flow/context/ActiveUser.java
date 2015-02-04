/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.context;

import br.com.acae.eva.model.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@SessionScoped
public class ActiveUser implements Serializable {
    
    @Getter @Setter private User user;
}