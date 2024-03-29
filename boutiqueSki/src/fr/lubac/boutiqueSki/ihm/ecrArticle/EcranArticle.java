package fr.lubac.boutiqueSki.ihm.ecrArticle;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import fr.lubac.boutiqueSki.bo.Article;
import fr.lubac.boutiqueSki.bo.Combinaison;
import fr.lubac.boutiqueSki.bo.Ski;
import fr.lubac.boutiqueSki.ihm.ArticleController;
import fr.lubac.boutiqueSki.ihm.IPanelBoutonObserver;
import fr.lubac.boutiqueSki.ihm.PanelBoutons;

public class EcranArticle extends JFrame implements IPanelBoutonObserver {
    private JLabel lblRef, lblDesign, lblMarque, lblStock, lblPrix, lblType, lblLongueur, lblCouleur;
    private JTextField txtRef, txtDesign, txtMarque, txtStock, txtPrix;
    private JPanel panelType, panelLongueur;
    private JRadioButton rbtnSki, rbtnCombi;
    private JCheckBox cb150cm, cb166cm;
    private JComboBox<String> comboBoxCouleur;
    private PanelBoutons panelButton;

    private Integer iDArticleCourant;

    public EcranArticle() {
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

        this.setContentPane(panel);
    }

    // -------------
    // CONTROLS VIEW
    // -------------

    public void afficherArticle(Article article) {
        this.iDArticleCourant = article.getIdArticle();

        getTxtRef().setText(article.getReference().trim());
        getTxtDesign().setText(article.getDesignation().trim());
        getTxtMarque().setText(article.getMarque().trim());
        getTxtStock().setText(String.valueOf(article.getQteStock()));
        getTxtPrix().setText(String.valueOf(article.getPrixUnitaire()));

        if (article instanceof Ski) {
            // type radio buttons
            getRbtnSki().setSelected(true);
            // Longueur checkbox :
            getCb150cm().setEnabled(true);
            getCb166cm().setEnabled(true);
            getCb150cm().setSelected(((Ski) article).getLongueur() == 150);
            getCb166cm().setSelected(((Ski) article).getLongueur() == 166);
            // Couleur list selection disabled:
            getComboBoxCouleur().setEnabled(false);
        }

        if (article instanceof Combinaison) {
            // type radio buttons
            getRbtnCombi().setSelected(true);
            // Longueur checkbox disabled :
            getCb150cm().setEnabled(false);
            getCb166cm().setEnabled(false);
            // couleur checkbox
            getComboBoxCouleur().setEnabled(true);
            getComboBoxCouleur().setSelectedItem(((Combinaison) article).getCouleur());
        }

        getRbtnSki().setEnabled(article.getIdArticle() == null);
        getRbtnCombi().setEnabled(article.getIdArticle() == null);
    }

    // --------
    // GETTERS
    // --------

    public Article getArticleCourant() {
        Article article = null;

        if (getRbtnCombi().isSelected()) {
            article = new Combinaison(getTxtMarque().getText(), getTxtRef().getText(), getTxtDesign().getText(),
                    Float.parseFloat(getTxtPrix().getText()), Integer.parseInt(getTxtStock().getText()),
                    (String) getComboBoxCouleur().getSelectedItem());
        } else {
            article = new Ski(getTxtMarque().getText(), getTxtRef().getText(), getTxtDesign().getText(),
                    Float.parseFloat(getTxtPrix().getText()), Integer.parseInt(getTxtStock().getText()),
                    getCb150cm().isSelected() ? 150 : 166);
        }

        if (this.iDArticleCourant != null) {
            article.setIdArticle(this.iDArticleCourant);
        }
        return article;

    }

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

            // if selected, desactivate color choice
            rbtnSki.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    getCb150cm().setEnabled(true);
                    getCb166cm().setEnabled(true);
                    getComboBoxCouleur().setEnabled(false);
                }

            });
        }
        return rbtnSki;
    }

    public JRadioButton getRbtnCombi() {
        if (rbtnCombi == null) {
            rbtnCombi = new JRadioButton("Combinaison");

            // If selected, desactivate LongeurSelection
            rbtnCombi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getCb150cm().setEnabled(false);
                    getCb166cm().setEnabled(false);
                    getComboBoxCouleur().setEnabled(true);
                }
            });
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
            String[] couleurs = { "bleu", "rouge", "noir", "vert" }; // To Improve that this list should be shared by
                                                                     // CatalogueManager and this view (interface ?)
            comboBoxCouleur = new JComboBox<String>(couleurs);
        }
        return comboBoxCouleur;
    }

    public PanelBoutons getPanelButton() {
        if (panelButton == null) {
            panelButton = new PanelBoutons();
            panelButton.addPanelBoutonObserver(this);
        }
        return panelButton;
    }

    public Integer getiDArticleCourant() {
        return iDArticleCourant;
    }

    // --------------------------------------
    // Interface IPanemBoutonObserver methods
    // --------------------------------------
    @Override
    public void precedent() {
        ArticleController.getInstance().previous();
    }

    @Override
    public void suivant() {
        ArticleController.getInstance().next();

    }

    @Override
    public void nouveau() {
        ArticleController.getInstance().newArticle();

    }

    @Override
    public void enregistrer() {
        ArticleController.getInstance().save();

    }

    @Override
    public void supprimer() {
        ArticleController.getInstance().deleteArticle();

    }

    // ----------------------------
    // Dialog window to show error
    // ----------------------------
    public void infoErreur(String msg) {
        JOptionPane.showMessageDialog(EcranArticle.this, msg, "", JOptionPane.ERROR_MESSAGE);
    }

}
