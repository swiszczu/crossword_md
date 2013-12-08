package org.md.crossword.io;

import org.md.crossword.app.Crossword;

import java.io.IOException;

/**
 * Created with Eclipse
 *
 * @author Marcin Długosz
 *         Date: 29.11.13
 *         Time: 11:43
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
