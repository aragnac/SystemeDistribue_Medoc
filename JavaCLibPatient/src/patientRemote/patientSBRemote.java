/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patientRemote;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Nicolas
 */
@Remote
public interface patientSBRemote {

    List<Patient> getPatients(String nom, String prenom);
    
}
