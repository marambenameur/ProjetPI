/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author SALAH
 */
public class Adherant {
    private int numTel,cin,idA ;
    private String nom,prenom,email, nomTerrain, address ,mdpAdh;

    public Adherant(String nom, String prenom,int cin, String email, String nomTerrain, String address,int numTel, String mdpAdh) {
        this.numTel = numTel;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.nomTerrain = nomTerrain;
        this.address = address;
        this.mdpAdh = mdpAdh;
    }

    public Adherant( int idA, String nom, String prenom,int cin , String email, String nomTerrain, String address,int numTel, String mdpAdh) {
        this.numTel = numTel;
        this.cin = cin;
        this.idA = idA;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.nomTerrain = nomTerrain;
        this.address = address;
        this.mdpAdh = mdpAdh;
    }

    public Adherant() {
    }
    
    
    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMdpAdh() {
        return mdpAdh;
    }

    public void setMdpAdh(String mdpAdh) {
        this.mdpAdh = mdpAdh;
    }

    @Override
    public String toString() {
        return "Adherant{"+"idA=" + idA + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin+ ", nomTerrain=" + nomTerrain + ", address=" + address + ", email=" + email + "numTel=" + numTel + ", mdpAdh=" + mdpAdh + '}';
    }

   

    
    
   
  
}
