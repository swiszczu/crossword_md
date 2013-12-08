package org.md.crossword.app;

import org.md.crossword.board.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Strategy.
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
