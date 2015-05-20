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
import br.com.acae.eva.flow.task.business.ProcessBusiness;
import br.com.acae.eva.flow.task.business.TaskBusiness;
import br.com.acae.eva.model.ProcessInstance;
import br.com.acae.eva.model.TaskDef;
import br.com.acae.eva.model.TaskInstance;
import br.com.acae.eva.model.User;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Vitor
 */
@RequestScoped
@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskService {
    @Inject private TaskBusiness business;
    @Inject private ProcessBusiness processBusiness;
    @Inject private AuthHosts host;
    @Inject @Json private RestClient client;
    @Inject @StackTrace private Event<Exception> event;
    
    @POST
    public TaskInstance post(@NotNull @QueryParam("taskName") String taskName,
                             @NotNull @QueryParam("processId") Long processId,
                             @QueryParam("user") String user) {
        
        WebApplicationException ex;
        try {
            final TaskDef def = business.getTask(taskName);
            if (def != null) {
                final ProcessInstance process = processBusiness.find(processId);
                if (process != null)
                    return business.create(def, process);
                else
                    ex = new WebApplicationException(Status.NOT_FOUND);
            } else
                ex = new WebApplicationException(Status.NOT_FOUND);
        } catch (Exception e) {
            event.fire(e);
            ex = new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        }
        throw ex;
    }
    
    @POST @Path("/createAndRun")
    public Response createAndRun(@NotNull @QueryParam("taskName") String taskName,
                                 @NotNull @QueryParam("processId") Long processId,
                                 @QueryParam("user") String user) {
        
        final TaskInstance instance = post(taskName, processId, user);
        run(user, instance.getId().toString());
        return Response.ok().build();
    }
    
    @GET @Path("/run")
    public Response run(@QueryParam("user") String user, 
                        @NotNull @QueryParam("taskId") String taskId) {
        try {
            final TaskInstance instance = loadTask(taskId);
            instance.setExecutedBy(loadUser(user));
            business.run(instance);
            return Response.ok().build();
        } catch (Exception e) {
            event.fire(e);
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GET @Path("/execute")
    public Response execute(@QueryParam("user") String user, 
                            @NotNull @QueryParam("taskId") String taskId) {
        try {
            final TaskInstance instance = loadTask(taskId);
            instance.setExecutedBy(loadUser(user));
            business.execute(instance);
            return Response.ok().build();
        } catch (Exception e) {
            event.fire(e);
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    private TaskInstance loadTask(final String taskId) {
        final TaskInstance find = business.find(Long.parseLong(taskId));
        if (find != null)
            throw new WebApplicationException(Status.NOT_FOUND);
        else
            return find;
    }
    
    private User loadUser(final String userName) {
        User user;
        if (userName == null || userName.isEmpty()) {
            user = client.get(host.admin(), User.class);
        } else {
            final Map<String, String> params = new HashMap<>();
            params.put("user", userName);

            user = client.get(host.getUser(), params, User.class);
            if (user == null) {
                throw new WebApplicationException(Status.NOT_FOUND);
            }
        }
        
        return user;
    }
}