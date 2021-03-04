/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;
import java.util.List;


/**
 *
 * @author ABS1
 */
public interface IService <D>{
     public void ajouter(D Demande);
    public void supprimer(D Demande);
    public void modifier(D Demande);
   public List<D> afficher();
    
}
