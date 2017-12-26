/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientRemote;

import java.util.ArrayList;
import java.util.List;
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
public class patientSB implements patientSBRemote {

    @Override
    public List<Patient> getPatients(String nom, String prenom) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibPatientPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        TypedQuery<Patient> query = em.createQuery(" select * from patient  where nom like '%"+ nom +"%' or prenom LIKE '%"+ prenom +"%'; ", Patient.class);
        List<Patient> results = query.getResultList();
        
        return results;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
