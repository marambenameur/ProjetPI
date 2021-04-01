/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author maram
 */
public class Event { 
    int id  ;
    Categorie categories_id ;
    String Nom ;
    Date Date_event ;
    String Description ;
    String Lieu_event ;
    Double Prix ;
    int etat ;

    public Event() {
    }

    public Event(int id, Categorie categories_id, String Nom, Date Date_event, String Description, String Lieu_event,Double Prix, int etat ) {
        this.id = id;
        this.categories_id = categories_id;
        this.Nom = Nom;
        this.Date_event = Date_event;
        this.Description = Description;
        this.Lieu_event = Lieu_event;
        this.Prix = Prix;
        this.etat=etat ;
       
    }
    public Event(int id,Categorie categories_id, String Nom, String Description, String Lieu_event, Date Date_event,Double Prix) {
        this.id = id;
        this.categories_id = categories_id;
        this.Nom = Nom;
        this.Date_event = Date_event;
        this.Description = Description;
        this.Lieu_event = Lieu_event;
        this.Prix = Prix;
       
    }

    public Event(Categorie categories_id, String Nom, String Description, String Lieu_event, Double Prix, int etat) {
        this.categories_id = categories_id;
        this.Nom = Nom;
        this.Description = Description;
        this.Lieu_event = Lieu_event;
        this.Prix = Prix;
        this.etat = etat;
    }

    public Event(String Nom, Date Date_event, String Description, String Lieu_event, Double Prix) {
        this.Nom = Nom;
        this.Date_event = Date_event;
        this.Description = Description;
        this.Lieu_event = Lieu_event;
        this.Prix = Prix;
    }
    
    

   
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie getCategories_id() {
        return categories_id;
    }

    

    public void setCategories_id(Categorie categories_id) {
        this.categories_id = categories_id;
    }

 

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public Date getDate_event() {
        return Date_event;
    }

    public void setDate_event(Date Date_event) {
        this.Date_event = Date_event;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLieu_event() {
        return Lieu_event;
    }

    public void setLieu_event(String Lieu_event) {
        this.Lieu_event = Lieu_event;
    }

    public Double getPrix() {
        return Prix;
    }

    public void setPrix(Double Prix) {
        this.Prix = Prix;
    }
     public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }


   
    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", categories_id=" + categories_id + ", Nom=" + Nom + ", Date_event=" + Date_event + ", Description=" + Description + ", Lieu_event=" + Lieu_event + ", Prix=" + Prix + ", etat=" + etat + '}';
    }

    
    
    
}


    

