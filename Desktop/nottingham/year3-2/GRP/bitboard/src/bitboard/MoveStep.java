package bitboard;

import java.util.Arrays;

public class MoveStep {
	/*private static final int  BlackCar1 = 0, 
			BlackCar2 = 0, 
			BlackCar3 = 0, 
			BlackHorse1 = 0,
    		BlackHorse2 = 0,
			BlackHorse3 = 0,
			BlackEle1 = 0, 
			BlackEle2 = 0, 
			BlackEle3 = 0, 
			BlackShi1 = 0,
		    BlackShi2 = 0,
			BlackShi3 = 0,
			BlackKing1 = 0, 
			BlackKing2 = 0, 
			BlackKing3 = 0, 
			BlackPao1 = 0,
			BlackPao2 = 0,
			BlackPao3 = 0,
			BlackSoldier1 = 0, 
			BlackSoldier2 = 0,
			BlackSoldier3 = 0,
			RedCar1 = 0,
			RedCar2 = 0,
			RedCar3 = 0,
			RedHorse1 = 0,
			RedHorse2 = 0,
			RedHorse3 = 0,
			RedEle1 = 0,
			RedEle2 = 0,
			RedEle3 = 0, 
			RedShi1 = 0,
		    RedShi2 = 0,
		    RedShi3 = 0,
		    RedKing1 = 0, 
		    RedKing2 = 0, 
		    RedKing3 = 0, 
		    RedPao1 = 0,
		    RedPao2 = 0,
		    RedPao3 = 0,
		    RedSoldier1 = 0,
			RedSoldier2 = 0,
			RedSoldier3 = 0;
			*/
	static int FILE_A = 0;//72340172838076673
    //10000000
    //10000000
    //10000000
    //10000000
    //10000000
    //10000000
    //100000000
	static int FILE_H = 0;//9187201950435737472
	static int FILE_AB = 0;//217020518514230019
	static int FILE_GH = 0;//-4557430888798830400
	static int RANK_1 = 0;//-72057594037927936
	static int RANK_4 = 0;//1095216660480
	static int RANK_5 = 0;//4278190080
	static int RANK_8 = 0;//255
	static int CENTER = 0;//103481868288
	static int EXTENDED_CENTER = 0;//66229406269440
	static int KING_SIDE = 0;//-1085102592571150096
	static int QUEEN_SIDE = 0;//1085102592571150095
	static int KING_B7 = 0;//460039;
	static int KNIGHT_C6 = 0;//43234889994;
	
	//static Board chessBoard;
	static int NOT_RED_PIECE;
	static int BLACK_PIECE;
	static int EMPTY;
        
