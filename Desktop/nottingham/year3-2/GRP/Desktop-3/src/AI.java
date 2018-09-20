

public class AI {
/********************************************* Create needed variables ****************************************/	
/***** Constant values *****/
	private int MAX_PLY = 5;
	private int NUM_BESTMOVES = 5;//give 5 tips

	private static final int SIZE_X = 9;
	private static final int SIZE_Y = 10;
	private static final int BOARD_SIZE = SIZE_X*SIZE_Y;

	private static final int MOVE_STACK = 4096;
	private static final int HIST_STACK = 500;

	private static final int BLACK = 0;
	private static final int RED = 1;

	private static final int PAWN = 0 ;
	private static final int BISHOP = 1;
	private static final int ELEPHANT = 2;
	private static final int KNIGHT = 3;
	private static final int CANNON = 4;
	private static final int ROOK = 5;
	private static final int KING = 6;
	private static final int EMPTY = 7;

	private static final int INFINITY = 20000;

/***** The board representation && the initial board state *****/
	private int[] color = new int[BOARD_SIZE];
	private int[] piece = new int[BOARD_SIZE];
	
/***** For getting board information *****/
	private int ply, side, xside;
	private Move newmove = new Move();
	private Move mov = new Move();

/***** Store possible moves for search *****/
	private Recorder[] gen_dat ;//record moved steps
	private int[] gen_begin = new int[HIST_STACK];
	private int[] gen_end = new int[HIST_STACK];
	private History[] hist_dat ;//history data
	private int hdp;
	
/***** For generate moves *****/
	//[7][8] possible positions offset
	private final int[][] offset = {
		{-1, 1,13, 0, 0, 0, 0, 0}, /* PAWN {for DARK side} */
		{-12,-14,12,14,0,0,0,0}, /* BISHOP */
		{-28,-24,24,28, 0, 0, 0, 0 }, /* ELEPHAN */
		{-11,-15,-25,-27,11,15,25,27}, /* KNIGHT */
		{-1, 1,-13,13, 0, 0, 0, 0}, /* CANNON */
		{-1, 1,-13,13, 0, 0, 0, 0}, /* ROOK */
		{-1, 1,-13,13, 0, 0, 0, 0}/* KING */
	}; 

	//14*13,10*9
	private final int[] mailbox182 = {
		-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
		-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
		-1,-1, 0, 1, 2, 3, 4, 5, 6, 7, 8,-1,-1,
		-1,-1, 9,10,11,12,13,14,15,16,17,-1,-1,
		-1,-1,18,19,20,21,22,23,24,25,26,-1,-1,
		-1,-1,27,28,29,30,31,32,33,34,35,-1,-1,
		-1,-1,36,37,38,39,40,41,42,43,44,-1,-1,
		-1,-1,45,46,47,48,49,50,51,52,53,-1,-1,
		-1,-1,54,55,56,57,58,59,60,61,62,-1,-1,
		-1,-1,63,64,65,66,67,68,69,70,71,-1,-1,
		-1,-1,72,73,74,75,76,77,78,79,80,-1,-1,
		-1,-1,81,82,83,84,85,86,87,88,89,-1,-1,
		-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
		-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1
	};

	//positions in mailbox182 10*9
	private final int[] mailbox90 = {
		28, 29, 30, 31, 32, 33, 34, 35, 36,//+5
		41, 42, 43, 44, 45, 46, 47, 48, 49,
		54, 55, 56, 57, 58, 59, 60, 61, 62,
		67, 68, 69, 70, 71, 72, 73, 74, 75,
		80, 81, 82, 83, 84, 85, 86, 87, 88,
		93, 94, 95, 96, 97, 98, 99,100,101,
		106, 107,108,109,110,111,112,113,114,
		119, 120,121,122,123,124,125,126,127,
		132, 133,134,135,136,137,138,139,140,
		145, 146,147,148,149,150,151,152,153
	};

	//...?
	private final int[] legalposition = {
		1, 1, 5, 3, 3, 3, 5, 1, 1,
		1, 1, 1, 3, 3, 3, 1, 1, 1,
		5, 1, 1, 3, 7, 3, 1, 1, 5,
		1, 1, 1, 1, 1, 1, 1, 1, 1,
		9, 1,13, 1, 9, 1,13, 1, 9,
		9, 9, 9, 9, 9, 9, 9, 9, 9,
		9, 9, 9, 9, 9, 9, 9, 9, 9,
		9, 9, 9, 9, 9, 9, 9, 9, 9,
		9, 9, 9, 9, 9, 9, 9, 9, 9,
		9, 9, 9, 9, 9, 9, 9, 9, 9
	};

