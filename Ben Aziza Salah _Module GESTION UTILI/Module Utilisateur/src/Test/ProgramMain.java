/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import DataSource.DataSource;
import Entity.Adherant;
import Entity.Admin;
import Entity.Client;
import Services.ServiceAdherant;
import Services.ServiceAdmin;
import Services.ServiceClient;

/**
 *
 * @author SALAH
 */
public class ProgramMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
         DataSource c = new DataSource();
          ServiceAdherant sp = new ServiceAdherant();
          Adherant a2= new Adherant("Ahmmmed", "Mahmoud",12,"5651","5156","611",5616,"151");
          Adherant a3= new Adherant("Ahmmmed", "Mahmoud",12,",51","5156","611",5616,"151");
          Adherant a5= new Adherant("pd", "Mahmoud",12,",51","5156","611",5616,"151");
          Adherant a1= new Adherant("ahmmmed", "Mahmoud",12,",51","5156","611",5616,"151");
          //sp.ajouter(a1);
          //sp.afficher().forEach(System.out::println);
         //System.out.println(sp.nombre());
        //System.out.println(sp.finfById(2));
        //System.out.println(sp.findByName("Ahmmmed"));
          //sp.modifier ("cc","d",9,"662","5151","511",51,"51",1);
          // sp.supprimer(1);
         // sp.readAllAdherantSortedByNom().forEach(System.out::println);
          
          ServiceClient cl =new ServiceClient();
          Client b = new Client("borhen","khlifi","tunis",28450361,"haha","1231");
          //System.out.println(cl.finfById(3));
          //System.out.println(cl.findByName("borhen"));
        //  System.out.println(cl.nombre());
        // cl.ajouter(b);
           // cl.supprimer(1);
          // cl.modifier("borhen","khlifi","tunnnnis",28450361,"hahhahaa","1231",2);
         // cl.supprimer(2);
         //cl.afficher().forEach(System.out::println);
         ServiceAdmin ad=new ServiceAdmin();
         Admin t = new Admin("Salah","benaziza","benaz","salah.benaziza@esprit.tn","12516498");
         Admin t1 = new Admin("ahmed","benaziza","abenaz","salah.benaziza@esprit.tn","12516498");
         Admin t2 = new Admin("ahmed","benaziza","abenaz","salah.benaziza@esprit.tn","12516498");
         //System.out.println(ad.finfById(2));
          //System.out.println(ad.findByName("s"));
         //System.out.println(ad.nombre());
         
         ad.ajouter(t2);
         //ad.supprimer(1);
        //ad.modifier("s", "p", "ps","mail","15", 2);
        // ad.afficher().forEach(System.out::println);
       // ad.readAllAdherantSortedByUsername().forEach(System.out::println);
    }
    
}


