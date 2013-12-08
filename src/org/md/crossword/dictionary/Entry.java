package org.md.crossword.dictionary;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * Basic class for the org.md.crossword entry
 *
 */
public class Entry implements Serializable {
	
	/**
	 * Entry constructor.
	 *
	 * @param word Word to store
	 * @param clue Clue to store
	 */
	public Entry(String word, String clue) {
		this.word = word;
		this.clue = clue;
	}
	
	/**
	 * Word.
	 *
	 * @return Stored word
	 */
	public String word() {
		return word;
	}
	
	/**
	 * Clue.
	 *
	 * @return Stored clue
	 */
	public String clue() {
		return clue;
	}
	
	/**
	 * Check if entry contains empty word or clue.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return word.length()*clue.length() == 0;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(word).append(clue);
		return out.toString();
	}
    
    /**
     * Entry id.
     *
     * @param en the en
     */
    public void entryID(int en)
    {
        entryID=en;
    }
	
	/**
	 * Entry id.
	 *
	 * @return the int
	 */
	public int entryID()
    {
        return entryID;
    }
	
	/** The clue. */
	private String word, clue;
    
    /** The entry id. */
    private int entryID = 0;
}
