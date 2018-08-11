public class BombCandy extends Candy {

	public BombCandy(int color, Board board,int row,int column) {
		super(color, board,row,column);		
	}

	@Override
	public void crush() {
		if (!wasCrushed){	
			
			wasCrushed=true;
			
			if (row >= 1 && column >= 1 && this.board.getCandies()[row-1][column-1]!=null) {
				this.board.getCandies()[row-1][column-1].crush();
			}
			if (row >= 1 && this.board.getCandies()[row-1][column]!=null) {
				this.board.getCandies()[row-1][column].crush();
			}
			if (row >= 1 && column <= board.BOARDSIZE - 1 && this.board.getCandies()[row-1][column+1]!=null) {
				this.board.getCandies()[row-1][column+1].crush();
			}
			if (column >= 1 && this.board.getCandies()[row][column-1]!=null) {
				this.board.getCandies()[row][column-1].crush();
			}
			if (column <= board.BOARDSIZE - 1 && this.board.getCandies()[row][column+1]!=null) {
				this.board.getCandies()[row][column+1].crush();
			}
			if (row < board.BOARDSIZE - 1 && column >= 1 && this.board.getCandies()[row+1][column-1]!=null) {
				this.board.getCandies()[row+1][column-1].crush();
			}
			if (row < board.BOARDSIZE - 1 && this.board.getCandies()[row+1][column]!=null) {
				this.board.getCandies()[row+1][column].crush();
			}
			if (row < board.BOARDSIZE - 1 && column <= board.BOARDSIZE - 1 && this.board.getCandies()[row+1][column+1]!=null) {
				this.board.getCandies()[row+1][column+1].crush();
			}
					
			this.getBoard().addRandomCandy();
			this.board.getCandies()[row][column] = null;
			
			if (row >= 1 && column >= 1) {
				this.board.getCandies()[row-1][column-1].crush();
			}
			if (row >= 1) {
				this.board.getCandies()[row-1][column].crush();
			}
			if (row >= 1 && column <= board.BOARDSIZE - 1) {
				this.board.getCandies()[row-1][column+1].crush();
			}
			if (column >= 1) {
				this.board.getCandies()[row][column-1].crush();
			}
			if (column < board.BOARDSIZE - 1) {
				this.board.getCandies()[row][column+1].crush();
			}
			if (row < board.BOARDSIZE - 1 && column >= 1) {
				this.board.getCandies()[row+1][column-1].crush();
			}
			if (row < board.BOARDSIZE - 1) {
				this.board.getCandies()[row+1][column].crush();
			}
			if (row < board.BOARDSIZE - 1 && column< board.BOARDSIZE - 1) {
				this.board.getCandies()[row+1][column+1].crush();
			}
		}
	}
	
	
	
}