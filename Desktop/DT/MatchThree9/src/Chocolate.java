
public class Chocolate {

	Chocolate(){
		
	}
	
	public boolean isMatchChocolate(Board board, Candy[][] candies, Candy candy){
		if (candy == null || candy.getcolour() == -10 || candy.getcolour() == -1) {
			return false;
		}
		int i = candy.getRow();
		int j = candy.getColumn();
		
	
		// (in one row) or (in one column)
		if (j >= 2 && j < board.BOARDSIZE - 2 && candies[i][j-1] != null && candies[i][j-2] != null && candies[i][j+1] != null && candies[i][j+2] != null
				&& candies[i][j-1].getcolour() != -10 && candies[i][j-2].getcolour() != -10 && candies[i][j+1].getcolour() != -10 && candies[i][j+2].getcolour() != -10){
			if (candy.getcolour() == candies[i][j-1].getcolour() && candy.getcolour() == candies[i][j-2].getcolour() && candy.getcolour() == candies[i][j+1].getcolour() && candy.getcolour() == candies[i][j+2].getcolour())
				return true;
		}
		if( i >= 2 && i < board.BOARDSIZE - 2 && candies[i-1][j] != null && candies[i-2][j] != null && candies[i+1][j] != null && candies[i+2][j] != null
				&& candies[i-1][j].getcolour() != -10 && candies[i-2][j].getcolour() != -10 && candies[i+1][j].getcolour() != -10 && candies[i+2][j].getcolour() != -10){
			if (candy.getcolour() == candies[i-1][j].getcolour() && candy.getcolour() == candies[i-2][j].getcolour() && candy.getcolour() == candies[i+1][j].getcolour() && candy.getcolour() == candies[i+2][j].getcolour())
				return true;
		}
		return false;
	}
	
	public void disappearChocolate(Board board, Candy [][] candies, Candy candy){
		if (candy == null || candy.getcolour() == -10 ) return;
		int i = candy.getRow();
		int j = candy.getColumn();
		if(j >= 2 && j < board.BOARDSIZE-2 && candies[i][j-1] != null && candies[i][j-2] != null && candies[i][j+1] != null && candies[i][j+2] != null
				&& candies[i][j-1].getcolour() != -10 && candies[i][j-2].getcolour() != -10 && candies[i][j+1].getcolour() != -10 && candies[i][j+2].getcolour() != -10){// 
			if (candy.getcolour() == candies[i][j-1].getcolour() && candy.getcolour() == candies[i][j-2].getcolour() && candy.getcolour() == candies[i][j+1].getcolour() && candy.getcolour() == candies[i][j+2].getcolour()){
				int savedcolour = candies[i][j].getcolour();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new ChocolateCandy(-1,board,i,j);
				board.setChocolateNUM(board.getChocolateNUM()+1);
				if (candies[i][j-1] != null) candies[i][j-1].crush();
				if (candies[i][j-2] != null) candies[i][j-2].crush();
				if (candies[i][j+1] != null) candies[i][j+1].crush();
				if (candies[i][j+2] != null) candies[i][j+2].crush();

			}
		}
		if (i >= 2 && i < board.BOARDSIZE-2 && candies[i-1][j] != null && candies[i-2][j] != null && candies[i+1][j] != null && candies[i+2][j] != null
				&& candies[i-1][j].getcolour() != -10 && candies[i-2][j].getcolour() != -10 && candies[i+1][j].getcolour() != -10 && candies[i+2][j].getcolour() != -10){//
			if (candy.getcolour() == candies[i-1][j].getcolour() && candy.getcolour() == candies[i-2][j].getcolour() && candy.getcolour() == candies[i+1][j].getcolour() && candy.getcolour() == candies[i+2][j].getcolour()){
				int savedcolour = candies[i][j].getcolour();
				if (candies[i][j] != null) candies[i][j].crush();
				candies[i][j] = new ChocolateCandy(-1,board,i,j);
				board.setChocolateNUM(board.getChocolateNUM()+1);
				if (candies[i+1][j] != null) candies[i+1][j].crush();
				if (candies[i+2][j] != null) candies[i+2][j].crush();
				if (candies[i-1][j] != null) candies[i-1][j].crush();
				if (candies[i-2][j] != null) candies[i-2][j].crush();

			}
		}
	}
	
