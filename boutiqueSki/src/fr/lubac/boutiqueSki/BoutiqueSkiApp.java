package fr.lubac.boutiqueSki;

import javax.swing.SwingUtilities;

import fr.lubac.boutiqueSki.ihm.ArticleController;

public class BoutiqueSkiApp {

    public static void main(String[] args) {

        // Excute the main screen in a separate thread:
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArticleController.getInstance().startApp();
            }
        });
    }

}
