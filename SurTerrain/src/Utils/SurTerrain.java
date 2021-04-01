/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Models.Facture;
import Models.Reservation;
import Models.Terrain;
import Services.ServiceFacture;
import Services.ServiceReservation;
import Services.ServiceTerrain;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author OussKh
 */
public class SurTerrain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
     Date datePayementFacture;
            //connexion :
            DataSource dt = new DataSource();

        ServiceTerrain st = new Services.ServiceTerrain();
        ServiceFacture sf = new Services.ServiceFacture();
        ServiceReservation sr = new ServiceReservation();
        
//        Terrain t1 = new Terrain("hh","hh","hh");
//        Terrain t2 = new Terrain("club Sokra/terrain1", "sokra ariana", "disponible");
////        Terrain t3 = new Terrain("orange", "Raoued", "réservé");
//        st.ajouter(t2);
//        st.supprimer(t1);
//        st.modifier(t1);
//        st.afficher().forEach(System.out::println);
//        System.out.println(t1.getIdTerrain());
////        
//        Date date = Date.valueOf(LocalDate.now());
//        Time heureDebut = Time.valueOf(LocalTime.of(1, 0));
//        Time heureFin = Time.valueOf(LocalTime.of(3, 0));
//        System.out.println(heureFin);
//        System.out.println(date);
//        Reservation r2 = new Reservation(date, heureDebut,heureFin, 30,1);
////        sr.supprimer(r2);
//        sr.ajouter(r2);
//        sr.afficher().forEach(System.out::println);
//        sr.modifier(r3);
//        st.rechercher("dispo").forEach(System.out::println);
//        sr.rechercher("6").forEach(System.out::println);
//        LocalDate date =LocalDate.now();
//        datePayementFacture = Date.valueOf(date);
//        Facture f1= new Facture(9,70,datePayementFacture);
//        sf.ajouter(f1);
//        int nbTerrainParEtat = st.nbTerrainParEtat("reservé");
//        System.out.println(nbTerrainParEtat);
//        sf.rechercher("50").forEach(System.out::println);
//        st.trierTerrainParNom().forEach(System.out::println);
//        sf.trierTerrainParSommeF().forEach(System.out::println);
//        int nbt=st.nbTerrainTotal();
//        System.out.println(nbt);
//        String stat =st.statTerrain("reservé");
//        System.out.println(stat);
        
    }
    
}