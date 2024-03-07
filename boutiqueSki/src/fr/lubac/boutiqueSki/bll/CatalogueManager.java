package fr.lubac.boutiqueSki.bll;

import java.util.ArrayList;
import java.util.List;

import fr.lubac.boutiqueSki.bo.Article;
import fr.lubac.boutiqueSki.bo.Combinaison;
import fr.lubac.boutiqueSki.bo.Ski;
import fr.lubac.boutiqueSki.dal.DALException;
import fr.lubac.boutiqueSki.dal.DAO;
import fr.lubac.boutiqueSki.dal.DAOFactory;

/**
 * @author Yvan Lubac
 * 
 */
public class CatalogueManager {
    private DAO<Article> daoArticle;
    private static CatalogueManager instance;

    // Singleton design pattern
    public static CatalogueManager getInstance() throws BLLException {
        if (instance == null) {
            instance = new CatalogueManager();
        }
        return instance;
    }

    // private Constructor
    private CatalogueManager() throws BLLException {
        // get Data Access Object
        daoArticle = DAOFactory.getArticleDAO();
    }

    /**
     * @return catalogue des articles
     * 
     */

    public List<Article> getCatalogue() throws BLLException {
        List<Article> catalogueArticles = new ArrayList<>();
        try {
            catalogueArticles = this.daoArticle.selectAll();
        } catch (DALException e) {
            e.printStackTrace();
            throw new BLLException("Erreur récupération catalogue", e);
        }
        return catalogueArticles;
    }

    /**
     * 
     * @param id : id d'un article
     * @return Article
     * @throws BLLException
     */
    public Article getArticle(int id) throws BLLException {
        Article a = null;
        try {
            a = this.daoArticle.selectById(id);
        } catch (DALException e) {
            throw new BLLException("Erreur récupération de l'article n°" + id, e);
        }
        return a;
    }

    /**
     * Ajout d'un article dans le catalogue
     * 
     * @param article
     * @return index du nouvel article dans le catalogue mis à jour dans l'objet
     *         article mis en entrée
     * @throws BLLException
     */

    public void addArticle(Article article) throws BLLException {

        try {
            this.validerArticle(article);
            this.daoArticle.insert(article);
        } catch (DALException e) {
            throw new BLLException("Echec addArticle", e);
        }
    }

    /**
     * updateArticle : modifier un article dans le catalogue
     * 
     * @param article
     * @throws BLLException
     */
    public void updateArticle(Article article) throws BLLException {

        try {
            this.validerArticle(article);
            this.daoArticle.update(article);
        } catch (DALException e) {
            throw new BLLException("Echec updateArticle de l'article:" + article, e);
        }
    }

    /**
     * Supprimer un article du catalogue
     * 
     * @param id
     * @throws BLLException
     */

    public void removeArticle(int id) throws BLLException {
        try {
            this.daoArticle.delete(id);
        } catch (DALException e) {
            throw new BLLException("Echec de la suppression de l'article - ", e);
        }
    }

    /**
     * Valider les données d'un article
     * 
     * @param article
     * @throws BLLException
     */
    public void validerArticle(Article article) throws BLLException {
        boolean valide = true;
        StringBuffer sb = new StringBuffer();

        if (article == null) {
            throw new BLLException("Article null");
        }

        if (article.getDesignation() == null || article.getDesignation().trim().length() == 0) {
            valide = false;
            sb.append("La désignation de l'article ne doit pas être vide.\n");
        }

        if (article.getMarque() == null || article.getMarque().trim().length() == 0) {
            valide = false;
            sb.append("La désignation de la marque ne doit pas être vide.\n");
        }

        if (article.getReference() == null || article.getReference().trim().length() == 0) {
            valide = false;
            sb.append("La référence de l'article ne doit pas être vide.\n");
        }

        if (article.getReference().length() > 10) {
            valide = false;
            sb.append("La référence de l'article ne doit pas faire plus de 10 caractères.\n");
        }

        if (article.getPrixUnitaire() == 0) {
            valide = false;
            sb.append("Le prix de l'article ne doit pas être nul.\n");
        }

        if (article.getQteStock() < 0) {
            valide = false;
            sb.append("La quantité en stock de l'article doit être positive ou nulle.\n");
        }

        if (article instanceof Ski) {
            Ski ski = (Ski) article;
            if (ski.getLongueur() != 150 && ski.getLongueur() != 166) {
                valide = false;
                sb.append("La longueur des ski doit être égale a 150 ou 166.\n");
            }
        }

        if (article instanceof Combinaison) {
            Combinaison combi = (Combinaison) article;
            if (combi.getCouleur() == null || combi.getCouleur().trim().length() == 0) {
                valide = false;
                sb.append("La couleur de la combinaison ne doit pas être vide.\n");
            }
        }

        if (!valide) {
            throw new BLLException(sb.toString());
        }
    }

}
