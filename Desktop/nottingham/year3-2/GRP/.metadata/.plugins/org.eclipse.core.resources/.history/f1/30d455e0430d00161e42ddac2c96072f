package engine;
public class MoveGenerater {
/********************************************* Create needed variables ****************************************/	

	/***** Store possible moves, for search *****/
	private static final int HIST_STACK = 5000;
	private static final int MOVE_STACK = 10240;

	public static Recorder[] gen_dat ;//record moved steps
	public static int[] gen_begin;
	public static int[] gen_end;

	/***** For generate moves *****/
		//14*13,10*9
		public static final int[] mailbox182 = {
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
		public static final int[] mailbox90 = {
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

		//[7][8] possible positions offset
		public static final int[][] offset = {//on182
			// left, right, up, down,0,0,0,0
			{ -1,  1, 13,  0,  0,  0,  0,  0}, /* PAWN */
			{-12,-14, 12, 14,  0,  0,  0,  0}, /* BISHOP */
			{-28,-24, 24, 28,  0,  0,  0,  0}, /* ELEPHAN */
			{-11,-15,-25,-27, 11, 15, 25, 27}, /* KNIGHT */
			{ -1,  1,-13, 13,  0,  0,  0,  0}, /* CANNON */
			{ -1,  1,-13, 13,  0,  0,  0,  0}, /* ROOK */
			{ -1,  1,-13, 13,  0,  0,  0,  0}  /* KING */
		}; 
		
		public static final int[] legalposition = {
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
		};//key
		
		public static final int[] maskpiece = {8, 2, 4, 1, 1, 1, 2}; // 2-Shi\Wang, 4-Xiang, 8-Bing, 1-other
		public static final int[] knightcheck = {1,-1,-9,-9,-1,1,9,9};
		public static final int[] elephancheck = {-10,-8,8,10,0,0,0,0};
		
/******************************************** End of create needed variables ***********************************/	

	public MoveGenerater(){	
		gen_dat = new Recorder[MOVE_STACK];
		for (int i = 0; i < MOVE_STACK; i++) {
			gen_dat[i] = new Recorder();
		}	
		gen_begin = new int[HIST_STACK];
		gen_end = new int[HIST_STACK];	
		gen_begin[0] = 0; 
	}

/***** Generate all moves *****/	
	//generate all possible moves
	public static void generateMoves(int ply)
	{
		int i, j, k, n, p, x, y, t, fcannon;
		gen_end[ply] = gen_begin[ply];

		for (i=0; i < 90; i++){
			if (AIBoard.color[i]==AIBoard.side)
			{
				p = AIBoard.piece[i];//piece kind
				for (j=0; j<8; j++)
				{
					if (offset[p][j] == 0) break;//find possible next position
					x = mailbox90[i]; //offset in mailbox128
					fcannon = 0;
					if (p==AIBoard.ROOK || p==AIBoard.CANNON) n = 9; else n = 1;//
					for (k=0; k<n; k++)
					{
						//  get offset result for (p==PAWN && side==LIGHT)
						//there is no offset table for it
						if (p==AIBoard.PAWN && AIBoard.side==AIBoard.RED) x -= offset[p][j]; else x += offset[p][j];

						y = mailbox182[x];
						//  t for the position in the board of this piece ,
						//according which side the piece is 
						if (AIBoard.side == AIBoard.BLACK) t = y; else t = 89-y;
						if (y==-1 || (legalposition[t] & maskpiece[p])==0) break;
						if (fcannon == 0)
						{
							if (AIBoard.color[y]!=AIBoard.side)
							switch (p)
							{
								case AIBoard.KNIGHT: if (AIBoard.color[i+knightcheck[j]]==AIBoard.EMPTY) pushGeneratedMove(i, y, ply); break;
								case AIBoard.ELEPHANT:if (AIBoard.color[i+elephancheck[j]]==AIBoard.EMPTY) pushGeneratedMove(i, y, ply); break;
								case AIBoard.CANNON: if (AIBoard.color[y]==AIBoard.EMPTY) pushGeneratedMove(i, y, ply); break;
								default: pushGeneratedMove(i, y, ply);
							}
							if (AIBoard.color[y]!=AIBoard.EMPTY) { if (p==AIBoard.CANNON) fcannon++; else break; }
						}
						else   /* CANNON switch */
						{
							if (AIBoard.color[y] != AIBoard.EMPTY)
							{
								if (AIBoard.color[y]==AIBoard.xside) pushGeneratedMove(i, y, ply);
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
	private static void pushGeneratedMove(int from, int dest, int ply)
	{
		if (!kingFace(from, dest))
		{
			gen_dat[gen_end[ply]].m.from = from;
			gen_dat[gen_end[ply]].m.dest = dest;
			gen_end[ply]++;
		}
	}
	
	//check whether King will be killed by opponent's King directly after computer moves King
	private static boolean kingFace(int from, int dest)
	{
		boolean result = false;
		int i = from % 9;
		if (i>=3 && i<=5 && AIBoard.piece[dest]!=AIBoard.KING)
		{
			int t = AIBoard.piece[dest]; AIBoard.piece[dest] = AIBoard.piece[from]; AIBoard.piece[from] = AIBoard.EMPTY;//make the move
			for (i=3; AIBoard.piece[i]!=AIBoard.KING; i++);//i=kingpalace[i]
			for (i += 9; i<90 && AIBoard.piece[i]==AIBoard.EMPTY; i += 9);
			if (i<90 && AIBoard.piece[i]==AIBoard.KING) result = true;
			AIBoard.piece[from] = AIBoard.piece[dest]; AIBoard.piece[dest] = t;//unmove
		}
		return result;
	}

/***** End of generate all moves *****/	
}
