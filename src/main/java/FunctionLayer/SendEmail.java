/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.FOGException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Perlt
 */
public class SendEmail {

    private static Session session = null;
    private final static String USERNAME = "fogcarport@gmail.com";
    private final static String PASSWORD = "FoggyFrog420";

    public static void sendMail(String mailTo, String title, String textMessage) throws FOGException {

        if(session == null){
            makeSession();
        }
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(USERNAME));
            message.setSubject(title);
            message.setText(textMessage);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new FOGException("Could not send email");
        }
    }

    private static void makeSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }
    
    public static void main(String[] args) throws FOGException {
        sendMail("karron11@gmail.com", "Test", "test");
    }
}
