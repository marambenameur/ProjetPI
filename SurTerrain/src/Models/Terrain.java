/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author OussKh
 */
public class Terrain {

   
    private int idTerrain;
    private String nomTerrain;
    private String adresse;
    private String etat;

    public Terrain(String nomTerrain, String addresse) {
        this.nomTerrain = nomTerrain;
        this.adresse = addresse;
    }

    public Terrain(int idTerrain, String nomTerrain, String adresse) {
        this.idTerrain = idTerrain;
        this.nomTerrain = nomTerrain;
        this.adresse = adresse;
    }
    
    
    

    public Terrain(String nomTerrain, String adresse, String etat) {
        this.nomTerrain = nomTerrain;
        this.adresse = adresse;
        this.etat = etat;
    }

    public Terrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public Terrain(int idTerrain, String nomTerrain, String adresse,String etat) {
        this.idTerrain = idTerrain;
        this.nomTerrain = nomTerrain;
        this.adresse = adresse;
        this.etat = etat;
    }

    public Terrain() {
    }

  
    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Terrain:" + "idTerrain:" + idTerrain + ", nomTerrain:" + nomTerrain + ", adresse:" + adresse + ", etat:" + etat ;
    }

    
@Override
  public boolean equals(Object obj) {
         if (obj instanceof Terrain) {
            Terrain t = (Terrain) obj;
            if (t.idTerrain == this.idTerrain ) { 
                
                return true;
            }
        }
        return false;      
    }
    
    
    
    
    
    
}
