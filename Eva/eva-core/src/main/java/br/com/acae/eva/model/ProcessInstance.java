/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.model;

import br.com.acae.eva.model.enums.ProcessDefinition;
import br.com.acae.eva.model.enums.State;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "PROCESS_INSTANCE")
public class ProcessInstance implements Serializable {
    
    @Id
    @Column(name = "PROCESS_INSTANCE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Integer id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PROCESS_DEF", nullable = false)
    @Getter @Setter private ProcessDefinition def;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PROCESS_STATE", nullable = false)
    @Getter @Setter private State state;
    
    @ManyToOne
    @JoinColumn(name = "PARENT_PROCESS_INSTANCE_ID", referencedColumnName = "PROCESS_INSTANCE_ID")
    @Getter @Setter private ProcessInstance parent;
    
    @ManyToOne
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID", nullable = false)
    @Getter @Setter private Project project;

    public ProcessInstance() {}

    public ProcessInstance(ProcessDefinition def, State state, Project project) {
        this.def = def;
        this.state = state;
        this.project = project;
    }
}