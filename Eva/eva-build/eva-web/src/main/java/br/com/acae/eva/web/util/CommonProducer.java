/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.web.util;

import br.com.acae.eva.connector.RestClient;
import br.com.acae.eva.connector.hosts.AuthHosts;
import br.com.acae.eva.connector.qualifier.Json;
import br.com.acae.eva.util.RestListWrapper;
import br.com.acae.eva.web.qualifiers.BlockedPages;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author vitor
 */
public class CommonProducer implements Serializable {
    @Inject @Json private RestClient rest;
    @Inject private AuthHosts hosts;
    
    @Produces @BlockedPages @ApplicationScoped
    public List<String> getBlockedPages() {
       final RestListWrapper wrapper = rest.get(hosts.blockedPages(), RestListWrapper.class);
       return wrapper.getValues();
    }
}
