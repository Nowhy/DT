public class ChocolateCandy extends Candy {

	public ChocolateCandy(int color, Board board,int row,int column) {
		super(-1, board,row,column);		
	}

	@Override
	public void crush() {
		if (!wasCrushed){	
			
			wasCrushed = true;
			this.board.getCandies()[row][column] = null;

		}
	}

}