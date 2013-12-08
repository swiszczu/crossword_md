package org.md.crossword.app;

import org.md.crossword.board.Board;
import org.md.crossword.board.CwEntry;

/**
 * Created with Eclipse
 *
 * @author Marcin Długosz
 *         Date: 27.11.13
 *         Time: 9:42
 */
public abstract class Strategy {

    /**
     * Find entry.
     *
     * @param cw the cw
     * @return the cw entry
     * @throws StrategyException the strategy exception
     */
    public abstract CwEntry findEntry(Crossword cw) throws StrategyException;

    /**
     * Update board.
     *
     * @param b the b
     * @param e the e
     */
    public abstract void updateBoard(Board b, CwEntry e);
}
