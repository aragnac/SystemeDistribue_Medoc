/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyseRemote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nicolas
 */
@Stateless
public class AnalyseSB implements AnalyseSBRemote {

    /*@PersistenceContext(unitName="JavaCLibAnalysesPU")
    private EntityManager em;*/
    @Resource SessionContext ctx;
    
    @Resource(mappedName = "jms/analyseQueue")
    private Queue analyseQueue;

    @Resource(mappedName = "jms/analyseQueueFactory")
    private ConnectionFactory factory;
    
    //EntityManagerFactory emf = null;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // un seul objet pour tous les types d'écouteurs
    
    
    @Override
    public boolean insertAnalyse(Analyses anal) {
        boolean exit = false;
        
        //On passe en parametre de emf le nom de la persitence unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibAnalysesPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        System.out.println(anal.getDateAnalyse());
        System.out.println(anal.getRefDemande());
        System.out.println(anal.getRefPatient());
        System.out.println(anal.getItem());
        System.out.println("valeur : " + anal.getValeur());
        
        try{
            em.persist(anal);
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
            demande.setTraitee(false);
            em.persist(demande);
            em.getTransaction().commit();
            ref = demande.getId();
            System.out.println("reference :" + ref);
            em.close();
            
            sendJMSMessageToAnalyseQueue(demande);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ref;
    }
    
    @Override
    public List<Demande> getDemandes(){
    
        List<Demande> results = new ArrayList<>();

        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibAnalysesPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<Demande> query = em.createQuery("SELECT d FROM Demande d WHERE d.traitee = false ", Demande.class);
            results = query.getResultList();

            em.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return results;
    }
    
    @Override
    public List<Analyses> getAnalyses(int refDemande){
    
        List<Analyses> results = new ArrayList<>();

        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibAnalysesPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            TypedQuery<Analyses> query = em.createQuery("SELECT a FROM Analyses a WHERE a.refDemande = "+ refDemande +"", Analyses.class);
            results = query.getResultList();

            em.close();
        
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return results;
    }

    private void sendJMSMessageToAnalyseQueue(Object messageData) {
        
        try {
            Connection con = factory.createConnection();
            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            con.start();
            MessageProducer producer = session.createProducer(analyseQueue);
            ObjectMessage objectMessage = session.createObjectMessage();
            
            objectMessage.setObject((Demande)messageData);
            producer.send(objectMessage);
            con.close();
            System.out.println("Envoye sur la queue.");
            //context.createProducer().send(analyseQueue, messageData);
        } catch (JMSException ex) {
            System.out.println("Exception : " + ex);
        }
    }
    
   /* private EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("JavaCLibAnalysePU");
        }
        return emf;
    }*/
    
}
