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
public class Client {
    private int numTelC,idC ;
    private String nom,prenom,email,address,mdpC;

    public Client( int idC, String nom, String prenom, String address,int numTelC,String email, String mdpC) {
        this.numTelC = numTelC;
        this.idC = idC;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.address = address;
        this.mdpC = mdpC;
    }

    public Client( String nom, String prenom, String address,int numTelC, String email, String mdpC) {
        this.numTelC = numTelC;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.address = address;
        this.mdpC = mdpC;
    }
    

    public int getNumTelC() {
        return numTelC;
    }

    public void setNumTelC(int numTelC) {
        this.numTelC = numTelC;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMdpC() {
        return mdpC;
    }

    public void setMdpC(String mdpC) {
        this.mdpC = mdpC;
    }

    @Override
    public String toString() {
        return "Client{" + "idC=" + idC + ", nom=" + nom + ", prenom=" + prenom + ", address=" + address +"numTelC=" + numTelC + ", email=" + email + ", mdpC=" + mdpC + '}';
    }

  

   

   
    
}
