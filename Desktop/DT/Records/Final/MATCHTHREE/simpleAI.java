
public class simpleAI {
	private Game game;
	private Board board;
	Board tempBoard;
	Board testBoard;
	double tempScore,tempCocholateNUM, tempFiveNUM,tempFourNUM;
	int tempI = -1;
	int tempJ = -1;
	
	int tempM = -1;
	int tempN = -1;
	double repeatScore = 0;
	double repeatChocolate = 0;
	double repeatFive = 0;
	double repeatFour = 0;
	
	
	simpleAI(Game game){
		this.game = game;
	}
	
	// NewObject<i, j, m, n, board, score>
	public void play() {
		board = game.getBoard();
		tempBoard = new Board(board);
		tempScore = tempBoard.getScore();
		tempCocholateNUM = tempBoard.getChocolateNUM();
		tempFiveNUM =  tempBoard.getFiveNUM();
		tempFourNUM =  tempBoard.getFourXNUM() + tempBoard.getFourYNUM();
		tempI = -1;
		tempJ = -1;
		tempM = -1;
		tempN = -1;
		for(int i = board.BOARDSIZE-1; i >= 0; i--) {
			for(int j = board.BOARDSIZE-1; j >= 0; j--) {
//				System.out.println("game score: " + game.getBoard().getScore()+ "\n");
//				board.printBoard();
				//In total, there are 24 methods:
				//1234 1243 1324 1342 1423 1432
				//2134 2143 2314 2341 2413 2431
				//3124 3142 3214 3241 3412 3421 
				//4123 4132 4213 4231 4312 4321 
				SGS(game.STRATEGY,  i, j);				
			}
		}
		
		
//		System.out.println(game.getBoard().getScore() + "   " + tempI + "   " + tempJ + "   " + tempM + "   " +tempN);
		// no valid move
		if(tempI == -1 && tempJ == -1) {
			for(int i = 0; i < board.BOARDSIZE; i++) {
				for(int j = 0; j < board.BOARDSIZE; j++) {
					if(board.getCandies()[i][j] != null && board.getCandies()[i][j].getcolour() != -10) {
						board.getCandies()[i][j] = null;
					}
				}
			}
			while(!board.isNONullCandy()) {
				board.addRandomCandy();
				board.checkChainReaction(0);
			}
//			System.out.println("repaint....");
			play();
		}else {
			game.getBoard().setSelectedFirst();
			game.getBoard().setFirstCandy(tempM, tempN);
			game.getBoard().move(tempI, tempJ);
//			game.getBoard().printBoard();
//			game.reDrawBoard();
			
		}

	}
	
	public void update(int a, int b, int c, int d, double score, double chocolate, double five, double four) {
		tempScore = score;
		tempCocholateNUM = chocolate;
		tempFiveNUM = five;
		tempFourNUM = four;
		setTempI(a);
		setTempJ(b);
		setTempM(c);
		setTempN(d);
	}
	
	public void Weight(int weight, int scoreWeight, int chocolateWeight, int fiveWeight, int fourWeight, int i, int j, int m, int n) {
		if(scoreWeight == weight) {
			if(getRepeatScore() > tempScore) {
				update(i,j,m, n, getRepeatScore(), getRepeatChocolate(), getRepeatFive(), getRepeatFour());
			}else if(getRepeatScore() == tempScore && weight < 4) {
				Weight(weight+1, scoreWeight, chocolateWeight, fiveWeight, fourWeight, i, j, m, n);
			}
		}else if(chocolateWeight == weight) {
			if(getRepeatChocolate() > tempCocholateNUM) {
				update(i,j,m, n, getRepeatScore(), getRepeatChocolate(), getRepeatFive(), getRepeatFour());
			}else if(getRepeatChocolate() == tempCocholateNUM && weight < 4) {
				Weight(weight+1, scoreWeight, chocolateWeight, fiveWeight, fourWeight,  i, j, m, n);
			}
		}else if(fiveWeight == weight) {
			if(getRepeatFive() > tempFiveNUM) {
				update(i,j,m, n, getRepeatScore(), getRepeatChocolate(), getRepeatFive(), getRepeatFour());
			}else if(getRepeatFive() == tempFiveNUM && weight < 4) {
				Weight(weight+1, scoreWeight, chocolateWeight, fiveWeight, fourWeight,  i, j, m, n);
			}
		}else if(fourWeight == weight) {
			if(getRepeatFour() > tempFourNUM) {
				update(i,j,m, n, getRepeatScore(), getRepeatChocolate(), getRepeatFive(), getRepeatFour());
			}else if(getRepeatFour() == tempFourNUM && weight < 4) {
				Weight(weight+1, scoreWeight, chocolateWeight, fiveWeight, fourWeight,  i, j, m, n);
			}
		}
		
	}


