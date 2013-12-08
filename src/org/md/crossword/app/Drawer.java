/*
 * 
 */
package org.md.crossword.app;

import org.md.crossword.board.Board;

import java.awt.*;

/**
 * Created with Eclipse
 *
 * @author Marcin Długosz
 *         Date: 29.11.13
 *         Time: 11:43
 */
public class Drawer extends Panel {

    /**
     * The cw.
     */
    private Crossword cw;

    /**
     * Instantiates a new drawer.
     *
     * @param cw the cw
     */
    public Drawer(Crossword cw) {
        this.cw = cw;
    }

    /* (non-Javadoc)
     * @see java.awt.Container#paint(java.awt.Graphics)
     */
    public void paint(Graphics arg0) {
        Board board = Progr.getCurrentCrossword().boardCopy();
        for (int i = 0; i < board.width(); ++i) {
            for (int j = 0; j < board.height(); ++j) {
                if (board.cell(i, j).content().length() > 0) {
                    Graphics g = getGraphics();
                    g.drawRect(i * 40 + 50, j * 40 + 50, 40, 40);
                    g.setColor(Color.white);
                    g.drawRect(i * 40 + 52, j * 40 + 52, 36, 36);
                    g.setColor(Color.black);
                    g.drawString(board.cell(i, j).content(), i * 40 + 65,
                            j * 40 + 75);
                }
            }
        }
    }
}
