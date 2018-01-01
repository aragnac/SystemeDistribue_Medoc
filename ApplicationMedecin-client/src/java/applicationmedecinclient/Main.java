/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmedecinclient;

import AnalyseRemote.AnalyseSBRemote;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import patientRemote.patientSBRemote;

/**
 *
 * @author Nicolas
 */
public class Main {

    @EJB
    private static patientSBRemote patientSB;
     
    @EJB
    private static AnalyseSBRemote analyseSB;

    @Resource(mappedName = "jms/analyseTopic")
    private static Topic analyseTopic;

    @Resource(mappedName = "jms/analyseTopicFactory")
    private static ConnectionFactory analyseTopicFactory;
    
    private static Connection connection = null;
    private static Session session = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            connection = analyseTopicFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
        
            searchFrame search = new searchFrame(patientSB, analyseSB, session, analyseTopic);
            search.setVisible(true);
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
