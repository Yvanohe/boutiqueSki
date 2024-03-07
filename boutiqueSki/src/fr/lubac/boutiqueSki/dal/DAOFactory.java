package fr.lubac.boutiqueSki.dal;

import fr.lubac.boutiqueSki.bo.Article;
import fr.lubac.boutiqueSki.dal.jdbc.ArticleDaoJdbcImpl;

public class DAOFactory {

    public static DAO<Article> getArticleDAO() {
        return new ArticleDaoJdbcImpl();
    }
}
