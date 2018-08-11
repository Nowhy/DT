public class XCandy extends Candy {

	public XCandy(int color, Board board,int row,int column) {
		super(color, board,row,column);		
	}

	@Override
	public void crush() {
		if (!wasCrushed){
			wasCrushed=true;
			for (int i = 0; i < Board.BOARDSIZE; i=i+1)
				if (this.board.getCandies()[i][column] != null && i != row) {
					this.board.getCandies()[i][column].crush();
				}
			this.board.getCandies()[row][column] = null;
		}	
	}

}