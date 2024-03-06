package fr.lubac.boutiqueSki.ihm.ecrArticle;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class EcranArticle extends JFrame {
    private JLabel lblRef, lblDesign, lblMarque, lblStock, lblPrix, lblType, lblLongueur, lblCouleur;
    private JTextField txtRef, txtDesign, txtMarque, txtStock, txtPrix;
    private JPanel panelType, panelLongueur, panelButton;
    private JRadioButton rbtnSki, rbtnCombi;
    private JCheckBox cb150cm, cb166cm;
    private JComboBox<String> comboBoxCouleur;
    private JButton btnPrecedent, btnNouveau, btnSauvegarde, btnSupprimer, btnSuivant;
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
            String[] couleurs = { "bleu", "rouge", "noir", "vert" };
            comboBoxCouleur = new JComboBox<String>(couleurs);
        }
        return comboBoxCouleur;
    }

    public JButton getBtnPrecedent() {
        if (btnPrecedent == null) {
            btnPrecedent = new JButton();
            btnPrecedent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.getInstance().previous();
                }
            });

            try {
                Image img = ImageIO.read(getClass().getResource("../images/Back24.gif"));
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
            btnNouveau.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.getInstance().newArticle();

                }
            });
            try {
                Image img = ImageIO.read(getClass().getResource("../images/New24.gif"));
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
            btnSauvegarde.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.getInstance().save();
                }
            });
            try {
                Image img = ImageIO.read(getClass().getResource("../images/Save24.gif"));
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
            btnSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.getInstance().deleteArticle();
                }

            });
            try {
                Image img = ImageIO.read(getClass().getResource("../images/Delete24.gif"));
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
            btnSuivant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.getInstance().next();
                }
            });
            try {
                Image img = ImageIO.read(getClass().getResource("../images/Forward24.gif"));
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

    public Integer getiDArticleCourant() {
        return iDArticleCourant;
    }

    public void infoErreur(String msg) {
        JOptionPane.showMessageDialog(EcranArticle.this, msg, "", JOptionPane.ERROR_MESSAGE);
    }

}
