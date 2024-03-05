package fr.lubac.boutiqueSki.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.lubac.boutiqueSki.bll.BLLException;
import fr.lubac.boutiqueSki.bll.CatalogueManager;
import fr.lubac.boutiqueSki.bo.Article;

public class EcranArticle extends JFrame {
    private JLabel lblRef, lblDesign, lblMarque, lblStock, lblPrix, lblType, lblLongueur, lblCouleur;
    private JTextField txtRef, txtDesign, txtMarque, txtStock, txtPrix;
    private CatalogueManager cmgr;
    private List<Article> listeArticles;

    public EcranArticle() {

        try {
            cmgr = CatalogueManager.getInstance();
        } catch (BLLException e) {
            e.printStackTrace();
        }

        try {
            listeArticles = cmgr.getCatalogue();
        } catch (BLLException e) {
            e.printStackTrace();
        }

        this.setTitle("Catalogue articles");
        this.setSize(new Dimension(500, 1000));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initIHM();
        pack();
    }

    private void initIHM() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // First 5 lines : labels and textFields
        // labels :
        gbc.gridx = 0;
        // line 1 to 8
        gbc.gridy = 0;
        panel.add(getLblRef(), gbc);
        gbc.gridy = 1;
        panel.add(getLblDesign(), gbc);
        gbc.gridy = 2;
        panel.add(getLblMarque(), gbc);
        gbc.gridy = 3;
        panel.add(getLblStock(), gbc);
        gbc.gridy = 4;
        panel.add(getLblPrix(), gbc);
        gbc.gridy = 5;
        panel.add(getLblType(), gbc);
        gbc.gridy = 6;
        panel.add(getLblLongueur(), gbc);
        gbc.gridy = 7;
        panel.add(getLblCouleur(), gbc);
        // textFields :
        gbc.gridx = 1;
        // line 1 to 5
        gbc.gridy = 0;
        panel.add(getTxtRef(), gbc);
        gbc.gridy = 1;
        panel.add(getTxtDesign(), gbc);
        gbc.gridy = 2;
        panel.add(getTxtMarque(), gbc);
        gbc.gridy = 3;
        panel.add(getTxtStock(), gbc);
        gbc.gridy = 4;
        panel.add(getTxtPrix(), gbc);
        // Radio boutons :
        // line 6
        gbc.gridy = 5;
        // TO COMPLETE

        // Initialisation of txtField with first article:
        getTxtRef().setText(listeArticles.get(0).getReference().trim());
        getTxtDesign().setText(listeArticles.get(0).getDesignation().trim());
        getTxtMarque().setText(listeArticles.get(0).getMarque().trim());
        getTxtStock().setText(String.valueOf(listeArticles.get(0).getQteStock()));
        getTxtPrix().setText(String.valueOf(listeArticles.get(0).getPrixUnitaire()));

        // Link this panel to the JFrame of ArticleScreen
        this.setContentPane(panel);
    }

    // --------
    // GETTERS
    // --------
    public JLabel getLblRef() {
        if (lblRef == null) {
            lblRef = new JLabel("Référence");
        }
        return lblRef;
    }

    public JLabel getLblDesign() {
        if (lblDesign == null) {
            lblDesign = new JLabel("Désignation");
        }
        return lblDesign;
    }

    public JLabel getLblMarque() {
        if (lblMarque == null) {
            lblMarque = new JLabel("Marque");
        }
        return lblMarque;
    }

    public JLabel getLblStock() {
        if (lblStock == null) {
            lblStock = new JLabel("Stock");
        }
        return lblStock;
    }

    public JLabel getLblPrix() {
        if (lblPrix == null) {
            lblPrix = new JLabel("Prix");
        }
        return lblPrix;
    }

    public JTextField getTxtRef() {
        if (txtRef == null) {
            txtRef = new JTextField(30);
        }
        return txtRef;
    }

    public JTextField getTxtDesign() {
        if (txtDesign == null) {
            txtDesign = new JTextField(30);
        }
        return txtDesign;
    }

    public JTextField getTxtMarque() {
        if (txtMarque == null) {
            txtMarque = new JTextField(30);
        }
        return txtMarque;
    }

    public JTextField getTxtStock() {
        if (txtStock == null) {
            txtStock = new JTextField(30);
        }
        return txtStock;
    }

    public JTextField getTxtPrix() {
        if (txtPrix == null) {
            txtPrix = new JTextField(30);
        }
        return txtPrix;
    }

    public JLabel getLblType() {
        if (lblType == null) {
            lblType = new JLabel("Type");

        }
        return lblType;
    }

    public JLabel getLblLongueur() {
        if (lblLongueur == null) {
            lblLongueur = new JLabel("Longueur");
        }
        return lblLongueur;
    }

    public JLabel getLblCouleur() {
        if (lblCouleur == null) {
            lblCouleur = new JLabel("Couleur");
        }
        return lblCouleur;
    }

}
