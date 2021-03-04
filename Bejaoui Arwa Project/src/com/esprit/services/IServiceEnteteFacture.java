/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author ArwaBj
 * @param <Entete_facture>
 */
public interface IServiceEnteteFacture<Entete_facture> {
    public void ajouter(Entete_facture t);
    public void supprimer(Entete_facture t);
    public void modifier(Entete_facture t);
    public List<Entete_facture> afficher(); 
    public List<Entete_facture> trier_entetefacture(); 
    public List<Entete_facture> rechercher(Date date_exp);
}
