/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author SALAH
 */
public class Adherant extends User {
    private int numTel,cin;
    private String  nomTerrain, address;

    public Adherant(int id, String nom, String prenom,int cin , String email, String nomTerrain, String address,int numTel, String mdp) {
        super(id, nom, prenom, email, mdp);
        this.numTel = numTel;
        this.cin = cin;
        this.nomTerrain = nomTerrain;
        this.address = address;
    }

    public Adherant(String nom, String prenom,int cin, String email, String nomTerrain, String address,int numTel, String mdp) {
        super(nom, prenom, email, mdp);
        this.numTel = numTel;
        this.cin = cin;
        this.nomTerrain = nomTerrain;
        this.address = address;
        
    }

    public Adherant(  String nom, String prenom, String address, String email,String nomTerrain,int numTel) {
        super(nom, prenom, email);
        this.numTel = numTel;
        this.nomTerrain = nomTerrain;
        this.address = address;
    }

    public Adherant(int id,  String nom, String prenom, String address,String nomTerrain,String email ,int numTel) {
        super(id, nom, prenom, email);
        this.numTel = numTel;
        this.nomTerrain = nomTerrain;
        this.address = address;
    }

    public Adherant(String nomTerrain) {
        this.nomTerrain = nomTerrain;
        
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

    @Override
    public String toString() {
        return "Adherant{" +super.toString()+ "numTel=" + numTel + ", cin=" + cin + ", nomTerrain=" + nomTerrain + ", address=" + address + '}';
    }
    
    

   
   

    
    
   
  
}
