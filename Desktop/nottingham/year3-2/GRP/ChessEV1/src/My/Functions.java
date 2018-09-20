package My;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.acerge.engine.*;

public class Functions{
	
	private ArrayList<String> moveStrList=new ArrayList<String>();
	
	private SearchEngine searchEngine = new SearchEngine();
	private SortedMoveNodes AllMoves = new SortedMoveNodes();
	private Format format = new Format();
	
	private int[][] HisTable = new int[90][90];
	
	private ActiveBoard newBoard;


	public String move(String move, ActiveBoard activeBoard){
		String result = "Invalid Move!";
		AllMoves.GenMoves(activeBoard, HisTable);
		int moveNum = AllMoves.MoveNum;
		
		int startX,startY,endX,endY;
		try{
			char[] coor = move.toCharArray();
			startX = format.getLocByCoor(coor[0]);
			startY = format.getLocByCoor(coor[1]);
			endX = format.getLocByCoor(coor[2]);
			endY = format.getLocByCoor(coor[3]);
		}catch(Exception e){
			return "Invalid Input!";
		}
		int validStart,validEnd,myStart,myEnd;
		myStart = startX * 10 + startY - 10;
		myEnd = endX * 10 + endY - 10;
		

		for (int i = 0; i < moveNum; i++) {
			validStart = AllMoves.MoveList[i].src;
			validEnd = AllMoves.MoveList[i].dst;
			if (myStart == validStart && myEnd == validEnd) {
				(activeBoard.getPieceBits(format.getTypeByCoor(activeBoard,startX,startY))).getLeftShift(0);				
				if (activeBoard.movePiece(new MoveNode(myStart, myEnd))) {
					result = move;
					moveStrList.add(result);
					newBoard = activeBoard;
				}			
				break;
			}
		}
		return result;
	}

	public String checkMove(String input, ActiveBoard activeBoard){
		String result = "";
		result = move(input, activeBoard);
		if(!result.equals("Invalid Move!") && newBoard != null){
			activeBoard = newBoard;
			result = search(activeBoard);
			undo(activeBoard);
		}
		return result;
	}
/*
	public String checkMove(String input, ActiveBoard activeBoard){
		String result = "";
		MoveNode[] move = null;
		MoveNode temp;
		move(input, activeBoard);
		
		for(int i = 0; i < 3; i++){
			result = result + (i+1) + ":"; 
			searchEngine.setStyle(i);
			searchEngine.setActiveBoard(activeBoard);
			
			try {
				move = searchEngine.getBestMoveTrack();
			} catch (LostException e) {
				e.printStackTrace();
			}
			for(int j = 0; j < 8; j++){
				temp = move[i];
				result = result + " " + format.getCoorByLoction(temp.src) + format.getCoorByLoction(temp.dst);
			}	
			result = result + "\n";
		}
		
		undo(activeBoard);
		searchEngine.setStyle(1);
		return result;
	}
	
	public String checkPiece(String input, ActiveBoard activeBoard){
		String result = "";
		String move = "" + input;
	
		int type = format.getTypeByCoor(activeBoard, format.getLocByCoor(input.charAt(0)), format.getLocByCoor(input.charAt(1)));
		int piece = format.getIndexByCoor(activeBoard, format.getLocByCoor(input.charAt(0)), format.getLocByCoor(input.charAt(1)));
		switch(type){
		case 0:
			AllMoves.GenKingMoves(activeBoard, HisTable);
			break;
		case 1:
			AllMoves.GenAdvisorMoves(activeBoard, HisTable);
			break;
		case 2:
			AllMoves.GenBishopMoves(activeBoard, HisTable);
			break;
		case 3:
			AllMoves.GenKnightMoves(activeBoard, HisTable);
			break;
		case 4:
			AllMoves.GenRookMoves(activeBoard, HisTable);
			break;
		case 5:
			AllMoves.GenCannonMoves(activeBoard, HisTable);
			break;
		case 6:
			AllMoves.GenPawnMoves(activeBoard, HisTable);
			break;
		}
		int moveNum = AllMoves.MoveNum;
		for (int i = 0; i < moveNum; i++) {
			activeBoard.movePiece(AllMoves.MoveList[i]);
			move = move + format.getCoorByLoction(activeBoard.getPieces(piece));
			result = "" + i + ": " + move + search(activeBoard) + "\n";
			activeBoard.undoMove();
		}
		
		return result;
	}
*/	
	public String show(){
		return "";
	}
	
	public String searchHelper(ActiveBoard activeBoard){
		String result = "Don't find a valid move!";
		searchEngine.setActiveBoard(activeBoard);
		MoveNode bestMove=null;
		try {
			bestMove = searchEngine.getBestMove();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (bestMove != null) {
			result =  "" + format.getCoorByLoction(bestMove.src) + format.getCoorByLoction(bestMove.dst);
//			move(result,activeBoard);
		}
		return result;
	}
	
	public String search(ActiveBoard activeBoard){
		String result = "";
		int index = 1;
		String hint = "";
		MoveNode move = null;
		
		for(int i = 0; i < 10; i++){ 
			searchEngine.setActiveBoard(activeBoard);
			searchEngine.setStyle((int)Math.random()*3);
			searchEngine.setupControl(i+1, 60000, 120000);
			try {
				move = searchEngine.getBestMove();
			} catch (LostException e) {
				e.printStackTrace();
			}
			if(move!=null){
				hint = format.getCoorByLoction(move.src) + format.getCoorByLoction(move.dst);
				if(!result.contains(hint)){
					result = result + index + ": " + hint  + "\n";
					index++;
				}
			}else{
				System.out.println("Null" + i);
			}
		}
		searchEngine.setStyle(1);
		searchEngine.setupControl(8, 60000, 1200000);
		return result;
	}
	
	
	public String undo(ActiveBoard activeBoard){
		String output = "";
		int oldStart = activeBoard.lastMove().src;
		int oldEnd = activeBoard.lastMove().dst;
		if (oldStart < 0 || oldEnd < 0) {
			output = "End of undo!";
			return output;
		}
		activeBoard.undoMove();
		if(moveStrList.size()>0){
			moveStrList.remove(moveStrList.size()-1);
		}
		output = "Undo Succeed!";
		return output;
	}

	public ActiveBoard newGame(){
		ActiveBoard activeBoard = new ActiveBoard();
		activeBoard.loadFen("rnbakabnr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RNBAKABNR w - - 0 1");
		
		moveStrList = new ArrayList<String>();
		
		searchEngine = new SearchEngine();
		searchEngine.clearHash();
		searchEngine.clearHistTab();
		try {
			searchEngine.loadBook("./data/book.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return activeBoard;
	}
	

	public String fail(ActiveBoard activeBoard) {
		String result = "";
		AllMoves.GenMoves(activeBoard, HisTable);
		for (int i = 0; i < AllMoves.MoveNum; i++) {
			MoveNode tmp = AllMoves.MoveList[i];
			if (activeBoard.movePiece(tmp)) {
				activeBoard.undoMove();
				return "No";
			}
		}
		if (activeBoard.getPlayer() == 0){
			result = "Black win!";
		}else{
			result = "Red win!";
		}
		return result;
	}
	
}