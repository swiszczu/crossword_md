package org.md.crossword.io;

import org.md.crossword.app.Crossword;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created with Eclipse
 *
 * @author Marcin Długosz
 *         Date: 29.11.13
 *         Time: 11:43
 */
public interface Reader {

    /**
     * Gets the cw.
     *
     * @param id the id
     * @return the cw
     * @throws IOException            Signals that an I/O exception has occurred.
     * @throws ClassNotFoundException the class not found exception
     */
    Crossword getCw(long id) throws IOException, ClassNotFoundException;

    /**
     * Gets the all cws.
     *
     * @return the all cws
     * @throws IOException            Signals that an I/O exception has occurred.
     * @throws ClassNotFoundException the class not found exception
     */
    LinkedList<Crossword> getAllCws() throws IOException,
            ClassNotFoundException;
}
