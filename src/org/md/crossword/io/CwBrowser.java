package org.md.crossword.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import org.md.crossword.app.CrossStrategy;
import org.md.crossword.app.Crossword;

// TODO: Auto-generated Javadoc
/**
 * Browse, load and write crosswords to file.
 */
public class CwBrowser {
	
	/**
	 * Constructor.
	 *
	 * @param path Path to the directory where crosswords should be stored.
	 */
	public CwBrowser(String path) {
		this.path = path;
	}

	/**
	 * Save org.md.crossword to the file, return an unique handler.
	 *
	 * @param cw Crossword to store
	 * @return Unique id of the stored org.md.crossword, can be used to load org.md.crossword
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void save(Crossword cw) throws IOException {
		cwrite.write(cw);
	}

	/**
	 * Save all opened crosswords.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveAll() throws IOException {
		cwrite.writeAll();
	}

	/**
	 * Load the org.md.crossword
	 *
	 * @param id Unique id of the org.md.crossword to load
	 * @return Loaded org.md.crossword
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Crossword load(long id) throws IOException, ClassNotFoundException {
		Crossword cw = cread.getCw(id);
		cws.add(cw);
		return cw;
	}

	/**
	 * Create new org.md.crossword with entries from the file
	 *
	 * @param width Width of the board
	 * @param height Height of the board
	 * @param pathToFileDb Name of the file where entries are stores and / or where store
	 * new entries
	 * @return the crossword
	 */
	public Crossword newCrosswordFromFile(int width, int height,
			String pathToFileDb) {
		Crossword cw = new Crossword(width, height, pathToFileDb);
		cw.generate(new CrossStrategy());
		cws.add(cw);
		return cw;
	}

	/**
	 * Load all crosswords.
	 *
	 * @return the linked list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public LinkedList<Crossword> loadAll() throws IOException,
			ClassNotFoundException {
		return cread.getAllCws();
	}

	/**
	 * List the id of the crosswords stored in the directory.
	 *
	 * @return the linked list
	 */
	public LinkedList<Long> list() {
		LinkedList<Long> list = new LinkedList<Long>();
		File dir = new File(path);
		String[] fileNames = dir.list(); // files in the directory
		for (String fileName : fileNames) {
			// our file names contain only numbers
			if (fileName.matches("[1-9]*.crosswd"))
				list.add(new Long(fileName.split(".")[0]));
		}
		return list;
	}

	/**
	 * The Class CwReader.
	 */
	private class CwReader implements Reader {
		
		/* (non-Javadoc)
		 * @see org.md.crossword.io.Reader#getAllCws()
		 */
		public LinkedList<Crossword> getAllCws() throws IOException,
				ClassNotFoundException {
			LinkedList<Crossword> cws = new LinkedList<Crossword>();
			for (Long id : list())
				cws.add(getCw(id));

			return cws;
		}

		/* (non-Javadoc)
		 * @see org.md.crossword.io.Reader#getCw(long)
		 */
		public Crossword getCw(long id) throws IOException,
				ClassNotFoundException {
			String filePath = new String();
			Crossword cw;

			filePath = filePath.concat(path);
			if (!path.endsWith("/"))
				filePath = filePath.concat("/");
			filePath = filePath.concat(new Long(id).toString());
			filePath = filePath.concat(".crosswd");

			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					filePath));
			try {
				cw = (Crossword) in.readObject();
			} finally {
				in.close();
			}

			return cw;
		}
	}

	/**
	 * The Class CwWriter.
	 */
	private class CwWriter implements Writer {
		
		/* (non-Javadoc)
		 * @see org.md.crossword.io.Writer#write(org.md.crossword.app.Crossword)
		 */
		public void write(Crossword cw) throws IOException {
			String filePath = new String();
			filePath = filePath.concat(path);
			if (!path.endsWith("/"))
				filePath = filePath.concat("/");
			filePath = filePath.concat(new Long(cw.uniqueId()).toString());
			filePath = filePath.concat(".crosswd");

			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(filePath));
			try {
				out.writeObject(cw);
			} finally {
				out.close();
			}
		}

		/**
		 * Write all.
		 *
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void writeAll() throws IOException {
			for (Crossword cw : cws)
				write(cw);
		}
	}

	/** The cread. */
	private CwReader cread = new CwReader();
	
	/** The cwrite. */
	private CwWriter cwrite = new CwWriter();
	
	/** The path. */
	String path;
	
	/** The cws. */
	LinkedList<Crossword> cws = new LinkedList<Crossword>();
}
