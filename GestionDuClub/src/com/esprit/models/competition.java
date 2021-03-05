/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

/**
 *
 * @author Hamidou
 */
public class competition {
    private int id_competition, id_club;
    private String nom_competition,nom_club;

    public competition(int id_competition, int id_club, String nom_competition, String nom_club) {
        this.id_club=id_club;
        this.id_competition = id_competition;
        this.nom_competition=nom_competition;
        this.nom_club= nom_club;
    }

    public competition(int id_club, String nom_competition) {
        this.id_club = id_club;
        this.nom_competition = nom_competition;
        

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

    public String getNom_competition() {
        return nom_competition;
    }

    public void setNom_competition(String nom_competition) {
        this.nom_competition = nom_competition;
    }

    @Override
    public String toString() {
        return "competition{" + "id_competition=" + id_competition + ", id_club=" + id_club + ",nom_club=" + nom_club + ", nom_competition=" + nom_competition + '}';
    }

    }

   