        public static String possibleMoves(String history,Board chessboard,int BlackCar1,int BlackHorse1, int BlackEle1, int BlackShi1, int BlackKing1,int BlackPao1,
        		int BlackSoldier1, int RedCar1, int RedHorse1,int RedEle1, int RedShi1, int RedKing1, int RedPao1, int RedSoldier1,int BlackCar2,int BlackHorse2,
        		int BlackEle2, int BlackShi2, int BlackKing2,int BlackPao2, int BlackSoldier2, int RedCar2, int RedHorse2,int RedEle2, int RedShi2, int RedKing2, 
        		int RedPao2, int RedSoldier2,int BlackCar3,int BlackHorse3, int BlackEle3, int BlackShi3, int BlackKing3,int BlackPao3, int BlackSoldier3, int RedCar3,
        		int RedHorse3,int RedEle3, int RedShi3, int RedKing3, int RedPao3, int RedSoldier3){
        	    BLACK_PIECE = BlackCar1|BlackHorse1|BlackEle1|BlackShi1|BlackPao1|BlackSoldier1|BlackCar2|BlackHorse2|BlackEle2|BlackShi2|BlackPao2|BlackSoldier2|BlackCar3|BlackHorse3|BlackEle3|BlackShi3|BlackPao3|BlackSoldier3;
        	    NOT_RED_PIECE = ~(RedCar1|RedHorse1|RedEle1|RedShi1|RedKing1|RedPao1|RedSoldier1|RedCar2|RedHorse2|RedEle2|RedShi2|RedKing2|RedPao2|RedSoldier2|RedCar3|RedHorse3|RedEle3|RedShi3|RedKing3|RedPao3|RedSoldier3|BlackKing1|BlackKing2|BlackKing3);
                EMPTY = ~(BlackCar1|BlackHorse1|BlackEle1|BlackShi1|BlackKing1|BlackPao1|BlackSoldier1|RedCar1|RedHorse1|RedEle1|RedShi1|RedKing1|RedPao1|RedSoldier1|
   					 BlackCar2|BlackHorse2|BlackEle2|BlackShi2|BlackKing2|BlackPao2|BlackSoldier2|RedCar2|RedHorse2|RedEle2|RedShi2|RedKing2|RedPao2|RedSoldier2|
   					 BlackCar3|BlackHorse3|BlackEle3|BlackShi3|BlackKing3|BlackPao3|BlackSoldier3|RedCar3|RedHorse3|RedEle3|RedShi3|RedKing3|RedPao3|RedSoldier3);
                String list = possibleBlackSoldier3(history,BlackSoldier3);
                return list;
        }
        
        
        public static String possibleBlackSoldier3(String history,int BlackSoldier3)//WP
        {
        	String list= "";
        	//x1,y1,x2,y2
        	int Soldier_Move = (BlackSoldier3 >> 7) & BLACK_PIECE & ~ RANK_8 & ~FILE_A;//capture right  吃右下的子
        	for(int i = Integer.numberOfTrailingZeros(Soldier_Move); i < 64 - Integer.numberOfLeadingZeros(Soldier_Move); i ++){
        		if(((Soldier_Move >> i)&1) == 1){
        			list += ""+(i/8+1) + (i%8-1) + (i/8) + (i%8);
        		}
        	}
        	Soldier_Move = (BlackSoldier3 >> 9) & BLACK_PIECE & ~ RANK_8 & ~FILE_H;//capture left  吃左下的子
        	for(int i = Integer.numberOfTrailingZeros(Soldier_Move); i < 64 - Integer.numberOfLeadingZeros(Soldier_Move); i ++){
        		if(((Soldier_Move >> i)&1) == 1){
        			list += ""+(i/8+1) + (i%8+1) + (i/8) + (i%8);
        		}
        	}
        	Soldier_Move = (BlackSoldier3 >> 8) & EMPTY & ~ RANK_8;//向下移一位
        	for(int i = Integer.numberOfTrailingZeros(Soldier_Move); i < 64 - Integer.numberOfLeadingZeros(Soldier_Move); i ++){
        		if(((Soldier_Move >> i)&1) == 1){
        			list += ""+(i/8+1) + (i%8) + (i/8) + (i%8);
        		}
        	}
        	Soldier_Move = (BlackSoldier3 >> 16) & EMPTY & (EMPTY >> 8) & RANK_4;//  移两位
        	for(int i = Integer.numberOfTrailingZeros(Soldier_Move); i < 64 - Integer.numberOfLeadingZeros(Soldier_Move); i ++){
        		if(((Soldier_Move >> i)&1) == 1){
        			list += ""+(i/8+2) + (i%8) + (i/8) + (i%8);
        		}
        	}
        	
        	//y1,y2,"P"
        	Soldier_Move = (BlackSoldier3 >> 7) & BLACK_PIECE & ~ RANK_8 & ~FILE_A;
        	for(int i = Integer.numberOfTrailingZeros(Soldier_Move); i < 64 - Integer.numberOfLeadingZeros(Soldier_Move); i ++){
        		if(((Soldier_Move >> i)&1) == 1){
        			list += ""+(i/8-1) + (i%8) +"QP"+ (i/8-1) + (i%8) + "RP" + (i/8-1) + (i%8) + "BP" + (i/8-1) + (i%8);
        		}
        	}
        	Soldier_Move = (BlackSoldier3 >> 9) & BLACK_PIECE & RANK_8 & ~FILE_H;//capture left  吃左下的子
        	for(int i = Integer.numberOfTrailingZeros(Soldier_Move); i < 64 - Integer.numberOfLeadingZeros(Soldier_Move); i ++){
        		if(((Soldier_Move >> i)&1) == 1){
        			list += ""+(i/8+1) + (i%8) +"QP"+ (i/8+1) + (i%8) + "RP" + (i/8+1) + (i%8) + "BP" + (i/8+1) + (i%8);
        		}
        	}
        	Soldier_Move = (BlackSoldier3 >> 8) & EMPTY & ~ RANK_8;//向下移一位
        	for(int i = Integer.numberOfTrailingZeros(Soldier_Move); i < 64 - Integer.numberOfLeadingZeros(Soldier_Move); i ++){
        		if(((Soldier_Move >> i)&1) == 1){
        			list += ""+(i%8) + (i%8) +"QP"+ (i%8) + (i%8) + "RP" + (i%8) + (i%8) + "BP" + (i%8) + (i%8);
        		}
        	}
        
        	return list;
        }
        
        public static void drawBitboard(long bitBoard){
        	String chessBoard[][] = new String[8][8];
        	for(int i = 0 ; i < 64 ; i ++){
        		chessBoard[i/8][i%8] = "";
        	}
        	for(int i = 0 ; i < 64 ; i ++){
        		if(((BlackSoldier3>>i)&1) == 1) {chessBoard[i/9][i%9] = "d";}
        		if("".equals(chessBoard[i/8][i%8])) {chessBoard[i/9][i%9] = " ";}
        	}
        	for(int i = 0; i < 64; i++){
        		System.out.println(Arrays.toString(chessBoard[i]));
        	}
        }
        
        public static void newGame(){
        	Board.initiateStandardChess();
        	MoveStep.possibleMoves("",chessBoard,BlackCar1,BlackHorse1,BlackEle1,BlackShi1,BlackKing1,BlackPao1,BlackSoldier1,RedCar1,RedHorse1,RedEle1,RedShi1,RedKing1,RedPao1,RedSoldier1,
					 BlackCar2,BlackHorse2,BlackEle2,BlackShi2,BlackKing2,BlackPao2,BlackSoldier2,RedCar2,RedHorse2,RedEle2,RedShi2,RedKing2,RedPao2,RedSoldier2,
					 BlackCar3,BlackHorse3,BlackEle3,BlackShi3,BlackKing3,BlackPao3,BlackSoldier3,RedCar3,RedHorse3,RedEle3,RedShi3,RedKing3,RedPao3,RedSoldier3);
        }
}
