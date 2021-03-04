/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Adherant;
import java.sql.ResultSet;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author SALAH
 * @param <T>
 */
public interface IService <T> {
    public void ajouter(T t);
    public boolean supprimer(int id);
    public void modifier(String nom,String prenom,int cin,String address,String nomTerain,String email ,int numTel,String mdp, int idA);
    public List<T> afficher();
    public Adherant finfById(int id);
    public ObservableList<Adherant> findByName(String name);
    public int nombre();
    public List<Adherant> readAllAdherantSortedByNom();
}
