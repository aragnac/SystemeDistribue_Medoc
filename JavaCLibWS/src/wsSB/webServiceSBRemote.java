/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsSB;

import java.util.List;
import javax.ejb.Remote;
import wsPackage.Analyses;

/**
 *
 * @author Nicolas
 */
@Remote
public interface webServiceSBRemote {
    List<Analyses> getAnalyses(int refDemande);
}
