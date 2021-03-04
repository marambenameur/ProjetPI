/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

/**
 *
 * @author ArwaBj
 */
public class Stock {
    public  int id;
    private int tot_entree;
    private int tot_sortie;
    private int qt_dispo;

    public Stock(int id, int tot_entree, int tot_sortie, int qt_dispo) {
        this.id = id;
        this.tot_entree = tot_entree;
        this.tot_sortie = tot_sortie;
        this.qt_dispo = qt_dispo;
    }

    public Stock(int tot_entree, int tot_sortie, int qt_dispo) {
        this.tot_entree = tot_entree;
        this.tot_sortie = tot_sortie;
        this.qt_dispo = qt_dispo;
    }

    public int getId() {
        return id;
    }

    public int getTot_entree() {
        return tot_entree;
    }

    public int getTot_sortie() {
        return tot_sortie;
    }

    public int getQt_dispo() {
        return qt_dispo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTot_entree(int tot_entree) {
        this.tot_entree = tot_entree;
    }

    public void setTot_sortie(int tot_sortie) {
        this.tot_sortie = tot_sortie;
    }

    public void setQt_dispo(int qt_dispo) {
        this.qt_dispo = qt_dispo;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", tot_entree=" + tot_entree + ", tot_sortie=" + tot_sortie + ", qt_dispo=" + qt_dispo + '}';
    }
    
    
}
