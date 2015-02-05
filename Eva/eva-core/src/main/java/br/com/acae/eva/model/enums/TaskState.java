/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
public enum TaskState {
    NEW(true),
    TODO(true), 
    DOING(true),
    DONE(false),
    FAILED(false),
    CANCELED(false),
    BLOCKED(true);
    
    @Getter private final boolean pending;

    private TaskState(boolean pending) {
        this.pending = pending;
    }
    
    public static List<TaskState> getPendingStates() {
        List<TaskState> pending = new ArrayList();
        for (TaskState state : values()) {
            if (state.isPending())
                pending.add(state);
        }
        return pending;
    }
    
    public static List<TaskState> getFinishedStates() {
        List<TaskState> finished = new ArrayList();
        finished.addAll(Arrays.asList(values()));
        finished.removeAll(getPendingStates());
        return finished;
    }
}