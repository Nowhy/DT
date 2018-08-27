public abstract class Candy {
	int colour;
	Board board;
	int row;
	int column;
	boolean wasCrushed;

	// constructor
	public Candy(int colour, Board board, int row, int column) {// constructor
		this.board = board;
		this.colour = colour;
		this.row = row;
		this.column = column;
	}

	public Candy(Candy oldCandy, Board newboard) {
		this.board = newboard;
		this.colour = oldCandy.colour;
		this.row = oldCandy.row;
		this.column = oldCandy.column;
	}

	public abstract void crush();

	public void setcolour(int colour) {
		this.colour = colour;
	}

	public int getcolour() {
		return this.colour;
	}

	public Board getBoard() {
		return this.board;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	public void setWasCrushed(boolean bool) {
		this.wasCrushed = bool;
	}

}