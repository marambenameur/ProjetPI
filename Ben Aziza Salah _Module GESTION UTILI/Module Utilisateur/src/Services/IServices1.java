/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Adherant;
import Entity.Client;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author SALAH
 * @param <T>
 */
public interface IServices1 <T> {
    public void ajouter(T t);
    public boolean supprimer(int id);
    public void modifier(String nom, String prenom, String address,int numTelC, String email, String mdpC,int idC);
    public List<T> afficher();
    public Client finfById(int id);
    public ObservableList<Client> findByName(String name);
    public int nombre();
}

