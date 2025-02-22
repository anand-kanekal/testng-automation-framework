package framework.util;

import framework.constant.Path;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class EmailUtils {

    private static final Properties emailProperties;

    static {
        emailProperties = new Properties();
        PropertyFileManager.getInstance().loadProperties(emailProperties, Path.MAIN_RESOURCES + File.separator + "config" + File.separator + "email.properties");
    }

    public static void sendEmail() throws MessagingException {
        String username = emailProperties.getProperty("username");
        String password = emailProperties.getProperty("password");
        String sender = emailProperties.getProperty("sender");
        String subject = emailProperties.getProperty("subject");
        String body = emailProperties.getProperty("body");

        Session session = authenticateCredentials(username, password);

        MimeMessage mimeMessage = new MimeMessage(session);

        if (!((Objects.isNull(sender) || sender.equalsIgnoreCase("")))) {
            mimeMessage.setFrom(new InternetAddress(sender));
        } else {
            throw new RuntimeException("No sender defined in email.properties");
        }

        addToRecipients(mimeMessage);
        addCCRecipients(mimeMessage);
        mimeMessage.setSubject(subject, "charset=utf-8");
        mimeMessage.setText(body);
        Transport.send(mimeMessage);
        System.out.println("Email sent!");
    }

    public static void sendEmailWithAttachment() throws MessagingException, IOException {
        String username = emailProperties.getProperty("username");
        String password = emailProperties.getProperty("password");
        String sender = emailProperties.getProperty("sender");
        String subject = emailProperties.getProperty("subject");
        String body = emailProperties.getProperty("body");

        Session session = authenticateCredentials(username, password);

        MimeMessage mimeMessage = new MimeMessage(session);

        if (!((Objects.isNull(sender) || sender.equalsIgnoreCase("")))) {
            mimeMessage.setFrom(new InternetAddress(sender));
        } else {
            throw new RuntimeException("No sender defined in email.properties");
        }

        addToRecipients(mimeMessage);
        addCCRecipients(mimeMessage);
        mimeMessage.setSubject(subject, "charset=utf-8");

        MimeMultipart mimeMultipart = new MimeMultipart();
        MimeBodyPart content = new MimeBodyPart();
        content.setText(body);
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(new File(""));
        mimeMultipart.addBodyPart(content);
        mimeMultipart.addBodyPart(attachment);
        mimeMessage.setContent(mimeMultipart);

        Transport.send(mimeMessage);
        System.out.println("Email sent!");
    }

    private static Session authenticateCredentials(String username, String password) {
        Session session = Session.getDefaultInstance(emailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        return session;
    }

    private static InternetAddress[] getRecipients(String recipients) throws AddressException {
        InternetAddress[] internetAddresses;

        if (!recipients.isEmpty()) {
            internetAddresses = new InternetAddress[recipients.split(";").length];

            int count = 0;

            for (String recipient : recipients.split(";")) {
                internetAddresses[count] = new InternetAddress(recipient.trim());
                count++;
            }
        } else {
            internetAddresses = new InternetAddress[0];
        }

        return internetAddresses;
    }

    private static void addToRecipients(MimeMessage mimeMessage) throws MessagingException {
      String recipients = emailProperties.getProperty("recipients.to");
      InternetAddress[] internetAddresses = getRecipients(recipients);

      if (internetAddresses.length != 0) {
          mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
      } else {
          throw new RuntimeException("No To recipients defined in email.properties");
      }
    }

    private static void addCCRecipients(MimeMessage mimeMessage) throws MessagingException {
        String recipients = emailProperties.getProperty("recipients.cc");
        InternetAddress[] internetAddresses = getRecipients(recipients);

        if (internetAddresses.length != 0) {
            mimeMessage.setRecipients(Message.RecipientType.CC, internetAddresses);
        }
    }
}
