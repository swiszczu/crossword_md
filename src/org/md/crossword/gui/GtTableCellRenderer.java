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
public class GtTableCellRenderer extends DefaultTableCellRenderer {

    /**
     * Instantiates a new gt table cell renderer.
     */
    public GtTableCellRenderer()
    {
        setHorizontalAlignment(JLabel.CENTER);
    }



    /* (non-Javadoc)
     * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        //this.setBackground(new Color(0,0,0));
        Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));

        return c;
    }
}
