package fr.lubac.boutiqueSki.ihm.ecrCatalogue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import fr.lubac.boutiqueSki.ihm.ArticleController;

public class EcranCatalogue extends JFrame {
    private JTable tableau;
    private JPanel recherchePanel;
    private JPanel boutonEditionCataloguePanel;
    private JButton btnRechercheMarque, btnRecherceMotCle, btnEditionCatalogue, btnAffichetTout;
    private JTextField txtMotCle, txtMarque;
    private JComboBox<String> comboBoxMarque;
    TableCatalogueModel tableCatalogueModel;

    public EcranCatalogue() {
        this.setTitle("Catalogue");
        this.setSize(new Dimension(1000, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initIHM();
        pack();
    }

    private void initIHM() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JScrollPane(getTableau()));
        panel.add(getRecherchePanel());
        panel.add(getBoutonEditionCataloguePanel());
        // this.setContentPane(new JScrollPane(getTableau()));
        this.setContentPane(panel);
    }

    public JTable getTableau() {
        if (tableau == null) {
            tableau = new JTable();

            tableau.setModel(getTableCatalogueModel());

            tableau.setPreferredScrollableViewportSize(new Dimension(1000, 200));
            tableau.setFillsViewportHeight(true);
            tableau.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableau.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableau.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableau.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableau.getColumnModel().getColumn(4).setPreferredWidth(50);
            tableau.getColumnModel().getColumn(5).setPreferredWidth(50);
            tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableau.setRowHeight(30);
            tableau.getColumnModel().getColumn(0).setCellRenderer(new ImageTypeTableCellRenderer());
        }
        return tableau;
    }

    public TableCatalogueModel getTableCatalogueModel() {
        if (tableCatalogueModel == null) {
            tableCatalogueModel = new TableCatalogueModel(ArticleController.getInstance().getCatalogue());
        }

        return tableCatalogueModel;
    }

    public JPanel getBoutonEditionCataloguePanel() {
        if (boutonEditionCataloguePanel == null) {
            boutonEditionCataloguePanel = new JPanel();
            boutonEditionCataloguePanel.add(getBtnEditionCatalogue());
        }
        return boutonEditionCataloguePanel;
    }

    public JButton getBtnEditionCatalogue() {
        if (btnEditionCatalogue == null) {
            btnEditionCatalogue = new JButton("Editer le catalogue");
            btnEditionCatalogue.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.getInstance().showCatalogueEditor();
                }

            });
        }
        return btnEditionCatalogue;
    }

    public JButton getBtnRechercheMarque() {
        if (btnRechercheMarque == null) {
            btnRechercheMarque = new JButton("Recherche par marque");
            btnRechercheMarque.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.getInstance()
                            .fitlerCatalogueByMarque((String) getComboBoxMarque().getSelectedItem());

                }

            });
        }
        return btnRechercheMarque;
    }

    public JButton getBtnRecherceMotCle() {
        if (btnRecherceMotCle == null) {
            btnRecherceMotCle = new JButton("Recherche par mot clé");
            btnRecherceMotCle.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ArticleController.getInstance().fitlerCatalogueByMotCle(getTxtMotCle().getText());
                }

            });
        }
        return btnRecherceMotCle;
    }

    public JButton getBtnAffichetTout() {
        if (btnAffichetTout == null) {
            btnAffichetTout = new JButton("Afficher tout");
            btnAffichetTout.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    getTableCatalogueModel().setCatalogue(ArticleController.getInstance().getCatalogue());
                }

            });
        }
        return btnAffichetTout;
    }

    public JTextField getTxtMotCle() {
        if (txtMotCle == null) {
            txtMotCle = new JTextField(15);
            txtMotCle.setText("Entrez votre mot clé ici...");
        }
        return txtMotCle;
    }

    public JComboBox<String> getComboBoxMarque() {
        if (comboBoxMarque == null) {
            // For list retrieves marques from all product in catalogue :
            comboBoxMarque = new JComboBox<String>(ArticleController.getInstance().retrieveMarqueList());
        }
        return comboBoxMarque;
    }

    public JPanel getRecherchePanel() {
        if (recherchePanel == null) {
            recherchePanel = new JPanel();
            recherchePanel.setLayout(new FlowLayout());
            recherchePanel.add(getComboBoxMarque());
            recherchePanel.add(getBtnRechercheMarque());
            recherchePanel.add(getTxtMotCle());
            recherchePanel.add(getBtnRecherceMotCle());
            recherchePanel.add(getBtnAffichetTout());
        }
        return recherchePanel;
    }

}
