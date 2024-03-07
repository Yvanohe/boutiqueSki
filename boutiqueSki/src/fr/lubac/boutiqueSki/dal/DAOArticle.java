package fr.lubac.boutiqueSki.dal;

import java.util.List;

import fr.lubac.boutiqueSki.bo.Article;

public interface DAOArticle extends DAO<Article> {

    public List<Article> selectByMarque(String marque) throws DALException;

    public List<Article> selectByMotCle(String mot) throws DALException;

}