	public boolean isNoMatchChocolate(Board board, Candy[][] candies) {
		for (int row = 0; row < board.BOARDSIZE; row++){ 
			for (int column = 0; column < board.BOARDSIZE; column++){
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
			for(int i = board.BOARDSIZE-1; i >= 0; i--) {
				for(int j = board.BOARDSIZE-1; j >= 0; j--) {
					if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getcolour() != -10){
						board.getCandies()[i][j].crush();
						board.setChocolateNUM(board.getChocolateNUM()+1);
						board.updateScore(120);
					}
				}
			}
		}
		
		// with BombCandy
		public void CombineWithBombCandy(Board board,  Candy firstCandy, Candy secondCandy) {
			int row = firstCandy.getRow();
			int column = firstCandy.getColumn();
			int colour = secondCandy.getcolour();
			board.getCandies()[row][column] = null;
			board.getCandies()[secondCandy.row][secondCandy.column] = null;
			firstCandy = null;
			secondCandy = null;
			
			for(int i = board.BOARDSIZE-1; i >= 0; i--) {
				for(int j = board.BOARDSIZE-1; j >= 0; j--) {
					if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getcolour() == colour){
						board.getCandies()[i][j] = new BombCandy(colour,board,i,j);
						board.setFiveNUM(board.getFiveNUM()+1);
						board.updateScore(200);
					}
				}
			}
			
			for(int i = board.BOARDSIZE-1; i >= 0; i--) {
				for(int j = board.BOARDSIZE-1; j >= 0; j--) {
					if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getcolour() == colour){
						board.getCandies()[i][j].crush();
						board.updateScore(30);
					}

				}
			}
		}
		
		
		// with XCandy
			public void CombineWithXCandy(Board board, Candy firstCandy, Candy secondCandy) {
				int row = firstCandy.getRow();
				int column = firstCandy.getColumn();
				int colour = secondCandy.getcolour();
				board.getCandies()[row][column] = null;
				board.getCandies()[secondCandy.row][secondCandy.column] = null;
				firstCandy = null;
				secondCandy = null;
				
				for(int i = board.BOARDSIZE-1; i >= 0; i--) {
					for(int j = board.BOARDSIZE-1; j >= 0; j--) {
						int ran = (int) (Math.random()*2);
						if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getcolour() == colour){
							if (ran == 1) {
								board.getCandies()[i][j] = new XCandy(colour,board,i,j);
								board.setFourXNUM(board.getFourXNUM()+1);
								board.updateScore(120);
							}else {
								board.getCandies()[i][j] = new YCandy(colour,board,i,j);
								board.setFourYNUM(board.getFourYNUM()+1);
								board.updateScore(120);
							}	
							
						}

					}
				}
				
				for(int i = board.BOARDSIZE-1; i >= 0; i--) {
					for(int j = board.BOARDSIZE-1; j >= 0; j--) {
						if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getcolour() == colour){
							board.getCandies()[i][j].crush();
							board.updateScore(30);
						}

					}
				}
			}
			
			
			public void CombineWithYCandy(Board board, Candy firstCandy, Candy secondCandy) {
				int row = firstCandy.getRow();
				int column = firstCandy.getColumn();
				int colour = secondCandy.getcolour();
				board.getCandies()[row][column] = null;
				board.getCandies()[secondCandy.row][secondCandy.column] = null;
				firstCandy = null;
				secondCandy = null;
				
				for(int i = board.BOARDSIZE-1; i >= 0; i--) {
					for(int j = board.BOARDSIZE-1; j >= 0; j--) {
						int ran = (int) (Math.random()*2);
						if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getcolour() == colour){
							if (ran == 1) {
								board.getCandies()[i][j] = new YCandy(colour,board,i,j);
								board.setFourYNUM(board.getFourYNUM()+1);
								board.updateScore(120);
							}else {
								board.getCandies()[i][j] = new XCandy(colour,board,i,j);
								board.setFourXNUM(board.getFourXNUM()+1);
								board.updateScore(120);
							}	
							
						}

					}
				}
				
				for(int i = board.BOARDSIZE-1; i >= 0; i--) {
					for(int j = board.BOARDSIZE-1; j >= 0; j--) {
						if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getcolour() == colour){
							board.getCandies()[i][j].crush();
							board.updateScore(30);
						}

					}
				}
			}
			
			
			public void CombineWithRegularCandy(Board board, Candy firstCandy, Candy secondCandy) {
				int row = firstCandy.getRow();
				int column = firstCandy.getColumn();
				int colour = secondCandy.getcolour();
				board.getCandies()[row][column] = null;
				board.getCandies()[secondCandy.row][secondCandy.column] = null;
				firstCandy = null;
				secondCandy = null;
				
				for(int i = board.BOARDSIZE-1; i >= 0; i--) {
					for(int j = board.BOARDSIZE-1; j >= 0; j--) {
						if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getcolour() == colour){
							board.getCandies()[i][j].crush();
							board.updateScore(360);
						}
					}
				}
				
			}
}