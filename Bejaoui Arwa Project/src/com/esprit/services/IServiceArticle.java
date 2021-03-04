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
public interface IServiceArticle<Article> {
    public void ajouter(Article t);
    public void supprimer(Article t);
    public void modifier(Article t);
    public List<Article> afficher();
    public void file(File f,Article t);
    public List<Article> trier_article(); 
    public List<Article> rechercher(String libelle); 
    public List<Article> article_en_rupture();
}
