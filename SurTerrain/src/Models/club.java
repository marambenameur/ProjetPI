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
public class club {

    private int id_club;
    private String nom_club;
    private int nbr_joueurs;

    public club(int id_club, String nom_club, int nbr_joueurs) {
        this.id_club = id_club;
        this.nom_club = nom_club;
        this.nbr_joueurs = nbr_joueurs;
    }

    public club(String nom_club, int nbr_joueurs) {

        this.nom_club = nom_club;
        this.nbr_joueurs = nbr_joueurs;
    }

    public club(String nom_club) {

        this.nom_club = nom_club;

    }

    public club() {

    }

    public int getid_club() {
        return id_club;
    }

    public void setid_club(int id_club) {
        this.id_club = id_club;
    }

    public String getNom_club() {
        return nom_club;
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    public int getNbr_joueurs() {
        return nbr_joueurs;
    }

    public void setNbr_joueurs(int nbr_joueurs) {
        this.nbr_joueurs = nbr_joueurs;
    }
//    @Override
//    public String toString() {
//        return "Club{" + "Club id=" + id_club + ", team name=" + nom_club + ", Number of players=" + nbr_joueurs + '}';
//    }

    @Override
    public String toString() {
        return "" + nom_club + ' ';
    }

}
