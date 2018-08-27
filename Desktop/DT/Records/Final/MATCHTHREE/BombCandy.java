public class BombCandy extends Candy {

	public BombCandy(int colour, Board board,int row,int column) {
		super(colour, board,row,column);		
	}

	@Override
	public void crush() {
		if (!wasCrushed){	
			
			wasCrushed=true;
			
			if (row >= 1 && column >= 1 && this.getBoard().getCandies()[row-1][column-1] != null && this.board.getCandies()[row-1][column-1].getcolour() != -10) {
				this.getBoard().getCandies()[row-1][column-1].crush();
			}
			if (row >= 1 && this.getBoard().getCandies()[row-1][column]!=null && this.board.getCandies()[row-1][column].getcolour() != -10) {
				this.getBoard().getCandies()[row-1][column].crush();
			}
			if (row >= 1 && column < getBoard().BOARDSIZE - 1 && this.getBoard().getCandies()[row-1][column+1]!=null && this.board.getCandies()[row-1][column+1].getcolour() != -10) {
				this.getBoard().getCandies()[row-1][column+1].crush();
			}
			if (column >= 1 && this.getBoard().getCandies()[row][column-1]!=null && this.board.getCandies()[row][column-1].getcolour() != -10) {
				this.getBoard().getCandies()[row][column-1].crush();
			}
			if (column < getBoard().BOARDSIZE - 1 && this.getBoard().getCandies()[row][column+1]!=null && this.board.getCandies()[row][column+1].getcolour() != -10) {
				this.getBoard().getCandies()[row][column+1].crush();
			}
			if (row < getBoard().BOARDSIZE - 1 && column >= 1 && this.getBoard().getCandies()[row+1][column-1]!=null && this.board.getCandies()[row+1][column-1].getcolour() != -10) {
				this.getBoard().getCandies()[row+1][column-1].crush();
			}
			if (row < getBoard().BOARDSIZE - 1 && this.getBoard().getCandies()[row+1][column]!=null && this.board.getCandies()[row+1][column].getcolour() != -10) {
				this.getBoard().getCandies()[row+1][column].crush();
			}
			if (row < getBoard().BOARDSIZE - 1 && column < getBoard().BOARDSIZE - 1 && this.getBoard().getCandies()[row+1][column+1]!=null && this.board.getCandies()[row+1][column+1].getcolour() != -10) {
				this.getBoard().getCandies()[row+1][column+1].crush();
			}
					
			if(!this.getBoard().isNONullCandy()) {
				this.getBoard().addRandomCandy();
				this.getBoard().checkChainReaction(0);
				this.getBoard().addRandomCandy();
			}
			
			this.getBoard().getCandies()[row][column] = null;
			
			if (row >= 1 && column >= 1 && this.getBoard().getCandies()[row-1][column-1] != null && this.board.getCandies()[row-1][column-1].getcolour() != -10) {
				this.getBoard().getCandies()[row-1][column-1].crush();
			}
			if (row >= 1 && this.getBoard().getCandies()[row-1][column] != null && this.board.getCandies()[row-1][column].getcolour() != -10) {
				this.getBoard().getCandies()[row-1][column].crush();
			}
			if (row >= 1 && column < getBoard().BOARDSIZE- 1 && this.getBoard().getCandies()[row-1][column+1] != null && this.board.getCandies()[row-1][column+1].getcolour() != -10) {
				this.getBoard().getCandies()[row-1][column+1].crush();
			}
			if (column >= 1 && this.getBoard().getCandies()[row][column-1] != null && this.board.getCandies()[row][column-1].getcolour() != -10) {
				this.getBoard().getCandies()[row][column-1].crush();
			}
			if (column < getBoard().BOARDSIZE - 1 && this.getBoard().getCandies()[row][column+1] != null && this.board.getCandies()[row][column+1].getcolour() != -10) {
				this.getBoard().getCandies()[row][column+1].crush();
			}
			if (row < getBoard().BOARDSIZE - 1 && column >= 1 && this.getBoard().getCandies()[row+1][column-1] != null && this.board.getCandies()[row+1][column-1].getcolour() != -10) {
				this.getBoard().getCandies()[row+1][column-1].crush();
			}
			if (row < getBoard().BOARDSIZE - 1 && this.getBoard().getCandies()[row+1][column] != null && this.board.getCandies()[row+1][column].getcolour() != -10) {
				this.getBoard().getCandies()[row+1][column].crush();
			}
			if (row < getBoard().BOARDSIZE - 1 && column < getBoard().BOARDSIZE - 1 && this.getBoard().getCandies()[row+1][column+1] != null && this.board.getCandies()[row+1][column+1].getcolour() != -10) {
				this.getBoard().getCandies()[row+1][column+1].crush();
			}
			
			this.getBoard().updateScore(540*2);
		}
	}
	
	
	
}