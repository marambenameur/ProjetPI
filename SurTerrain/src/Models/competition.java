/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.text.DateFormat;

/**
 *
 * @author Hamidou
 */
public class competition {

    private int id_competition;
    private String nom_competition;
    private Date date_debut, date_fin;

    public competition(int id_competition, String nom_competition, Date date_debut, Date date_fin) {
        this.id_competition = id_competition;
        this.nom_competition = nom_competition;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public competition(String nom_competition, Date date_debut, Date date_fin) {
        this.nom_competition = nom_competition;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public competition() {
    }
    

    public int getId_competition() {
        return id_competition;
    }

    public void setId_competition(int id_competition) {
        this.id_competition = id_competition;
    }

    public String getNom_competition() {
        return nom_competition;
    }

    public void setNom_competition(String nom_competition) {
        this.nom_competition = nom_competition;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "competition{" + "id_competition=" + id_competition + ", nom_competition=" + nom_competition + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

}
