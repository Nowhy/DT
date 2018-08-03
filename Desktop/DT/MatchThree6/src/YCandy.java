public class YCandy extends Candy {

	public YCandy(int color, Board board,int row,int column) {
		super(color, board,row,column);		
	}

	@Override
	public void crush() {
		if (!wasCrushed){
			wasCrushed=true;
			for (int i = 0; i < board.BOARDSIZE; i = i+1)
				if (this.board.getCandies()[row][i] != null && i != column) {
					this.board.getCandies()[row][i].crush();
					board.updateScore(60);
				}
			this.board.getCandies()[row][column] = null;
		}	
	}

}