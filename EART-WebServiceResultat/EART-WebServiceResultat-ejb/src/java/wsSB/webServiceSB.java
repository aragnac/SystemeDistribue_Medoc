/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsSB;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import wsPackage.Analyses;

/**
 *
 * @author Nicolas
 */
@Stateless
public class webServiceSB implements webServiceSBRemote {

    @Override
    public List<Analyses> getAnalyses(int refDemande) {
        List<Analyses> results = new ArrayList<>();

        try{
            System.out.println("analyse debut");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaCLibWSPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            System.out.println("analyse debut");
            TypedQuery<Analyses> query = em.createQuery("SELECT a FROM Analyses a WHERE a.refDemande = "+ refDemande +"", Analyses.class);
            results = query.getResultList();

            em.close();
            System.out.println("analyse recup");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return results;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
