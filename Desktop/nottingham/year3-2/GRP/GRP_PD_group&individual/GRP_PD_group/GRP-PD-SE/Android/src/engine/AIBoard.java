/**
 Writen by Meng Yuan, Han Bao, reused coded from Zhijie Lee: https://github.com/zhijie/ChineseChess4Android/blob/master/src/com/onezeros/chinesechess/AI.java
**/

package engine;

public class AIBoard{
	/***** Constant values *****/ // reused from Zhijie Lee
	public static final int BOARD_SIZE = 90;
	public static final int MAX_MOVE_STACK = 500;
	
	public static final int BLACK = 0;
	public static final int RED = 1;

	public static final int PAWN = 0 ;
	public static final int BISHOP = 1;
	public static final int ELEPHANT = 2;
	public static final int KNIGHT = 3;
	public static final int CANNON = 4;
	public static final int ROOK = 5;
	public static final int KING = 6;
	public static final int EMPTY = 7;
	
	/***** The board representation *****/ // reused from Zhijie Lee
	public static int[] color;
	public static int[] piece;
	public static int side, xside;
	
	/***** For undo *****/
	private static History[] boardHist;
	private static int histindex;
	
	
	public AIBoard(){
		init();
	}

	//Start a new game, writen by Zhijie Lee, modified by Meng Yuan 
	public void init() {
		color = new int[BOARD_SIZE]; piece = new int[BOARD_SIZE]; // create board
		side = RED; xside = BLACK; // initial side
		
		boardHist = new History[MAX_MOVE_STACK]; // used for undo
		for (int i = 0; i < MAX_MOVE_STACK; i++) {
			boardHist[i] = new History(); 
		}
		histindex = 0; // index of boardHist

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
		}; // initial state of color board
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
		}; // initial state of pieces board
		System.arraycopy(clr, 0, color, 0, clr.length); // initail color board
		System.arraycopy(pc, 0, piece, 0, pc.length); // initial piece board
	}
	
	//Make a move, reused codes from Zhejie Lee, modified by Meng Yuan
	public static boolean update(int from, int dest)
	{
		int p = piece[dest];
		piece[dest] = piece[from]; piece[from] = EMPTY; // update piece board
		color[dest] = color[from]; color[from] = EMPTY;	// update color board
		boardHist[histindex].m.from = from; // record move to histroy list
		boardHist[histindex].m.dest = dest;
		boardHist[histindex].capture = p;
		histindex++; 
		side = xside; xside = 1 - side; // update side
		return p == KING;
	}
	
	//Undo, reused codes from Zhejie Lee, modified by Meng Yuan
	public static void undo()
	{
		int from, dest;
		histindex--; side = xside; xside = 1 - side; // update side
		from = boardHist[histindex].m.from; dest = boardHist[histindex].m.dest;
		piece[from] = piece[dest]; // update pieces board
		piece[dest] = boardHist[histindex].capture;
		color[from] = color[dest]; // update color board
		if (piece[dest] == EMPTY) color[dest] = EMPTY; else color[dest] = xside; 
	}

	//print board -- for test // Writen by Hao Bao
    public void printBoard(){
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
}
