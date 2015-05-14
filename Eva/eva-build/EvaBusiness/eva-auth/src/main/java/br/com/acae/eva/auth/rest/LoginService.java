/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.rest;

import br.com.acae.eva.auth.dao.UserDAO;
import br.com.acae.eva.exception.StackTrace;
import br.com.acae.eva.model.User;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
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
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
    @Inject private UserDAO dao;
    @Inject @StackTrace(printStackTrace = true) private Event<Exception> event;
    
    @GET @Path("/login")
    public User get(@NotNull @QueryParam("user") String user, 
                    @NotNull @QueryParam("password") String password) {
        
        WebApplicationException ex;
        try {
            final User loaded = dao.findByLoginEqualAndPasswordEqual(user, password);
            if (loaded != null)
                return loaded;
            else
                ex = new WebApplicationException(Status.NOT_FOUND);
        } catch (Exception e) {
            event.fire(e);
            ex = new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        }
        throw ex;
    }
    
    @POST @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(User user) {
        WebApplicationException ex;
        try {
            final User loaded = dao.findByLoginEqualAndPasswordEqual(user.getLogin(), user.getPassword());
            if (loaded == null) {
                dao.save(user);
                return Response.status(Status.CREATED).build();
            } else {
                ex = new WebApplicationException(Status.CONFLICT);
            }
        } catch (Exception e) {
            event.fire(e);
            ex = new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        }
        throw ex;
    }
    
    @GET @Path("/admin")
    public User get() {
        final String user = "admin";
        return get(user);
    }
    
    @GET
    public User get(@QueryParam("user") String user) {
        WebApplicationException ex;
        try {
            final User found = dao.findByLoginEqual(user);
            if (found != null)
                return found;
            else
                ex = new WebApplicationException(Status.NOT_FOUND);
        } catch (Exception e) {
            event.fire(e);
            ex = new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        }
        throw ex;
    }
}