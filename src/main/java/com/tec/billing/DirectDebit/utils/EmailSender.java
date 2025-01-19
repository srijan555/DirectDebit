package com.tec.billing.DirectDebit.utils;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;
import java.io.File;

@Component
public class EmailSender {

    public void sendEmail(String toEmail, String subject, String body, String attachmentPath) {
        String fromEmail = "ultimategamecentric@gmail.com";  // Replace with your email
        String host = "smtp.gmail.com";  // Replace with your SMTP host

        // Set up properties for the email server
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");  // SMTP Port
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        // Create a session
        Session session = null;
        try {
            session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("ultimategamecentric@gmail.com", "zvkb npbb cqdn kbrf");  // Replace with your email and password
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            // Create email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);

            // Create the message part with the email body
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Create a multipart message for the attachment
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attach the PDF
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(attachmentPath));
            multipart.addBodyPart(attachmentPart);

            // Set the content of the email
            message.setContent(multipart);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}

