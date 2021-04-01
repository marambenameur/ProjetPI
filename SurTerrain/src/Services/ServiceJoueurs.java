/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Joueurs;
import Utils.DataSource;
import com.itextpdf.text.BaseColor;
import java.io.IOException;
import javax.mail.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author aissa
 */
public class ServiceJoueurs implements IServices<Joueurs> {

    Connection cnx = DataSource.getInstance().getCnx();
    private int idclub;

    @Override
    public void ajouter(Joueurs j) {
        try {
            String requete = "INSERT INTO joueurs (nom,prenom,age,id_club,email) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, j.getNom());
            pst.setString(2, j.getPrenom());
            pst.setInt(3, j.getAge());
            pst.setInt(4, j.getId_club());
            pst.setString(5, j.getEmail());
            pst.executeUpdate();
            System.out.println("Joueur ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Joueurs> list() throws SQLException {
        ObservableList<Joueurs> listj = FXCollections.observableArrayList();

        String req = "select id,nom,prenom,age,id_club,email from joueurs";
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            listj.add(new Joueurs(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("age"), rs.getInt("id_club"), rs.getString("email")));
        }
        return listj;
    }

    public ObservableList<Joueurs> listrecherche(String x) throws SQLException {
        ObservableList<Joueurs> listj = FXCollections.observableArrayList();

        String req = "select id,nom,prenom,age,id_club,email from joueurs where nom like '%" + x + "%'";

        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            listj.add(new Joueurs(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getInt("age"), rs.getInt("id_club"), rs.getString("email")));

        }
        return listj;
    }

    @Override
    public void supprimer(Joueurs j) {
        try {

            String requete = "DELETE FROM joueurs WHERE id='" + j.getId() + "'";
            PreparedStatement pst = cnx.prepareStatement(requete);

            pst.executeUpdate();
            System.out.println("Joueur supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Joueurs j) {
        try {

            String requete = "UPDATE joueurs SET nom = '" + j.getNom() + "' ,prenom = '" + j.getPrenom() + "',age = '" + j.getAge() + "',id_club= '" + j.getId_club() + "', email='" + j.getEmail() + "' WHERE id= '" + j.getId() + "'";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Joueur modifiée !");

        } catch (SQLException ex) {
            System.out.println("met3adech");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Joueurs> afficher() {
        List<Joueurs> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM joueurs";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Joueurs(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public String getmailj(Joueurs j) throws DocumentException, SQLException, IOException, AddressException, MessagingException {
        String x = "mail not sended";

        String req0 = "Select email from joueurs where id='" + j.getId() + "'";
        PreparedStatement pst0 = cnx.prepareStatement(req0);
        ResultSet rs0 = pst0.executeQuery();
        rs0.next();
        String em = rs0.getString(1);
        String emsender = "issaouihamidou@gmail.com";
        String empass = "2info2hamidou";
        try {
            String filename = "C:/PDF/dbj.pdf";
            Document doucment = new Document();
            PdfWriter.getInstance(doucment, new FileOutputStream(filename));

            doucment.open();
            String req = "select id,nom,prenom,age,id_club,email from Joueurs where id = '" + j.getId() + "'";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            Font bfBold12 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));
            float[] columnWidths = {150f, 150f};
            PdfPTable table = new PdfPTable(columnWidths);
            table.setWidthPercentage(90f);

            Serviceclub sc = new Serviceclub();
            Image img = Image.getInstance("C:/PDF/logo.png");

            while (rs.next()) {
                String nclub = sc.getnomclub(rs.getInt(5));
                //Paragraph para = new Paragraph("*" + "id : " + rs.getInt(1) + "\n" + "Nom :" + rs.getString(2) + "\n" + "Prenom :" + rs.getString(3) + "\n" + "Age :" + rs.getInt(4) + "\n" + "Nom club : " + nclub + "\n" + "Email :" + rs.getString(6) + "\n" + "\n");

                insertCell(table, "Welcome To the club!", Element.ALIGN_CENTER, 2, bfBold12);
                insertCell(table, "", Element.ALIGN_CENTER, 2, bfBold12);
                insertCell(table, "ID : ", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "" + rs.getInt(1) + "", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "Nom : ", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, rs.getString(2), Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "Prenom : ", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "" + rs.getString(3) + "", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "Age : ", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "" + rs.getInt(4) + "", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "Club name", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "" + nclub + "", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "Email", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "" + rs.getString(6) + "", Element.ALIGN_CENTER, 1, bfBold12);
                //doucment.add(para);
                //doucment.add(para2);
                Paragraph tab = new Paragraph();
                tab.add(table);
                doucment.add(tab);
                doucment.close();
                JOptionPane.showMessageDialog(null, "ajout termine");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("erreur creation pdf");
            System.out.println(ex.getMessage());

        }

        final String username = emsender;
        final String password = empass;
        String fromEmail = "JAVA";
        String toEmail = "hamidou_issaoui@hotmail.com";
         //String toEmail = em;

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }

        });
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("API JAVA");

            Multipart emailContent = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Register Confirmation!");

            //Attachment body part.
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile("C:\\PDF\\dbj.pdf");

            //Attach body parts
            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(pdfAttachment);

            //Attach multipart to message
            msg.setContent(emailContent);
            Transport.send(msg);
            System.out.println("Sent message");
            x = "mail sent ";
        } catch (MessagingException e) {
            System.out.println("errerur mail");
        }

        return x;
    }

    public List<Joueurs> trierJoueursParAge() {

        List<Joueurs> listjoueursTri = new ArrayList<>();
        try {
            String req = "Select nom,prenom,age,email from joueurs";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                int age = rs.getInt("age");
                int id_club = rs.getInt("club_id");
                String email = rs.getString("email");

                Joueurs j = new Joueurs(id, nom, prenom, age, id_club, email);
                listjoueursTri.add(j);
                listjoueursTri.stream()
                        .sorted(Comparator.comparing((Joueurs)
                                -> {
                            return Joueurs.getAge();
                        }
                        ));
            }
        } catch (SQLException ex) {
        }
        return listjoueursTri;
    }

    public List<Joueurs> triage() throws SQLException {
        List<Joueurs> agetri = new ArrayList<>();
        String req = "Select nom,prenom,age,email from joueurs ORDER BY age DESC";
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            int age = rs.getInt("age");
            String email = rs.getString("email");

            Joueurs j = new Joueurs(age, nom, prenom, email);
            agetri.add(j);
        }
        return agetri;
    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {

        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));

        cell.setHorizontalAlignment(align);

        cell.setColspan(colspan);

        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }

        table.addCell(cell);

    }

}
