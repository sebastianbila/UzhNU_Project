package nullproject.database;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class SendEmail {
    static Properties properties = new Properties();
    public String requestCod = generateCod();

    static {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
    }

    public SendEmail(String email) {
        String returnStatement = null;
        try {
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("nullovskyi@gmail.com", "1null123");
                }
            };
            Session session = Session.getInstance(properties, auth);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("nullovskyi@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSentDate(new Date());
            message.setSubject("NULL team");
            message.setText("Привіт! Для завершення регістрації введіть цей код у потрібну ячейку. \nВаш код: " + requestCod);
            returnStatement = "The e-mail was sent successfully";
            System.out.println(returnStatement);
            Transport.send(message);
        } catch (Exception e) {
            returnStatement = "error in sending mail";
            e.printStackTrace();
        }
    }

    private String generateCod(){
        char[] symbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'k', 'q'};
        String request = "";
        Random rnd = new Random();

        for(int i = 0; i < 6; i++){
            request += symbols[rnd.nextInt(symbols.length-1)];
        }

        return request;
    }
}