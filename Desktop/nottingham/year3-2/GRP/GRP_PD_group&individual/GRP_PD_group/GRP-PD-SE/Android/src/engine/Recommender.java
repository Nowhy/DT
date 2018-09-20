/**
Writen by Meng Yuan, modified by Yu Qu reused codes from Zhejie, Lee
**/


package engine;

import com.example.test.NewGame;

import android.widget.ImageButton;

public class Recommender {
/********************************************* Create needed variables ****************************************/	

	/***** For recommend record *****/
	private String black;//record recommends
	private String red;//record recommends
	private boolean checkKing;
	
	NewGame ng = null;
	ImageButton[][] cb = null;
	// init, writen by Yu Qu	
	public Recommender(NewGame n){
		ng = n;
	}

	/***** For generate moves *****/ // writen by Zhejie, Lee
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
	};// legal positions that can arrived by each kind of pieces
	
	public static final int[] maskpiece = {8, 2, 4, 1, 1, 1, 2}; // 2-BISHOP/GENERAL, 4-ELEPHANT, 8-PAWN, 1-other
	public static final int[] knightcheck = {1,-1,-9,-9,-1,1,9,9};
	public static final int[] elephancheck = {-10,-8,8,10,0,0,0,0};		

	/*****  Record recommendation *****/	
	private void generateCaptured() // writen by Zhejie, Lee, modified by Meng Yuan
	{
		int i, // 90 board index -- start
			j, // offset index
			x, // 128 board index
			p, // piece kind
			n, // max move grids
			k, // each grids index
			y, // 90 board index -- end
			t, // change both side into one side to check illegel
			fcannon, // cannon has middle piece
			colorStart,
			colorDest;
		
		for (i=0; i < AIBoard.BOARD_SIZE; i++) //each piece -- start90
		{
			p = AIBoard.piece[i];//piece kind
			if(p == AIBoard.EMPTY) continue;
			
			for (j=0; j<8; j++)//each direction in offset
			{
				if (offset[p][j] == 0) break; //rule for each piece
				
				fcannon = 0; //initial
				x = mailbox90[i]; //find current position in 128 board -- start128
				
				if (p == AIBoard.ROOK || p == AIBoard.CANNON) n = 9; else n = 1; //set max move grids
				for (k=0; k<n; k++) // for each grid
				{
					if (p==AIBoard.PAWN && AIBoard.side==AIBoard.RED) x -= offset[p][j]; else x += offset[p][j]; //move and record end in 128 board -- end128
					y = mailbox182[x]; //get end in 90 board -- end90
					if(y == -1) break; // Illegal move -- out of bounds
						
					colorStart = AIBoard.color[i];
					colorDest = AIBoard.color[y];
						
					if (colorStart == AIBoard.BLACK) t = y; else t = 89-y;//t for the position in the board of this piece, according which side the piece is -- change to one side
					if ((legalposition[t] & maskpiece[p])==0) {break;} //Illegal move
						
					if(fcannon == 0) // if there is no piece between start and end
					{	
						if(colorDest == AIBoard.EMPTY) continue;
						if(colorDest == 1-colorStart)
						{
							switch (p)
							{
								case AIBoard.KNIGHT: 
									if (AIBoard.color[i+knightcheck[j]]==AIBoard.EMPTY) record(i, y);
									break;
								case AIBoard.ELEPHANT:
									if(AIBoard.color[i+elephancheck[j]]==AIBoard.EMPTY) record(i, y);
									break;
								case AIBoard.CANNON:
									break;
								default:
									record(i, y);
									break;
							}
						}
						if(p==AIBoard.CANNON) fcannon++; else break;					
					}else{ // if there is a piece between start and end
						if(colorDest != AIBoard.EMPTY){
							if(colorDest == 1-colorStart){
									record(i, y);
							}
							break;
						}
					}
				} /* for k */
			}/* for j */
		}/* for i */
	}
	
	//Record the captured general messages, as well as the other captured pieces, writen by Meng Yuan, modified by Yu Qu
	private void record(int from, int dest){
		if(AIBoard.piece[dest] == AIBoard.KING && AIBoard.side != AIBoard.color[dest]){// check whether the own side's general is captured
			checkKing = true;
		}
		if(AIBoard.color[dest] == AIBoard.BLACK){ // record all the captured pieces
	        black =black+ng.getPieceTable().get(ng.getChessBoard()[from%9][from/9].getId())+" will capture "+ng.getPieceTable().get(ng.getChessBoard()[dest%9][dest/9].getId())+"\n";
		}else{
	        red =red+ng.getPieceTable().get(ng.getChessBoard()[from%9][from/9].getId())+" will capture "+ng.getPieceTable().get(ng.getChessBoard()[dest%9][dest/9].getId())+"\n";
		}	
	}
	
	//Get the warnign messages for captured pieces, writen by Meng Yuan, modified by Yu Qu
	public void getRecords(){
		black = ""; red = ""; // init 
		generateCaptured(); // generat records
		ng.reminderRed(red); // return records
		ng.reminderBlack(black);
	}
	
	//Check whether the move is valid regard with the captured general, writen by Meng Yuan
	public boolean getCheck(int from, int dest){
		checkKing = false; // init
		AIBoard.update(from, dest); // move 
		generateCaptured(); // check whether the king is still be captured
		AIBoard.undo(); // unmove
		return checkKing;
	}
	
	//Check whether the move is valid regard with the king face, reused codes from Zhejie, Lee
	public boolean kingFace(int from, int dest)
	{
		boolean result = false;
		int i = from % 9;
		if (i>=3 && i<=5 && AIBoard.piece[dest]!=AIBoard.KING)
		{
			AIBoard.update(from, dest); // move
			for (i=3; AIBoard.piece[i]!=AIBoard.KING; i++); // find one king
			for (i += 9; i<90 && AIBoard.piece[i]==AIBoard.EMPTY; i += 9); // find a piece in this column
			if (i<90 && AIBoard.piece[i]==AIBoard.KING) result = true; // if there is no piece between these two generals, return true
			AIBoard.undo(); // unmove
		}
		return result;
	}
}
