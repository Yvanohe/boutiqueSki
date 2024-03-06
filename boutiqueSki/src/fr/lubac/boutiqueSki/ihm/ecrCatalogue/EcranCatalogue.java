package fr.lubac.boutiqueSki.ihm.ecrCatalogue;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.lubac.boutiqueSki.ihm.ArticleController;

public class EcranCatalogue extends JFrame {

    public EcranCatalogue() {
        this.setTitle("Catalogue");
        this.setSize(new Dimension(1000, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initIHM();
        pack();
    }

    private void initIHM() {
        JTable tableau = new JTable(new TableCatalogueModel(ArticleController.getInstance().getCatalogue()));
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

        this.setContentPane(new JScrollPane(tableau));
    }

}