	//limitation?
	private final int[] maskpiece = {8, 2, 4, 1, 1, 1, 2};
	private final int[] knightcheck = {1,-1,-9,-9,-1,1,9,9};
	private final int[] elephancheck = {-10,-8,8,10,0,0,0,0};

/***** For tips to record best moves *****/	
	private Move[] bestMoves = new Move[NUM_BESTMOVES];//the move of 5 tips
	private int[] bestValues = new int[NUM_BESTMOVES];//the value of 5 tips
	private int currentIndex = 0;//the index of last valid value in bestValues&bestMoves
	
/***** For recommend record *****/
	private String black = "";//record recommends
	private String red = "";//record recommends
	
/***** For undo *****/
	private History[] boardHist;
	private int myhdp;
	
/******************************************** End of create needed variables ***********************************/	
	
	
	
/***************************************** Methods can be called by other classes. *****************************/	
	//Start a new game.
	public void init() {
		
		gen_dat = new Recorder[MOVE_STACK];
		for (int i = 0; i < MOVE_STACK; i++) {
			gen_dat[i] = new Recorder();
		}
		hist_dat = new History[HIST_STACK];
		for (int i = 0; i < HIST_STACK; i++) {
			hist_dat[i] = new History(); 
		}
		boardHist = new History[HIST_STACK];
		for (int i = 0; i < HIST_STACK; i++) {
			boardHist[i] = new History(); 
		}
		
		gen_begin[0] = 0; 
		ply = 0; 
		hdp = 0;
		myhdp = 0;
		side = RED; 
		xside = BLACK; 
		
		int[] clr = {
			0, 0, 0, 0, 0, 0, 0, 0, 0,
			7, 7, 7, 7, 7, 7, 7, 7, 7,
			7, 0, 7, 7, 7, 7, 7, 0, 7,
			0, 7, 0, 7, 0, 7, 0, 7, 0,
			7, 7, 7, 7, 7, 7, 7, 7, 7,
			7, 7, 7, 7, 7, 7, 7, 7, 7,
			1, 7, 1, 7, 1, 7, 1, 7, 1,
			7, 1, 7, 7, 7, 7, 7, 1, 7,
			7, 7, 7, 7, 7, 7, 7, 7, 7,
			1, 1, 1, 1, 1, 1, 1, 1, 1
		};
		int[] pc = 	{
			5, 3, 2, 1, 6, 1, 2, 3, 5,
			7, 7, 7, 7, 7, 7, 7, 7, 7,
			7, 4, 7, 7, 7, 7, 7, 4, 7,
			0, 7, 0, 7, 0, 7, 0, 7, 0,
			7, 7, 7, 7, 7, 7, 7, 7, 7,
			7, 7, 7, 7, 7, 7, 7, 7, 7,
			0, 7, 0, 7, 0, 7, 0, 7, 0,
			7, 4, 7, 7, 7, 7, 7, 4, 7,
			7, 7, 7, 7, 7, 7, 7, 7, 7,
			5, 3, 2, 1, 6, 1, 2, 3, 5
		};
		System.arraycopy(clr, 0, color, 0, clr.length);
		System.arraycopy(pc, 0, piece, 0, pc.length);
	}
	
	//Make a new move. Return true if the opposite king is captured.
	public boolean takeAMove(int from , int to) {
		newmove.from = from;
		newmove.dest = to;
		boolean result = updateNewMove();
		search();
		return result;
	}
	
	//Undo
	public void deleteNewMove()
	{
		int from, dest;
		myhdp--; side = xside; xside = 1-xside;		
		from = boardHist[myhdp].m.from; dest = boardHist[myhdp].m.dest;
		piece[from] = piece[dest]; color[from] = color[dest];
		piece[dest] = boardHist[myhdp].capture;
		if (piece[dest] == EMPTY) color[dest] = EMPTY; else color[dest] = xside;
		search();
	}
	
	//Search function. -- Must call this function right after make a new move.
	public void search(){
		initEveryMove();
		alphabeta(-INFINITY, INFINITY, MAX_PLY);
		generateOppositeMoves();
	}
	
	//Get recommends of capture & captured pieces
	public String getRecomend(){
		String results = "";
		results = "Captured red: " + red + "\nCaptured black: " + black + "\n";
		return results;
	}

