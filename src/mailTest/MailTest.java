package mailTest;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailTest {
    public static void main(String[] args) {



        final String username = System.getProperty("USERNAME");
        final String password = System.getProperty("PASSWORD");

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pburke@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("pburkepro@gmail.com, pburkepro@outlook.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Hello Patrick,"
                    + "\n\n It's great to speak with you!");

            Transport.send(message);

            System.out.println("Message sent ....");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