	public void SGS(int STRATEGY, int i, int j) {
		
		int ScoreWeight = STRATEGY/1000;
		int ChocolateWeight = (STRATEGY-ScoreWeight*1000)/100;
		int FiveWeight = (STRATEGY-ScoreWeight*1000-ChocolateWeight*100)/10;
		int FourWeight = STRATEGY-ScoreWeight*1000-ChocolateWeight*100-FiveWeight*10;
		
		if(j != 0) {
			repeatForHundred(i,j,i,j-1);
			Weight(1, ScoreWeight, ChocolateWeight, FiveWeight, FourWeight, i, j, i, j-1);
		}
		
		
		if(i != 0) {
			repeatForHundred(i,j,i-1,j);
			Weight(1, ScoreWeight, ChocolateWeight, FiveWeight, FourWeight, i, j, i-1, j);
		}
		
	}
	
//	public void ScoreFirst(int i, int j) {
//		
//		if(j != 0) {
//			testBoard = new Board(tempBoard);
//			testBoard.setFirstCandy(i, j);
//			testBoard.setSelectedFirst();
//			testBoard.move(i,j-1);
//			
//			repeatForTwenty(i,j,i,j-1);
//			if(getRepeatScore() > tempBoard.getScore()) {
//				update(i,j,i,j-1);
//			}else if(getRepeatScore() == tempBoard.getScore()) {
//				if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
//					update(i,j,i,j-1);
//				}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
//					if(getRepeatFive() > tempBoard.getFiveNUM()) {
//						update(i,j,i,j-1);
//					}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
//						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
//							update(i,j,i,j-1);
//						}
//					}
//				}
//			}
//			
//		}
//		
//		
//		if(i != 0) {
//			testBoard = new Board(tempBoard);
//			testBoard.setFirstCandy(i, j);
//			testBoard.setSelectedFirst();
//			testBoard.move(i-1,j);
//			repeatForTwenty(i,j,i-1,j);
//			if(getRepeatScore() > tempBoard.getScore()) {
//				update(i,j,i-1,j);
//			}else if(getRepeatScore() == tempBoard.getScore()) {
//				if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
//					update(i,j,i-1,j);
//				}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
//					if(getRepeatFive() > tempBoard.getFiveNUM()) {
//						update(i,j,i-1,j);
//					}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
//						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
//							update(i,j,i-1,j);
//						}
//					}
//				}
//			}
//		}
//		
//	}
//	
//	
//	// chocolate > score > five > four
//	public void ChocolateFirst(int i, int j) {
//		
//		if(j != 0) {
//			testBoard = new Board(tempBoard);
//			testBoard.setFirstCandy(i, j);
//			testBoard.setSelectedFirst();
//			testBoard.move(i,j-1);
//			repeatForTwenty(i,j,i,j-1);
//			if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
//				update(i,j,i,j-1);
//			}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
//				if(getRepeatScore() > tempBoard.getScore()) {
//					update(i,j,i,j-1);
//				}else if(getRepeatScore() == tempBoard.getScore()) {
//					if(getRepeatFive() > tempBoard.getFiveNUM()) {
//						update(i,j,i,j-1);
//					}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
//						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
//							update(i,j,i,j-1);
//						}
//					}
//				}
//			}
//		}
//		
//		
//		if(i != 0) {
//			testBoard = new Board(tempBoard);
//			testBoard.setFirstCandy(i, j);
//			testBoard.setSelectedFirst();
//			testBoard.move(i-1,j);
//			repeatForTwenty(i,j,i-1,j);
//			if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
//				update(i,j,i-1,j);
//			}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
//				if(getRepeatScore() > tempBoard.getScore()) {
//					update(i,j,i-1,j);
//				}else if(getRepeatScore() == tempBoard.getScore()) {
//					if(getRepeatFive() > tempBoard.getFiveNUM()) {
//						update(i,j,i-1,j);
//					}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
//						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
//							update(i,j,i-1,j);
//						}
//					}
//				}
//			}
//		}
//		
//	}
//	
//	
//	// five > score > chocolate > four
//	public void FiveFirst(int i, int j) {
//		
//		if(j != 0) {
//			testBoard = new Board(tempBoard);
//			testBoard.setFirstCandy(i, j);
//			testBoard.setSelectedFirst();
//			testBoard.move(i,j-1);
//			repeatForTwenty(i,j,i,j-1);
//			if(getRepeatFive() > tempBoard.getFiveNUM()) {
//				update(i,j,i,j-1);
//			}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
//				if(getRepeatScore() > tempBoard.getScore()) {
//					update(i,j,i,j-1);
//				}else if(getRepeatScore() == tempBoard.getScore()) {
//					if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
//						update(i,j,i,j-1);
//					}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
//						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
//							update(i,j,i,j-1);
//						}
//					}
//				}
//			}
//			
//		}
//		
//		
//		if(i != 0) {
//			testBoard = new Board(tempBoard);
//			testBoard.setFirstCandy(i, j);
//			testBoard.setSelectedFirst();
//			testBoard.move(i-1,j);
//			repeatForTwenty(i,j,i-1,j);
//			if(getRepeatFive() > tempBoard.getFiveNUM()) {
//				update(i,j,i-1,j);
//			}else if(getRepeatFive() == tempBoard.getFiveNUM()) {
//				if(getRepeatScore() > tempBoard.getScore()) {
//					update(i,j,i-1,j);
//				}else if(getRepeatScore() == tempBoard.getScore()) {
//					if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
//						update(i,j,i-1,j);
//					}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
//						if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
//							update(i,j,i-1,j);
//						}
//					}
//				}
//			}
//		}
//		
//	}
//	
//	
//	
//	// four > score > chocolate > five
//	public void FourFirst(int i, int j) {
//		
//		if(j != 0) {
//			testBoard = new Board(tempBoard);
//			testBoard.setFirstCandy(i, j);
//			testBoard.setSelectedFirst();
//			testBoard.move(i,j-1);
//			repeatForTwenty(i,j,i,j-1);
//			if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
//				update(i,j,i,j-1);
//			}else if(getRepeatFour() == (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
//				if(getRepeatScore() > tempBoard.getScore()) {
//					update(i,j,i,j-1);
//				}else if(getRepeatScore() == tempBoard.getScore()) {
//					if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
//						update(i,j,i,j-1);
//					}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
//						if(getRepeatFive() > tempBoard.getFiveNUM()) {
//							update(i,j,i,j-1);
//						}
//					}
//				}
//			}
//		}
//		
//		
//		if(i != 0) {
//			testBoard = new Board(tempBoard);
//			testBoard.setFirstCandy(i, j);
//			testBoard.setSelectedFirst();
//			testBoard.move(i-1,j);
//			repeatForTwenty(i,j,i-1,j);
//			if(getRepeatFour() > (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
//				update(i,j,i-1,j);
//			}else if(getRepeatFour() == (tempBoard.getFourXNUM() + tempBoard.getFourYNUM())) {
//				if(getRepeatScore() > tempBoard.getScore()) {
//					update(i,j,i-1,j);
//				}else if(getRepeatScore() == tempBoard.getScore()) {
//					if(getRepeatChocolate() > tempBoard.getChocolateNUM()) {
//						update(i,j,i-1,j);
//					}else if(getRepeatChocolate() == tempBoard.getChocolateNUM()) {
//						if(getRepeatFive() > tempBoard.getFiveNUM()) {
//							update(i,j,i-1,j);
//						}
//					}
//				}
//			}
//			
//		}
//		
//	}
	
	
	public void repeatForHundred(int tempI, int tempJ, int tempM, int tempN) {
		setRepeatScore(0);
		setRepeatChocolate(0);
		setRepeatFive(0);
		setRepeatFour(0);
		for(int i = 0; i < 100; i++) {
			testBoard = null;
			testBoard = new Board(board);
			testBoard.setFirstCandy(tempI, tempJ);
			testBoard.setSelectedFirst();
			testBoard.move(tempM, tempN);
			setRepeatScore(getRepeatScore() + testBoard.getScore());
			setRepeatChocolate(getRepeatChocolate() + testBoard.getChocolateNUM());
			setRepeatFive(getRepeatFive() + testBoard.getFiveNUM());
			setRepeatFour(getRepeatFour() + testBoard.getFourXNUM() + testBoard.getFourYNUM());
		}
		setRepeatScore(((double)getRepeatScore())/100);
		setRepeatChocolate(((double)getRepeatChocolate())/100);
		setRepeatFive(((double)getRepeatFive())/100);
		setRepeatFour(((double)getRepeatFour())/100);
		
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
	
	
	void setRepeatScore(double i) {
		repeatScore = i;
	}
	
	void setRepeatChocolate(double i) {
		repeatChocolate = i;
	}
	
	void setRepeatFive(double i) {
		repeatFive = i;
	}
	
	void setRepeatFour(double i) {
		repeatFour = i;
	}
	
	double getRepeatScore() {
		return repeatScore;
	}
	
	double getRepeatChocolate() {
		return repeatChocolate;
	}
	
	double getRepeatFive() {
		return repeatFive;
	}
	
	double getRepeatFour() {
		return repeatFour;
	}
	
}