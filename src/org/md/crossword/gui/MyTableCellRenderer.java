package org.md.crossword.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created with Eclipse
 *
 * @author Marcin Długosz
 *         Date: 29.11.13
 *         Time: 11:43
 */
public class MyTableCellRenderer extends DefaultTableCellRenderer {

    /**
     * Instantiates a new gt table cell renderer.
     */
    public MyTableCellRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    /* (non-Javadoc)
     * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        //this.setBackground(new Color(0,0,0));
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        return c;
    }
}
