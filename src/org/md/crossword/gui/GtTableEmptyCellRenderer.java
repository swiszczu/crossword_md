package org.md.crossword.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created with Eclipse
 *
 * @author Marcin Długosz
 *         Date: 29.11.13
 *         Time: 11:43
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
