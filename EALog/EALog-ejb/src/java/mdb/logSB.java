/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logPackage.Logs;

/**
 *
 * @author Nicolas
 */
@Stateless
public class logSB implements logSBRemote {

    @Override
    public int insertLog(Logs log){
            int ref = 0;
        
        //On passe en parametre de emf le nom de la persitence unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibAnalysesPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        
        try{  
            em.persist(log);
            em.getTransaction().commit();
            em.close();
            
        }catch(Exception e){
            System.out.println("insertDemande :" + e.getMessage());
        }
        return ref;
    }
}