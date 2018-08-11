package game.backend;

import game.backend.cell.Cell;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.move.Direction;
import game.backend.move.Move;
import game.backend.move.MoveMaker;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Grid {

	public static final int SIZE = 9;
	public static final int DELAY_MS = 100;

	protected Cell[][] g = new Cell[SIZE][SIZE];
	private Map<Cell, Point> gMap = new HashMap<Cell, Point>();
	private GameState state;
	private List<GameListener> listeners = new ArrayList<GameListener>();
	private MoveMaker moveMaker;
	private FigureDetector figureDetector;

	protected abstract GameState newState();

	protected abstract void fillCells();

	protected Cell[][] g() {
		return g;
	}

	protected void setCell(int i, int j, Cell cell) {
		Cell old = g[i][j];
		gMap.remove(old);
		g[i][j] = cell;
		gMap.put(g[i][j], new Point(i, j));
	}

	protected GameState state() {
		return state;
	}

	public void initialize() {
		moveMaker = new MoveMaker(this);
		figureDetector = new FigureDetector(this);

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				g[i][j] = new Cell(this);
				gMap.put(g[i][j], new Point(i, j));
			}
		}

		preInitialize();
		fillCells();
		fallElements();
		postInitialize();
	}

	protected void preInitialize() {
	}

	protected void postInitialize() {
	}

	public Element get(int i, int j) {
		return g[i][j].getContent();
	}

	public Cell getCell(int i, int j) {
		return g[i][j];
	}

	// FallElements nuevo con Caida Lateral incluida.
	public void fallElements() {

		boolean emptyflag = false;

		int i = SIZE - 1;

		while (i >= 0) {

			int j = 0;

			while (j < SIZE) {

				if (g[i][j].isEmpty()) {
					if (g[i][j].fallContent(g[i][j].getAround()[Direction.UP.ordinal()])) {
						emptyflag = false;
						i = SIZE;
						j = -1;
						break;
					}
					emptyflag = true;
				}
				j++;
			}
			i--;
		}
		if (emptyflag) {
			for (i = SIZE - 1; i >= 0; i--) {
				for (int j = 0; j < SIZE; j++) {
					if (g[i][j].isEmpty()) {
						if (g[i][j].fallContent(g[i][j].getAround()[Direction.UP.ordinal()].getAround()[Direction.LEFT.ordinal()]))
							fallElements();
						else if (g[i][j].fallContent(g[i][j].getAround()[Direction.UP.ordinal()].getAround()[Direction.RIGHT.ordinal()]))
							fallElements();

					}
				}
			}

		}
	}

	public void clearContent(int i, int j) {
		g[i][j].clearContent();
	}

	public void setContent(int i, int j, Element e) {
		g[i][j].setContent(e);
	}

	public boolean tryMove(int i1, int j1, int i2, int j2) {
		Move move = moveMaker.getMove(i1, j1, i2, j2);
		swapContent(i1, j1, i2, j2);
		if (move.isValid()) {
			move.removeElements();
			fallElements();
			return true;
		} else {
			swapContent(i1, j1, i2, j2);
			return false;
		}
	}

	public Figure tryRemove(Cell cell) {
		if (gMap.containsKey(cell)) {
			Point p = gMap.get(cell);
			Figure f = figureDetector.checkFigure(p.x, p.y);
			if (f != null) {
				removeFigure(p.x, p.y, f);
			}
			return f;
		}
		return null;
	}

	private void removeFigure(int i, int j, Figure f) {
		CandyColor color = ((Candy) get(i, j)).getColor();
		if (f.hasReplacement()) {
			setContent(i, j, f.generateReplacement(color));
		} else {
			clearContent(i, j);
		}
		for (Point p : f.getPoints()) {
			clearContent(i + p.x, j + p.y);
		}
	}

	public void swapContent(int i1, int j1, int i2, int j2) {
		Element e = g[i1][j1].getContent();
		g[i1][j1].setContent(g[i2][j2].getContent());
		g[i2][j2].setContent(e);
		wasUpdated();
	}

	public GameState createState() {
		this.state = newState();
		return this.state;
	}

	public void addListener(GameListener listener) {
		listeners.add(listener);
	}

	public void wasUpdated() {
		if (listeners.size() > 0) {
			for (GameListener gl : listeners) {
				gl.gridUpdated();
			}
			delay(DELAY_MS);
		}
	}

	public void cellExplosion(Element e) {
		for (GameListener gl : listeners) {
			gl.cellExplosion(e);
		}
	}

	public void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ie) {
		}
	}

}
