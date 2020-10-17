package com.fcicb.utilities;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {

    public static void sendMail(String recipient, String defaultPassword)  {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Properties gmailCredentials = new Properties();

        try ( FileReader file = new FileReader("resources//config.properties")) {
            gmailCredentials.load(file);
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        String myAccount = gmailCredentials.getProperty("GmailAccount");
        String password = gmailCredentials.getProperty("GmailPassword");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount,password);

            }
        });

        Message message = prepareMassage(session, myAccount, recipient,defaultPassword);
        try {
            if(message!=null) {
                Transport.send(message);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static Message prepareMassage(Session session, String myAccount, String recepient, String defaultPassword){

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Email Configuration");

            String content = new String (Files.readAllBytes(Paths.get("src//com//fcicb//utilities//EmailMessage.html").toAbsolutePath()));
            content+=defaultPassword;
            message.setContent(content,"text/html");

            return message;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}