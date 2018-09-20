public class AIBoard{

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
	
	/***** The board representation *****/
	public static int[] color;
	public static int[] piece;
	public static int side, xside;
	
	/***** For undo *****/
	private static History[] boardHist;
	private static int histindex;
	
	AIBoard(){
		init();
	}

	//Start a new game.
	public void init() {
		color = new int[BOARD_SIZE]; piece = new int[BOARD_SIZE];
		side = RED; xside = BLACK;
		
		boardHist = new History[MAX_MOVE_STACK];
		for (int i = 0; i < MAX_MOVE_STACK; i++) {
			boardHist[i] = new History(); 
		}
		histindex = 0;

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
	
	public static boolean update(int from, int dest)
	{
		int p = piece[dest];
		piece[dest] = piece[from]; piece[from] = EMPTY;
		color[dest] = color[from]; color[from] = EMPTY;	
		boardHist[histindex].m.from = from;
		boardHist[histindex].m.dest = dest;
		boardHist[histindex].capture = p;
		histindex++; side = xside; xside = 1 - side;
		return p == KING;
	}
	
	//Undo
	public static void undo()
	{
		int from, dest;
		histindex--; side = xside; xside = 1 - side;
		from = boardHist[histindex].m.from; dest = boardHist[histindex].m.dest;
		piece[from] = piece[dest]; color[from] = color[dest];
		piece[dest] = boardHist[histindex].capture;
		if (piece[dest] == EMPTY) color[dest] = EMPTY; else color[dest] = xside;
	}

	//print board -- for test
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
