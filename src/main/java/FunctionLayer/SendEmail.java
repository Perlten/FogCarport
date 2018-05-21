/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.entities.Employee;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Perlt
 */
public class SendEmail implements Runnable {

    private Session session = null;
    private final String USERNAME = "fogcarport@gmail.com";
    private final String PASSWORD = "FoggyFrog420";
    private String title;
    private String textMessage;
    private List<String> empList;

    public SendEmail(List<String> mailList, String title, String textMessage) {
        this.title = title;
        this.textMessage = textMessage;
        this.empList = mailList;
    }

    public SendEmail(String mailTo, String title, String textMessage) {
        this(Arrays.asList(mailTo), title, textMessage);
    }

    private void makeSession() {
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

    private void sendEmail(String mailTo) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(USERNAME));
            message.setSubject(title);
            message.setText(textMessage);
            Transport.send(message);

        } catch (MessagingException e) {
        }
    }

    @Override
    public void run() {
        if (session == null) {
            makeSession();
        }
        for (String mailTo : empList) {
            sendEmail(mailTo);
        }
    }
}
