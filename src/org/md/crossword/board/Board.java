package org.md.crossword.board;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created with Eclipse
 *
 * @author Marcin Długosz
 *         Date: 27.11.13
 *         Time: 13:48
 */
public class Board implements Serializable {

    /**
     * The board.
     */
    private BoardCell[][] board;

    /**
     * Board constructor.
     *
     * @param width  Width of the board
     * @param height Height of the board
     */
    public Board(int width, int height) {
        board = new BoardCell[width][height];
        for (int i = 0; i < width; ++i)
            for (int j = 0; j < height; ++j)
                board[i][j] = new BoardCell(i, j);

        for (int i = 0; i < width; ++i) {
            board[i][0].allow(BoardCell.Direction.VERTICAL,
                    BoardCell.Place.INNER, false); // first row
            board[i][height - 1].allow(BoardCell.Direction.VERTICAL,
                    BoardCell.Place.INNER, false); // last row
        }
        for (int i = 0; i < height; ++i) {
            board[0][i].allow(BoardCell.Direction.HORIZONTAL,
                    BoardCell.Place.INNER, false); // first column
            board[width - 1][i].allow(BoardCell.Direction.HORIZONTAL,
                    BoardCell.Place.INNER, false); // last column
        }
    }

    /**
     * Get width of the board.
     *
     * @return Rows amount
     */
    public int width() {
        return board.length;
    }

    /**
     * Get height of the board.
     *
     * @return Colls amount
     */
    public int height() {
        return board[0].length;
    }

    /**
     * Return stored cell of the org.md.crossword
     *
     * @param x X coordinate of the cell
     * @param y Y coordinate of the cell
     * @return Stored cell
     */
    public BoardCell cell(int x, int y) {
        if (x >= 0 && y >= 0 && x < board.length && y < board[0].length)
            return board[x][y];
        return new BoardCell(-1, -1);
    }

    /**
     * Set cell position in the board.
     *
     * @param x X coordinate of the cell
     * @param y Y coordinate of the cell
     * @param c Cell to be stored
     */
    public void setCell(int x, int y, BoardCell c) {
        board[x][y] = c;
    }

    /**
     * Get a deep copy of the board.
     *
     * @return Copy of the board
     */
    public Board clone() {
        Board boardCopy = new Board(board.length, board[0].length);

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                BoardCell cellCopy = cell(i, j).clone();
                boardCopy.setCell(i, j, cellCopy);
            }
        }

        return boardCopy;
    }

    /**
     * Get all cells which be the beginning for a new entry.
     *
     * @return List of the cells
     */
    public LinkedList<BoardCell> getStartCells() {
        LinkedList<BoardCell> startCells = new LinkedList<BoardCell>();

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                BoardCell cell = board[i][j];
                if (cell.canBeStartCell())
                    startCells.add(cell);
            }
        }

        return startCells;
    }

    /**
     * Creates a regular expression pattern for a pointed coordinates.
     *
     * @param fromx X coordinate of the word's first letter
     * @param fromy Y coordinate of the word's first letter
     * @param tox   X coordinate of the word's last letter
     * @param toy   Y coordinate of the word's last letter
     * @return Regular expression which suits pointed coordinates of the word
     */
    public String createPattern(int fromx, int fromy, int tox, int toy) {
        StringBuffer pattern = new StringBuffer();
        String letter;
        // when entry is in the one row or column and isn't too long
        boolean canBeInserted = (fromx == tox || fromy == toy) && fromx >= 0
                && fromy >= 0 && tox < board.length && toy < board[0].length;
        if (!canBeInserted) {
            return new String("");
        }
        if (fromx == tox) { // vertically
            boolean canBeVertStart = board[fromx][fromy].canBe(
                    BoardCell.Direction.VERTICAL, BoardCell.Place.START);
            if (!canBeVertStart) {
                return new String("");
            }

            for (int i = fromy; i < toy + 1; ++i) {
                boolean canBeInner = canBeInner(fromx, i,
                        BoardCell.Direction.VERTICAL);
                if (i != fromy && i != toy && !canBeInner) {
                    return new String(""); // it can't be inner cell
                }
                letter = board[fromx][i].content();
                if (letter.equals(""))
                    pattern.append(".");
                else
                    pattern.append(letter);
            }

            boolean canBeVertEnd = board[fromx][toy].canBe(
                    BoardCell.Direction.VERTICAL, BoardCell.Place.END);
            if (!canBeVertEnd) {
                return new String("");
            }
        } else if (fromy == toy) { // horizontally
            boolean canBeHorizStart = board[fromx][fromy].canBe(
                    BoardCell.Direction.HORIZONTAL, BoardCell.Place.START);
            if (!canBeHorizStart) {
                return new String("");
            }

            for (int i = fromx; i < tox + 1; ++i) {
                boolean canBeInner = canBeInner(i, fromy,
                        BoardCell.Direction.HORIZONTAL);
                if (i != fromx && i != tox && !canBeInner) {
                    return new String(""); // can't be inner cell
                }

                letter = board[i][fromy].content();
                if (letter.equals(""))
                    pattern.append(".");
                else
                    pattern.append(letter);
            }

            boolean canBeHorizEnd = board[tox][fromy].canBe(
                    BoardCell.Direction.HORIZONTAL, BoardCell.Place.END);
            if (!canBeHorizEnd) {
                return new String("");
            }
        }

        return pattern.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(board);
        return out.toString();
    }

    /**
     * Can be inner.
     *
     * @param x the x
     * @param y the y
     * @param d the d
     * @return true, if successful
     */
    private boolean canBeInner(int x, int y, BoardCell.Direction d) {
        return board[x][y].canBe(d, BoardCell.Place.INNER);
    }
}
