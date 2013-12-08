package org.md.crossword.dictionary;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created with Eclipse
 *
 * @author Marcin Długosz
 *         Date: 26.11.13
 *         Time: 12:26
 */
public class InteliCwDB extends CwDB implements Serializable {

    /**
     * The longest word.
     */
    public static int longestWord = 0;

    /**
     * InteliCwDB constructor.
     *
     * @param filename Filename which stores entries to read
     */
    public InteliCwDB(String filename) {
        super(filename);
    }

    /**
     * Find all entries which match the pointed pattern.
     *
     * @param pattern Pattern of the word - regular expression
     * @return List of the entries, which match the pattern
     */
    public LinkedList<Entry> findAll(String pattern) {
        LinkedList<Entry> words = new LinkedList<Entry>();
        for (int i = 0; i < dict.size(); ++i) {
            if (dict.get(i).word().matches(pattern))
                words.add(dict.get(i));
        }
        return words;
    }

    /**
     * Get random entry from database.
     *
     * @return Random entry
     */
    public Entry random() {
        Random rand = new Random();
        if (dict.size() > 0)
            return dict.get(rand.nextInt(dict.size() - 1));
        return new Entry("", "");
    }

    /**
     * Get random entry which has pointed length.
     *
     * @param length Length of the word
     * @return Random entry with pointed length
     */
    public Entry random(int length) {
        // build list of the indexes with pointed length of the words
        LinkedList<Integer> wordInds = new LinkedList<Integer>();
        for (int i = 0; i < dict.size(); ++i) {
            if (dict.get(i).word().length() == length)
                wordInds.add(i);
        }
        // return default when no entry with pointed length
        if (wordInds.size() == 0)
            return new Entry("", "");
        // return random entry
        Random rand = new Random();
        int ind = wordInds.get(rand.nextInt(wordInds.size() - 1));
        return dict.get(ind);
    }

    /**
     * Get random entry which match the pointed pattern.
     *
     * @param pattern Pattern of the word - regular expression
     * @return Random entry which matches pointed pattern
     */
    public Entry random(String pattern) {
        if (pattern.length() == 0)
            return new Entry("", "");
        LinkedList<Entry> words = findAll(pattern);
        // return default when no entry matches the pattern
        if (words.size() <= 1)
            return new Entry("", "");
        // return random entry
        Random rand = new Random();
        return words.get(rand.nextInt(words.size() - 1));
    }

    /* (non-Javadoc)
     * @see org.md.crossword.dictionary.CwDB#add(java.lang.String, java.lang.String)
     */
    public void add(String word, String clue) {
        if (word.length() > 0 && clue.length() > 0) {
            if (word.length() > longestWord)
                longestWord = word.length();
            for (int i = 0; i < dict.size(); ++i) {
                if (dict.get(i).word().compareTo(word) >= 0) {
                    dict.add(i, new Entry(word, clue));
                    return;
                }
            }
            dict.add(new Entry(word, clue));
        }
    }

    /* (non-Javadoc)
     * @see org.md.crossword.dictionary.CwDB#toString()
     */
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(longestWord);
        return out.toString();
    }
}
