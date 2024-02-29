package fr.lubac.boutiqueSki.bo;

public abstract class Article {
    private int idArticle;
    private String reference, marque, designation;
    private float prixUnitaire;
    private int qteStock;

    // ------------
    // CONSTRUCTORS
    // -----------
    public Article(int idArticle, String marque, String reference, String designation, float prixUnitaire,
            int qteStock) {
        this.idArticle = idArticle;
        this.reference = reference;
        this.marque = marque;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.qteStock = qteStock;
    }

    public Article(String marque, String reference, String designation, float prixUnitaire, int qteStock) {
        this.reference = reference;
        this.marque = marque;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.qteStock = qteStock;
    }

    // -------------------
    // GETTERS AND SETTERS
    // -------------------

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    // --------------
    // toString
    // --------------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Article id [idArticle=" + this.idArticle + ", ");
        sb.append("reference=" + this.reference + ", ");
        sb.append("marque=" + this.marque + ", ");
        sb.append("designation=" + this.designation + ", ");
        sb.append("prixUnitaire=" + this.prixUnitaire + ", ");
        sb.append("qteStock=" + this.qteStock + "]");

        return sb.toString();
    }

}