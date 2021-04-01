/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Hamidou
 */
public class clubx {
    private int id_clubx,id,id_club;
    private String Nom_club,Nom_joueur;

    public clubx(int id_clubx, int id, int id_club, String Nom_club, String Nom_joueur) {
        this.id_clubx = id_clubx;
        this.id = id;
        this.id_club = id_club;
        this.Nom_club = Nom_club;
        this.Nom_joueur = Nom_joueur;
    }

    public clubx(int id, int id_club, String Nom_club, String Nom_joueur) {
        this.id = id;
        this.id_club = id_club;
        this.Nom_club = Nom_club;
        this.Nom_joueur = Nom_joueur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public String getNom_club() {
        return Nom_club;
    }

    public void setNom_club(String Nom_club) {
        this.Nom_club = Nom_club;
    }

    public String getNom_joueur() {
        return Nom_joueur;
    }

    public void setNom_joueur(String Nom_joueur) {
        this.Nom_joueur = Nom_joueur;
    }
    
    
    
}
