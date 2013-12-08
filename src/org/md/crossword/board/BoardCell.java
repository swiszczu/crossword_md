package org.md.crossword.board;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * Single cell with stored letter in the org.md.crossword
 * 
 */
public class BoardCell implements Serializable {
	
	/**
	 * The Enum Direction.
	 */
	public enum Direction {
		
		/** The horizontal. */
		HORIZONTAL, 
 /** The vertical. */
 VERTICAL
	};

	/**
	 * The Enum Place.
	 */
	public enum Place {
		
		/** The start. */
		START, 
 /** The inner. */
 INNER, 
 /** The end. */
 END
	};

	/**
	 * BoardCell constructor.
	 *
	 * @param x X coordinate of the cell
	 * @param y Y coordinate of the cell
	 */
	public BoardCell(int x, int y) {
		this.x = x;
		this.y = y;
		this.content = "";
		initPerms();
	}

	/**
	 * BoardCell constructor.
	 *
	 * @param x X coordinate of the cell
	 * @param y Y coordinate of the cell
	 * @param content Letter to store
	 */
	public BoardCell(int x, int y, String content) {
		this.x = x;
		this.y = y;
		this.content = content;
		initPerms();
	}

	/**
	 * Set stored letter in org.md.crossword
	 * 
	 * @param content
	 *            Letter to store
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Get stored letter.
	 *
	 * @return Stored letter
	 */
	public String content() {
		return content;
	}

	/**
	 * Check if the cell can be start cell vertically or horizontally.
	 *
	 * @return true, if successful
	 */
	public boolean canBeStartCell() {
		if (canBe(Direction.HORIZONTAL, Place.START)
				|| canBe(Direction.VERTICAL, Place.START))
			return true;
		return false;
	}

	/**
	 * Check if the cell can have some certain role.
	 *
	 * @param d Direction of an entry
	 * @param p Place of the letter inside the entry
	 * @return true, if successful
	 */
	public boolean canBe(Direction d, Place p) {
		return perms[d.ordinal()][p.ordinal()];
	}

	/**
	 * Enable or disable cell's position.
	 *
	 * @param d Direction of an entry
	 * @param p Place of the letter inside the entry
	 * @param allow the allow
	 */
	public void allow(Direction d, Place p, boolean allow) {
		perms[d.ordinal()][p.ordinal()] = allow;
	}

	/**
	 * Get deep copy of the current cell.
	 *
	 * @return Copy of the cell
	 */
	public BoardCell clone() {
		return new BoardCell(x, y, content);
	}

	/**
	 * Get x coordinate of the cell.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get Y coordinate of the cell.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(x).append(y).append(content);
		for (boolean[] els : perms) {
			for (boolean per : els) {
				out.append(per);
			}
		}
		return out.toString();
	}

	/**
	 * Inits the perms.
	 */
	private void initPerms() {
		for (int i = 0; i < 2; ++i)
			for (int j = 0; j < 3; ++j)
				perms[i][j] = true;
	}

	/** The y. */
	private int x, y;
	
	/** The content. */
	private String content = "";

	/** The perms. */
	private boolean perms[][] = new boolean[2][3];
}
