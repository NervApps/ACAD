/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.flow.rest;

import br.com.acae.eva.connector.RestClient;
import br.com.acae.eva.connector.hosts.AuthHosts;
import br.com.acae.eva.connector.qualifier.Json;
import br.com.acae.eva.exception.StackTrace;
import br.com.acae.eva.flow.dao.TaskInstanceDAO;
import br.com.acae.eva.flow.task.listener.TaskExecutorDispatcher;
import br.com.acae.eva.model.TaskInstance;
import br.com.acae.eva.model.User;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
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
    @Inject @StackTrace(printStackTrace = true) private Event<Exception> event;
    
    @GET @Path("/run")
    public Response run(@QueryParam("user") String user, @QueryParam("taskId") String taskId) {
        try {
            final TaskInstance instance = loadTask(taskId);
            instance.setExecutedBy(loadUser(user));
            executor.run(instance);
            return Response.ok().build();
        } catch (Exception e) {
            event.fire(e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GET @Path("/execute")
    public Response execute(@QueryParam("user") String user, @QueryParam("taskId") String taskId) {
        try {
            final TaskInstance instance = loadTask(taskId);
            instance.setExecutedBy(loadUser(user));
            executor.execute(instance);
            return Response.ok().build();
        } catch (Exception e) {
            event.fire(e);
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
    
    private User loadUser(final String userName) {
        User user;
        if (userName == null || userName.isEmpty()) {
            user = client.get(host.systemUser(), User.class);
        } else {
            final Map<String, String> params = new HashMap<>();
            params.put("user", userName);

            user = client.get(host.getUser(), params, User.class);
            if (user == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        }
        
        return user;
    }
}