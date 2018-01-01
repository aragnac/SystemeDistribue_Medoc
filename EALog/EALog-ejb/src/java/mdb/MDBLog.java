/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import AnalyseRemote.Demande;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author Nicolas
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/analyseTopic")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/analyseTopic")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/analyseTopic")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MDBLog implements MessageListener {
    
    public MDBLog() {
    }
    
    @Override
    public void onMessage(Message message) {
        try{
        ObjectMessage objMessage = (ObjectMessage) message;
        Demande dem = (Demande) objMessage.getObject();
        System.out.println("MDB : " + dem.getId());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
