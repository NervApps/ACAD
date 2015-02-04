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
@Table(name = "TASK_ORDER")
public class TaskOrder implements Serializable {
    
    @Id
    @Column(name = "TASK_ORDER_ID")
    @Getter @Setter private Long id;
    
    @JoinColumn(name = "PREVIOUS_TASK_DEF_ID", referencedColumnName = "TASK_DEF_ID", nullable = false)
    @ManyToOne
    @Getter @Setter private TaskDef previous;
    
    @JoinColumn(name = "TASK_DEF_ID", referencedColumnName = "TASK_DEF_ID", nullable = false)
    @ManyToOne
    @Getter @Setter private TaskDef task;
}