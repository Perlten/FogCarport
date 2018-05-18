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

    public static void sendMail(String mailTo, String title, String textMessage) throws FOGException {

        final String username = "fogcarport@gmail.com";
        final String password = "FoggyFrog420";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(username));
            message.setSubject(title);
            message.setText(textMessage);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new FOGException("Could not send email");
        }
    }
}
