package fr.lubac.boutiqueSki.ihm;

import java.util.List;

import fr.lubac.boutiqueSki.bll.BLLException;
import fr.lubac.boutiqueSki.bll.CatalogueManager;
import fr.lubac.boutiqueSki.bo.Article;
import fr.lubac.boutiqueSki.bo.Ski;

public class ArticleController {
    private EcranArticle ecranArticle;
    private CatalogueManager cmgr;
    private List<Article> listeArticles;
    private static ArticleController instance;
    private int indexCatalogue;

    protected static ArticleController getInstance() {
        if (instance == null) {
            instance = new ArticleController();
        }
        return instance;
    }

    // Constructor
    private ArticleController() {
        try {
            cmgr = CatalogueManager.getInstance();
            listeArticles = cmgr.getCatalogue();
            indexCatalogue = 0;
        } catch (BLLException e) {
            e.printStackTrace();
        }

    }

    // Control of article to display
    public void startApp() {
        this.ecranArticle = new EcranArticle();
        this.afficherPremierArticle();
        ecranArticle.setVisible(true);
    }

    public void afficherPremierArticle() {
        if (this.listeArticles.size() > 0) {
            indexCatalogue = 0;
            this.ecranArticle.afficherArticle(listeArticles.get(indexCatalogue));
        } else {
            // Default article is a type "Ski"
            this.ecranArticle.afficherArticle(new Ski("", "", "", 0, 0, 0));
        }
    }

    public void next() {
        if (this.indexCatalogue < listeArticles.size() - 1) {
            this.indexCatalogue++;
        } else {
            this.indexCatalogue = 0;
        }
        ecranArticle.afficherArticle(listeArticles.get(indexCatalogue));
    }

    public void previous() {
        if (indexCatalogue > 0) {
            indexCatalogue--;
        } else {
            indexCatalogue = listeArticles.size() - 1;
        }
        ecranArticle.afficherArticle(listeArticles.get(indexCatalogue));

    }

    public void save() {
        Article article = this.ecranArticle.getArticleCourant();
        if (article.getIdArticle() != null) {
            try {
                this.cmgr.updateArticle(article);
            } catch (BLLException e) {
                this.ecranArticle.infoErreur(e.getMessage());
                e.printStackTrace();
            }
        } else {
            try {
                this.cmgr.addArticle(article);
                this.ecranArticle.afficherArticle(article);
            } catch (BLLException e) {
                this.ecranArticle.infoErreur(e.getMessage());
                e.printStackTrace();
            }
        }
        // Retrieve updated catalogue
        try {
            this.listeArticles = cmgr.getCatalogue();
        } catch (BLLException e) {
            this.ecranArticle.infoErreur(e.getMessage());
            e.printStackTrace();
        }

    }

    public void newArticle() {
        this.indexCatalogue = listeArticles.size();
        this.ecranArticle.afficherArticle(new Ski("", "", "", 0, 0, 0));

    }

    public void deleteArticle() {
        try {
            this.cmgr.removeArticle(this.ecranArticle.getiDArticleCourant());
            this.listeArticles = cmgr.getCatalogue();
        } catch (BLLException e) {
            this.ecranArticle.infoErreur(e.getMessage());
            e.printStackTrace();
        }

        if (this.listeArticles.size() == 0) {
            this.newArticle();
        } else if (this.indexCatalogue == this.listeArticles.size()) {
            indexCatalogue--;
            this.ecranArticle.afficherArticle(this.listeArticles.get(indexCatalogue));
        } else {
            this.ecranArticle.afficherArticle(this.listeArticles.get(indexCatalogue));
        }

    }

}
