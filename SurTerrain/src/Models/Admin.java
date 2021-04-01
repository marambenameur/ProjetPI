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
public class Admin extends User  {
   
    private String  username;

    public Admin(int id, String nom, String prenom, String username, String email, String mdp) {
        super(id, nom, prenom, email, mdp);
        this.username = username;
        
    }

    public Admin(String nom, String prenom, String username, String email,String mdp) {
        super(nom, prenom, email, mdp);
        this.username = username;
        
    }

    public Admin(String username, String nom, String prenom, String email) {
        super(nom, prenom, email);
        this.username = username;
    }

    public Admin( int id, String nom, String prenom,String username, String email) {
        super(id, nom, prenom, email);
        this.username = username;
    }
    

    public Admin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Admin{" +super.toString()+ "username=" + username + '}';
    }

    
   
   

   
   
    
   
    
    
}
