package fr.lubac.boutiqueSki.bo;

import java.util.ArrayList;
import java.util.List;

public class Panier {
    private float montant;
    private List<Ligne> lignesPanier = new ArrayList<>();

    // -------------
    // CONSTRUCTORS
    // ------------
    public Panier() {

    }

    // -------------------
    // GETTERS AND SETTERS
    // -------------------
    public float getMontant() {
        return this.montant;
    }

    /**
     * Retourne la ligne sélectionnée du Panier ou null si pas trouvée *
     * 
     * @param index
     * @return
     */
    public Ligne getLigne(int index) {
        return lignesPanier.get(index);
    }

    public List<Ligne> getLignesPanier() {
        return this.lignesPanier;
    }

    public void addLigne(Article article, int qte) {
        this.lignesPanier.add(new Ligne(qte, article));
        // calcul du nouveau montant du panier
        this.montant = montant + qte * (article.getPrixUnitaire());
    }

    public void updateLigne(int index, int newQte) {
        int oldQte = this.lignesPanier.get(index).qte;
        // changement de la quantité de l'article à l'index des lignes du panier
        this.lignesPanier.get(index).setQte(newQte);
        // calcul du nouveau montant par différence entre nouvelle quantité et ancienne
        // quantié de larticle
        this.montant = this.montant + (newQte - oldQte) * lignesPanier.get(index).getArticle().getPrixUnitaire();
    }

    public void removeLigne(int index) {
        // montant de la ligne à retirer
        float montantLigne = this.lignesPanier.get(index).getQte()
                * this.lignesPanier.get(index).getArticle().getPrixUnitaire();
        // on retire la ligne :
        this.lignesPanier.remove(index);
        // nouveau montant :
        this.montant = this.montant - montantLigne;
    }

    // --------------
    // toString
    // --------------
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Montant [montant=" + this.montant + "]\n");
        for (Ligne l : lignesPanier) {
            sb.append(l.toString() + "\n");
        }
        return sb.toString();
    }
}
