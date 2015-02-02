/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth;

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

/**
 *
 * @author Vitor
 */
@RequestScoped
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class Login {
    
    @Inject private UserDAO dao;
    
    @GET @Path("/doLogin")
    public User doLogin(@QueryParam("user") String user,
            @QueryParam("password") String password) {
        
        try {
            final User loaded = dao.findByLoginEqualAndPasswordEqual(user, password);
            if (loaded != null)
                return loaded;
            else
                throw new WebApplicationException(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @POST @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public User create(User user) {
        try {
            final User loaded = dao.findByLoginEqualAndPasswordEqual(user.getLogin(), user.getPassword());
            if (loaded == null) {
                return dao.save(user);
            } else {
                throw new WebApplicationException(Response.Status.CONFLICT);
            }
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}