/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "PROJECT")
public class Project implements Serializable {
    
    @Id
    @Column(name = "PROJECT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Integer id;
    
    @Column(name = "NAME")
    @Getter @Setter private String name;
}