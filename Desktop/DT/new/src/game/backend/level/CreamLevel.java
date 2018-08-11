package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.cell.CreamCell;
import game.backend.element.Wall;

public class CreamLevel extends Grid {

	private static int REQUIRED_SCORE = 1000;
	private static int MAX_MOVES = 7;

	private Cell wallCell;
	private Cell candyGenCell;
	private CreamCell[] ccArray;

	@Override
	protected GameState newState() {
		return new CreamLevelState(REQUIRED_SCORE, MAX_MOVES);
	}

	@Override
	public void preInitialize() {

		ccArray = new CreamCell[20];
		int count = 0;

		for (int i = 5; i <  6; i++) {
			for (int j = 0; j < 9; j++) {
				CreamCell cc = new CreamCell(this);
				setCell(i, j, cc);
				ccArray[count] = cc;
				count++;
			}
		}
	}

	
	@Override
	protected CreamLevelState state() {
		return (CreamLevelState) super.state();
	}

	@Override
	protected void fillCells() {

		wallCell = new Cell(this);
		wallCell.setContent(new Wall());
		candyGenCell = new CandyGeneratorCell(this);

		// corners
		g()[0][0].setAround(candyGenCell, g()[1][0], wallCell, g()[0][1]);
		g()[0][SIZE - 1].setAround(candyGenCell, g()[1][SIZE - 1],
				g()[0][SIZE - 2], wallCell);
		g()[SIZE - 1][0].setAround(g()[SIZE - 2][0], wallCell, wallCell,
				g()[SIZE - 1][1]);
		g()[SIZE - 1][SIZE - 1].setAround(g()[SIZE - 2][SIZE - 1], wallCell,
				g()[SIZE - 1][SIZE - 2], wallCell);

		// upper line cells
		for (int j = 1; j < SIZE - 1; j++) {
			g()[0][j].setAround(candyGenCell, g()[1][j], g()[0][j - 1],
					g()[0][j + 1]);
		}
		// bottom line cells
		for (int j = 1; j < SIZE - 1; j++) {
			g()[SIZE - 1][j].setAround(g()[SIZE - 2][j], wallCell,
					g()[SIZE - 1][j - 1], g()[SIZE - 1][j + 1]);
		}
		// left line cells
		for (int i = 1; i < SIZE - 1; i++) {
			g()[i][0].setAround(g()[i - 1][0], g()[i + 1][0], wallCell,
					g()[i][1]);
		}
		// right line cells
		for (int i = 1; i < SIZE - 1; i++) {
			g()[i][SIZE - 1].setAround(g()[i - 1][SIZE - 1],
					g()[i + 1][SIZE - 1], g()[i][SIZE - 2], wallCell);
		}
		// central cells
		for (int i = 1; i < SIZE - 1; i++) {
			for (int j = 1; j < SIZE - 1; j++) {
				g()[i][j].setAround(g()[i - 1][j], g()[i + 1][j],
						g()[i][j - 1], g()[i][j + 1]);
			}
		}
	}

	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean ret = super.tryMove(i1, j1, i2, j2);
		if (ret) {
			state().addMove();
		}
		return ret;
	}
	
	
	private class CreamLevelState extends GameState {
		private long requiredScore;
		private long maxMoves;

		public CreamLevelState(long requiredScore, int maxMoves) {
			this.requiredScore = requiredScore;
			this.maxMoves = maxMoves;
		}

		private boolean remainingJelly() {

			for (CreamCell cc : ccArray) {
				if (cc.isActive())
					return true;
			}
			return false;
		}

		public boolean gameOver() {

			return playerWon() && getMoves() <= maxMoves && !remainingJelly();
		}

		public boolean playerWon() {
			return getScore() > requiredScore;
		}

	}
}