package Helpers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class javaMail {
    public static void sendMail(String receveursList, String Object, String Corps) {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String MonEmail = "habibekh97@gmail.com";
        String password = "pwd";

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication()
            {
                return new javax.mail.PasswordAuthentication(MonEmail, password);
            }

        });

        javax.mail.Message message = prepareMessage(session,MonEmail,receveursList, Object, Corps
        );

        try {
            javax.mail.Transport.send(message);
        } catch (javax.mail.MessagingException ex) {
            Logger.getLogger(javaMail.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.err.println("Message envoyé avec succès");
    }

    private static javax.mail.Message prepareMessage(Session session, String email, String receveursList, String Object, String Corps)
    {
        javax.mail.Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(email));

            message.setSubject(Object);
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receveursList));
            message.setText(Corps);

            return message;
        } catch (javax.mail.MessagingException ex) {
            Logger.getLogger(javaMail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}




