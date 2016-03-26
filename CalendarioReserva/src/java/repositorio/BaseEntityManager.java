/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sandio
 */
@Stateless 
public class BaseEntityManager {
    
    @PersistenceContext(unitName = "calendario_teste_2PU")
    private EntityManager entityManager;
    
    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
	return entityManager;
    }
}
