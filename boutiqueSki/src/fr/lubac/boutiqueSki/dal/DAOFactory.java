package fr.lubac.boutiqueSki.dal;

import fr.lubac.boutiqueSki.dal.jdbc.ArticleDaoJdbcImpl;

public class DAOFactory {

    public static DAOArticle getArticleDAO() {
        return new ArticleDaoJdbcImpl();
    }
}
