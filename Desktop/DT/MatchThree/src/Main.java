import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String args[]) throws IOException { 
		Game game = new Game();
		simpleAI simple = new simpleAI(game);
		testAI(game, simple);
	}
		
		public static void testAI(Game game, simpleAI simple) {
			while(game.stepNUM  >= 0 && game.getBoard().getScore() <  game.target) {
				System.out.println(game.stepNUM +"      ffffffffffff    " + game.getBoard().getScore());
				simple.play();
				game.upateStep(game.stepNUM-1);
			}
			if( game.stepNUM  == 0 && game.getBoard().getScore() <  game.target) {
				JOptionPane.showMessageDialog(null, "Sorry!", "You lose!", JOptionPane.PLAIN_MESSAGE); // 弹出消息框
				try {
					game = new Game();
					simple = new simpleAI(game);
					testAI(game,simple);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(game.getBoard().getScore() >= game.target && game.stepNUM   >= 0) {
				int result = JOptionPane.showConfirmDialog(null, "Congratulations! Continue the game?", "YES", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE); 
				if(result != JOptionPane.OK_OPTION){
				    System.exit(0);
				}else {
					try {
						game = new Game();
						simple = new simpleAI(game);
						testAI(game,simple);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
	}
	
	// board is fixed... but the fill is random finish...
	// bomb   finish...
	// two bombs together... finish...
	
	
	// !!!!! chocolate .... I forget that aaaaaaaaa!
	
	
	// score (including History) 
	// target: for certain steps and get certain target....  finish...
	// simple AI: 1. choose the best score finish....
	// 			  2. specify candy (5,4)
	//            3. bottom candy
	
	
	// if there is no valid moves....
}
