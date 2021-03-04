/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.util.List;

/**
 *
 * @author ArwaBj
 * @param <Detail_facture>
 */
public interface IServiceDetailFacture<Detail_facture> {
    public void ajouter(Detail_facture t);
    public void supprimer(Detail_facture t);
    public void modifier(Detail_facture t);
    public List<Detail_facture> afficher(); 
   public void modifier_stock(Detail_facture t);
}
