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
public class Admin   {
    private int id ;
    private String mdp, nom, prenom , username;
    private String email;

    public Admin(int id, String nom, String prenom, String username, String email, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.mdp = mdp;
        this.email = email;
    }

    public Admin(String nom, String prenom, String username, String email,String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.mdp = mdp;
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", email=" + email +  ", mdp=" + mdp +'}';
    }

   
    
   
    
    
}