	//Get move tips -- change the number of tips by change NUM_BESTMOVES at the beginning
	public String getSuggestions(){
		String results = "";
		int x1 = -1, y1 = -1, x2 = -1, y2 = -1;
		for(int i = 0; i < NUM_BESTMOVES; i++){
			x1 = bestMoves[i].from % SIZE_X; 
			y1 = bestMoves[i].from / SIZE_X;
			x2 = bestMoves[i].dest % SIZE_X; 
			y2 = bestMoves[i].dest / SIZE_X;
			results = results + x1 + y1+x2+y2 + " ";		
//			results = results + (x1+1) +  (char)(y1+65)  +  (x2+1)  +  (char)(y2+65) + " ";		
		}
		return results;
	}	
	
	//print board -- for test
    public  void printBoard(){
    	for(int i = 0; i < 90; i ++){
    		if(i%9 == 0){
    			System.out.print( i/9 + "  ");
    		}    		
    		System.out.print(piece[i] +""+color[i]+ " ");
    		if(i%9 == 8 && i != 89){
    			System.out.println("\n");
    		}
    	}
    	System.out.println("\n=================================");
    	System.out.println("   0  1  2  3  4  5  6  7  8");
	}
/******************************************* End of public functions ***************************************/

    
    
/************************************************ Help functions *******************************************/	
/***** Search function *****/
	private int alphabeta(int alpha, int beta, int depth)//breadth first
	{
		int value;
		if (depth == 0){
			if(side == RED){
				return evaluateRed();
			}else{
				return evaluateBlack();
			}
		}
		generateMoves();//each level
		
		for (int i=gen_begin[ply]; i<gen_end[ply]; i++)//go through all possible steps on one level
		{
			if (move(gen_dat[i].m) == KING) value = INFINITY-ply;
			else value = -alphabeta(-beta, -alpha, depth-1);
			unmove();
			if(value >= beta){
				return beta;
			}
			
			boolean isRecord = false;
			if(ply == 0 && currentIndex < (NUM_BESTMOVES - 1)){
					record(value, gen_dat[i].m);
					isRecord = true;
			}
			
			if(value > alpha){
				alpha = value;
				if(ply == 0){
					newmove.from = gen_dat[i].m.from;
					newmove.dest = gen_dat[i].m.dest;
					if(!isRecord){
						record(value, gen_dat[i].m);
					}
				}
			}		
//			if(ply == 0){
//				if(currentIndex < (NUM_BESTMOVES - 1) || bestValues[currentIndex] < value){
//					record(value, gen_dat[i].m);
//				}
//			}
		}
		return alpha;
	}
/***** End of search function *****/	
	
	
/***** Generate all moves *****/	
	//generate all possible moves
	private void generateMoves()
	{
		int i, j, k, n, p, x, y, t, fcannon;

		gen_end[ply] = gen_begin[ply];

		for (i=0; i < BOARD_SIZE; i++){
			if (color[i]==side)
			{
				p = piece[i];//piece kind
				for (j=0; j<8; j++)
				{
					if (offset[p][j] == 0) break;//find possible next position
					x = mailbox90[i]; //offset in mailbox128
					fcannon = 0;
					if (p==ROOK || p==CANNON) n = 9; else n = 1;//
					for (k=0; k<n; k++)
					{
						//  get offset result for (p==PAWN && side==LIGHT)
						//there is no offset table for it
						if (p==PAWN && side==RED) x -= offset[p][j]; else x += offset[p][j];

						y = mailbox182[x];
						//  t for the position in the board of this piece ,
						//according which side the piece is 
						if (side == BLACK) t = y; else t = 89-y;
						if (y==-1 || (legalposition[t] & maskpiece[p])==0) break;
						if (fcannon == 0)
						{
							if (color[y]!=side)
								switch (p)
							{
								case KNIGHT: if (color[i+knightcheck[j]]==EMPTY) pushGeneratedMove(i, y); break;
								case ELEPHANT:if (color[i+elephancheck[j]]==EMPTY) pushGeneratedMove(i, y); break;
								case CANNON: if (color[y]==EMPTY) pushGeneratedMove(i, y); break;
								default: pushGeneratedMove(i, y);
							}
							if (color[y]!=EMPTY) { if (p==CANNON) fcannon++; else break; }
						}
						else   /* CANNON switch */
						{
							if (color[y] != EMPTY)
							{
								if (color[y]==xside) pushGeneratedMove(i, y);
								break;
							}
						}
					} /* for k */
				} /* for j */
			}
		}
		gen_end[ply+1] = gen_end[ply]; gen_begin[ply+1] = gen_end[ply];
	}
	
