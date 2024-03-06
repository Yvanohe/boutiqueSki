package fr.lubac.boutiqueSki.ihm.ecrCatalogue;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageTypeTableCellRenderer extends DefaultTableCellRenderer {
    private Icon skiImage;
    private Icon combiImage;

    public ImageTypeTableCellRenderer() {
        skiImage = new ImageIcon(getClass().getResource("../images/ski_icon.png"));
        combiImage = new ImageIcon(getClass().getResource("../images/icon_combi.jpg"));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String type = (String) value;
        setText("");

        if (type.equals("Ski")) {
            setIcon(skiImage);
        } else {
            setIcon(combiImage);
        }
        setHorizontalAlignment(SwingConstants.CENTER);
        return this;
    }

}
