package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Controller {

    public TextField emailToField;
    public TextField emailFromField;
    public TextArea emailMessageField;
    public TextField emailSubjectField;
    public PasswordField emailPasswordField;
    public Label sentBoolValue;

    public void buttonClicked(ActionEvent actionEvent){
        sendEmail();
    }

    public void sendEmail(){
        String to = emailToField.getText();
        String from = emailFromField.getText();
        String host = "smtp.gmail.com";
        final String username = emailFromField.getText();
        final String password = emailPasswordField.getText();

        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject(emailSubjectField.getText());
            m.setText(emailMessageField.getText());

            //send mail

            Transport.send(m);
            sentBoolValue.setVisible(true);
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

    }

}
