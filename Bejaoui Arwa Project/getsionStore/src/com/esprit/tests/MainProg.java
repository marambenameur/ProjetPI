package com.esprit.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.esprit.models.Article;
import com.esprit.models.Detail_facture;
import com.esprit.models.Entete_facture;

import com.esprit.services.ServiceArticle;
import com.esprit.services.ServiceDetailFacture;
import com.esprit.services.ServiceEnteteFacture;

import com.esprit.utils.DataSource;
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
ServiceArticle sa = new ServiceArticle();
sa.supprimerarticle(102);
//sa.getArticlesList().forEach(System.out::println);
//sa.ajouter(new Article( "crampons blanc et bleu ", "crampons","http://localhost/image/crampons1.jpg",300,8,003));
 //sa.supprimer(new Article( "proteine GH MAX ", "proteine","proteine3",11,150,001));
//sa.rechercher("crampons noir");
//sa.modifier(new Article( "proteine  MAX ", "proteine","proteine3",11,150, 001));
  //sa.afficher().forEach(System.out::println);     
//sa.article_en_rupture().forEach(System.out::println);  
//sa.trier_article().forEach(System.out::println);

// entete facture:

//ServiceEnteteFacture sa = new ServiceEnteteFacture();
//java.util.Date myDate = new java.util.Date("03/04/2012");
//java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
//sa.ajouter(new Entete_facture( 3, "achat", (sqlDate),15,001));
//sa.modifier(new Entete_facture( "vente", (sqlDate),30,001));
//sa.supprimer((new Entete_facture( "vente", (sqlDate),30,001)));
//   sa.afficher().forEach(System.out::println);    
//sa.trier_entetefacture().forEach(System.out::println);

//detail facture:

//ServiceFacture sef = new ServiceFacture();
//java.util.Date myDate = new java.util.Date("04/04/2012");
//java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
////Facture t1=new Facture( 1,1,(sqlDate),47,"proteine  MAX",30);
//sef.ajouter(t1);
//sef.modifier(t1);
//sef.afficher().forEach(System.out::println);
//sef.supprimer(t1);
//sef.trier_facture();
//ServiceDetailFacture sef = new ServiceDetailFacture();
////sef.ajouter(new Detail_facture("proteine  MAX",23,41,"achat",001));
//sef.modifier(new Detail_facture("proteine  MAX",23,11,"achat",001));
//sef.supprimer(new Detail_facture("proteine  MAX",23,41,"achat",1));
    }

   
}
