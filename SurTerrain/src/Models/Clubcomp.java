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
public class Clubcomp {
    private int id_competition, id_club, idclubcomp;
    private String nom_club;

    public Clubcomp(int id_competition, int id_club, String nom_club) {
        this.id_competition = id_competition;
        this.id_club = id_club;
        this.nom_club = nom_club;
    }

    public Clubcomp(int id_competition, int id_club, int idclubcomp, String nom_club) {
        this.id_competition = id_competition;
        this.id_club = id_club;
        this.idclubcomp = idclubcomp;
        this.nom_club = nom_club;
    }

    public Clubcomp(String nom_club) {
        this.nom_club = nom_club;
    }
    

    public int getId_competition() {
        return id_competition;
    }

    public void setId_competition(int id_competition) {
        this.id_competition = id_competition;
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public int getIdclubcomp() {
        return idclubcomp;
    }

    public void setIdclubcomp(int idclubcomp) {
        this.idclubcomp = idclubcomp;
    }

    public String getNom_club() {
        return nom_club;
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    @Override
    public String toString() {
        return "Clubcomp{" + "id_competition=" + id_competition + ", id_club=" + id_club + ", nom_club=" + nom_club + '}';
    }
    
    
}
