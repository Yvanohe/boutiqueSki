package fr.lubac.boutiqueSki.ihm;

import javax.swing.SwingUtilities;

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