	//save a possible move & record the capture pieces
	private void pushGeneratedMove(int from, int dest)
	{
		if (!kingFace(from, dest))
		{
			gen_dat[gen_end[ply]].m.from = from;
			gen_dat[gen_end[ply]].m.dest = dest;
			gen_end[ply]++;
		}
		if(ply == 0 && color[dest] != 7){
			if(color[dest] == BLACK){
				black = black + (from % SIZE_X + 1) + (char)(from / SIZE_X + 65) + (dest % SIZE_X + 1) + (char)(dest / SIZE_X + 65) + " ";
			}else{
				red = red + (from % SIZE_X + 1) + (char)(from / SIZE_X + 65) + (dest % SIZE_X + 1) + (char)(dest / SIZE_X + 65) + " ";
			}
		}
	}
	
	//check whether King will be killed by opponent's King directly after computer moves King
	private boolean kingFace(int from, int dest)
	{
		boolean result = false;
		int i = from % SIZE_X;
		if (i>=3 && i<=5 && piece[dest]!=KING)
		{
			int t = piece[dest]; piece[dest] = piece[from]; piece[from] = EMPTY;//make the move
			for (i=3; piece[i]!=KING; i++);//i=kingpalace[i]
			for (i += SIZE_X; i<BOARD_SIZE && piece[i]==EMPTY; i += SIZE_X);
			if (i<BOARD_SIZE && piece[i]==KING) result = true;
			piece[from] = piece[dest]; piece[dest] = t;//unmove
		}
		return result;
	}
	
	private void generateOppositeMoves()
	{
		int i, j, k, n, p, x, y, t, fcannon;

		for (i=0; i < BOARD_SIZE; i++){
			if (color[i]==xside)
			{
				p = piece[i];//piece kind
				for (j=0; j<8; j++)
				{
					if (offset[p][j] == 0) break;//find possible next position
					x = mailbox90[i]; //offset in mailbox128
					fcannon = 0;
					if (p==ROOK || p==CANNON) n = 9; else n = 1;//
					for (k=0; k<n; k++)
					{
						//  get offset result for (p==PAWN && side==LIGHT)
						//there is no offset table for it
						if (p==PAWN && side==RED) x -= offset[p][j]; else x += offset[p][j];

						y = mailbox182[x];
						//  t for the position in the board of this piece ,
						//according which side the piece is 
						if (side == BLACK) t = y; else t = 89-y;
						if (y==-1 || (legalposition[t] & maskpiece[p])==0) break;
						if (fcannon == 0)
						{
							if (color[y]!=xside)
								switch (p)
							{
								case KNIGHT: if (color[i+knightcheck[j]]==EMPTY) pushGeneratedOppositeMove(i, y); break;
								case ELEPHANT:if (color[i+elephancheck[j]]==EMPTY) pushGeneratedOppositeMove(i, y); break;
								case CANNON: if (color[y]==EMPTY) pushGeneratedOppositeMove(i, y); break;
								default: pushGeneratedOppositeMove(i, y);
							}
							if (color[y]!=EMPTY) { if (p==CANNON) fcannon++; else break; }
						}
						else   /* CANNON switch */
						{
							if (color[y] != EMPTY)
							{
								if (color[y]==side) pushGeneratedOppositeMove(i, y);
								break;
							}
						}
					} /* for k */
				} /* for j */
			}
		}
	}
	
