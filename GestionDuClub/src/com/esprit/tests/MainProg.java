/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import com.esprit.models.Personne;
import com.esprit.services.ServicePersonne;
import com.sun.xml.internal.bind.v2.model.core.ID;
import java.nio.file.Files;


/**
 *
 * @author aissa
 */
public class MainProg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServicePersonne sp = new ServicePersonne();
        sp.ajouter(new Personne("Ahmed", "Mahmoud", 20)); 
        sp.supprimer(new Personne(1, "", "",0 ));
        sp.modifier("ahmed","aissaoui",23,3);
        sp.afficher().forEach(System.out::println);
        
          
    }
    
}
