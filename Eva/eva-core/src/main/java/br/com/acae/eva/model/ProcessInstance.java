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
@Table(name = "PROCESS_INSTANCE")
public class ProcessInstance implements Serializable {
    
    @Id
    @Column(name = "PROCESS_INSTANCE_ID")
    @Getter @Setter private Integer id;
}