package fr.lubac.boutiqueSki.bo;

public class Ligne {
    protected int qte;
    private Article article;

    // ------------
    // CONSTRUCTORS
    // ------------
    public Ligne(int qte, Article article) {
        this.qte = qte;
        this.article = article;
    }

    // -------------------
    // GETTERS AND SETTERS
    // -------------------
    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Article getArticle() {
        return article;
    }

    private void setArticle(Article article) {
        this.article = article;
    }

    // --------------
    // toString
    // --------------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Article [Article=" + this.article.getDesignation() + "], ");
        sb.append("Quantité [qte=" + this.qte + "]");

        return sb.toString();
    }

}
