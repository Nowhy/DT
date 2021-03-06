
public class FourX {

	FourX(){
		
	}
	
	
	public boolean isNoMatchFourX(Board board, Candy[][] candies) {
		for (int row = 0; row < board.BOARDSIZE; row = row+1){ 
			for (int column = 0; column < board.BOARDSIZE; column = column+1){
				if(isMatchFourX(board, candies, candies[row][column])){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isMatchFourX(Board board, Candy[][] candies, Candy candy){
		if (candy == null) return false;
		int i = candy.getRow();
		int j = candy.getColumn();
		// X  FirstCandy X X
		if (j >= 1 && j < board.BOARDSIZE-2 && candies[i][j-1] != null && candies[i][j+1] != null && candies[i][j+2] != null){
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor())
				return true;
		}
		// X X FirstCandy X
		if (j >= 2 && j< board.BOARDSIZE-1 && candies[i][j-1] != null && candies[i][j+1] != null && candies[i][j-2] != null){
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j-2].getColor())
				return true;
		}
		return false;
	}
	
	public void disappearFourX(Board board, Candy[][] candies, Candy candy){
		if (candy == null) return;
		int i = candy.getRow();
		int j = candy.getColumn();
		if (j >= 1 && j < board.BOARDSIZE -2 && candies[i][j-1] != null && candies[i][j+1] != null && candies[i][j+2] != null){//case inner left {0100}
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor()){
				int savedColor=candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j]= new XCandy(savedColor,board,i,j);
				if (candies[i][j-1] != null) candies[i][j-1].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();
				if (candies[i][j-1] != null) candies[i][j-1].crush();
			}
		}
		
		if (j >= 2 && j < board.BOARDSIZE-1 && candies[i][j-1] != null && candies[i][j+1] != null && candies[i][j-2] != null){//case inner right {0010}
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j-2].getColor()){
				int savedColor=candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j]= new XCandy(savedColor,board,i,j);
				if (candies[i][j-1] != null) candies[i][j-1].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();
				if (candies[i][j-2] != null) candies[i][j-2].crush();
			}
		}
	}
}
