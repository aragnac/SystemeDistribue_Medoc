/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import javax.ejb.Remote;
import logPackage.Logs;

/**
 *
 * @author Nicolas
 */
@Remote
public interface logSBRemote {
    
    int insertLog(Logs log);
    
}
