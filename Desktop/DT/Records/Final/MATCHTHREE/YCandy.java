public class YCandy extends Candy {

	public YCandy(int colour, Board board,int row,int column) {
		super(colour, board,row,column);		
	}

	@Override
	public void crush() {
		if (!wasCrushed){
//			board.updateScore(120);
			wasCrushed=true;
			for (int i = 0; i < board.BOARDSIZE; i = i+1)
				if (this.getBoard().getCandies()[row][i] != null && this.getBoard().getCandies()[row][i].getcolour() != -10 && i != column) {
					this.getBoard().getCandies()[row][i].crush();
					this.getBoard().updateScore(60);
				}
			this.getBoard().getCandies()[row][column] = null;
		}	
	}

}