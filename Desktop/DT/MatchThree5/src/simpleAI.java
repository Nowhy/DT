
public class simpleAI {
	private Game game;
	private Board board;
	int tempI = -1;
	int tempJ = -1;
	
	int tempM = -1;
	int tempN = -1;
	
	simpleAI(Game game){
		this.game = game;
	}
	
	// NewObject<i, j, m, n, board, score>
	public void play() {
		board = game.getBoard();
		int score = board.getScore();
		int tempScore = score;
		Board tempBoard = new Board(board);
		tempI = -1;
		tempJ = -1;
		tempM = -1;
		tempN = -1;
		for(int i = Board.BOARDSIZE-1; i >= 0; i--) {
			for(int j = Board.BOARDSIZE-1; j >= 0; j--) {
//				System.out.println("game score: " + game.getBoard().getScore()+ "\n");
//				board.printBoard();
				
				
				// repeat for multiple times and get an average score
				
				// 1. fixed random number -- how good is it?
				// 2. not fixed random number 
				// 3. not fixed random number + average(multiple times)
				// 4. not add Combos
				Board testBoard = new Board(board);
				testBoard.setFirstCandy(i, j);
				testBoard.setSelectedFirst();
//				System.out.println("\ni + j" + i + "  " + j);
				if(j != 0) {
				testBoard.move(i,j-1);
				if(testBoard.getScore() > tempBoard.getScore()) {
					tempScore = testBoard.getScore();
					tempBoard = new Board(testBoard);
					tempI = i;
					tempJ = j;
					tempM = i;
					tempN = j-1;
					
				}else if(testBoard.getScore() == tempBoard.getScore()) {
					if(testBoard.getChocolateNUM() > tempBoard.getChocolateNUM()) {
						tempScore = testBoard.getScore();
						tempBoard = new Board(testBoard);
						tempI = i;
						tempJ = j;
						tempM = i;
						tempN = j-1;
					}else if(testBoard.getFiveNUM() > tempBoard.getFiveNUM()) {
						tempScore = testBoard.getScore();
						tempBoard = new Board(testBoard);
						tempI = i;
						tempJ = j;
						tempM = i;
						tempN = j-1;
					}else if((testBoard.getFourXNUM() + testBoard.getFourYNUM()) > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM()) ) {
						tempScore = testBoard.getScore();
						tempBoard = new Board(testBoard);
						tempI = i;
						tempJ = j;
						tempM = i;
						tempN = j-1;
					}
				}
				}
				
				if(i != 0) {
				testBoard = new Board(board);
				testBoard.setFirstCandy(i, j);
				testBoard.setSelectedFirst();
//				System.out.println(testBoard.getCandies()[8][8].color + "  color " + testBoard.getCandies()[7][8].color + "  " + testBoard.getCandies()[6][8].color);
				testBoard.move(i-1,j);
//				System.out.println(testBoard.getCandies()[8][8].color + "  color " + testBoard.getCandies()[7][8].color + "  " + testBoard.getCandies()[6][8].color);
				if(testBoard.getScore() > tempBoard.getScore()) {
					tempScore = testBoard.getScore();
					tempBoard = new Board(testBoard);
					tempI = i;
					tempJ = j;
					tempM = i-1;
					tempN = j;
					
				}else if(testBoard.getScore() == tempBoard.getScore()) {
					if(testBoard.getChocolateNUM() > tempBoard.getChocolateNUM()) {
						tempScore = testBoard.getScore();
						tempBoard = new Board(testBoard);
						tempI = i;
						tempJ = j;
						tempM = i-1;
						tempN = j;
					}else if(testBoard.getFiveNUM() > tempBoard.getFiveNUM()) {
						tempScore = testBoard.getScore();
						tempBoard = new Board(testBoard);
						tempI = i;
						tempJ = j;
						tempM = i-1;
						tempN = j;
					}else if((testBoard.getFourXNUM() + testBoard.getFourYNUM()) > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM()) ) {
						tempScore = testBoard.getScore();
						tempBoard = new Board(testBoard);
						tempI = i;
						tempJ = j;
						tempM = i-1;
						tempN = j;
					}
				}
				}
			}
		}
		
		
		System.out.println(game.getBoard().getScore() + "   " + tempI + "   " + tempJ + "   " + tempM + "   " +tempN);
		// no valid move
		if(tempScore == score) {
			board = new Board(game);
			System.out.println("repaint....");
			play();
		}else {
			game.getBoard().setSelectedFirst();
			game.getBoard().setFirstCandy(tempM, tempN);
			game.getBoard().printBoard();
			game.getBoard().move(tempI, tempJ);
			System.out.println("\nAfter moving:     ");
			game.getBoard().printBoard();
			game.reDrawBoard();
			
		}

	}
	
	int getTempI() {
		return tempI;
	}
	
	int getTempJ() {
		return tempJ;
	}
	
	int getTempM() {
		return tempM;
	}
	
	int getTempN() {
		return tempN;
	}
}
