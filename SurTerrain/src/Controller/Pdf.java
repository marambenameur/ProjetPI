/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Event;
import Services.ServiceEvent;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maram
 */
public class Pdf {
    //webcam.main(args);  
    public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {    
        Document document=new  Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
        //Image img = Image.getInstance("photo.png");
        //Image img2 = Image.getInstance("logo.png");
        ServiceEvent es=new ServiceEvent();
        List<Event> list=es.Afficher();    
        document.add(new Paragraph("La liste des evenments :"));
        document.add(new Paragraph("     "));
         for(Event e:list)
        {
        document.add(new Paragraph("l'evenment de nom :"+e.getNom()));
        document.add(new Paragraph("id :"+e.getId()));
        document.add(new Paragraph("Categories:"+e.getCategories_id()));
        document.add(new Paragraph("Date_event :"+e.getDate_event()));
        document.add(new Paragraph("Description :"+e.getDescription()));
        document.add(new Paragraph("Lieu_event:"+e.getLieu_event()));
        document.add(new Paragraph("Prix :"+e.getPrix()));
   System.out.println(" hello");

        document.add(new Paragraph("---------------------------------------- ------------------------------------------------------------------------------------------ "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
}
