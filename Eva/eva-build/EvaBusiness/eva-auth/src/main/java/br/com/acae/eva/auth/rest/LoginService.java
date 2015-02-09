/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.rest;

import br.com.acae.eva.auth.dao.UserDAO;
import br.com.acae.eva.model.User;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginService {
    
    @Inject private UserDAO dao;
    
    @GET @Path("/doLogin")
    public User doLogin(@QueryParam("user") String user,
            @QueryParam("password") String password) {
        
        try {
            final User loaded = dao.findByLoginEqualAndPasswordEqual(user, password);
            if (loaded != null)
                return loaded;
            else
                throw new WebApplicationException(Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GET @Path("/getUser")
    public User getUser(@QueryParam("user") String user) {
        if (user == null)
            throw new WebApplicationException(Status.BAD_REQUEST);
        
        try {
            final User loaded = dao.findBy(user);
            if (loaded != null)
                return loaded;
            else
                throw new WebApplicationException(Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @POST @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(User user) {
        try {
            final User loaded = dao.findByLoginEqualAndPasswordEqual(user.getLogin(), user.getPassword());
            if (loaded == null) {
                dao.save(user);
                return Response.status(Status.CREATED).build();
            } else {
                throw new WebApplicationException(Status.CONFLICT);
            }
        } catch (Exception e) {
            throw new WebApplicationException(e, Status.INTERNAL_SERVER_ERROR);
        }
    }
}