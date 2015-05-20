/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task;

import br.com.acae.eva.model.TaskInstance;

/**
 *
 * @author Vitor
 */
public interface Task {
    
    void start(TaskInstance instance);
    
    void doing(TaskInstance instance);
    
    void done(TaskInstance instance);
    
    void error(TaskInstance instance);
    
    void block(TaskInstance instance);
    
    void cancel(TaskInstance instance);
}