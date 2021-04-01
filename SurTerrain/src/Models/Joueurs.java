/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Services.Serviceclub;

/**
 *
 * @author aissa
 */
public class Joueurs {

    private int id, age, id_club;
    private String nom, email;
    private String prenom, nom_club;

    public Joueurs(String nom, String prenom, int age, int id_club, String email) {

        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.id_club = id_club;
        this.email = email;

    }

    public Joueurs(int id, String nom, String prenom, int age, int id_club, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.id_club = id_club;
        Serviceclub x = new Serviceclub();
        this.nom_club = x.getnomclub(this.id_club);
        this.email = email;
    }

    public Joueurs(int age, String nom, String prenom, String email) {
        this.age = age;
        this.nom = nom;
        this.email = email;
        this.prenom = prenom;
    }
    

    public Joueurs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public String getNom_club() {
        return nom_club;
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Joueurs{" + "id=" + id + ", age=" + age + ", id_club=" + id_club + ", nom=" + nom + ", prenom=" + prenom + ", nom_club=" + nom_club + ", email=" + email + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Joueurs) {
            Joueurs j = (Joueurs) obj;
            if (j.age == this.age) {

                return true;
            }
        }
        return false;
    }

}
