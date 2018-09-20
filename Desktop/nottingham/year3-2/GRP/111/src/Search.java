

public class Search implements Runnable{
/********************************************* Create needed variables ****************************************/
	Evaluate eva = new Evaluate();
	MoveGenerater mg = new MoveGenerater();
	
/***** Constant values *****/
//	private int MAX_PLY = 4;
	private int NUM_BESTMOVES = 5;//give 5 tips
	private static final int HIST_STACK = 500;
	public static final int INFINITY = 20000;

/***** For getting board information *****/
	private int ply;

/***** Store possible moves for search *****/
	private History[] hist_dat ;//history data
	private int hdp;

/***** For tips to record best moves *****/	
	private Move[] bestMoves = new Move[NUM_BESTMOVES];//the move of 5 tips
	private int[] bestValues = new int[NUM_BESTMOVES];//the value of 5 tips
	private int currentIndex = 0;//the index of last valid value in bestValues&bestMoves
	
	public boolean keepSearching = true;
	
/******************************************** End of create needed variables ***********************************/	
	
	//Start a new game.
	Search() {
		hist_dat = new History[HIST_STACK];
		for (int i = 0; i < HIST_STACK; i++) {
			hist_dat[i] = new History(); 
		}
		ply = 0; 
		hdp = 0;
	}

	public void run(){
		int max_ply = 0;
		while(keepSearching){
			max_ply++;
			initEveryMove();
			alphabeta(-INFINITY, INFINITY, max_ply);
			if(keepSearching) getSuggestions();
		}
	}
	
	
	//Get move tips -- change the number of tips by change NUM_BESTMOVES at the beginning
	private String getSuggestions(){
		String results = "";
		int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
		for(int i = 0; i < NUM_BESTMOVES; i++){
			x1 = bestMoves[i].from % 9; y1 = bestMoves[i].from / 9;
			x2 = bestMoves[i].dest % 9; y2 = bestMoves[i].dest / 9;
			results = results + x1 +  y1  +  x2  +  y2 + " ";			
		}
		System.out.println(results);
		return results;
	}	

/***** Search function *****/
	private int alphabeta(int alpha, int beta, int depth)//breadth first
	{
		int value;
		if (depth == 0 || !keepSearching){
			if(AIBoard.side == AIBoard.RED){
				return eva.evaluateRed();
			}else{
				return eva.evaluateBlack();
			}
		}
		MoveGenerater.generateMoves(ply);//each level

		for (int i=MoveGenerater.gen_begin[ply]; i<MoveGenerater.gen_end[ply]; i++)//go through all possible steps on one level
		{
			if(!keepSearching) break;
			if (move(MoveGenerater.gen_dat[i].m) == AIBoard.KING) value = INFINITY-ply;
			else value = -alphabeta(-beta, -alpha, depth-1);
			unmove();
			if(value >= beta){
				return beta;
			}
			boolean isRecord = false;
			if(ply == 0 && bestValues[NUM_BESTMOVES - 1] == -INFINITY){
					record(value, MoveGenerater.gen_dat[i].m);
					isRecord = true;
			}
			if(value > alpha){
				alpha = value;
				if(ply == 0 && !isRecord){
					record(value, MoveGenerater.gen_dat[i].m);
				}
			}		
		}
		return alpha;
	}
/***** End of search function *****/	
	
/*****  Record best move functions *****/	
	//Record five(NUM_BESTMOVES) best moves. -- For tips
	private void record(int best, Move move){
		int newIndex = currentIndex;
		int tempValue = best;

		while(newIndex > 0 && bestValues[newIndex - 1] < tempValue){
			bestValues[newIndex] = bestValues[newIndex - 1];
			bestMoves[newIndex] = bestMoves[newIndex - 1];
			newIndex--;
		}
		bestValues[newIndex] = tempValue;
		bestMoves[newIndex] = move;
		
		if(currentIndex < 4){
			currentIndex++;
		}
	}
	
	//Initial the variable of best moves and recommends. -- For tips
	private void initEveryMove(){
		currentIndex = 0;
		for(int i = 0; i < NUM_BESTMOVES; i++){
			bestValues[i] = -INFINITY;
			bestMoves[i] = new Move();
		}
	}
/*****  End of record best move functions *****/

	
/***** Move and undo for search *****/
	private int move(Move m)
	{
		int from, dest, result;
		from = m.from;
		dest = m.dest;
		hist_dat[hdp].m.from = m.from;
		hist_dat[hdp].m.dest = m.dest;
		hist_dat[hdp].capture = result = AIBoard.piece[dest];		
		AIBoard.piece[dest] = AIBoard.piece[from]; AIBoard.piece[from] = AIBoard.EMPTY;
		AIBoard.color[dest] = AIBoard.color[from]; AIBoard.color[from] = AIBoard.EMPTY;
		hdp++; ply++; AIBoard.side = AIBoard.xside; AIBoard.xside = 1-AIBoard.xside;
		return result;
	}

	private void unmove()
	{
		int from, dest;
		hdp--; ply--; AIBoard.side = AIBoard.xside; AIBoard.xside = 1-AIBoard.xside;		
		from = hist_dat[hdp].m.from; dest = hist_dat[hdp].m.dest;
		AIBoard.piece[from] = AIBoard.piece[dest]; AIBoard.color[from] = AIBoard.color[dest];
		AIBoard.piece[dest] = hist_dat[hdp].capture;
		if (AIBoard.piece[dest] == AIBoard.EMPTY) AIBoard.color[dest] = AIBoard.EMPTY; else AIBoard.color[dest] = AIBoard.xside;
	}
/***** End of move and undo for search *****/
//	private void clear(){
//		while(hdp > 0) {unmove();}
//		Thread.interrupted();
//	}
/********************************************* End of help functions *******************************************/
}

