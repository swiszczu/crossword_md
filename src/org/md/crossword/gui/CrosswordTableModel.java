package org.md.crossword.gui;

import org.md.crossword.app.Crossword;
import org.md.crossword.app.Progr;
import org.md.crossword.board.BoardCell;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

// TODO: Auto-generated Javadoc
/**
 * Created with IntelliJ IDEA.
 * User: Grzegorz
 * Date: 29.11.13
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public class CrosswordTableModel extends AbstractTableModel {
    
    /**
     * Instantiates a new crossword table model.
     *
     * @param cw the cw
     */
    public CrosswordTableModel(Crossword cw)
    {

    }
    
    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount() {
        return Progr.getCurrentCrossword().board().width()+1;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount() {
        return Progr.getCurrentCrossword().board().height()+1;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#getColumnName(int)
     */
    public String getColumnName(int col) {
        return "";
    }

    /* (non-Javadoc)
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt(int row, int col) {
        if(row==0||col==0)
            return "";
        return Progr.getCurrentCrossword().board().cell(col-1,row-1).content();
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    public Class getColumnClass(int c) {
        return BoardCell.class;
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     */
    public boolean isCellEditable(int row, int col) {
        //return true;
       return (row!=0&&col!=0)&&!Progr.getCurrentCrossword().board().cell(col-1,row-1).content().equals("");
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(Object value, int row, int col) {
        if(row==0||col==0)
            return;
        Progr.getCurrentCrossword().board().cell(col-1,row-1).setContent((String)value);
        fireTableCellUpdated(row, col);
    }
}
