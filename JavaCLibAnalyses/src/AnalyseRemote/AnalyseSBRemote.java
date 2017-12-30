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
    
    List<Analyses> getAnalyses(int refDemande);
    
    int insertDemande(Demande demande);
    
    List<Demande> getDemandes();
    
}
