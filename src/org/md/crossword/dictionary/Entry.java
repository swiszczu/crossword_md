package org.md.crossword.dictionary;

import java.io.Serializable;

/**
 * Created with Eclipse
 *
 * @author Marcin Długosz
 *         Date: 29.11.13
 *         Time: 11:43
 */
public class Entry implements Serializable {

    /**
     * The clue.
     */
    private String word, clue;
    /**
     * The entry id.
     */
    private int entryID = 0;

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
        return word.length() * clue.length() == 0;
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
    public void entryID(int en) {
        entryID = en;
    }

    /**
     * Entry id.
     *
     * @return the int
     */
    public int entryID() {
        return entryID;
    }
}
