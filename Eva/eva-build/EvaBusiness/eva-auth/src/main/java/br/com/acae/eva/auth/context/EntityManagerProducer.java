/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.acae.eva.auth.context;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Vitor
 */
public class EntityManagerProducer {
    
    @PersistenceUnit
    private EntityManagerFactory factory;
    
    @Produces @RequestScoped
    protected EntityManager create() {
        return factory.createEntityManager();
    }
    
    protected void close(@Disposes EntityManager manager) {
        if (manager.isOpen())
            manager.close();
    }
}