/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.context.interceptor;

import br.com.acae.eva.connector.RestClient;
import br.com.acae.eva.connector.hosts.AuthHosts;
import br.com.acae.eva.connector.qualifier.Json;
import br.com.acae.eva.model.User;
import br.com.acae.eva.web.context.interceptor.qualifier.Restrict;
import br.com.acae.eva.web.messages.rest.RestMessages;
import br.com.acae.eva.web.qualifiers.LoggedIn;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author vitor
 */
@Interceptor @Restrict
public class OperationPermissionListener {
    private final int unauthorizedStatus = 401;
    @Inject private AuthHosts hosts;
    @Inject @Json private RestClient rest;
    @Inject @LoggedIn private User logged;
    @Inject private RestMessages messages;
    
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception {
        final Method operation = ctx.getMethod();
        if (operation.isAnnotationPresent(Restrict.class)) {
            final Restrict annotation = operation.getAnnotation(Restrict.class);
            final String name = annotation.ruleName();
            
            if (!name.isEmpty()) {
                callService(name);
            }
        }
        return ctx.proceed();
    }
    
    private void callService(final String name) throws Exception {
        final String user = logged.getLogin();
        final Map<String, String> params = new ConcurrentHashMap<>();
        params.put("name", name);
        params.put("user", user);

        try {
            rest.get(hosts.hasPermission(), params);
        } catch (WebApplicationException e) {
            if (unauthorizedStatus == e.getResponse().getStatus())
                throw new NotAuthorizedException(messages.translate(unauthorizedStatus, user));
            else
                throw e;
        }
    }
}