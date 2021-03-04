/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Adherant;
import Entity.Admin;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author SALAH
 * @param <T>
 */
public interface IServices2 <T> {
     public void ajouter(T t);
    public boolean supprimer(int id);
    public void modifier(String nom, String prenom, String username, String mdp, String email,int id);
    public List<T> afficher();
    public Admin finfById(int id);
    public ObservableList<Admin> findByName(String name);
    public int nombre();
    public List<Admin> readAllAdminSortedByUsername();
    
}
