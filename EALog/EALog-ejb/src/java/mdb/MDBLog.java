/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import AnalyseRemote.Demande;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import logPackage.Logs;

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

    @EJB
    private logSBRemote logSB;
    
    public MDBLog() {
    }
    
    @Override
    public void onMessage(Message message) {
        try{
        ObjectMessage objMessage = (ObjectMessage) message;
        Demande dem = (Demande) objMessage.getObject();
        System.out.println("MDB : " + dem.getId());
        
        Calendar c1 = new GregorianCalendar();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(dem.getDateHeureDemande());

        long difference = c1.getTime().getTime() - c2.getTime().getTime();
        Calendar c3 = Calendar.getInstance();
        c3.setTimeInMillis(difference);
        String string = ("La duree de traitement de l'analyse " + dem.getId() + " a ete de " + TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS) + "h" + c3.getTime().getMinutes());
        Logs log = new Logs();
        log.setInfos(string);
        logSB.insertLog(log);
                
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    
}
