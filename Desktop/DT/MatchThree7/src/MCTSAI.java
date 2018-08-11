import java.util.*;

public class MCTSAI {
	Game game;
	Board board;
	Board tempBoard;
	int tempI = -1;
	int tempJ = -1;
	
	int tempM = -1;
	int tempN = -1;
	
	MCTSAI(Game game){
		this.game = game;
	}
	
	void play(){
		board = game.getBoard();
		int score = board.getScore();
		int tempScore = score;
		tempBoard = new Board(board);
		tempI = -1;
		tempJ = -1;
		tempM = -1;
		tempN = -1;
	}
	
	public void makeTransition(int i, int j, int m, int n, Board oldboard) {
        tempBoard = new Board(oldboard);
        tempBoard.setFirstCandy(i, j);
        tempBoard.setSelectedFirst();
        tempBoard.move(m, n);
    }
	
	public Board simulationTransition(Set<Board> possibleTransitions) {
		List<Board> transitions = new ArrayList<Board>(possibleTransitions);
		return transitions.get((int) Math.floor(Math.random() * possibleTransitions.size()));
	}
	
	public Board expansionTransition(Set<Board> possibleTransitions) {
		List<Board> transitions = new ArrayList<Board>(possibleTransitions);
		return transitions.get((int) Math.floor(Math.random() * possibleTransitions.size()));
	}
	
	public Set<Board> getPossibleTransitions() {
    	Set<Board> moves = new HashSet<Board>();
    	Board possibleBoard = new Board(board);
        for (int i = 0; i < possibleBoard.BOARDSIZE; i++) {
            for (int j = 0; j < possibleBoard.BOARDSIZE; j++) {
            		if(possibleBoard.getCandies()[i][j]!=null && possibleBoard.getCandies()[i-1][j]!=null 
            				&& possibleBoard.getCandies()[i][j].getColor() != -10 && possibleBoard.getCandies()[i-1][j].getColor() != -10) {
            				Candy FirstCandy = possibleBoard.getCandies()[i][j];
            				Candy SecondCandy = possibleBoard.getCandies()[i-1][j];
            				if (possibleBoard.isValid(FirstCandy, SecondCandy)) {
            					possibleBoard.setFirstCandy(i, j);
            					possibleBoard.setSelectedFirst();
            					possibleBoard.move(i-1, j);
                            moves.add(possibleBoard);
                        }
            		}
             
            }
        }
        return moves;
    }

}
