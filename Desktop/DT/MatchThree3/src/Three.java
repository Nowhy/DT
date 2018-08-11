
public class Three {
	Three(){
		
	}
	
	public boolean isNoMatchThree(Board board, Candy[][] candies) {
		for (int row = 0; row < board.BOARDSIZE; row = row+1){ 
			for (int column = 0; column < board.BOARDSIZE; column = column+1){
				if(isMatchThree(board, candies, candies[row][column])){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isMatchThree(Board board, Candy[][] candies, Candy candy) {
		if (candy == null) return false;
		int i = candy.getRow();
		int j = candy.getColumn();
		
		//FirstCandy X X
		if (j < board.BOARDSIZE - 2 && candies[i][j+1] != null && candies[i][j+2] != null){
			if (candy.getColor() == candies[i][j+1].getColor() 
					&& candy.getColor() == candies[i][j+2].getColor())
				return true;
		}
		
		//X FirstCandy X
		if (j >= 1 && j < board.BOARDSIZE - 1 && candies[i][j+1] != null && candies[i][j-1] != null){
			if (candy.getColor() == candies[i][j+1].getColor() 
					&& candy.getColor() == candies[i][j-1].getColor())
				return true;
		}
		
		//X X FirstCandy
		if (j >= 2 && candies[i][j-1] != null && candies[i][j-2] != null){
			if (candy.getColor() == candies[i][j-1].getColor() 
					&& candy.getColor() == candies[i][j-2].getColor())
				return true;
		}
		
		//FirstCandy // X // X
		if (i < board.BOARDSIZE - 2 && candies[i+1][j] != null && candies[i+2][j] != null){
			if (candy.getColor() == candies[i+1][j].getColor() 
					&& candy.getColor() == candies[i+2][j].getColor())
				return true;
		}
		
		//X // FistCandy // X
		if (i >= 1 && i < board.BOARDSIZE - 1 && candies[i+1][j] != null && candies[i-1][j] != null){// case middle
			if (candy.getColor() == candies[i+1][j].getColor() 
					&& candy.getColor() == candies[i-1][j].getColor())
				return true;
		}
		
		//X // X  // FistCandy
		if(i >= 2 && candies[i-1][j] != null && candies[i-2][j] != null){// case down
			if (candy.getColor() == candies[i-1][j].getColor() 
					&& candy.getColor() == candies[i-2][j].getColor())
				return true;
		}
		
		
		
		return false;
	}

	public void disappearThree(Board board, Candy[][] candies, Candy candy){
		if (candy == null) return;
		int i = candy.getRow();
		int j = candy.getColumn();
		if(i >= 2 && candies[i-1][j] != null && candies[i-2][j] != null){
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i-2][j].getColor()){
				if (candies[i][j] != null) candies[i][j].crush();
				if (candies[i-1][j] != null) candies[i-1][j].crush();
				if (candies[i-2][j] != null) candies[i-2][j].crush();
			}
		}
		if (i < board.BOARDSIZE-2 && candies[i+1][j] != null && candies[i+2][j] != null){// case up
			if (candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor()){
				if (candies[i][j] != null) candies[i][j].crush();
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i+2][j] != null) candies[i+2][j].crush();
			}
		}
		if (i>= 1 && i < board.BOARDSIZE-1 && candies[i+1][j] != null && candies[i-1][j] != null){
			if (candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i-1][j].getColor()){
				if (candies[i][j] != null) candies[i][j].crush();
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i-1][j] != null) candies[i-1][j].crush();
			}
		}
		if (j >= 2 && candies[i][j-1] != null && candies[i][j-2] != null){
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j-2].getColor()){
				if (candies[i][j] != null) candies[i][j].crush();
				if (candies[i][j-1] != null) candies[i][j-1].crush();
				if (candies[i][j-2] != null) candies[i][j-2].crush();
			}
		}
		if (j < board.BOARDSIZE-2 && candies[i][j+1] != null && candies[i][j+2] != null){
			if (candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor()){
				if (candies[i][j] != null) candies[i][j].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();
				if (candies[i][j+2] != null) candies[i][j+2].crush();
			}
		}
		if (j >= 1 && j < board.BOARDSIZE-1 && candies[i][j+1] != null && candies[i][j-1] != null){
			if (candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j-1].getColor()){
				if (candies[i][j] != null) candies[i][j].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();
				if (candies[i][j-1] != null) candies[i][j-1].crush();
			}
		}
	
}
}
