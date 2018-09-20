package My;

import java.util.Scanner;

import org.acerge.engine.*;

public class IOView{
	private ActiveBoard activeBoard;
	private Functions function;
	private Format format;
	private Scanner reader = new Scanner(System.in);//input reader
	private String input = "";//user input

	
	public IOView(){
		function = new Functions();
		activeBoard = function.newGame();
		format = new Format();
	}
	
	public void start(){
		
		printCommands();
		printChessBoard();
		userMove();
		String[] command = input.split(" ");
		String output = "";
		
		while(!command[0].equals("Quit")){
			switch(command[0]){
			case "Move":
				output = function.move(command[1], activeBoard);
				break;
			case "Check":
				output = function.checkMove(command[1], activeBoard);
				break;
			case "Show":
				output = function.show();
				break;
			case "Search":
				output = function.search(activeBoard);
				break;
			case "Undo":
				output = function.undo(activeBoard);
				break;
			case "NewGame":
				activeBoard = function.newGame();
				output = "New Game";
				break;
			default:
				output = "Invalid Input!";
				break;
			}			
			System.out.println(output);
			if(command[0].equals("Move")){
				printChessBoard();
			}
			output = function.fail(activeBoard);
			if(!output.equals("No")){
				System.out.println(output);
				break;
			}
			
			userMove();
			command = input.split(" ");
		}
		System.out.println("Quiting....");	
		
	}
	
	public void userMove(){
		System.out.println("Choose your Operation:");
		input = reader.nextLine();
	}
	
	public void printChessBoard(){
		int board;
		int step = activeBoard.getMoveNum();
		int player = activeBoard.getPlayer();
		String playerString;
		if(player == 1)
			playerString = "Red";
		else 
			playerString = "Black";
		System.out.println("Player: " + playerString + "\tStep: " + step);
		System.out.println("   ------------------------------"
				+ "-----------------------------------------------");
		for(int y = 0; y < 10; y++){
			System.out.print(y);
			for(int x = y; x < 90; x=x+10){
				board = activeBoard.getSquares(x);
				System.out.print("\t" + format.getNameByIndex(board));
			}
			System.out.print("\n");
			if(y==4){
				System.out.println();
			}
		}
		System.out.println("   ---------------------------------"
				+ "--------------------------------------------");
		System.out.println("   \tA\tB\tC\tD\tE\tF\tG\tH\tI\t");
//		temp();
//		temp2();
	}
	
	public void printCommands(){
		System.out.println(
				"\"Move xyxy\" for make a movement\n" +
				"\"Check xyxy\" for checking the 3 situations after moving this piece\n" +
//				"\"Check xy\" for checking 3 best moves of this piece\n" +
				"\"Search\" for searching the best movement\n" +
				"\"Show\" for show the movements of one posible situation\n" + 
				"\"Undo\" for canceling\n" +
				"\"NewGame\" for opening a new game\n" +
				"\"Quit\" for quitting\n");
	}
	
	public void temp(){
		int board;
		System.out.println("   ------------------------------"
				+ "-----------------------------------------------");
		for(int i = 0; i < 90; i++){
			if(i%9 == 0){
				System.out.print(i/9);
			}
			board = activeBoard.getSquares(i);
			System.out.print("\t" + format.getNameByIndex(board));
			if(i%9 == 8){
				System.out.println();
			}
		}
		System.out.println("   ---------------------------------"
				+ "--------------------------------------------");
		System.out.println("   \t A\t B\t C\t D\t E\t F\t G\t H\t I\t");
	}
	
	public void temp2(){
		System.out.println(activeBoard.getPieceBits(13));
	}

}