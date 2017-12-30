/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationlaborantinsclient;

import AnalyseRemote.AnalyseSBRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
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
            login.setVisible(true);
            
            //login.dispose();
            
            //Run mainframe
            appLaborantin app = new appLaborantin(analyseTopic, session, connection, analyseSB);
            app.setVisible(true);
            //Resultats result = new Resultats(analyseTopic, session, connection, analyseSB);
            //result.setVisible(true);
            
        }catch(JMSException ex){
            
        }
    }

    /*private Message createJMSMessageForjmsAnalyseQueue(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToAnalyseQueue(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = analyseQueueFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(analyseQueue);
            messageProducer.send(createJMSMessageForjmsAnalyseQueue(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }*/

    /*private Message createJMSMessageForjmsAnalyseTopic(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToAnalyseTopic(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = analyseTopicFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(analyseTopic);
            messageProducer.send(createJMSMessageForjmsAnalyseTopic(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }*/
    
}
