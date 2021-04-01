/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.competition;
import Utils.DataSource;
import com.itextpdf.text.BaseColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import javafx.scene.control.Cell;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author Hamidou
 */
public class Servicecompetition {

    Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(competition com) {
        try {
            String requete = "INSERT INTO competition (nom_competition, date_debut, date_fin) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, com.getNom_competition());
            pst.setDate(2, com.getDate_debut());
            pst.setDate(3, com.getDate_fin());
            pst.executeUpdate();
            System.out.println("compétition ajouter!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public ObservableList<competition> lister() {
        ObservableList<competition> list1 = FXCollections.observableArrayList();
        String requete = "SELECT id_competition,nom_competition, date_debut , date_fin FROM competition";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list1.add(new competition(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4)));
            }
            System.out.println("liste des compétitions");
        } catch (SQLException ex) {

        }

        return list1;
    }

    public List<competition> afficher() {
        List<competition> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM competition";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new competition(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public String getmail(competition comp) throws DocumentException, SQLException, IOException {
//        Joueurs j = new Joueurs();
        String x = "mail not sent";
        String req0 = "Select email from joueurs where id='29'";
        PreparedStatement pst0 = cnx.prepareStatement(req0);
        ResultSet rs0 = pst0.executeQuery();
        rs0.next();
        String em = rs0.getString(1);
        String emsender = "issaouihamidou@gmail.com";
        String empass = "2info2hamidou";
        String imgfile = "C:/PDF/logo.png";

        try {
            String filename = "C:/PDF/db.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            String req = "Select nom_competition,date_debut,date_fin from competition where id_competition='" + comp.getId_competition() + "'";
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0));

            document.open();

            float[] columnWidths = {150f, 150f};

            PdfPTable table = new PdfPTable(columnWidths);

            table.setWidthPercentage(90f);
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            Image img = Image.getInstance(imgfile);
            while (rs.next()) {
                Paragraph para = new Paragraph("Team up and join us in this brand new competition A.S.A.P, " +"\n"+ " 32 clubs to participate and only ONE will be the victor! " + " Is it your Team ?");

                insertCell(table, "New Competition is in Town!", Element.ALIGN_CENTER, 2, bfBold12);
                insertCell(table, "", Element.ALIGN_CENTER, 2, bfBold12);
                insertCell(table, "Competition : ", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, rs.getString(1), Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "Date de début : ", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "" + rs.getDate(2) + "", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "Date de fin : ", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "" + rs.getDate(3) + "", Element.ALIGN_CENTER, 1, bfBold12);
                insertCell(table, "", Element.ALIGN_CENTER, 2, bfBold12);
                insertCell(table, "Affiche :", Element.ALIGN_CENTER, 1, bfBold12);
                table.setHeaderRows(1);
                table.addCell(img);

                Paragraph para1 = new Paragraph();
                para1.add(table);

                document.add(para1);
                document.add(para);
                document.close();
                
                JOptionPane.showMessageDialog(null, "pdf remplis");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("erreur creation pdf");
            System.out.println(ex.getMessage());
        }

        final String sender = emsender;
        final String passwd = empass;
        String fromEmail = sender;
        String toEmail = "hamidou_issaoui@hotmail.com";

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("javax.net.ssl.trustStore", "pathToTruststore.ts");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, passwd);
            }

        });
        //Start our mail message
        System.out.println("start our mail message");
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("API JAVA");
            msg.setText("Sent from hamidou With love");

            Multipart emailContent = new MimeMultipart();

            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("My multipart text");

            //Attachment body part.
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            pdfAttachment.attachFile("C:/PDF/db.pdf");

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
            System.out.println(e.getMessage());
        }

        return x;

    }

    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        URL url = null;
        PdfPCell cell2 = new PdfPCell(new Image(url) {
        });

        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);

        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }

        table.addCell(cell);

    }
    public void supprimercomp (competition comp) throws SQLException{
        String del="delete from competition where id ='"+comp.getId_competition()+"'";
        PreparedStatement pst = cnx.prepareStatement(del);
        ResultSet rs = pst.executeQuery();
        rs.next();
    }

}
