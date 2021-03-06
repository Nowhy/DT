public class ChocolateCandy extends Candy {

	public ChocolateCandy(int color, Board board,int row,int column) {
		super(-1, board,row,column);		
	}

	@Override
	public void crush() {
		if (!wasCrushed){	
			
			wasCrushed = true;
			this.board.getCandies()[row][column] = null;
			int random = (int) Math.random()*6;
			for (int i = 0; i < board.BOARDSIZE-1; i++){
				for (int j = 0; j < board.BOARDSIZE-1; j++){
					if (board.getCandies()[i][j] != null && board.getCandies()[i][j].getColor() != -10 && board.getCandies()[i][j].getColor() == random) {
						board.getCandies()[i][j].crush();
					}
				}
			}
			board.updateScore(60);

		}
	}

}