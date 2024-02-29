package fr.lubac.boutiqueSki.bo;

public class Ski extends Article {
    private int longueur;

    // -------------
    // CONSTRUCTORS
    // ------------
    public Ski(int idArticle, String marque, String reference, String designation, float prixUnitaire, int qteStock,
            int longueur) {
        super(idArticle, marque, reference, designation, prixUnitaire, qteStock);
        this.longueur = longueur;
    }

    public Ski(String marque, String reference, String designation, float prixUnitaire, int qteStock, int longueur) {
        super(marque, reference, designation, prixUnitaire, qteStock);
        this.longueur = longueur;
    }

    // -------------------
    // GETTERS AND SETTERS
    // -------------------
    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    // --------
    // ToString
    // --------
    @Override
    public String toString() {
        return (super.toString() + ", Ski [longueur=" + this.longueur + "]");
    }

}
