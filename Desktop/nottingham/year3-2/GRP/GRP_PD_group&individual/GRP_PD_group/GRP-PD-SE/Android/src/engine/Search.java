/**
Writen by Meng Yuan, Yu Qu, reused codes from Zhejie, Lee
**/

package engine;
import com.example.test.*;

import android.os.Handler;
import android.os.Looper;
//import android.widget.ImageButton;
//import android.widget.Toast;
import android.os.Message;


public class Search extends Thread implements Runnable {
/********************************************* Create needed variables ****************************************/
	Evaluate eva = new Evaluate();
	MoveGenerater mg;
	NewGame ng = null;//new com.example.test.NewGame();
	
/***** Constant values *****/
//	private int MAX_PLY = 4;
	private int NUM_BESTMOVES = 5;//give 5 tips
	private static final int HIST_STACK = 5000;
	public static final int INFINITY = 20000;

/***** For getting board information *****/
	private int ply;

/***** Store possible moves for search *****/
	private History[] hist_dat ;//history data
	private int hdp;


/***** For tips to record best moves *****/	
	public Move[] bestMoves = new Move[NUM_BESTMOVES];//the move of 5 tips
	private int[] bestValues = new int[NUM_BESTMOVES];//the value of 5 tips
	private int currentIndex = 0;//the index of last valid value in bestValues&bestMoves
	
	public volatile boolean keepSearching = true;
	private boolean endGame = false;
	
	
/******************************************** End of create needed variables ***********************************/	
	
	public Search(NewGame n) {// Writen by Meng Yuan
		ng = n;
		mg = new MoveGenerater(ng);
		hist_dat = new History[HIST_STACK];
		for (int i = 0; i < HIST_STACK; i++) {
			hist_dat[i] = new History(); 
		}
		ply = 0; 
		hdp = 0;
	}
	
	// New search thread, writen by Meng Yuan, modified by Yu Qu
	public void run(){
		int max_ply = 0;// search depth
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while(max_ply<3 && keepSearching && !endGame){ // control loop, android cannot search into depth
			max_ply++; // depth + 1
			initEveryMove(); //clear record from last search round
			alphabeta(-INFINITY, INFINITY, max_ply); // search
			if(keepSearching){ // if the seach in this deep is finish, record, output modified by Yu Qu
				ng.getHandler().sendMessage(getSuggestions());
			}
		}
	}	
	
	//Get move tips -- change the number of tips by change NUM_BESTMOVES at the beginning, writen by Meng Yuan, modified by Yu Qu
	public Message getSuggestions(){
		Message msg = new Message(); // init
		msg.obj = "";
		int x1 = -1, y1 = -1, x2 = -1, y2 = -1;

		for(int i = 0; i < NUM_BESTMOVES; i++){ //record
			
			x1 = bestMoves[i].from % 9; y1 = bestMoves[i].from / 9;
			x2 = bestMoves[i].dest % 9; y2 = bestMoves[i].dest / 9;
			if(x1 == 0 && x2 == 0 && y1 == 0 && y2 == 0){ // if all the values equal to 0, means there is no more suggestion in the data structure
				break;
			}
			msg.obj = msg.obj + "Move "+ ng.getPieceTable().get(ng.getChessBoard()[x1][y1].getId())+ " from (" + x1 +", "+ y1 + ") to (" + x2+", "+y2+ ").\n" ;		
		}
		if(msg.obj == ""){
			endGame = true;
		}
		return msg;
	}	
	
	public boolean getEndGame(){
		return endGame;
	}

/***** Search function *****/
	// search function, writen by Zhejie, Lee, modified by Meng Yuan
	private int alphabeta(int alpha, int beta, int depth) // depth first
	{
		int value;
		
		if (depth == 0 || !keepSearching){ // base case
			if(AIBoard.side == AIBoard.RED){ // return the evaluation value of current board
				return eva.evaluateRed();
			}else{
				return eva.evaluateBlack();
			}
		}
		MoveGenerater.generateMoves(ply); // each level

		for (int i=MoveGenerater.gen_begin[ply]; i<MoveGenerater.gen_end[ply]; i++)//go through all possible steps on one level
		{
			if(!keepSearching) break; // if the thread is asked to stop
			if (move(MoveGenerater.gen_dat[i].m) == AIBoard.KING) value = INFINITY-ply; // move a move, and if the king will be captured, stop searching in this subtree
			else value = -alphabeta(-beta, -alpha, depth-1); // else go depth
			unmove(); // unmove 
			if(value >= beta){ // if this value is not the worst in the subtree, abandon it
				return beta;
			}
			boolean isRecord = false;
			if(ply == 0 && bestValues[NUM_BESTMOVES - 1] == -INFINITY){ // if the data structure cantains the bestMoves is not full, and this move is in the initial level in the search tree 
					record(value, MoveGenerater.gen_dat[i].m); // record this move no matter whether it is good or not
					isRecord = true; // mark this move as recorded
			}
			if(value > alpha){ // if this move is the best move in the same level, record this move
				alpha = value;
				if(ply == 0 && !isRecord){  // if this move is in the initial level and not be recorded,
					record(value, MoveGenerater.gen_dat[i].m); // record it
				}
			}		
		}
		return alpha;
	}
/***** End of search function *****/	
	
/*****  Record best move functions *****/ 
	//Record five(NUM_BESTMOVES) best moves, writen by Meng Yuan
	private void record(int best, Move move){
		int newIndex = currentIndex;
		int tempValue = best;

		//insertion sort
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
	
	//Initial the variable of best moves and recommends, writen by Meng Yuan
	private void initEveryMove(){
		currentIndex = 0;
		for(int i = 0; i < NUM_BESTMOVES; i++){
			bestValues[i] = -INFINITY;
			bestMoves[i] = new Move();
		}
	}
/*****  End of record best move functions *****/

	
/***** Move and undo for search *****/ // reused codes from Zhejie, Lee
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

/********************************************* End of help functions *******************************************/
}


