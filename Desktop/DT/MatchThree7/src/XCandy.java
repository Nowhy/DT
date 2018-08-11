public class XCandy extends Candy {

	public XCandy(int color, Board board,int row,int column) {
		super(color, board,row,column);		
	}

	@Override
	public void crush() {
		if (!wasCrushed){
//			board.updateScore(120);
			wasCrushed=true;
			for (int i = 0; i < board.BOARDSIZE; i=i+1)
				if (this.board.getCandies()[i][column] != null && this.board.getCandies()[i][column].getColor() != -10 && i != row) {
					this.board.getCandies()[i][column].crush();
					board.updateScore(60);
				}
			this.board.getCandies()[row][column] = null;
		}	
	}

}