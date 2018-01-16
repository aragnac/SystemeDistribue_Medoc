/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientRemote;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.*;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Nicolas
 */
@Stateless
//@DeclareRoles("medecin")
public class patientSB implements patientSBRemote {
    @Resource SessionContext ctx;
    
    @Override
    //@RolesAllowed("medecin")
    public List<patientRemote.Patient> getPatients(String nom, String prenom) {
        
        List<patientRemote.Patient> results = new ArrayList<>();
        
        /*if(ctx.isCallerInRole("medecin")){
            System.out.println(ctx.isCallerInRole("medecin"));
            Principal callerPrincipal = ctx.getCallerPrincipal();
            if(callerPrincipal.getName().equals("nicolas"))
            {*/
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibPatientPU");// JavaCLibPatientPU
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                TypedQuery<patientRemote.Patient> query = em.createQuery("SELECT p FROM Patient p WHERE p.nom LIKE '%"+ nom +"%' or p.prenom LIKE '%"+ prenom +"%' ", Patient.class); //WHERE nom LIKE '%"+ nom +"%' or prenom LIKE '%"+ prenom +"%'
                results = query.getResultList();

                em.close();
            //}
        //}

        return results;
    }
    
    @Override
    public int insertPatient(Patient p) {
        int ref = 0;
        
        //On passe en parametre de emf le nom de la persitence unit
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibPatientPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin(); 
        
        try{  
            em.persist(p);
            em.getTransaction().commit();
            //ref = p.getId();
            System.out.println("MESSAGE: Numero patient :" + ref);
            em.close();
            
        }catch(Exception e){
            System.out.println("MESSAGE: insertPatient :" + e.getMessage());
        }
        return ref;
    }
    
    @Override 
    public boolean updatePatient(Patient p){
        boolean exit = false;
                
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibPatientPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        try
        {
            Patient patient = em.find(Patient.class,p.getId());
            patient.setNom(p.getNom());
            patient.setPrenom(p.getPrenom());
            patient.setAdresse(p.getAdresse());

            em.persist(patient);
            em.getTransaction().commit();
            exit = true;
        }
        catch(Exception ex)
        {
            exit = false;
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
        finally
        {
            em.close();
        }
        
        return exit;
    } 

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Patient> getPatientById(int id) {
        List<patientRemote.Patient> results = new ArrayList<>();
        
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibPatientPU");// JavaCLibPatientPU
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                TypedQuery<patientRemote.Patient> query = em.createQuery("SELECT p FROM Patient p WHERE p.id = "+id+"", Patient.class);
                results = query.getResultList();

                em.close();


        return results;
    }
}
