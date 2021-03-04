/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pi;
import datasource.DataSource;
import entity.Demande;

import entity.Equipe;
import java.sql.SQLException;
import service.ServiceDemande;
import service.ServiceEquipe;


/**
 *
 * @author ABS1
 */
public class Pi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        DataSource cnx = new DataSource();
        ServiceDemande sd = new ServiceDemande();
        ServiceEquipe se = new ServiceEquipe();
        Demande d1= new Demande(3,"ala",21,55);
   // sd.ajouter(d1);
   //     se.ajouter(new Equipe(1,"ahmed", 12));
   // Equipe e1 = new Equipe(1,"",25);
//      se.ajouter(e1);
//      se.supprimer(e1);
//       se.modifier(e1);
     //  sd.modifier(d1);
   //sd.readAllEmployeessSortedByDate().forEach(System.out::println);
//      se.afficher().forEach(System.out::println);     
          
           
//         sd.ajouter(new Demande("24/5/2020",11, 12));
//         Demande d1 = new Demande(2, "25025", 11, 12);
//        
//         sd.supprimer(d1);
//         sd.afficher().forEach(System.out::println);
        System.out.println(sd.getId(3));
        
    }
    
}
