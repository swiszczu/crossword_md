package org.md.crossword.dictionary;

import java.io.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * Database of the entries. Load and stores content in the text files.
 * 
 */
public class CwDB implements Serializable {
	
	/**
	 * Database constructor.
	 *
	 * @param filename Name of the file which containes org.md.crossword's entries
	 */
	public CwDB(String filename) {
		dict = new LinkedList<Entry>();
		createDB(filename);
	}

	/**
	 * Add new entry to database.
	 *
	 * @param word Word in the org.md.crossword
	 * @param clue Clue for the word
	 */
	public void add(String word, String clue) {
		if (word.length() > 0 && clue.length() > 0)
			dict.add(new Entry(word, clue));
	}

	/**
	 * Get pointed entry from database with the pointed word.
	 *
	 * @param word Word of the entry
	 * @return Crossword entry
	 */
	public Entry get(String word) {
		for (int i = 0; i < dict.size(); i++) {
			if (dict.get(i).word().equals(word))
				return dict.get(i);
		}
		return new Entry("", "");
	}

	/**
	 * Remove one pointed entry from the database.
	 *
	 * @param word Word of the entry to remove
	 */
	public void remove(String word) {
		for (int i = 0; i < dict.size(); i++) {
			if (dict.get(i).word().equals(word)) {
				dict.remove(i);
				break;
			}
		}
	}

	/**
	 * Store database in the text file.
	 *
	 * @param filename Path to the file where entries should be stored
	 */
	public void saveDB(String filename) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(new File(
					filename).getAbsoluteFile()));
			try {
				for (Entry el : dict) {
					out.write(el.word());
					out.newLine();
					out.write(el.clue());
					out.newLine();
				}
			} finally {
				out.close();
			}
		} catch (IOException e) {
			// ignore
		}
	}

	/**
	 * Get size of database.
	 *
	 * @return Amount of the entries in database
	 */
	public int size() {
		return dict.size();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(dict);
		return out.toString();
	}

	/**
	 * Load entries from the text file and add to the database.
	 *
	 * @param filename Name of the file which stores entries to load
	 */
	protected void createDB(String filename) {
		try {
    BufferedReader in = new BufferedReader( new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			try {
				String word, clue;
				while ((word = in.readLine()) != null
						&& (clue = in.readLine()) != null) {
					add(word, clue);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			// ignore
		}
	}

	/** The dict. */
	protected LinkedList<Entry> dict;
}
