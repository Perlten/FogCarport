/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.mail;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Order;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Perlt
 */
public class SendEmail {

    public static void sendMail(Order order) {

        final String username = "fogcarport@gmail.com";
        final String password = "FoggyFrog420";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("fogcarport@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(order.getCustomer().getEmail()));
            message.setSubject("Fog carport");
            message.setText("Dear " + order.getCustomer().getFirstname() + ",\n"
                    + "We thank you for your recent carport request!"
                    + "\nTo follow the process of your request, please use your"
                    + "reference link:\n\n"
                    + " http://159.89.19.132/FogCarport/FrontController?command=LoadOrder&id=" + order.getOrderid() );

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
