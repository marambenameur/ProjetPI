/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import com.esprit.models.Joueurs;
import com.esprit.models.club;
import com.esprit.models.competition;
import com.esprit.services.ServiceJoueurs;
import com.esprit.services.Serviceclub;
import com.esprit.services.Servicecompetition;


/**
 *
 * @author aissa
 */
public class MainProg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceJoueurs sj = new ServiceJoueurs();
        Serviceclub sc = new Serviceclub();
        Servicecompetition scom = new Servicecompetition();
        
//        sj.ajouter(new Joueurs("Ahmed", "hamidou", 20, "Leaders")); 
 //       sj.supprimer(new Joueurs(4, "", "",0, "" ));
     //   sj.modifier("mohamed","kacem",11,"barcelona",5);
      sj.afficher().forEach(System.out::println);
//        
//        sc.ajouter (new club("Leaders", 3));
//        sc.supprimer(new club(3,"",0));
//        sc.modifier(4,"taraji", 10);
       sc.afficher().forEach(System.out::println);

        
       // scom.ajouter(new competition(4,"competition1"));
       scom.afficher().forEach(System.out::println);
          
    }
    
}
