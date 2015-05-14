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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "TASK_DEF")
@EqualsAndHashCode(of = "id")
public class TaskDef implements Serializable {
    @Id
    @Column(name = "TASK_DEF_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;
    
    @Column(name = "TASK_NAME", nullable = false)
    @Getter @Setter private String name;
}
