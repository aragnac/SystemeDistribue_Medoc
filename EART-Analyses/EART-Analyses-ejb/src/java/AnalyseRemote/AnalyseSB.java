/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyseRemote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Nicolas
 */
@Stateless
public class AnalyseSB implements AnalyseSBRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // un seul objet pour tous les types d'écouteurs
    private final EventListenerList listeners = new EventListenerList();
    
    
    @Override
    public boolean insertAnalyse(String item, String value, int ref) {
        boolean exit = false;
        
        //On passe en parametre de emf le nom de la persitence unit
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
            em.close();
            exit = true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return exit;
    }

    @Override
    public int insertDemande(Demande demande) {
        int ref = 0;
        
        //On passe en parametre de emf le nom de la persitence unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibAnalysesPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        
        try{  
            /*AnalyseRemote.Analyses a = new Analyses();
            a.setItem(item);
            a.setValeur(value);
            a.setRefPatient(ref);*/
            em.persist(demande);
            em.getTransaction().commit();
            ref = demande.getId();
            em.close();
            
            this.fireTemperatureChanged(demande);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ref;
    }
    
    @Override
    public List<Demande> getDemandes(){
    
        List<Demande> results = new ArrayList<>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibAnalysePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Demande> query = em.createQuery("SELECT d FROM Demande d ", Demande.class);
        results = query.getResultList();

        em.close();

        return results;
    }
    
    /***  Listener demande ***/
    @Override
    public void addDemandeListener(demandeListener listener) {
        listeners.add(demandeListener.class, listener);
    }
    
    @Override
    public void removeDemandeListener(demandeListener listener) {
        listeners.remove(demandeListener.class, listener);
    }
    
    @Override
    public demandeListener[] getDemandeListeners() {
        return listeners.getListeners(demandeListener.class);
    }
    
    protected void fireTemperatureChanged(Demande d) {
        
        for(demandeListener listener : getDemandeListeners()) {
            listener.nouvelleDemande(d);
        }
    }
    
}