	//Record the capture pieces
	private void pushGeneratedOppositeMove(int from, int dest)
	{
		if(ply == 0 && color[dest] != 7){
			if(color[dest] == BLACK){
				black = black + (from % SIZE_X + 1) + (char)(from / SIZE_X + 65) + (dest % SIZE_X + 1) + (char)(dest / SIZE_X + 65) + " ";
			}else{
				red = red + (from % SIZE_X + 1) + (char)(from / SIZE_X + 65) + (dest % SIZE_X + 1) + (char)(dest / SIZE_X + 65) + " ";
			}
		}
	}
/***** End of generate all moves *****/	


/***** Evaluate function *****/
	//Evaluate the current board simply by counting what kind of pieces on the current place
	private int evaluateRed()
	{	
		//values for every kind of pieces in different place
		int[][] pieceValues = {
			{//PAWN
				9,  9,  9, 11, 13, 11,  9,  9,  9, 
				19, 24, 34, 42, 44, 42, 34, 24, 19, 
				19, 24, 32, 37, 37, 37, 32, 24, 19, 
				19, 23, 27, 29, 30, 29, 27, 23, 19, 
				14, 18, 20, 27, 29, 27, 20, 18, 14, 
				7,  0, 13,  0, 16,  0, 13,  0,  7, 
				7,  0,  7,  0, 15,  0,  7,  0,  7, 
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0
			},{//BISHOP
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0, 20,  0, 20,  0,  0,  0, 
				0,  0,  0,  0, 23,  0,  0,  0,  0, 
				0,  0,  0, 20,  0, 20,  0,  0,  0
			},{//ELEPHANT
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0, 20,  0,  0,  0, 20,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			   18,  0,  0,  0, 23,  0,  0,  0, 18, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0, 20,  0,  0,  0, 20,  0,  0
			},{//KNIGHT
				90, 90, 90, 96, 90, 96, 90, 90, 90, 
				90, 96,103, 97, 94, 97,103, 96, 90, 
				92, 98, 99,103, 99,103, 99, 98, 92, 
				93,108,100,107,100,107,100,108, 93, 
				90,100, 99,103,104,103, 99,100, 90, 
				90, 98,101,102,103,102,101, 98, 90, 
				92, 94, 98, 95, 98, 95, 98, 94, 92, 
				93, 92, 94, 95, 92, 95, 94, 92, 93, 
				85, 90, 92, 93, 78, 93, 92, 90, 85, 
				88, 85, 90, 88, 90, 88, 90, 85, 88
			},{//CANNON
				100,100, 96, 91, 90, 91, 96,100,100, 
				98, 98, 96, 92, 89, 92, 96, 98, 98, 
				97, 97, 96, 91, 92, 91, 96, 97, 97, 
				96, 99, 99, 98,100, 98, 99, 99, 96, 
				96, 96, 96, 96,100, 96, 96, 96, 96, 
				95, 96, 99, 96,100, 96, 99, 96, 95, 
				96, 96, 96, 96, 96, 96, 96, 96, 96, 
				97, 96,100, 99,101, 99,100, 96, 97, 
				96, 97, 98, 98, 98, 98, 98, 97, 96, 
				96, 96, 97, 99, 99, 99, 97, 96, 96
			},{//ROOK 
				206,208,207,213,214,213,207,208,206, 
				206,212,209,216,233,216,209,212,206, 
				206,208,207,214,216,214,207,208,206, 
				206,213,213,216,216,216,213,213,206, 
				208,211,211,214,215,214,211,211,208, 
				208,212,212,214,215,214,212,212,208, 
				204,209,204,212,214,212,204,209,204, 
				198,208,204,212,212,212,204,208,198, 
				200,208,206,212,200,212,206,208,200, 
				194,206,204,212,200,212,204,206,194
			},{//KING
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0,  
				0,  0,  0,  1,  1,  1,  0,  0,  0,  
				0,  0,  0,  2,  2,  2,  0,  0,  0,  
				0,  0,  0, 11, 15, 11,  0,  0,  0 
			} 
		};
		int value = 0;
		for (int i=0; i<BOARD_SIZE; i++){
			if (color[i]==side){
				value = value + pieceValues[piece[i]][i];
			}else if(color[i] == xside){
				value = value - pieceValues[piece[i]][i];
			}
		}
		return value;
	}
	
