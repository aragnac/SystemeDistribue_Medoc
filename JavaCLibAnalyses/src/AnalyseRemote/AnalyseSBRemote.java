/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyseRemote;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Nicolas
 */
@Remote
public interface AnalyseSBRemote {
    
    boolean insertAnalyse(Analyses anal);
    
    int updateAnalyse(Analyses anal);
    
    List<Analyses> getAnalyses(int refDemande);
    
    List<Analyses> getAnalysesPatient(int refPatient);
    
    int insertDemande(Demande demande);
    
    int updateDemande(Demande demande);
    
    List<Demande> getDemandes();
    
    List<Demande> getDemandesPatient(int refPatient);
    
    void sendJMSMessageToAnalyseTopic(Object messageData);
    
}
