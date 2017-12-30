/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnalyseRemote;

import java.util.EventListener;

/**
 *
 * @author Nicolas
 */
public interface demandeListener extends EventListener{
    void nouvelleDemande(Demande d);
}
