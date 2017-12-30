/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyseRemote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Nicolas
 */
@Stateless
public class AnalyseSB implements AnalyseSBRemote {

    @Resource(mappedName = "jms/analyseQueue")
    private Queue analyseQueue;

    @Resource(mappedName = "jms/analyseQueueFactory")
    private ConnectionFactory factory;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // un seul objet pour tous les types d'écouteurs
    
    
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
            demande.setTraitee(false);
            em.persist(demande);
            em.getTransaction().commit();
            ref = demande.getId();
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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibAnalysePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Demande> query = em.createQuery("SELECT d FROM Demande d WHERE d.traitee = false ", Demande.class);
        results = query.getResultList();

        em.close();

        return results;
    }
    
    @Override
    public List<Analyses> getAnalyses(int refDemande){
    
        List<Analyses> results = new ArrayList<>();

        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory("JavaCLibAnalysePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Analyses> query = em.createQuery("SELECT a FROM Analyses a ", Analyses.class);
        results = query.getResultList();

        em.close();

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
    

    /*private void sendJMSMessageToAnalyseQueue(Object messageData) {

        context.createProducer().send(analyseQueue, (Message) messageData);
        System.out.println("Message envoye sur la queue");
    }*/
    
}
