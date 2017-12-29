/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientRemote;

import java.util.List;
import javax.annotation.security.*;
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
@DeclareRoles("medecin")
public class patientSB implements patientSBRemote {
    
    @Override
    @RolesAllowed("medecin")
    public List<patientRemote.Patient> getPatients(String nom, String prenom) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibPatientPU");// JavaCLibPatientPU
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<patientRemote.Patient> query = em.createQuery("SELECT p FROM Patient p WHERE p.nom LIKE '%"+ nom +"%' or p.prenom LIKE '%"+ prenom +"%' ", Patient.class); //WHERE nom LIKE '%"+ nom +"%' or prenom LIKE '%"+ prenom +"%'
        List<patientRemote.Patient> results = query.getResultList();

        em.close();

        return results;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
