package org.md.crossword.app;

import java.util.LinkedList;
import java.util.Random;

import org.md.crossword.board.Board;
import org.md.crossword.board.BoardCell;
import org.md.crossword.board.CwEntry;
import org.md.crossword.dictionary.Entry;
import org.md.crossword.dictionary.InteliCwDB;

/**
 * Used to fill org.md.crossword with the entries in random way
 * 
 */
/**
 * @author Marcin Długosz
 *
 */
public class CrossStrategy extends Strategy {
	/**
	 * Generate random entry in the org.md.crossword
	 * 
	 * @param cw
	 *            Crossword which generate entry for
	 */
	public CwEntry findEntry(Crossword cw) throws StrategyException {
		Board board = cw.board();
		InteliCwDB cwDB = cw.cwDB();
		Random rnd = new Random();
		int fromx, fromy, tox, toy, i;
		CwEntry.Direction direction;
		// first few words have to be put in random way to the org.md.crossword
		boolean randomPhase = true;

		LinkedList<BoardCell> startCells = board.getStartCells();
		i = 0;

		while (i < startCells.size()) {
			startCells = board.getStartCells();
			BoardCell bc;

			if (randomPhase && i == startCells.size() - 1) {
				randomPhase = false;
				i = 0;
			}

			if (randomPhase)
				bc = startCells.get(rnd.nextInt(startCells.size() - 1));
			else
				bc = startCells.get(i);

			fromx = bc.getX();
			fromy = bc.getY();

			boolean canBeHorizStart = bc.canBe(BoardCell.Direction.HORIZONTAL,
					BoardCell.Place.START);
			boolean canBeVertStart = bc.canBe(BoardCell.Direction.VERTICAL,
					BoardCell.Place.START);

			if (canBeHorizStart && canBeVertStart) {
				direction = rnd.nextBoolean() ? CwEntry.Direction.HORIZ
						: CwEntry.Direction.VERT;
			} else if (canBeHorizStart) {
				direction = CwEntry.Direction.HORIZ;
			} else {
				direction = CwEntry.Direction.VERT;
			}

			++i;

			for (int wordLength = 1; wordLength < InteliCwDB.longestWord; ++wordLength) {
				if (direction == CwEntry.Direction.HORIZ) {
					if (randomPhase) {
						tox = fromx + rnd.nextInt(InteliCwDB.longestWord - 1);
						toy = fromy;
					} else {
						tox = fromx + wordLength;
						toy = fromy;
					}
				} else {
					if (randomPhase) {
						toy = fromy + rnd.nextInt(InteliCwDB.longestWord - 1);
						tox = fromx;
					} else {
						tox = fromx;
						toy = fromy + wordLength;
					}
				}

				if (randomPhase)
					wordLength = InteliCwDB.longestWord;

				String pattern = board.createPattern(fromx, fromy, tox, toy);
				if (pattern.length() > 0) {
					Entry entr = cwDB.random(pattern);
					if (!entr.isEmpty()) {
						if (!words.contains(entr.word())) {
							--i;
							words.add(entr.word());
							return new CwEntry(entr.word(), entr.clue(), fromx,
									fromy, direction);
						}
					}
				}
			}
		}
		throw new StrategyException();
	}

	/**
	 * Marks place of the entry in the board
	 * 
	 * @param board
	 *            Crossword's board
	 * @param entry
	 *            Crossword's entry
	 */
	public void updateBoard(Board board, CwEntry entry) {
		int fromx, fromy, tox, toy;
		fromx = entry.getX();
		fromy = entry.getY();

		if (entry.direction() == CwEntry.Direction.HORIZ) {
			tox = fromx + entry.word().length() - 1;
			toy = fromy;
			for (int i = fromx; i < tox + 1; ++i) {
				board.cell(i, toy).setContent(
						entry.word().substring(i - fromx, i - fromx + 1));
			}

			for (int i = fromx - 1; i < tox + 2; ++i) {
				board.cell(i, toy - 1).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.START, false);
				board.cell(i, toy - 1).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.INNER, false);
				board.cell(i, toy - 1).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.END, false);

				board.cell(i, toy).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.START, false);
				board.cell(i, toy).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.INNER, false);
				board.cell(i, toy).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.END, false);

				board.cell(i, toy + 1).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.START, false);
				board.cell(i, toy + 1).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.INNER, false);
				board.cell(i, toy + 1).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.END, false);
			}

			board.cell(fromx - 1, fromy).allow(BoardCell.Direction.VERTICAL,
					BoardCell.Place.START, false);
			board.cell(fromx - 1, fromy).allow(BoardCell.Direction.VERTICAL,
					BoardCell.Place.INNER, false);
			board.cell(fromx - 1, fromy).allow(BoardCell.Direction.VERTICAL,
					BoardCell.Place.END, false);

			board.cell(tox + 1, fromy).allow(BoardCell.Direction.VERTICAL,
					BoardCell.Place.START, false);
			board.cell(tox + 1, fromy).allow(BoardCell.Direction.VERTICAL,
					BoardCell.Place.INNER, false);
			board.cell(tox + 1, fromy).allow(BoardCell.Direction.VERTICAL,
					BoardCell.Place.END, false);

			for (int i = fromx; i < tox + 1; ++i) {
				board.cell(i, fromy + 1).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.START, false);
				board.cell(i, fromy - 1).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.END, false);
			}
		} else {
			tox = fromx;
			toy = fromy + entry.word().length() - 1;

			for (int i = fromy; i < toy + 1; ++i) {
				board.cell(tox, i).setContent(
						entry.word().substring(i - fromy, i - fromy + 1));
			}

			for (int i = fromy - 1; i < toy + 2; ++i) {
				board.cell(tox - 1, i).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.START, false);
				board.cell(tox - 1, i).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.INNER, false);
				board.cell(tox - 1, i).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.END, false);

				board.cell(tox, i).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.START, false);
				board.cell(tox, i).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.INNER, false);
				board.cell(tox, i).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.END, false);

				board.cell(tox + 1, i).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.START, false);
				board.cell(tox + 1, i).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.INNER, false);
				board.cell(tox + 1, i).allow(BoardCell.Direction.VERTICAL,
						BoardCell.Place.END, false);
			}

			board.cell(fromx, fromy - 1).allow(BoardCell.Direction.HORIZONTAL,
					BoardCell.Place.START, false);
			board.cell(fromx, fromy - 1).allow(BoardCell.Direction.HORIZONTAL,
					BoardCell.Place.INNER, false);
			board.cell(fromx, fromy - 1).allow(BoardCell.Direction.HORIZONTAL,
					BoardCell.Place.END, false);

			board.cell(fromx, toy + 1).allow(BoardCell.Direction.HORIZONTAL,
					BoardCell.Place.START, false);
			board.cell(fromx, toy + 1).allow(BoardCell.Direction.HORIZONTAL,
					BoardCell.Place.INNER, false);
			board.cell(fromx, toy + 1).allow(BoardCell.Direction.HORIZONTAL,
					BoardCell.Place.END, false);

			for (int i = fromy; i < toy + 1; ++i) {
				board.cell(fromx + 1, i).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.START, false);
				board.cell(fromx - 1, i).allow(BoardCell.Direction.HORIZONTAL,
						BoardCell.Place.END, false);
			}
		}
	}

	private LinkedList<String> words = new LinkedList<String>();
}
