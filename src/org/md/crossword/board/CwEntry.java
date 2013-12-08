package org.md.crossword.board;

import java.io.Serializable;

import org.md.crossword.dictionary.Entry;

// TODO: Auto-generated Javadoc
/**
 * Represents the org.md.crossword entry
 * 
 */
public class CwEntry extends Entry implements Serializable {
	
	/**
	 * Constructor.
	 *
	 * @param word Word to store
	 * @param clue Clue to store
	 * @param x X coordinate of the beginning of the word
	 * @param y Y coordinate of the begining of the word
	 * @param d Direction of the word in the org.md.crossword
	 */
	public CwEntry(String word, String clue, int x, int y, Direction d) {
		super(word, clue);
		this.x = x;
		this.y = y;
		this.d = d;
	}

	/**
	 * Represents the direction of the word in the org.md.crossword
	 * 
	 */
	public enum Direction {
		
		/** The horiz. */
		HORIZ, 
 /** The vert. */
 VERT
	}

	/**
	 * Get X coordinate of the word in the org.md.crossword
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get direction of the entry.
	 *
	 * @return the direction
	 */
	public Direction direction() {
		return d;
	}

	/**
	 * Get Y coordinate of the word in the org.md.crossword
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	
	/* (non-Javadoc)
	 * @see org.md.crossword.dictionary.Entry#toString()
	 */
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(x).append(y).append(d);
		return out.toString();
	}

	/** The y. */
	private int x, y;
	
	/** The d. */
	private Direction d;
}
