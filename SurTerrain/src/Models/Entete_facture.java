/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author ArwaBj
 */
public class Entete_facture {
   private int num_piece;
   private String type;
   private Date date_exp;
   private int tier;
   private int ref_facture;

    public Entete_facture(int num_piece, String type, Date date_exp, int tier,int ref_facture) {
        this.num_piece = num_piece;
        this.type = type;
        this.date_exp = date_exp;
        this.tier = tier;
        this.ref_facture = ref_facture;
    }

    public Entete_facture(Date date_exp, int tier, int ref_facture) {
        this.date_exp = date_exp;
        this.tier = tier;
        this.ref_facture = ref_facture;
    }

    public Entete_facture(String type, Date date_exp, int tier, int ref_facture) {
        this.type = type;
        this.date_exp = date_exp;
        this.tier = tier;
        this.ref_facture = ref_facture;
    }

    

    
   
   public Entete_facture(int ref_facture) {
        this.ref_facture = ref_facture;
    }


    

    

    public Entete_facture(Integer parseInt ) {
    }

   
   
   
    
   

    public int getNum_piece() {
        return num_piece;
    }

    public String getType() {
        return type;
    }

    public Date getDate_exp() {
        return date_exp;
    }

    public int getTier() {
        return tier;
    }

    public void setNum_piece(int num_piece) {
        this.num_piece = num_piece;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate_exp(Date date_exp) {
        this.date_exp = date_exp;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Entete_facture{" + "num_piece=" + num_piece + ", type=" + type + ", date_exp=" + date_exp + ", Tier=" + tier + ", Ref_facture=" + ref_facture +'}';
    }

    public int getRef_facture() {
        return ref_facture;
    }

    public void setRef_facture(int ref_facture) {
        this.ref_facture = ref_facture;
    }
  
   
}
