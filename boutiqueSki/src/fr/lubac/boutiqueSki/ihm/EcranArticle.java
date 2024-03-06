package fr.lubac.boutiqueSki.ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.lubac.boutiqueSki.bll.BLLException;
import fr.lubac.boutiqueSki.bll.CatalogueManager;
import fr.lubac.boutiqueSki.bo.Article;

public class EcranArticle extends JFrame {
    private JLabel lblRef, lblDesign, lblMarque, lblStock, lblPrix, lblType, lblLongueur, lblCouleur;
    private JTextField txtRef, txtDesign, txtMarque, txtStock, txtPrix;
    private JPanel panelType, panelLongueur, panelButton;
    private JRadioButton rbtnSki, rbtnCombi;
    private JCheckBox cb150cm, cb166cm;
    private JComboBox<String> comboBoxCouleur;
    private JButton btnPrecedent, btnNouveau, btnSauvegarde, btnSupprimer, btnSuivant;
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
        this.setSize(new Dimension(500, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initIHM();
        pack();
    }

    private void initIHM() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Line 1
        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(getLblRef(), gbc);
        gbc.gridx = 1;
        panel.add(getTxtRef(), gbc);
        // Line2
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(getLblDesign(), gbc);
        gbc.gridx = 1;
        panel.add(getTxtDesign(), gbc);
        // line 3
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(getLblMarque(), gbc);
        gbc.gridx = 1;
        panel.add(getTxtMarque(), gbc);
        // line 4:
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(getLblStock(), gbc);
        gbc.gridx = 1;
        panel.add(getTxtStock(), gbc);
        // line 5
        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(getLblPrix(), gbc);
        gbc.gridx = 1;
        panel.add(getTxtPrix(), gbc);
        // line 6
        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(getLblType(), gbc);
        gbc.gridx = 1;
        panel.add(getPanelType(), gbc);
        // line 7
        gbc.gridy = 6;
        gbc.gridx = 0;
        panel.add(getLblLongueur(), gbc);
        gbc.gridx = 1;
        panel.add(getPanelLongueur(), gbc);
        // line 8
        gbc.gridy = 7;
        gbc.gridx = 0;
        panel.add(getLblCouleur(), gbc);
        gbc.gridx = 1;
        panel.add(getComboBoxCouleur(), gbc);
        // line 9
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(getPanelButton(), gbc);

        // gbc.gridy = 6;
        //

        // gbc.gridy = 7;
        // gbc.gridheight = 1;
        // panel.add(getLblCouleur(), gbc);
        // textFields :
        // gbc.gridx = 1;
        // // line 1 to 5

        // // Radio boutons :
        // // line 6
        // gbc.gridy = 5;
        // gbc.gridx = 1;
        // // TO COMPLETE
        // panel.add(getRbtnSki(), gbc);
        // gbc.gridy = 6;
        // panel.add(getRbtnCombi(), gbc);

        // Initialisation of txtField with first article:
        getTxtRef().setText(listeArticles.get(0).getReference().trim());
        getTxtDesign().setText(listeArticles.get(0).getDesignation().trim());
        getTxtMarque().setText(listeArticles.get(0).getMarque().trim());
        getTxtStock().setText(String.valueOf(listeArticles.get(0).getQteStock()));
        getTxtPrix().setText(String.valueOf(listeArticles.get(0).getPrixUnitaire()));

        // Link this panel to the JFrame of ArticleScreen
        this.setContentPane(panel);
    }

    // -------------
    // CONTROLS VIEW
    // -------------

    protected void afficherArticle() {

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

    public JRadioButton getRbtnSki() {
        if (rbtnSki == null) {
            rbtnSki = new JRadioButton("Ski");
        }
        return rbtnSki;
    }

    public JRadioButton getRbtnCombi() {
        if (rbtnCombi == null) {
            rbtnCombi = new JRadioButton("Combinaison");
        }
        return rbtnCombi;
    }

    public JCheckBox getCb150cm() {
        if (cb150cm == null) {
            cb150cm = new JCheckBox("150 cm");
        }
        return cb150cm;
    }

    public JCheckBox getCb166cm() {
        if (cb166cm == null) {
            cb166cm = new JCheckBox("166 cm");
        }
        return cb166cm;
    }

    public JPanel getPanelType() {
        if (panelType == null) {
            panelType = new JPanel();
            panelType.setLayout(new BoxLayout(panelType, BoxLayout.PAGE_AXIS));
            panelType.add(getRbtnSki());
            panelType.add(getRbtnCombi());
            // group radios buttons :
            ButtonGroup radiosGroup = new ButtonGroup();
            radiosGroup.add(getRbtnSki());
            radiosGroup.add(getRbtnCombi());
        }
        return panelType;
    }

    public JPanel getPanelLongueur() {
        if (panelLongueur == null) {
            panelLongueur = new JPanel();
            panelLongueur.setLayout(new BoxLayout(panelLongueur, BoxLayout.PAGE_AXIS));
            panelLongueur.add(getCb150cm());
            panelLongueur.add(getCb166cm());
            ButtonGroup checkboxesGroup = new ButtonGroup();
            checkboxesGroup.add(getCb150cm());
            checkboxesGroup.add(getCb166cm());
        }
        return panelLongueur;
    }

    public JComboBox<String> getComboBoxCouleur() {
        if (comboBoxCouleur == null) {
            String[] couleurs = { "bleu", "rouge", "noir", "vert" };
            comboBoxCouleur = new JComboBox<String>(couleurs);
        }
        return comboBoxCouleur;
    }

    public JButton getBtnPrecedent() {
        if (btnPrecedent == null) {
            btnPrecedent = new JButton();
            try {
                Image img = ImageIO.read(getClass().getResource("./images/Back24.gif"));
                btnPrecedent.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return btnPrecedent;
    }

    public JButton getBtnNouveau() {
        if (btnNouveau == null) {
            btnNouveau = new JButton();
            try {
                Image img = ImageIO.read(getClass().getResource("./images/New24.gif"));
                btnNouveau.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return btnNouveau;
    }

    public JButton getBtnSauvegarde() {
        if (btnSauvegarde == null) {
            btnSauvegarde = new JButton();
            try {
                Image img = ImageIO.read(getClass().getResource("./images/Save24.gif"));
                btnSauvegarde.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return btnSauvegarde;
    }

    public JButton getBtnSupprimer() {
        if (btnSupprimer == null) {
            btnSupprimer = new JButton();
            try {
                Image img = ImageIO.read(getClass().getResource("./images/Delete24.gif"));
                btnSupprimer.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return btnSupprimer;
    }

    public JButton getBtnSuivant() {
        if (btnSuivant == null) {
            btnSuivant = new JButton();
            try {
                Image img = ImageIO.read(getClass().getResource("./images/Forward24.gif"));
                btnSuivant.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return btnSuivant;
    }

    public JPanel getPanelButton() {
        if (panelButton == null) {
            panelButton = new JPanel();
            panelButton.setLayout(new FlowLayout());
            panelButton.add(getBtnPrecedent());
            panelButton.add(getBtnNouveau());
            panelButton.add(getBtnSauvegarde());
            panelButton.add(getBtnSupprimer());
            panelButton.add(getBtnSuivant());
        }
        return panelButton;
    }

}
