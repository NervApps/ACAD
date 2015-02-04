/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.task;

import br.com.acae.eva.model.TaskInstance;
import br.com.acae.eva.model.enums.TaskState;

/**
 *
 * @author Vitor
 */
public abstract class UserTask extends AutomaticTask {

    @Override
    public void start(TaskInstance instance) {
        updateState(instance, TaskState.DOING);
    }
}