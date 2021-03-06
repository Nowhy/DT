
public class FourY {

	FourY(){
		
	}
	
	public boolean isNoMatchFourY(Board board, Candy[][] candies) {
		for (int row = 0; row < board.BOARDSIZE; row = row+1){ 
			for (int column = 0; column < board.BOARDSIZE; column = column+1){
				if(isMatchFourY(board, candies, candies[row][column])){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isMatchFourY(Board board, Candy[][] candies, Candy candy){
		if (candy == null) return false;
		int i = candy.getRow();
		int j = candy.getColumn();
		if(i>=1 && i <  board.BOARDSIZE -2 && candies[i-1][j] != null && candies[i+1][j] != null && candies[i+2][j] != null){// case inner down
			if (candy.getColor() != -10 && candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor())
				return true;
		}
		if(i>=2 && i <   board.BOARDSIZE -1 && candies[i-1][j] != null && candies[i+1][j] != null && candies[i-2][j] != null){// case inner up
			if (candy.getColor() != -10 && candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i-2][j].getColor())
				return true;
		}

		return false;
	}
	
	public void disappearFourY(Board board, Candy[][] candies, Candy candy){
		if (candy == null) return;
		int i = candy.getRow();
		int j = candy.getColumn();
		if(i >= 1 && i < board.BOARDSIZE -2 && candies[i-1][j] != null && candies[i+1][j] != null && candies[i+2][j] != null){
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor()){
				int savedColor=candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j]= new YCandy(savedColor,board,i,j);
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i+2][j] != null) candies[i+2][j].crush();
				if (candies[i-1][j] != null) candies[i-1][j].crush();
			}
		}
		if(i >= 2 && i < board.BOARDSIZE -1 && candies[i-1][j] != null && candies[i+1][j] != null && candies[i-2][j] != null){
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i-2][j].getColor()){
				int savedColor=candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j]= new YCandy(savedColor,board,i,j);
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i-2][j] != null) candies[i-2][j].crush();
				if (candies[i-1][j] != null) candies[i-1][j].crush();
			}
		}
	}
}
