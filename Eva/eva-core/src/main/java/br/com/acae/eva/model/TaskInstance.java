/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.model;

import br.com.acae.eva.model.enums.TaskState;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "TASK_INSTANCE")
@EqualsAndHashCode(of = "id")
public class TaskInstance implements Serializable {
    
    @Id
    @Column(name = "TASK_INSTANCE_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "TASK_STATE")
    @Enumerated(EnumType.STRING)
    @Getter @Setter private TaskState state;
    
    @ManyToOne
    @JoinColumn(name = "TASK_DEF_ID", referencedColumnName = "TASK_DEF_ID")
    @Getter @Setter private TaskDef taskDef;
    
    @ManyToOne
    @JoinColumn(name = "PROCESS_INSTANCE_ID", referencedColumnName = "PROCESS_INSTANCE_ID")
    @Getter @Setter private ProcessInstance process;
}