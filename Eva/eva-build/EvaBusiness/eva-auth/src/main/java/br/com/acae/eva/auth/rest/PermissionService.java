/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.rest;

import br.com.acae.eva.auth.business.LoginBusiness;
import br.com.acae.eva.auth.business.ProfileBusiness;
import br.com.acae.eva.model.User;
import javax.enterprise.context.RequestScoped;
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
 * @author vitor
 */
@RequestScoped
@Path("/permission")
@Produces(MediaType.APPLICATION_JSON)
public class PermissionService {
    @Inject private LoginBusiness loginBusiness;
    @Inject private ProfileBusiness profileBusiness;
    
    @GET
    public Response get(@NotNull @QueryParam("name") String name,
                        @NotNull @QueryParam("user") String user) {
        try {
            final User loaded = loginBusiness.get(user);
            if (loaded != null) {
                if (profileBusiness.hasPermission(loaded.getProfile(), name))
                    return Response.status(Status.ACCEPTED).build();
                else 
                    throw new WebApplicationException(Status.UNAUTHORIZED);
            } else
                throw new WebApplicationException(Status.NOT_FOUND);
        } catch (Exception e) {
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }
    }
}
