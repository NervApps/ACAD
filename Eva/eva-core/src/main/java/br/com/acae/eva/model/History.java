/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.model;

import br.com.acae.eva.model.enums.HistoryObjectType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "HISTORY")
public class History implements Serializable {
    
    @Id
    @Column(name = "HISTORY_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "STATE_FROM", nullable = false)
    @Getter @Setter private String from;
    
    @Column(name = "STATE_TO", nullable = false)
    @Getter @Setter private String to;
    
    @ManyToOne
    @JoinColumn(name = "EXECUTED_BY", referencedColumnName = "USER_ID", nullable = false)
    @Getter @Setter private User executedBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXECUTED_IN")
    @Getter @Setter private Date executedIn;
    
    @Column(name = "OBJECT_ID")
    @Getter @Setter private Long objectId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "OBJECT_TYPE")
    @Getter @Setter private HistoryObjectType objectType;
}