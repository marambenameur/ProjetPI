/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


import java.sql.Date;




/**
 *
 * @author OussKh
 */
public class Facture {
    
    private int idFacture;
    private int idRes;
    private float sommeFacture;
    private Date datePayementFacture;

    public Facture() {
    }

    public Facture(int idFacture, int idR, float sommeFacture, Date datePayementFacture) {
        this.idFacture = idFacture;
       
        this.idRes = idR;
        this.sommeFacture = sommeFacture;
        this.datePayementFacture = new Date(System.currentTimeMillis());
    }

    public Facture( int idR, float sommeFacture, Date datePayementFacture) {
        this.idRes = idR;
        this.sommeFacture = sommeFacture;
        this.datePayementFacture = datePayementFacture;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

   

    public int getIdRes() {
        return idRes;
    }

    public void setIdRes(int idR) {
        this.idRes = idR;
    }

    public float getSommeFacture() {
        return sommeFacture;
    }

    public void setSommeFacture(float sommeFacture) {
        this.sommeFacture = sommeFacture;
    }

    public Date getDatePayementFacture() {
        return datePayementFacture;
    }

    public void setDatePayementFacture(Date datePayementFacture) {
        this.datePayementFacture = datePayementFacture;
    }

    @Override
    public String toString() {
        return "Facture:" + "idFacture:" + idFacture + ", idRes:" + idRes + ", sommeFacture:" + sommeFacture + ", datePayementFacture:" + datePayementFacture ;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Facture) {
            Facture f = (Facture) obj;
            if (f.idFacture == this.idFacture) {

                return true;
            }
        }
        return false;
    }
    
}
