/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmedecinclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author Nicolas
 */
public class Main {

    @Resource(mappedName = "jms/analyseTopic")
    private static Topic analyseTopic;

    @Resource(mappedName = "jms/analyseTopicFactory")
    private static ConnectionFactory analyseTopicFactory;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    private Message createJMSMessageForjmsAnalyseTopic(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToAnalyseTopic(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        
        try{
            connection = analyseTopicFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
        }
        catch(JMSException ex){
            System.out.println("Main error : " + ex);
        }
        
    }
    
}
