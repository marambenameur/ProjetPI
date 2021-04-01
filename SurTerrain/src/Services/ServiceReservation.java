/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Controller.ReserverTerrainController;
import Models.Reservation;
import Models.Terrain;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataSource;
import java.sql.Time;

/**
 *
 * @author OussKh
 */
public class ServiceReservation  implements IServices <Reservation>{

     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reservation r) {
        try {
            String requete = "INSERT INTO reservation (date,heureDebut,heureFin,idTerrain) VALUES('" + r.getDate()+ "','" + r.getHeureDebut()+"','" + r.getHeureFin()+ "','" +r.getIdTerrain()+ "')";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Réservation ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Reservation r) {
         try {
            String requete = "DELETE FROM reservation WHERE idTerrain="+ r.getIdTerrain();
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("Réservation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }


@Override
    public void modifier(Reservation r)  {
        try{
        Statement stm = cnx.createStatement();
        
        String query = "UPDATE reservation SET date= '"+r.getDate()+"', heureDebut= '"+r.getHeureDebut()+"', heureFin= '"+r.getHeureFin()+"',idterrain= '"+r.getIdTerrain()+"' WHERE idRes='"+r.getIdRes()+"'";
        stm.executeUpdate(query);
        System.out.println("Réservation modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
}
      }

    
    @Override
    public List<Reservation> afficher() {
        List<Reservation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reservation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt(1), rs.getDate(2),rs.getTime(3),rs.getTime(4),rs.getInt(5),rs.getInt(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    
//  @Override
    public List<Reservation> rechercher(String charac) {
        
        String requete = "select * from reservation where  idterrain LIKE '%"+charac+"%'"
                + " or date LIKE '%"+charac+"%' "
                + "or heureDebut LIKE '%"+charac+"%'"
                + "or heureFin LIKE '%"+charac+"%'";
        List<Reservation> listReservation = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            while (rst.next()) {
                Reservation result = new Reservation();
                result.setIdTerrain(rst.getInt("idterrain"));
                result.setDate(rst.getDate("date"));
                result.setHeureDebut(rst.getTime("heureDebut"));
                result.setHeureFin(rst.getTime("heureFin"));
                listReservation.add(result);
            }
        } catch (SQLException ex) {
            System.out.println("aucune Reservation disponible!");
        }
        return listReservation;
    }
    
    
    public List<Reservation> trierReservationParDateDebut() {

        List<Reservation> listReservationTri = new ArrayList<>();
        try {
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery("select * from reservation");
            while (rs.next()) {
                int idR = rs.getInt("idRes");
               Date date =rs.getDate("date");
               Time heureF=rs.getTime("heureFin");
               Time heureD=rs.getTime("heureDebut");
                 int idT = rs.getInt("idterrain");
                 int idC= rs.getInt("idclient");
                
                Reservation t = new Reservation(idR,date ,heureF,heureD, idT,idC);
                 listReservationTri.add(t);
                 listReservationTri.stream()
                              .sorted(Comparator.comparing((Reservation) -> {
                              return Reservation.getDate();
                        }));
            }
        } catch (SQLException ex) {
        }
        return  listReservationTri;
    }
    
    public int getIdTerrainByName(String nomT){
        int id=0;
     String requete ="select idTerrain from terrain where nomTerrain = '"+nomT+"'";
         try {
             PreparedStatement pst = cnx.prepareStatement(requete);
              ResultSet rs = pst.executeQuery();
              if (rs.next()) {
            id = rs.getInt(1);
        }
         } catch (SQLException ex) {
             Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
         }
   return id;
    }
    
    
       public void ajouter2(Reservation r,String nom) {
//        Terrain t=new Terrain();
        int idt =this.getIdTerrainByName(nom);
//           System.out.println(idt);
        try {
            String requete = "INSERT INTO reservation (date,heureDebut,heureFin,idTerrain) VALUES('" + r.getDate()+ "','" + r.getHeureDebut()+"','" + r.getHeureFin()+ "','" + idt + "')";
            PreparedStatement pst = cnx.prepareStatement(requete);
//             pst.setInt(1,t.getIdTerrain());
//           pst.setInt(2, r.getU().getId());
            pst.executeUpdate();
            System.out.println("Réservation ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
    

}
