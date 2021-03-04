/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author ABS1
 */
public class Demande {
    private int id;
    private String date;
    private int  idterrain;
    private int idequipe;
    
    public Demande (int id, String date, int idterrain, int idequipe) {
        this.id = id;
        this.date =date;
        this.idterrain = idterrain;
        this.idequipe= idequipe;
        
    }

    public Demande(int id, int idterrain, int idequipe) {
        this.id = id;
        this.idterrain = idterrain;
        this.idequipe = idequipe;
    }

    
    

    public Demande(String date, int idterrain, int idequipe) {
        this.date = date;
        this.idterrain = idterrain;
        this.idequipe = idequipe;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getIdterrain() {
        return idterrain;
    }

    public int getIdequipe() {
        return idequipe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdterrain(int idterrain) {
        this.idterrain = idterrain;
    }

    public void setIdequipe(int idequipe) {
        this.idequipe = idequipe;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", date=" + date + ", idterrain=" + idterrain + ", idequipe=" + idequipe + '}';
    }
    
    
    
     
}