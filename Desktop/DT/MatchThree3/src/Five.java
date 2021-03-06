
public class Five {
	Five(){
		
	}
	
	public boolean isMatchFiveCenter(Board board, Candy[][] candies, Candy candy){
		if (candy == null) return false;
		int i = candy.getRow();
		int j = candy.getColumn();
		
		
		// L
		if(i >= 2 && j < board.BOARDSIZE-2 && candies[i-1][j] != null && candies[i-2][j] != null && candies[i][j+1] != null && candies[i][j+2] != null){// 
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i-2][j].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor())
				return true;
		}
		if (j >= 2 && i < board.BOARDSIZE-2 && candies[i][j-1] != null && candies[i][j-2] != null && candies[i+1][j] != null && candies[i+2][j] != null){//
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j-2].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor())
				return true;
		}
		if (j < board.BOARDSIZE-2 && i < board.BOARDSIZE-2 && candies[i][j+1] != null && candies[i][j+2] != null && candies[i+1][j] != null && candies[i+2][j] != null){//
			if (candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor())
				return true;
		}
		if (j >= 2 && i >= 2 && candies[i][j-1] != null && candies[i][j-2] != null && candies[i-1][j] != null && candies[i-2][j] != null){//
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j-2].getColor() && candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i-2][j].getColor())
				return true;
		}
		
		
		
		// (T) in four directions
		if (j >= 1 && j < board.BOARDSIZE - 1 && i < board.BOARDSIZE - 2 && candies[i][j-1] != null && candies[i][j+1] != null && candies[i+1][j] != null && candies[i+2][j] != null){//
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor())
				return true;
		}
		if (j >= 1 && j < board.BOARDSIZE - 1 && i >= 2 && candies[i][j-1] != null && candies[i][j+1] != null && candies[i-1][j] != null && candies[i-2][j] != null){//
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i-2][j].getColor())
				return true;
		}
		if (i >= 1 && i < board.BOARDSIZE - 1 && j < board.BOARDSIZE - 2 && candies[i-1][j] != null && candies[i+1][j] != null && candies[i][j+1] != null && candies[i][j+2] != null){//
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor())
				return true;
		}
		if (i >= 1 && i < board.BOARDSIZE - 1 && j >= 2 && candies[i-1][j] != null && candies[i+1][j] != null && candies[i][j-1] != null && candies[i][j-2] != null){//
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j-2].getColor())
				return true;
		}
		

		return false;
	}
	
	public void disappearFiveCenter(Board board, Candy [][] candies, Candy candy){
		if (candy == null) return;
		int i = candy.getRow();
		int j = candy.getColumn();
		if(i >= 2 && j < board.BOARDSIZE-2 && candies[i-1][j] != null && candies[i-2][j] != null && candies[i][j+1] != null && candies[i][j+2] != null){// 
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i-2][j].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor()){
				int savedColor = candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new BombCandy(savedColor,board,i,j);
				if (candies[i-1][j] != null) candies[i-1][j].crush();
				if (candies[i-2][j] != null) candies[i-2][j].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();
				if (candies[i][j+2] != null) candies[i][j+2].crush();

			}
		}
		if (j >= 2 && i < board.BOARDSIZE-2 && candies[i][j-1] != null && candies[i][j-2] != null && candies[i+1][j] != null && candies[i+2][j] != null){//
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j-2].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor()){
				int savedColor = candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new BombCandy(savedColor,board,i,j);
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i+2][j] != null) candies[i+2][j].crush();
				if (candies[i][j-1] != null) candies[i][j-1].crush();
				if (candies[i][j-2] != null) candies[i][j-2].crush();

			}
		}
		if (j < board.BOARDSIZE-2 && i < board.BOARDSIZE-2 && candies[i][j+1] != null && candies[i][j+2] != null && candies[i+1][j] != null && candies[i+2][j] != null){//
			if (candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor()){
				int savedColor = candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new BombCandy(savedColor,board,i,j);
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i+2][j] != null) candies[i+2][j].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();
				if (candies[i][j+2] != null) candies[i][j+2].crush();

			}
		}
		if (j >= 2 && i >= 2 && candies[i][j-1] != null && candies[i][j-2] != null && candies[i-1][j] != null && candies[i-2][j] != null){//
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j-2].getColor() && candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i-2][j].getColor()){
				int savedColor = candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new BombCandy(savedColor,board,i,j);
				if (candies[i-1][j] != null) candies[i-1][j].crush();
				if (candies[i-2][j] != null) candies[i-2][j].crush();
				if (candies[i][j-1] != null) candies[i][j-1].crush();
				if (candies[i][j-2] != null) candies[i][j-2].crush();

			}
		}
		

		if (j >= 1 && j < board.BOARDSIZE-1 && i< board.BOARDSIZE-2 && candies[i][j-1] != null && candies[i][j+1] != null && candies[i+1][j] != null && candies[i+2][j] != null){//
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor()){
				int savedColor = candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new BombCandy(savedColor,board,i,j);
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i+2][j] != null) candies[i+2][j].crush();
				if (candies[i][j-1] != null) candies[i][j-1].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();

			}
		}
		if (j >= 1 && j < board.BOARDSIZE-1 && i >= 2 && candies[i][j-1] != null && candies[i][j+1] != null && candies[i-1][j] != null && candies[i-2][j] != null){//
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i-2][j].getColor()){
				int savedColor = candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new BombCandy(savedColor,board,i,j);
				if (candies[i-1][j] != null) candies[i-1][j].crush();
				if (candies[i-2][j] != null) candies[i-2][j].crush();
				if (candies[i][j-1] != null) candies[i][j-1].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();

			}
		}
		if (i >= 1 && i < board.BOARDSIZE-1 && j< board.BOARDSIZE-2 && candies[i-1][j] != null && candies[i+1][j] != null && candies[i][j+1] != null && candies[i][j+2] != null){//
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor()){
				int savedColor = candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new BombCandy(savedColor,board,i,j);
				if (candies[i-1][j] != null) candies[i-1][j].crush();
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();
				if (candies[i][j+2] != null) candies[i][j+2].crush();

			}
		}
		if (i >= 1 && i < board.BOARDSIZE-1 && j >= 2 && candies[i-1][j] != null && candies[i+1][j] != null && candies[i][j-1] != null && candies[i][j-2] != null){//
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j-2].getColor()){
				int savedColor = candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new BombCandy(savedColor,board,i,j);
				if (candies[i-1][j] != null) candies[i-1][j].crush();
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i][j-1] != null) candies[i][j-1].crush();
				if (candies[i][j-2] != null) candies[i][j-2].crush();

			}
		}
	}
	

	
	public boolean isNoMatchFive(Board board, Candy[][] candies) {
		for (int row = 0; row < board.BOARDSIZE; row = row+1){ 
			for (int column = 0; column < board.BOARDSIZE; column = column+1){
				if(isMatchFiveCenter(board, candies, candies[row][column])){
					return true;
				}
			}
		}
		return false;
	}
	
}