	private int evaluateBlack()
	{	
		//values for every kind of pieces in different place
		int[][] pieceValues = {
			{//PAWN
				0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    7,  0,  7,  0, 15,  0,  7,  0,  7,
			    7,  0, 13,  0, 16,  0, 13,  0,  7, 
			    14, 18, 20, 27, 29, 27, 20, 18, 14,
			    19, 23, 27, 29, 30, 29, 27, 23, 19, 
			    19, 24, 32, 37, 37, 37, 32, 24, 19, 
			    19, 24, 34, 42, 44, 42, 34, 24, 19, 
			    9,  9,  9, 11, 13, 11,  9,  9,  9
			},{//BISHOP
			    0,  0,  0, 20,  0, 20,  0,  0,  0, 
			    0,  0,  0,  0, 23,  0,  0,  0,  0, 
			    0,  0,  0, 20,  0, 20,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0
			},{//ELEPHANT
				0,  0, 20,  0,  0,  0, 20,  0,  0,
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			   18,  0,  0,  0, 23,  0,  0,  0, 18,  
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0, 20,  0,  0,  0, 20,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0, 
			    0,  0,  0,  0,  0,  0,  0,  0,  0 
			},{//KNIGHT
				88, 85, 90, 88, 90, 88, 90, 85, 88,
				85, 90, 92, 93, 78, 93, 92, 90, 85,
				93, 92, 94, 95, 92, 95, 94, 92, 93, 
				92, 94, 98, 95, 98, 95, 98, 94, 92,
				90, 98,101,102,103,102,101, 98, 90, 
				90,100, 99,103,104,103, 99,100, 90,
				93,108,100,107,100,107,100,108, 93,
				92, 98, 99,103, 99,103, 99, 98, 92, 
				90, 96,103, 97, 94, 97,103, 96, 90, 
				90, 90, 90, 96, 90, 96, 90, 90, 90
			},{//CANNON
			    96, 96, 97, 99, 99, 99, 97, 96, 96,
			    96, 97, 98, 98, 98, 98, 98, 97, 96, 
			    97, 96,100, 99,101, 99,100, 96, 97, 
			    96, 96, 96, 96, 96, 96, 96, 96, 96, 
			    95, 96, 99, 96,100, 96, 99, 96, 95, 
			    96, 96, 96, 96,100, 96, 96, 96, 96,
			    96, 99, 99, 98,100, 98, 99, 99, 96,
			    97, 97, 96, 91, 92, 91, 96, 97, 97, 
			    98, 98, 96, 92, 89, 92, 96, 98, 98,
			    100,100, 96, 91, 90, 91, 96,100,100
			},{//ROOK 
				194,206,204,212,200,212,204,206,194,
				200,208,206,212,200,212,206,208,200, 
				198,208,204,212,212,212,204,208,198, 
				204,209,204,212,214,212,204,209,204, 
				208,212,212,214,215,214,212,212,208, 
				208,211,211,214,215,214,211,211,208,
				206,213,213,216,216,216,213,213,206, 
				206,208,207,214,216,214,207,208,206, 
				206,212,209,216,233,216,209,212,206, 
				206,208,207,213,214,213,207,208,206
			},{//KING
				0,  0,  0, 11, 15, 11,  0,  0,  0, 
				0,  0,  0,  2,  2,  2,  0,  0,  0, 
				0,  0,  0,  1,  1,  1,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0,  
				0,  0,  0,  0,  0,  0,  0,  0,  0,
				0,  0,  0,  0,  0,  0,  0,  0,  0, 
				0,  0,  0,  0,  0,  0,  0,  0,  0 
			} 
		};
		int value = 0;
		for (int i=0; i<BOARD_SIZE; i++){
			if (color[i]==side){
				value = value + pieceValues[piece[i]][i];
			}else if(color[i] == xside){
				value = value - pieceValues[piece[i]][i];
			}
		}
		return value;
	}
/***** End of evaluate function *****/

	
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
		red = "";
		black = "";
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
		hist_dat[hdp].capture = result = piece[dest];		
		piece[dest] = piece[from]; piece[from] = EMPTY;
		color[dest] = color[from]; color[from] = EMPTY;
		hdp++; ply++; side = xside; xside = 1-xside;
		return result;
	}

	private Move unmove()
	{
		int from, dest;
		hdp--; ply--; side = xside; xside = 1-xside;		
		from = hist_dat[hdp].m.from; dest = hist_dat[hdp].m.dest;
		piece[from] = piece[dest]; color[from] = color[dest];
		piece[dest] = hist_dat[hdp].capture;
		if (piece[dest] == EMPTY) color[dest] = EMPTY; else color[dest] = xside;
		mov.from = from; mov.dest = dest;
		return mov;
	}
/***** End of move and undo for search *****/
	
	
/***** Real move on the board *****/	
	private boolean updateNewMove()
	{
		int from, dest, p;
		from = newmove.from; dest = newmove.dest; p = piece[dest];
		piece[dest] = piece[from]; piece[from] = EMPTY;
		color[dest] = color[from]; color[from] = EMPTY;	
		boardHist[myhdp].m.from = from;boardHist[myhdp].m.dest = dest;boardHist[myhdp].capture = p;
		myhdp++; side = xside; xside = 1-xside;
		return p == KING;
	}
/***** End of real move on the board *****/	
/********************************************* End of help functions *******************************************/
}


/******************************************  Used new data structures ******************************************/
class Move{
	public int from;
	public int dest;
};

class Recorder{
	public Move m ;
	public Recorder() {
		m = new Move();
	}
};

class History{
	public Move m ;
	public int capture;

	public History() {
		m = new Move();
	}
} ;
/********************************************** End of new data structures **************************************/
