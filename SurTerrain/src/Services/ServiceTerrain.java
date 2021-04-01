/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Reservation;
import Models.Terrain;
import java.sql.Connection;
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

/**
 *
 * @author OussKh
 */
public class ServiceTerrain implements IServices<Terrain> {
    
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Terrain t) {
        try {
            String requete = "INSERT INTO terrain (nomTerrain,adresse,etat) VALUES('" + t.getNomTerrain()+ "','" + t.getAdresse()+ "','disponible')";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("Terrain ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Terrain t) {
        try {
            String requete = "DELETE FROM terrain WHERE idTerrain="+ t.getIdTerrain();
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("Terrain supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Terrain t)  {
        try{
        Statement stm = cnx.createStatement();
        String requete = "UPDATE terrain SET nomTerrain= '"+t.getNomTerrain()+"', adresse= '"+t.getAdresse()+"', etat= '"+t.getEtat()+"' WHERE idTerrain='"+t.getIdTerrain()+"'";
        stm.executeUpdate(requete);
        System.out.println("Terrain modifiée !");
    } catch (SQLException ex) {
           Logger.getLogger(ServiceTerrain.class.getName()).log(Level.SEVERE, null, ex);
}
      }
    

    @Override
    public List<Terrain> afficher() {
        List<Terrain> listTerrain = new ArrayList<>();
        try {
            String requete = "SELECT * FROM terrain";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                listTerrain.add(new Terrain(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return listTerrain;
    }

//    @Override
    public List<Terrain> rechercher(String charac) {
        String requete = "select * from terrain where  nomTerrain LIKE '%"+charac+"%' or adresse LIKE '%"+charac+"%' or etat LIKE '%"+charac+"%'";
        List<Terrain> listTerrain = new ArrayList<>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            while (rst.next()) {
                Terrain t = new Terrain();
                t.setNomTerrain(rst.getString("nomTerrain"));
                t.setAdresse(rst.getString("adresse"));
                t.setEtat(rst.getString("etat"));
                listTerrain.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("aucun terrain disponible!");
        }
        return listTerrain;
    }
    
    
    
    public int nbTerrainParEtat(String etat)  {
        int nb = 0;
        String reqete = "SELECT count(*) FROM terrain where etat = '"+etat+"'";
        try {
        PreparedStatement pst = cnx.prepareStatement(reqete);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nb = rs.getInt(1);
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nb;
    }
    
     public int nbTerrainTotal()  {
        int nb = 0;
        String reqete = "SELECT count(*) FROM terrain ";
        try {
        PreparedStatement pst = cnx.prepareStatement(reqete);

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nb = rs.getInt(1);
        }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nb;
    }
     
     

       
//        public String statTerrain(String charac )  {
//        double res = ((double) nbTerrainParEtat(charac) / nbTerrainTotal());  
//        double res2=res*100;
//        String stat =String.valueOf(res2)+"%";
//        return stat ;
//    }
    
    

    public List<Terrain> trierTerrainParNom() {

        List<Terrain> listTerrainTri = new ArrayList<>();
        try {
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery("select * from terrain");
            while (rs.next()) {
                int id = rs.getInt("idTerrain");
                String nom = rs.getString("nomTerrain");
                 String adresse =rs.getString("adresse");
                String etat=rs.getString("etat");
                
                
                Terrain t = new Terrain(id, nom, adresse, etat);
                listTerrainTri.add(t);
                listTerrainTri.stream()
                              .sorted(Comparator.comparing((Terrain) -> {
                              return Terrain.getNomTerrain();
                        }));
            }
        } catch (SQLException ex) {
        }
        return  listTerrainTri;
    }

   
    
}
