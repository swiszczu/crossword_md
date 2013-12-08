package org.md.crossword.app;

import java.io.Serializable;
import java.util.*;

import org.md.crossword.board.*;
import org.md.crossword.dictionary.*;

// TODO: Auto-generated Javadoc
/**
 * Crossword with entries and board.
 */
public class Crossword implements Serializable {
	
	/**
	 * Crossword constructor.
	 *
	 * @param width Width of the board
	 * @param height Height of the board
	 * @param filename Name of the file where entries are stores and / or where store
	 * new entries
	 */
	public Crossword(int width, int height, String filename) {
		board = new Board(width, height);
		cwdb = new InteliCwDB(filename);
		ID = getUniqueID();
	}

	/**
	 * Get entries iterator.
	 *
	 * @return the iterator
	 */
	public Iterator<CwEntry> rOEntryIter() {
		return java.util.Collections.unmodifiableCollection(entries).iterator();
	}

	/**
	 * Make a deep copy of the board.
	 *
	 * @return Board's copy
	 */
	public Board boardCopy() {
		return board.clone();
	}

	/**
	 * Board.
	 *
	 * @return the board
	 */
	public Board board() {
		return board;
	}

	/**
	 * Get file handler of the database.
	 *
	 * @return the inteli cw db
	 */
	public InteliCwDB cwDB() {
		return cwdb;
	}

	/**
	 * Set file handler of the database.
	 *
	 * @param cwdb the new cw db
	 */
	public void setCwDB(InteliCwDB cwdb) {
		this.cwdb = cwdb;
	}

	/**
	 * Check if org.md.crossword contains pointed entry
	 *
	 * @param word Word to check
	 * @return true, if successful
	 */
	public boolean contains(String word) {
		for (CwEntry el : entries) {
			if (el.word().equalsIgnoreCase(word))
				return true;
		}
		return false;
	}

	/**
	 * Add new entry to the org.md.crossword
	 * 
	 * @param cwe
	 *            Crossword's entry
	 * @param str
	 *            Way for adding entries
	 */
	public final void addCwEntry(CwEntry cwe, Strategy str) {
		System.out.println(cwe.word());
        cwe.entryID(entries.size()+1);
		entries.add(cwe);
		str.updateBoard(board, cwe);
	}

	/**
	 * Fill the org.md.crossword board with the words using Strategy class
	 * 
	 * @param str
	 *            Strategy class which is responsible for searching positions of
	 *            the words
	 */
	public final void generate(Strategy str) {
		try {
			for (;;) {
				CwEntry entry = str.findEntry(this);
				addCwEntry(entry, str);
			}
		} catch (StrategyException e) {
			// ignore
		}

	}

	/**
	 * Get a unique id of the org.md.crossword
	 *
	 * @return the long
	 */
	public long uniqueId() {
		return ID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(entries).append(board).append(cwdb).append(ID);
		return out.toString();
	}

	/**
	 * Gets the unique id.
	 *
	 * @return the unique id
	 */
	private long getUniqueID() {
		Date d = new Date();
		return d.getTime();
	}

	/** The entries. */
	private LinkedList<CwEntry> entries = new LinkedList<CwEntry>();
	
	/** The board. */
	private Board board;
	
	/** The cwdb. */
	private InteliCwDB cwdb;
	
	/** The id. */
	private final long ID;
}
