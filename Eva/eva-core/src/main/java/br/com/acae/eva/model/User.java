/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {
    
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;
    
    @Column(name = "LOGIN", nullable = false) 
    @Getter @Setter private String login;
    
    @Column(name = "PASSWORD", nullable = false)
    @Getter @Setter private String password;
    
    @Column(name = "EMAIL", nullable = false)
    @Getter @Setter private String email;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROFILE_ID", referencedColumnName = "PROFILE_ID")
    @Getter @Setter private Profile profile;
    
    public void toLowerCaseLoginAndPassword() {
        if (login != null)
            this.login = login.toLowerCase();
        
        if (password != null)
            this.password = password.toLowerCase();
    }
}