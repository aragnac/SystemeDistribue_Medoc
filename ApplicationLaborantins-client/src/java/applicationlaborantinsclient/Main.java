/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationlaborantinsclient;

import AnalyseRemote.AnalyseSBRemote;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

/**
 *
 * @author Nicolas
 */
public class Main {

    @EJB
    private static AnalyseSBRemote analyseSB;
    
    @Resource(mappedName = "jms/analyseTopic")
    private static Topic analyseTopic;

    @Resource(mappedName = "jms/analyseTopicFactory")
    private static ConnectionFactory analyseTopicFactory;

    @Resource(mappedName = "jms/analyseQueue")
    private static Queue analyseQueue;

    @Resource(mappedName = "jms/analyseQueueFactory")
    private static ConnectionFactory analyseQueueFactory;

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
            
            //Run login frame
            Login login = new Login();
            // login.setVisible(true);
          
            //login.dispose();
            
            //Run mainframe
            appLaborantin app = new appLaborantin(analyseQueue, session, connection, analyseSB);
            app.setVisible(true);
            
        }catch(JMSException ex){
            
        }
    }
}
