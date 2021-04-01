/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.sql.Time;


/**
 *
 * @author OussKh
 */
public class Reservation {
    
    private int idRes;
    private Date date;
    private Time heureDebut;
    private Time heureFin;
    private int idTerrain;
    private int idClient;

    public Reservation() {
    }

    public Reservation(int idRes, Date date, Time heureDebut, Time heureFin, int idTerrain, int idClient) {
        this.idRes = idRes;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.idTerrain = idTerrain;
        this.idClient = idClient;
    }

    public Reservation(Date date, Time heureDebut, Time heureFin, int idTerrain, int idClient) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.idTerrain = idTerrain;
        this.idClient = idClient;
    }

    public Reservation(Date date, Time heureDebut, Time heureFin, int idTerrain) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.idTerrain = idTerrain;
    }
    

    public Reservation(Date date, Time heureDebut, Time heureFin) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idRes) {
        this.idRes = idRes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

  

    
    @Override
  public boolean equals(Object obj) {
         if (obj instanceof Reservation) {
            Reservation r = (Reservation) obj;
            if (r.idRes == this.idRes ) { 
                
                return true;
            }
        }
        return false;      
    }
   
    
    
    
}
