import java.util.*;
import java.util.Map.*;

public class simpleAI2 {
	private Game game;
	private Board board;
	Board tempBoard;
	Board testBoard;
	int tempScore;
	int tempI = -1;
	int tempJ = -1;
	
	int tempM = -1;
	int tempN = -1;
	int stepNUM = 0;
	
	
	simpleAI2(Game game){
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
		stepNUM = board.STEP;
		if(game.STRATEGY == 1) {
			String moveID = ScoreFirstHeuristic(stepNUM);
			if(moveID!="") {
				tempI = Integer.parseInt(moveID.charAt(0)+"");
				tempJ = Integer.parseInt(moveID.charAt(1)+"");
				tempM = Integer.parseInt(moveID.charAt(2)+"");
				tempN = Integer.parseInt(moveID.charAt(3)+"");
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
	
	public void update(int a, int b, int c, int d) {
		tempScore = testBoard.getScore();
		tempBoard = new Board(testBoard);
		setTempI(a);
		setTempJ(b);
		setTempM(c);
		setTempN(d);
	}
	
	public void addAllPossibleMoves(Board testBoard, String str, Map<String, Integer> allPossible, int num) {
		Board b = null;
		b = new Board(testBoard);
		for(int i = testBoard.BOARDSIZE-1 ; i >=0; i--) {
			for(int j = testBoard.BOARDSIZE-1 ; j >=0; j--) {
				if(j != 0) {
					Candy FirstCandy = b.getCandies()[i][j];
					Candy SecondCandy = b.getCandies()[i][j-1];
					b.setFirstCandy(i, j);
					b.setSelectedFirst();
					if(b.isValid(FirstCandy, SecondCandy)) {
						String temp =  i+""+j + ""+i+""+(j-1);
						str += temp; 
						b.move(i, j-1);
						allPossible.put(str, b.getScore());
						if(str.length() < stepNUM*4 && num>1) {
							addAllPossibleMoves(b, str, allPossible, num-1);
						}
					}
				}
				if(i != 0) {
					Candy FirstCandy = b.getCandies()[i][j];
					Candy SecondCandy = b.getCandies()[i-1][j];
					b.setFirstCandy(i, j);
					b.setSelectedFirst();
					if(b.isValid(FirstCandy, SecondCandy)) {
						String temp =  i+""+j + ""+(i-1)+""+j;
						str += temp; 
						b.move(i-1, j);
						allPossible.put(str, b.getScore());
						if(str.length() < stepNUM*4 && num>1) {
							addAllPossibleMoves(b, str, allPossible, num-1);
						}
					}
				}
				}
			}	
	}

	public String ScoreFirstHeuristic(int stepNUM) {
		testBoard = null;
		testBoard = new Board(board);
		Map<String, Integer> allPossible = new HashMap<String, Integer>();
		addAllPossibleMoves(testBoard, "", allPossible, stepNUM);
		
		Map<String, String> rankingOfPossibleMoves = new HashMap<String, String>();
		for (Entry<String, Integer> entry : allPossible.entrySet())
		{
			String moves = entry.getKey().toString();
			int value = entry.getValue();
			if(moves.length() == 4*stepNUM) {
//			System.out.println(moves + "        " + value);
			String firstMove = moves.substring(0, 4);
			if(!rankingOfPossibleMoves.containsKey(firstMove)) {
				if(value >= board.TARGET) {
					rankingOfPossibleMoves.put(firstMove, "1");
				}else {
					rankingOfPossibleMoves.put(firstMove, "0");
				}
			}else {
				if(value >= board.TARGET) {
					rankingOfPossibleMoves.put(firstMove, rankingOfPossibleMoves.get(firstMove)+"1");
				}else {
					rankingOfPossibleMoves.put(firstMove, rankingOfPossibleMoves.get(firstMove)+"0");
				}
			}
			}
		}
		
		Map<String, Double> winratesOfPossibleMoves = new HashMap<String, Double>();
		for (Entry<String, String> entry : rankingOfPossibleMoves.entrySet())
		{
			String firstMove = entry.getKey().toString();
			String value = entry.getValue();
//			System.out.println(firstMove + "   " +value);
			if(!winratesOfPossibleMoves.containsKey(firstMove)) {
				int win = 0;
				int total = 0;
				for (int i = 0; i < value.length(); i++)
				{
					if(value.charAt(i) == '1') {
						win ++;
						total ++;
					}else {
						total ++;
					}
				}
				double winrate =  ((double)win/(double)total);
				winratesOfPossibleMoves.put(firstMove, (double) (winrate));
			}
		}
			List<Map.Entry<String, Double>> infoIds = new ArrayList<Map.Entry<String, Double>>(winratesOfPossibleMoves.entrySet());
			Collections.sort(infoIds, new Comparator<Map.Entry<String, Double>>() {
				public int compare(Map.Entry<String, Double> o1,
						Map.Entry<String, Double> o2) {
					return (o1.getValue()).toString().compareTo(o2.getValue().toString());
				}
			});
			for (int i = 0; i < infoIds.size(); i++) {
				String id = infoIds.get(i).toString();
				System.out.print(id + "  ");
			}
	
						
			if(infoIds.isEmpty()) {
				return "";
			}else {
				String IJMN = infoIds.get(0).toString();
				return IJMN;
			}
		
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
	
	
}