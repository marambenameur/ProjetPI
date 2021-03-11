/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.awt.Image;
import java.io.File;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ArwaBj
 * @param <Article>
 */
public interface IServiceArticle<T> {
    public void ajouter(T t);
    public void supprimer(T t);
    public void modifier(T t);
    public List<T> afficher();
    
}
