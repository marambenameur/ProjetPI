/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import com.esprit.models.Article;
import com.esprit.models.Detail_facture;
import com.esprit.models.Entete_facture;

import com.esprit.services.ServiceArticle;
import com.esprit.services.ServiceDetailFacture;
import com.esprit.services.ServiceEnteteFacture;
import com.esprit.utils.DataSource;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import static java.time.Clock.system;

/**
 *
 * @author ArwaBj
 */
public class MainProg {
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {



Connection cnx=DataSource.getInstance().getCnx();


// article:

//ServiceArticle sa = new ServiceArticle();
//File file = new File("C:\Users\\ArwaBj\\Desktop\\projet\\image\protein3.jpg");
//sa.file(file, new Article( 15,"proteine GH MAX ", "proteine","proteine3",11,150));
 //sa.supprimer(new Article( 15,"proteine GH MAX ", "proteine","proteine3",11,150));
//sa.rechercher("crampons noir");
//sa.modifier(new Article(12,"crampons noir", "crampons ","crampons2",15,100));
//   sa.afficher().forEach(System.out::println);    
//sa.trier_article().forEach(System.out::println); 
//sa.article_en_rupture().forEach(System.out::println);  


// entete facture:

ServiceEnteteFacture sa = new ServiceEnteteFacture();
java.util.Date myDate = new java.util.Date("03/04/2012");
java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
//sa.ajouter(new Entete_facture( 3, "achat", (sqlDate),15));
sa.modifier(new Entete_facture(21, "vente", (sqlDate),15));
//   sa.afficher().forEach(System.out::println);    
//sa.trier_entetefacture().forEach(System.out::println);

//detail facture:

//ServiceDetailFacture sef = new ServiceDetailFacture();
//Detail_facture t1=new Detail_facture( 32,"accessoire de cheuveux ",17,2,"vente");
//sef.ajouter(t1);
//sef.modifier(new Detail_facture( 32,"accessoire de cheuveux ",17,40,"achat"));
       //sef.afficher().forEach(System.out::println);

    }

   
}
