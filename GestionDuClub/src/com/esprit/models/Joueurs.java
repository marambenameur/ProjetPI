/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

/**
 *
 * @author aissa
 */
public class Joueurs {

    private int id,age;
    private String nom;
    private String prenom;
    private String club_name;
    

    public Joueurs(int id, String nom, String prenom, int age, String club_name) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.club_name=club_name;
    }

    public Joueurs(String nom, String prenom, int age, String club_name) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.club_name=club_name;
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
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String getclubname(){
        return club_name;
    }
    public void setclubname(String club_name){
        this.club_name=club_name;
    }

    @Override
    public String toString() {
        return "Joueurs{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ",Club name=" + club_name +'}';
    }
}
