package org.md.crossword.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// TODO: Auto-generated Javadoc
/**
 * Created with IntelliJ IDEA.
 * User: Grzegorz
 * Date: 29.11.13
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
public class GtTableEmptyCellRenderer extends GtTableCellRenderer {

    /* (non-Javadoc)
     * @see org.md.crossword.gui.GtTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
            //this.setBackground(new Color(0,0,0));

            this.setBorder(BorderFactory.createEmptyBorder());
            return this;
    }
}
