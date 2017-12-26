/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyseRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nicolas
 */
@Stateless
public class AnalyseSB implements AnalyseSBRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public boolean insertAnalyse(String item, String value, int ref) {
        boolean exit = false;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibAnalysesPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        try{  
            AnalyseRemote.Analyses a = new Analyses();
            a.setItem(item);
            a.setValeur(value);
            a.setRefPatient(ref);
            em.persist(a);
            em.getTransaction().commit();
            
            exit = true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return exit;
    }
}
