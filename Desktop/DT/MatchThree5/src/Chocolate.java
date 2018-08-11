
public class Chocolate {

	Chocolate(){
		
	}
	
	public boolean isMatchChocolate(Board board, Candy[][] candies, Candy candy){
		if (candy == null) return false;
		int i = candy.getRow();
		int j = candy.getColumn();
		
	
		// (in one row) or (in one column)
		if (j >= 2 && j < board.BOARDSIZE - 2 && candies[i][j-1] != null && candies[i][j-2] != null && candies[i][j+1] != null && candies[i][j+2] != null){
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j-2].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor())
				return true;
		}
		if( i >= 2 && i < board.BOARDSIZE - 2 && candies[i-1][j] != null && candies[i-2][j] != null && candies[i+1][j] != null && candies[i+2][j] != null){
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i-2][j].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor())
				return true;
		}
		return false;
	}
	
	public void disappearChocolate(Board board, Candy [][] candies, Candy candy){
		if (candy == null) return;
		int i = candy.getRow();
		int j = candy.getColumn();
		if(j >= 2 && j < board.BOARDSIZE-2 && candies[i][j-1] != null && candies[i][j-2] != null && candies[i][j+1] != null && candies[i][j+2] != null){// 
			if (candy.getColor() == candies[i][j-1].getColor() && candy.getColor() == candies[i][j-2].getColor() && candy.getColor() == candies[i][j+1].getColor() && candy.getColor() == candies[i][j+2].getColor()){
				int savedColor = candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new ChocolateCandy(-1,board,i,j);
				if (candies[i][j-1] != null) candies[i][j-1].crush();
				if (candies[i][j-2] != null) candies[i][j-2].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();
				if (candies[i][j+2] != null) candies[i][j+2].crush();

			}
		}
		if (i >= 2 && i < board.BOARDSIZE-2 && candies[i-1][j] != null && candies[i-2][j] != null && candies[i+1][j] != null && candies[i+2][j] != null){//
			if (candy.getColor() == candies[i-1][j].getColor() && candy.getColor() == candies[i-2][j].getColor() && candy.getColor() == candies[i+1][j].getColor() && candy.getColor() == candies[i+2][j].getColor()){
				int savedColor = candies[i][j].getColor();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new ChocolateCandy(-1,board,i,j);
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i+2][j] != null) candies[i+2][j].crush();
				if (candies[i-1][j] != null) candies[i-1][j].crush();
				if (candies[i-2][j] != null) candies[i-2][j].crush();

			}
		}
	}
	
	public boolean isNoMatchChocolate(Board board, Candy[][] candies) {
		for (int row = 0; row < board.BOARDSIZE; row = row+1){ 
			for (int column = 0; column < board.BOARDSIZE; column = column+1){
				if(isMatchChocolate(board, candies, candies[row][column])){
					return true;
				}
			}
		}
		return false;
	}
	
	
	// all crush
		public void CombineWithChocolateCandy(Board board, Candy firstCandy, Candy secondCandy) {
			int row = firstCandy.getRow();
			int column = firstCandy.getColumn();
			board.getCandies()[row][column] = null;
			board.getCandies()[secondCandy.row][secondCandy.column] = null;
			firstCandy = null;
			secondCandy = null;
			for(int i = Board.BOARDSIZE-1; i >= 0; i--) {
				for(int j = Board.BOARDSIZE-1; j >= 0; j--) {
					if(board.getCandies()[i][j]!=null){
						board.getCandies()[i][j].crush();
						board.updateScore(60);
					}
				}
			}
		}
		
		// with BombCandy
		public void CombineWithBombCandy(Board board,  Candy firstCandy, Candy secondCandy) {
			int row = firstCandy.getRow();
			int column = firstCandy.getColumn();
			int color = secondCandy.getColor();
			board.getCandies()[row][column] = null;
			board.getCandies()[secondCandy.row][secondCandy.column] = null;
			firstCandy = null;
			secondCandy = null;
			
			for(int i = Board.BOARDSIZE-1; i >= 0; i--) {
				for(int j = Board.BOARDSIZE-1; j >= 0; j--) {
					if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getColor() == color){
						board.getCandies()[i][j] = new BombCandy(color,board,i,j);
						board.updateScore(200);
					}
				}
			}
			
			for(int i = Board.BOARDSIZE-1; i >= 0; i--) {
				for(int j = Board.BOARDSIZE-1; j >= 0; j--) {
					if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getColor() == color){
						board.getCandies()[i][j].crush();
					}

				}
			}
		}
		
		
		// with XCandy
			public void CombineWithXCandy(Board board, Candy firstCandy, Candy secondCandy) {
				int row = firstCandy.getRow();
				int column = firstCandy.getColumn();
				int color = secondCandy.getColor();
				board.getCandies()[row][column] = null;
				board.getCandies()[secondCandy.row][secondCandy.column] = null;
				firstCandy = null;
				secondCandy = null;
				
				for(int i = Board.BOARDSIZE-1; i >= 0; i--) {
					for(int j = Board.BOARDSIZE-1; j >= 0; j--) {
						int ran = (int) (Math.random()*2);
						if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getColor() == color){
							if (ran == 1) {
								board.getCandies()[i][j] = new XCandy(color,board,i,j);
								board.updateScore(120);
							}else {
								board.getCandies()[i][j] = new YCandy(color,board,i,j);
								board.updateScore(120);
							}	
							
						}

					}
				}
				
				for(int i = Board.BOARDSIZE-1; i >= 0; i--) {
					for(int j = Board.BOARDSIZE-1; j >= 0; j--) {
						if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getColor() == color){
							board.getCandies()[i][j].crush();
						}

					}
				}
			}
			
			
			public void CombineWithYCandy(Board board, Candy firstCandy, Candy secondCandy) {
				int row = firstCandy.getRow();
				int column = firstCandy.getColumn();
				int color = secondCandy.getColor();
				board.getCandies()[row][column] = null;
				board.getCandies()[secondCandy.row][secondCandy.column] = null;
				firstCandy = null;
				secondCandy = null;
				
				for(int i = Board.BOARDSIZE-1; i >= 0; i--) {
					for(int j = Board.BOARDSIZE-1; j >= 0; j--) {
						int ran = (int) (Math.random()*2);
						if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getColor() == color){
							if (ran == 1) {
								board.getCandies()[i][j] = new YCandy(color,board,i,j);
								board.updateScore(120);
							}else {
								board.getCandies()[i][j] = new XCandy(color,board,i,j);
								board.updateScore(120);
							}	
							
						}

					}
				}
				
				for(int i = Board.BOARDSIZE-1; i >= 0; i--) {
					for(int j = Board.BOARDSIZE-1; j >= 0; j--) {
						if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getColor() == color){
							board.getCandies()[i][j].crush();
						}

					}
				}
			}
			
			
			public void CombineWithRegularCandy(Board board, Candy firstCandy, Candy secondCandy) {
				int row = firstCandy.getRow();
				int column = firstCandy.getColumn();
				int color = secondCandy.getColor();
				board.getCandies()[row][column] = null;
				board.getCandies()[secondCandy.row][secondCandy.column] = null;
				firstCandy = null;
				secondCandy = null;
				
				for(int i = Board.BOARDSIZE-1; i >= 0; i--) {
					for(int j = Board.BOARDSIZE-1; j >= 0; j--) {
						if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getColor() == color){
							board.getCandies()[i][j].crush();
							board.updateScore(60);
						}
					}
				}
				
			}
}
