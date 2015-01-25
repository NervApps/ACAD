/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.context;

import br.com.acae.eva.auth.Login;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Vitor
 */
@ApplicationPath("rest")
public class RestPublisher extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> servers = new HashSet<>();
        servers.add(Login.class);
        
        return servers;
    }
}
