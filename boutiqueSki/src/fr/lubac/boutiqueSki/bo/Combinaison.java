package fr.lubac.boutiqueSki.bo;

public class Combinaison extends Article {

    private String couleur;

    // ------------
    // CONSTRUCTORS
    // ------------
    // public Combinaison () {

    // }

    public Combinaison(int idArticle, String marque, String reference, String designation, float prixUnitaire,
            int qteStock, String couleur) {
        super(idArticle, marque, reference, designation, prixUnitaire, qteStock);
        this.couleur = couleur;
    }

    public Combinaison(String marque, String reference, String designation, float prixUnitaire, int qteStock,
            String couleur) {
        super(marque, reference, designation, prixUnitaire, qteStock);
        this.couleur = couleur;
    }

    // -------------------
    // GETTERS AND SETTERS
    // -------------------
    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    // ----------
    // To string
    // ---------
    @Override
    public String toString() {
        return (super.toString() + ", Combinaison [couleur=" + this.couleur + "]");
    }
}
