/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task;

import br.com.acae.eva.flow.task.impl.qualifier.AlocarPorta;
import br.com.acae.eva.model.TaskDef;
import javax.enterprise.util.AnnotationLiteral;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
public enum TaskNames {
    ALOCAR_PORTA_ACESSO(new AnnotationLiteral<AlocarPorta>(){}, "Alocar Porta Acesso");
    
    @Getter private final AnnotationLiteral qualifier;
    @Getter private final String name;
    
    private TaskNames(final AnnotationLiteral qualifier, final String name) {
        this.qualifier = qualifier;
        this.name = name;
    }
    
    public static TaskNames getByTaskDef(final TaskDef def) {
        for (TaskNames t : values()) {
            if (t.name.equals(def.getName()))
                return t;
        }
        
        return null;
    }
}