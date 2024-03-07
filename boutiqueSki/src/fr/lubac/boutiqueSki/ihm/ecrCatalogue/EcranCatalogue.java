package fr.lubac.boutiqueSki.ihm.ecrCatalogue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import fr.lubac.boutiqueSki.bo.Article;
import fr.lubac.boutiqueSki.ihm.ArticleController;

public class EcranCatalogue extends JFrame {
    private JTable tableau;
    private JPanel recherchePanel;
    private JPanel boutonEditionCataloguePanel;
    private JButton btnRechercheMarque, btnRecherceMotCle, btnEditionCatalogue;
    private JTextField txtMotCle;
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
        }
        return boutonEditionCataloguePanel;
    }

    public JButton getBtnRechercheMarque() {
        if (btnRechercheMarque == null) {
            btnRechercheMarque = new JButton("Recherche par marque");
        }
        return btnRechercheMarque;
    }

    public JButton getBtnRecherceMotCle() {
        if (btnRecherceMotCle == null) {
            btnRecherceMotCle = new JButton("Recherche par mot clé");
        }
        return btnRecherceMotCle;
    }

    public JButton getBtnEditionCatalogue() {
        return btnEditionCatalogue;
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
            String[] marques = { "ROSSIGNOL", "WEDZE", "HEAD", "ATOMIC" }; // To Improve that this list should be shared
                                                                           // by CatalogueManager and this view
                                                                           // (interface ?)
            comboBoxMarque = new JComboBox<String>(marques);
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
        }
        return recherchePanel;
    }

}
