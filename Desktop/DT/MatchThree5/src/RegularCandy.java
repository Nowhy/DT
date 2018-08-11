public class RegularCandy extends Candy {

	public RegularCandy(int color, Board board,int row,int column) {
		super(color, board,row,column);		
	}

	@Override
	public void crush() { //crushing a regular candy - set to null and add points
		this.board.getCandies()[row][column]=null;
	}

}