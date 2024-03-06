package fr.lubac.boutiqueSki.ihm.ecrCatalogue;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import fr.lubac.boutiqueSki.bll.BLLException;
import fr.lubac.boutiqueSki.bll.CatalogueManager;
import fr.lubac.boutiqueSki.bo.Article;
import fr.lubac.boutiqueSki.bo.Ski;
import fr.lubac.boutiqueSki.ihm.ArticleController;

public class TableCatalogueModel extends AbstractTableModel {

    private List<Article> catalogue;

    private final String[] headers = { "Type", "Référence", "Marque", "Désignation", "Prix €", "Stock" };

    public TableCatalogueModel(List<Article> catalogue) {
        this.catalogue = catalogue;
    }

    @Override
    public int getRowCount() {
        return catalogue.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    public String getColumnName(int col) {
        return headers[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = this.catalogue.get(rowIndex) instanceof Ski ? "Ski" : "Combi";
                break;
            case 1:
                value = this.catalogue.get(rowIndex).getReference();
                break;
            case 2:
                value = this.catalogue.get(rowIndex).getMarque();
                break;
            case 3:
                value = this.catalogue.get(rowIndex).getDesignation();
                break;
            case 4:
                value = this.catalogue.get(rowIndex).getPrixUnitaire();
                break;
            case 5:
                value = this.catalogue.get(rowIndex).getQteStock();
                break;
        }
        return value;

    }

}
