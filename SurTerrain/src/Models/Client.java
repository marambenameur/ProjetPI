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
public class Client extends User{
    private int numTelC;
    private String address;

    public Client(int id, String nom, String prenom, String address,int numTelC,String email, String mdp) {
        super(id, nom, prenom, email, mdp);
        this.numTelC = numTelC;
        this.address = address;
    }

    public Client(String nom, String prenom, String address,int numTelC, String email, String mdp) {
        super(nom, prenom, email, mdp);
        this.numTelC = numTelC;
        this.address = address;
    }

    public Client( String address, String nom, String prenom,int numTelC, String email) {
        super(nom, prenom, email);
        this.numTelC = numTelC;
        this.address = address;
    }

    public Client( int id, String nom, String prenom, String address,int numTelC, String email) {
        super(id, nom, prenom, email);
        this.numTelC = numTelC;
        this.address = address;
    }
    

    public Client() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getNumTelC() {
        return numTelC;
    }

    public void setNumTelC(int numTelC) {
        this.numTelC = numTelC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +super.toString()+ "numTelC=" + numTelC + ", address=" + address + '}';
    }


   
    
}
