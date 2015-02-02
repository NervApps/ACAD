/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @Getter @Setter private Long id;
    
    @Column(name = "LOGIN", nullable = false) 
    @Getter @Setter private String login;
    
    @Column(name = "PASSWORD", nullable = false)
    @Getter @Setter private String password;
    
    @Column(name = "EMAIL", nullable = false)
    @Getter @Setter private String email;
}