package bitboard;

import java.util.Arrays;

public class Board {

	public static void initiateStandardChess(){
		int BlackCar1 = 0, 
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
		
		
		String chessBoard[][] = {
				{"c","h","e","s","k","s","e","h","c"},
				{" "," "," "," "," "," "," "," "," "},
				{" ","p"," "," "," "," "," ","p"," "},
				{"d"," ","d"," ","d"," ","d"," ","d"},
				{" "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "},
				{"D"," ","D"," ","D"," ","D"," ","D"},
				{" ","P"," "," "," "," "," ","P"," "},
				{" "," "," "," "," "," "," "," "," "},
				{"C","H","E","S","K","S","E","H","C"}
		};
			 arrayToBitboard(chessBoard,BlackCar1,BlackHorse1,BlackEle1,BlackShi1,BlackKing1,BlackPao1,BlackSoldier1,RedCar1,RedHorse1,RedEle1,RedShi1,RedKing1,RedPao1,RedSoldier1,
					 BlackCar2,BlackHorse2,BlackEle2,BlackShi2,BlackKing2,BlackPao2,BlackSoldier2,RedCar2,RedHorse2,RedEle2,RedShi2,RedKing2,RedPao2,RedSoldier2,
					 BlackCar3,BlackHorse3,BlackEle3,BlackShi3,BlackKing3,BlackPao3,BlackSoldier3,RedCar3,RedHorse3,RedEle3,RedShi3,RedKing3,RedPao3,RedSoldier3);
	}
	
	
	
	public static void arrayToBitboard(String[][] chessBoard, int BlackCar1,
			int BlackHorse1, int BlackEle1, int BlackShi1, int BlackKing1,
			int BlackPao1, int BlackSoldier1, int RedCar1, int RedHorse1,
			int RedEle1, int RedShi1, int RedKing1, int RedPao1, int RedSoldier1,
			int BlackCar2,
			int BlackHorse2, int BlackEle2, int BlackShi2, int BlackKing2,
			int BlackPao2, int BlackSoldier2, int RedCar2, int RedHorse2,
			int RedEle2, int RedShi2, int RedKing2, int RedPao2, int RedSoldier2,
			int BlackCar3,
			int BlackHorse3, int BlackEle3, int BlackShi3, int BlackKing3,
			int BlackPao3, int BlackSoldier3, int RedCar3, int RedHorse3,
			int RedEle3, int RedShi3, int RedKing3, int RedPao3, int RedSoldier3) {
	
		
		String Binary;
		for(int i = 0 ; i < 90 ; i ++){
			Binary = "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
			Binary = Binary.substring(i+1) + "1" + Binary.substring(0, i);
			switch(chessBoard[i/9][i%9]){
				case "c": 
					BlackCar1 +=convertStringToBitboard(Binary.substring(0,32));
					BlackCar2 +=convertStringToBitboard(Binary.substring(32,64));
					BlackCar3 +=convertStringToBitboard(Binary.substring(64,90));
				     break;
				case "C": 
					RedCar1 +=convertStringToBitboard(Binary.substring(0,32));
					RedCar2 +=convertStringToBitboard(Binary.substring(32,64));
					RedCar3 +=convertStringToBitboard(Binary.substring(64,90));
			     	 break;
				case "h": 
					BlackHorse1 +=convertStringToBitboard(Binary.substring(0,32));
					BlackHorse2 +=convertStringToBitboard(Binary.substring(32,64));
					BlackHorse3 +=convertStringToBitboard(Binary.substring(64,90));
					 break;
				case "H": 
					RedHorse1 +=convertStringToBitboard(Binary.substring(0,32));
					RedHorse2 +=convertStringToBitboard(Binary.substring(32,64));
					RedHorse3 +=convertStringToBitboard(Binary.substring(64,90));
					 break;
				case "e": 
					BlackEle1 +=convertStringToBitboard(Binary.substring(0,32));
					BlackEle2 +=convertStringToBitboard(Binary.substring(32,64));
					BlackEle3 +=convertStringToBitboard(Binary.substring(64,90));
					 break;
				case "E": 
					RedEle1 +=convertStringToBitboard(Binary.substring(0,32));
					RedEle2 +=convertStringToBitboard(Binary.substring(32,64));
					RedEle3 +=convertStringToBitboard(Binary.substring(64,90));
					 break;
				case "s": 
					BlackShi1 +=convertStringToBitboard(Binary.substring(0,32));
					BlackShi2 +=convertStringToBitboard(Binary.substring(32,64));
					BlackShi3 +=convertStringToBitboard(Binary.substring(64,90));
				 	 break;
				case "S": 
					RedShi1 +=convertStringToBitboard(Binary.substring(0,32));
					RedShi2 +=convertStringToBitboard(Binary.substring(32,64));
					RedShi3 +=convertStringToBitboard(Binary.substring(64,90));
				     break;	 
				case "k": 
					BlackKing1 +=convertStringToBitboard(Binary.substring(0,32));
					BlackKing2 +=convertStringToBitboard(Binary.substring(32,64));
					BlackKing3 +=convertStringToBitboard(Binary.substring(64,90));
				     break;
				case "K": 
					RedKing1 +=convertStringToBitboard(Binary.substring(0,32));
					RedKing2 +=convertStringToBitboard(Binary.substring(32,64));
					RedKing3 +=convertStringToBitboard(Binary.substring(64,90));
					 break;
				case "p": 
					BlackPao1 +=convertStringToBitboard(Binary.substring(0,32));
					BlackPao2 +=convertStringToBitboard(Binary.substring(32,64));
					BlackPao3 +=convertStringToBitboard(Binary.substring(64,90));
					 break;
				case "P": 
					RedPao1 +=convertStringToBitboard(Binary.substring(0,32));
					RedPao2 +=convertStringToBitboard(Binary.substring(32,64));
					RedPao3 +=convertStringToBitboard(Binary.substring(64,90));
					 break;	 
				case "d": 
					BlackSoldier1 +=convertStringToBitboard(Binary.substring(0,32));
					BlackSoldier2 +=convertStringToBitboard(Binary.substring(32,64));
					BlackSoldier3 +=convertStringToBitboard(Binary.substring(64,90));
			         break;
				case "D": 
					RedSoldier1 +=convertStringToBitboard(Binary.substring(0,32));
					RedSoldier2 +=convertStringToBitboard(Binary.substring(32,64));
					RedSoldier3 +=convertStringToBitboard(Binary.substring(64,90));
				     break;
				default:
				     break;
			}
		}
		drawArray(BlackCar1,BlackHorse1,BlackEle1,BlackShi1,BlackKing1,BlackPao1,BlackSoldier1,RedCar1,RedHorse1,RedEle1,RedShi1,RedKing1,RedPao1,RedSoldier1,
				BlackCar2,BlackHorse2,BlackEle2,BlackShi2,BlackKing2,BlackPao2,BlackSoldier2,RedCar2,RedHorse2,RedEle2,RedShi2,RedKing2,RedPao2,RedSoldier2,
				BlackCar3,BlackHorse3,BlackEle3,BlackShi3,BlackKing3,BlackPao3,BlackSoldier3,RedCar3,RedHorse3,RedEle3,RedShi3,RedKing3,RedPao3,RedSoldier3);
	}


	public  static int convertStringToBitboard(String Binary) {
	    if(Binary.charAt(0) == '0'){
	    	return Integer.parseInt(Binary, 2);
	    }else{
	    	return Integer.parseInt("1" + Binary.substring(2),2)*2;
	    }
    }
	
    public static void drawArray(int BlackCar1,
			int BlackHorse1, int BlackEle1, int BlackShi1, int BlackKing1,
			int BlackPao1, int BlackSoldier1, int RedCar1, int RedHorse1,
			int RedEle1, int RedShi1, int RedKing1, int RedPao1, int RedSoldier1,
			int BlackCar2,
			int BlackHorse2, int BlackEle2, int BlackShi2, int BlackKing2,
			int BlackPao2, int BlackSoldier2, int RedCar2, int RedHorse2,
			int RedEle2, int RedShi2, int RedKing2, int RedPao2, int RedSoldier2,
			int BlackCar3,
			int BlackHorse3, int BlackEle3, int BlackShi3, int BlackKing3,
			int BlackPao3, int BlackSoldier3, int RedCar3, int RedHorse3,
			int RedEle3, int RedShi3, int RedKing3, int RedPao3, int RedSoldier3){
    	
    	    String chessBoard[][] = new String[10][9];
    	    for(int i = 0; i < 90; i ++){
    	    	 chessBoard[i/9][i%9] = " ";
    	    }

    	    for(int i = 0; i < 26; i ++){
    	    	if(((BlackCar3>>i)&1) == 1) {chessBoard[i/9][i%9] = "c";}
    	    	if(((BlackHorse3>>i)&1) == 1) {chessBoard[i/9][i%9] = "h";}
    	    	if(((BlackEle3>>i)&1) == 1) {chessBoard[i/9][i%9] = "e";}
    	    	if(((BlackShi3>>i)&1) == 1) {chessBoard[i/9][i%9] = "s";}
    	    	if(((BlackKing3>>i)&1) == 1) {chessBoard[i/9][i%9] = "k";}
    	    	if(((BlackPao3>>i)&1) == 1) {chessBoard[i/9][i%9] = "p";}
    	    	if(((BlackSoldier3>>i)&1) == 1) {chessBoard[i/9][i%9] = "d";}
    	    	if(((RedCar3>>i)&1) == 1) {chessBoard[i/9][i%9] = "C";}
    	    	if(((RedHorse3>>i)&1) == 1) {chessBoard[i/9][i%9] = "H";}
    	    	if(((RedEle3>>i)&1) == 1) {chessBoard[i/9][i%9] = "E";}
    	    	if(((RedShi3>>i)&1) == 1) {chessBoard[i/9][i%9] = "S";}
    	    	if(((RedKing3>>i)&1) == 1) {chessBoard[i/9][i%9] = "K";}
    	    	if(((RedPao3>>i)&1) == 1) {chessBoard[i/9][i%9] = "P";}
    	    	if(((RedSoldier3>>i)&1) == 1) {chessBoard[i/9][i%9] = "D";}
 
    	    }
    	    for(int i = 0; i < 32; i ++){
       	    	
    	    	if(((BlackCar2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "c";}
    	    	if(((BlackHorse2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "h";}
    	    	if(((BlackEle2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "e";}
    	    	if(((BlackShi2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "s";}
    	    	if(((BlackKing2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "k";}
    	    	if(((BlackPao2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "p";}
    	    	if(((BlackSoldier2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "d";}
    	    	if(((RedCar2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "C";}
    	    	if(((RedHorse2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "H";}
    	    	if(((RedEle2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "E";}
    	    	if(((RedShi2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "S";}
    	    	if(((RedKing2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "K";}
    	    	if(((RedPao2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "P";}
    	    	if(((RedSoldier2>>i)&1) == 1) {chessBoard[(i+26)/9][(i+26)%9] = "D";}
    	    	
    	    	if(((BlackCar1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "c";}
    	    	if(((BlackHorse1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "h";}
    	    	if(((BlackEle1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "e";}
    	    	if(((BlackShi1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "s";}
    	    	if(((BlackKing1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "k";}
    	    	if(((BlackPao1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "p";}
    	    	if(((BlackSoldier1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "d";}
    	    	if(((RedCar1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "C";}
    	    	if(((RedHorse1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "H";}
    	    	if(((RedEle1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "E";}
    	    	if(((RedShi1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "S";}
    	    	if(((RedKing1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "K";}
    	    	if(((RedPao1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "P";}
    	    	if(((RedSoldier1>>i)&1) == 1) {chessBoard[(i+58)/9][(i+58)%9] = "D";}
    	    }
    	    
    	    for(int k = 0; k < 10; k ++){
    	    	System.out.println(Arrays.toString(chessBoard[k]));
    	    }
    }
    





}
