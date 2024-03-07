package fr.lubac.boutiqueSki.ihm;

public interface IPanelBoutonObserver {
    // Implementation of Observer pattern
    void precedent();

    void suivant();

    void nouveau();

    void enregistrer();

    void supprimer();
}
