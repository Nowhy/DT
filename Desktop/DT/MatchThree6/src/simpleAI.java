
public class simpleAI {
	private Game game;
	private Board board;
	Board tempBoard;
	Board testBoard;
	int tempScore;
	int tempI = -1;
	int tempJ = -1;
	
	int tempM = -1;
	int tempN = -1;
	int repeatScore = 0;
	int repeatChocolate = 0;
	int repeatFive = 0;
	int repeatFour = 0;
	
	
	simpleAI(Game game){
		this.game = game;
	}
	
	// NewObject<i, j, m, n, board, score>
	public void play() {
		board = game.getBoard();
		int score = board.getScore();
		int tempScore = score;
		tempBoard = new Board(board);
		tempI = -1;
		tempJ = -1;
		tempM = -1;
		tempN = -1;
		for(int i = board.BOARDSIZE-1; i >= 0; i--) {
			for(int j = board.BOARDSIZE-1; j >= 0; j--) {
//				System.out.println("game score: " + game.getBoard().getScore()+ "\n");
//				board.printBoard();
				
				
				// repeat for multiple times and get an average score
				
				// 1. fixed random number -- how good is it?
				// 2. not fixed random number 
				// 3. not fixed random number + average(multiple times)
				testBoard = new Board(board);
				testBoard.setFirstCandy(i, j);
				testBoard.setSelectedFirst();
//				System.out.println("\ni + j" + i + "  " + j);
				
				if(game.STRATEGY == 1) ScoreFirst(i, j);
				else if(game.STRATEGY == 2) ChocolateFirst(i, j);
				else if(game.STRATEGY == 3) FiveFirst(i, j);
				else if(game.STRATEGY == 4) FourFirst(i, j);
				else ScoreFirst(i, j);
				
			}
		}
		
		
		System.out.println(game.getBoard().getScore() + "   " + tempI + "   " + tempJ + "   " + tempM + "   " +tempN);
		// no valid move
		if(tempI == -1 && tempJ == -1) {
			for(int i = 0; i < board.BOARDSIZE; i++) {
				for(int j = 0; j < board.BOARDSIZE; j++) {
					if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getColor() != -10) {
						board.getCandies()[i][j] = null;
					}
				}
			}
			while(!board.isNONullCandy()) {
				board.addRandomCandy(board.METHOD);
				board.checkChainReaction(0);
			}
			System.out.println("repaint....");
			play();
		}else {
			game.getBoard().setSelectedFirst();
			game.getBoard().setFirstCandy(tempM, tempN);
			game.getBoard().move(tempI, tempJ);
			game.getBoard().printBoard();
			game.reDrawBoard();
			
		}

	}
	
	public void update(int a, int b, int c, int d) {
		tempScore = testBoard.getScore();
		tempBoard = new Board(testBoard);
		setTempI(a);
		setTempJ(b);
		setTempM(c);
		setTempN(d);
	}

	public void ScoreFirst(int i, int j) {
		
		if(j != 0) {
			testBoard = new Board(tempBoard);
			testBoard.setFirstCandy(i, j);
			testBoard.setSelectedFirst();
			testBoard.move(i,j-1);
			
			repeatForTwenty(i,j,i,j-1);
			if(getRepeatScore() > tempBoard.getScore()) {
				update(i,j,i,j-1);
			}else if(getRepeatScore() == tempBoard.getScore()) {
				if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
					update(i,j,i,j-1);
				}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
					if(getRepeatFive() > tempBoard.getFiveNUM()) {
						update(i,j,i,j-1);
					}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
							update(i,j,i,j-1);
						}
					}
				}
			}
			
		}
		
		
		if(i != 0) {
			testBoard = new Board(tempBoard);
			testBoard.setFirstCandy(i, j);
			testBoard.setSelectedFirst();
			testBoard.move(i-1,j);
			repeatForTwenty(i,j,i-1,j);
			if(getRepeatScore() > tempBoard.getScore()) {
				update(i,j,i-1,j);
			}else if(getRepeatScore() == tempBoard.getScore()) {
				if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
					update(i,j,i-1,j);
				}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
					if(getRepeatFive() > tempBoard.getFiveNUM()) {
						update(i,j,i-1,j);
					}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
							update(i,j,i-1,j);
						}
					}
				}
			}
		}
		
	}
	
	
	// chocolate > score > five > four
	public void ChocolateFirst(int i, int j) {
		
		if(j != 0) {
			testBoard = new Board(tempBoard);
			testBoard.setFirstCandy(i, j);
			testBoard.setSelectedFirst();
			testBoard.move(i,j-1);
			repeatForTwenty(i,j,i,j-1);
			if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
				update(i,j,i,j-1);
			}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
				if(getRepeatScore() > tempBoard.getScore()) {
					update(i,j,i,j-1);
				}else if(getRepeatScore() == tempBoard.getScore()) {
					if(getRepeatFive() > tempBoard.getFiveNUM()) {
						update(i,j,i,j-1);
					}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
							update(i,j,i,j-1);
						}
					}
				}
			}
		}
		
		
		if(i != 0) {
			testBoard = new Board(tempBoard);
			testBoard.setFirstCandy(i, j);
			testBoard.setSelectedFirst();
			testBoard.move(i-1,j);
			repeatForTwenty(i,j,i-1,j);
			if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
				update(i,j,i-1,j);
			}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
				if(getRepeatScore() > tempBoard.getScore()) {
					update(i,j,i-1,j);
				}else if(getRepeatScore() == tempBoard.getScore()) {
					if(getRepeatFive() > tempBoard.getFiveNUM()) {
						update(i,j,i-1,j);
					}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
							update(i,j,i-1,j);
						}
					}
				}
			}
		}
		
	}
	
	
	// five > score > chocolate > four
	public void FiveFirst(int i, int j) {
		
		if(j != 0) {
			testBoard = new Board(tempBoard);
			testBoard.setFirstCandy(i, j);
			testBoard.setSelectedFirst();
			testBoard.move(i,j-1);
			repeatForTwenty(i,j,i,j-1);
			if(getRepeatFive() > tempBoard.getFiveNUM()) {
				update(i,j,i,j-1);
			}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
				if(getRepeatScore() > tempBoard.getScore()) {
					update(i,j,i,j-1);
				}else if(getRepeatScore() == tempBoard.getScore()) {
					if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
						update(i,j,i,j-1);
					}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
							update(i,j,i,j-1);
						}
					}
				}
			}
			
		}
		
		
		if(i != 0) {
			testBoard = new Board(tempBoard);
			testBoard.setFirstCandy(i, j);
			testBoard.setSelectedFirst();
			testBoard.move(i-1,j);
			repeatForTwenty(i,j,i-1,j);
			if(getRepeatFive() > tempBoard.getFiveNUM()) {
				update(i,j,i-1,j);
			}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
				if(getRepeatScore() > tempBoard.getScore()) {
					update(i,j,i-1,j);
				}else if(getRepeatScore() == tempBoard.getScore()) {
					if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
						update(i,j,i-1,j);
					}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
							update(i,j,i-1,j);
						}
					}
				}
			}
		}
		
	}
	
	
	
	// four > score > chocolate > five
	public void FourFirst(int i, int j) {
		
		if(j != 0) {
			testBoard = new Board(tempBoard);
			testBoard.setFirstCandy(i, j);
			testBoard.setSelectedFirst();
			testBoard.move(i,j-1);
			repeatForTwenty(i,j,i,j-1);
			if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
				update(i,j,i,j-1);
			}else if(getRepeatFour() == (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
				if(getRepeatScore() > tempBoard.getScore()) {
					update(i,j,i,j-1);
				}else if(getRepeatScore() == tempBoard.getScore()) {
					if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
						update(i,j,i,j-1);
					}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
						if(getRepeatFive() > tempBoard.getFiveNUM()) {
							update(i,j,i,j-1);
						}
					}
				}
			}
		}
		
		
		if(i != 0) {
			testBoard = new Board(tempBoard);
			testBoard.setFirstCandy(i, j);
			testBoard.setSelectedFirst();
			testBoard.move(i-1,j);
			repeatForTwenty(i,j,i-1,j);
			if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
				update(i,j,i-1,j);
			}else if(getRepeatFour() == (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
				if(getRepeatScore() > tempBoard.getScore()) {
					update(i,j,i-1,j);
				}else if(getRepeatScore() == tempBoard.getScore()) {
					if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
						update(i,j,i-1,j);
					}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
						if(getRepeatFive() > tempBoard.getFiveNUM()) {
							update(i,j,i-1,j);
						}
					}
				}
			}
			
		}
		
	}
	
	
	public void repeatForTwenty(int tempI, int tempJ, int tempM, int tempN) {
		setRepeatScore(0);
		setRepeatChocolate(0);
		setRepeatFive(0);
		setRepeatFour(0);
		for(int i = 0; i < 20; i++) {
			testBoard = null;
			testBoard = new Board(board);
			testBoard.setFirstCandy(tempI, tempJ);
			testBoard.setSelectedFirst();
			testBoard.move(tempM, tempN);
			repeatScore = getRepeatScore() + testBoard.getScore();
			repeatChocolate = getRepeatChocolate() + testBoard.getChocolateNUM();
			repeatFive = getRepeatFive() + testBoard.getFiveNUM();
			repeatFour = getRepeatFour() + testBoard.getFourXNUM() + testBoard.getFourYNUM();
		}
		setRepeatScore(getRepeatScore()/20);
		setRepeatChocolate(getRepeatChocolate()/20);
		setRepeatFive(getRepeatFive()/20);
		setRepeatFour(getRepeatFour()/20);
		
	}
	
	
	void setTempI(int i) {
		tempI = i;
	}
	
	void setTempJ(int i) {
		tempJ = i;
	}
	
	void setTempM(int i) {
		tempM = i;
	}
	
	void setTempN(int i) {
		tempN = i;
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
	
	
	void setRepeatScore(int i) {
		repeatScore = i;
	}
	
	void setRepeatChocolate(int i) {
		repeatChocolate = i;
	}
	
	void setRepeatFive(int i) {
		repeatFive = i;
	}
	
	void setRepeatFour(int i) {
		repeatFour = i;
	}
	
	int getRepeatScore() {
		return repeatScore;
	}
	
	int getRepeatChocolate() {
		return repeatChocolate;
	}
	
	int getRepeatFive() {
		return repeatFive;
	}
	
	int getRepeatFour() {
		return repeatFour;
	}
	
}