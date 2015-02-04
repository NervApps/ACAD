/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.rest;

import br.com.acae.eva.connector.RestClient;
import br.com.acae.eva.connector.hosts.AuthHosts;
import br.com.acae.eva.connector.qualifier.Json;
import br.com.acae.eva.flow.context.ActiveUser;
import br.com.acae.eva.flow.dao.TaskInstanceDAO;
import br.com.acae.eva.flow.task.listener.TaskExecutorDispatcher;
import br.com.acae.eva.model.TaskInstance;
import br.com.acae.eva.model.User;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@RequestScoped
@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskService {
    
    @Inject private AuthHosts host;
    @Inject private TaskInstanceDAO instanceDAO;
    @Inject private TaskExecutorDispatcher executor;
    @Inject @Json private RestClient client;
    @Inject private ActiveUser active;
    
    @GET @Path("/run")
    public Response run(@QueryParam("user") String user, @QueryParam("taskId") String taskId) {
        try {
            putInSession(user);
            final TaskInstance instance = loadTask(taskId);
            executor.run(instance);
            return Response.ok().build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GET @Path("/execute")
    public Response execute(@QueryParam("user") String user, @QueryParam("taskId") String taskId) {
        try {
            putInSession(user);
            final TaskInstance instance = loadTask(taskId);
            executor.execute(instance);
            return Response.ok().build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Transactional
    private TaskInstance loadTask(final String taskId) {
        final TaskInstance find = instanceDAO.findBy(Long.parseLong(taskId));
        
        if (find != null)
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        else
            return find;
    }
    
    private void putInSession(final String userName) {
        User get;
        if (userName == null || userName.isEmpty()) {
            get = client.get(host.systemUser(), User.class);
        } else {
            final Map<String, String> params = new HashMap<>();
            params.put("user", userName);

            get = client.get(host.getUser(), params, User.class);
            if (get == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        }
        
        active.setUser(get);
    }
}