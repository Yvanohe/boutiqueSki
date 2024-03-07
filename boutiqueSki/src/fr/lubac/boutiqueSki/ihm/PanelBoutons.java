package fr.lubac.boutiqueSki.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

//PanelBouton component to be used in ecrArticle
public class PanelBoutons extends JPanel {
    private JButton btnPrecedent, btnNouveau, btnSauvegarde, btnSupprimer, btnSuivant;
    private List<IPanelBoutonObserver> observateurs;

    // Constructor
    public PanelBoutons() {
        this.add(getBtnPrecedent());
        this.add(getBtnNouveau());
        this.add(getBtnSauvegarde());
        this.add(getBtnSupprimer());
        this.add(getBtnSuivant());
        this.observateurs = new ArrayList<>();
    }

    public void addPanelBoutonObserver(IPanelBoutonObserver observateur) {
        this.observateurs.add(observateur);
    }

    // -------
    // GETTERS
    // -------
    public JButton getBtnPrecedent() {
        if (btnPrecedent == null) {
            btnPrecedent = new JButton();
            btnPrecedent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (IPanelBoutonObserver obs : observateurs) {
                        obs.precedent();
                    }
                }
            });

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
            btnNouveau.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (IPanelBoutonObserver obs : observateurs) {
                        obs.nouveau();
                    }
                }
            });
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
            btnSauvegarde.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (IPanelBoutonObserver obs : observateurs) {
                        obs.enregistrer();
                    }
                }
            });
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
            btnSupprimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (IPanelBoutonObserver obs : observateurs) {
                        obs.supprimer();
                    }
                }
            });
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
            btnSuivant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (IPanelBoutonObserver obs : observateurs) {
                        obs.suivant();
                    }
                }
            });
            try {
                Image img = ImageIO.read(getClass().getResource("./images/Forward24.gif"));
                btnSuivant.setIcon(new ImageIcon(img));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return btnSuivant;
    }

}
