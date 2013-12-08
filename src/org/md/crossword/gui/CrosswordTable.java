package org.md.crossword.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

// TODO: Auto-generated Javadoc
/**
 * Created with Eclipse
 * User: Marcin D�ugosz
 * Date: 29.11.13
 * Time: 11:43
 * To change this template use File | Settings | File Templates.
 */
public class CrosswordTable extends JTable {
    
    /** The gt cell render. */
    GtTableCellRenderer gtCellRender=new GtTableCellRenderer();
    
    /** The gt empty cell render. */
    GtTableCellRenderer gtEmptyCellRender=new GtTableEmptyCellRenderer();
    
    /**
     * Instantiates a new crossword table.
     *
     * @param dm the dm
     */
    public CrosswordTable(TableModel dm) {
        super(dm, null, null);
        //ukryj header
        this.setTableHeader(null);
        this.setCellSelectionEnabled(true);
        this.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        this.setRowHeight(20);
        setPreferredScrollableViewportSize(new Dimension(600, 400));
        this.setPreferredSize(new Dimension(600,400));
        setFillsViewportHeight(true);
        setDefaultRenderer(Boolean.class, new GtTableCellRenderer());
        setIntercellSpacing(new Dimension(1,1));
        setShowGrid(false);
        for(int i=this.getColumnCount()-1;i>=0;i--)
        {
            this.getColumnModel().getColumn(i).setWidth(20);
        }

    }
    
    /* (non-Javadoc)
     * @see javax.swing.JTable#getCellRenderer(int, int)
     */
    @Override
    public TableCellRenderer getCellRenderer (int arg0, int arg1) {
        if(getValueAt(arg0,arg1).equals(""))
            return gtEmptyCellRender;
        else
            return gtCellRender;
    }

    /* (non-Javadoc)
     * @see javax.swing.JTable#getCellEditor(int, int)
     */
    @Override
    public TableCellEditor getCellEditor(int row, int col)
    {
        //@TODO: Jak wpisujemy litere to żeby czyściło przed tym pole

        JTextField cellEditor=new JTextField(1);
        cellEditor.setDocument(new JTextFieldLimit(1));
        return new DefaultCellEditor(cellEditor);
    }

}
class JTextFieldLimit extends PlainDocument {
    private int limit;
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    JTextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}