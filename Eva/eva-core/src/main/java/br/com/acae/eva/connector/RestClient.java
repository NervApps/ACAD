/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.connector;

import java.util.Map;
import java.util.Map.Entry;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Vitor
 */
public abstract class RestClient {
    private final Client client;

    public RestClient() {
        this.client = ClientBuilder.newClient();
    }
    
    public Response get(final String url) throws WebApplicationException {
        return target(url).get();
    }
    
    public Response get(final String url, final Map<String, String> urlParams) 
            throws WebApplicationException {
        final String endpoint = buildUrl(url, urlParams);
        return get(endpoint);
    }
    
    public <T> T get(final String url, final Map<String, String> urlParams, 
            final Class<T> type) throws WebApplicationException {
        
        final String endpoint = buildUrl(url, urlParams);
        return get(endpoint, type);
    }
    
    public <T> T get(final String url, final Class<T> type) 
            throws WebApplicationException {
        return target(url).get(type);
    }
    
    public <T> T get(final String url, final GenericType<T> type) 
            throws WebApplicationException {
        return target(url).get(type);
    }
    
    public <T> T get(final String url, final String path, 
            final Object param, final Class<T> type) throws WebApplicationException {
        
        return path(url, "/{"+path+"}").resolveTemplate(path, param)
                .request().get(type);
    }
    
    public Response doPost(final String url, final Object param) 
            throws WebApplicationException {
        return target(url).post(Entity.entity(param, getType()));
    }
    
    public <T> T post(final String url, final Object param) 
            throws WebApplicationException {
        return (T) target(url).post(Entity.entity(param, getType()));
    }
    
    public <T> T post(final String url, final Object param, 
            final GenericType<T> type) throws WebApplicationException {
        return (T) target(url).post(Entity.entity(param, getType()), type);
    }
    
    public String buildUrl(final String url, final Map<String, String> params) {
        final StringBuilder sb = new StringBuilder();
        
        for (Entry<String, String> entry : params.entrySet()) {
            if (sb.toString().isEmpty())
                sb.append(url).append("?");
            else
                sb.append("&");
            
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        
        return sb.toString();
    }
    
    protected Invocation.Builder target(final String url) {
        return client.target(url).request();
    }
    
    protected WebTarget path(final String url, final String path) {
        return client.target(url).path(path);
    }

    public Client getClient() {
        return client;
    }
    
    protected abstract MediaType getType();
}