
public class simpleAI2 {
	private Game game;
	private Board board;
	private simpleAI ai;
	
	simpleAI2(Game game){
		this.game = game;
	}
	
	// NewObject<i, j, m, n, board, score>
	public void play() {
		board = game.getBoard();
		int score = board.getScore();
		int tempScore = score;
		Board tempBoard = new Board(board);
		int tempI = -1;
		int tempJ = -1;
		
		int tempM = -1;
		int tempN = -1;
		while(game.stepNUM > 0) {
			ai.play();
			
			
		}
	}
}
