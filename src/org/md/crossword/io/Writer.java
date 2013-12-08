package org.md.crossword.io;

import java.io.IOException;

import org.md.crossword.app.Crossword;

// TODO: Auto-generated Javadoc
/**
 * The Interface Writer.
 */
public interface Writer {
	
	/**
	 * Write.
	 *
	 * @param cw the cw
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void write(Crossword cw) throws IOException;
}
