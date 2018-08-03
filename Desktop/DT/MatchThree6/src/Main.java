import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {
	
	
	public static void main(String args[]) throws IOException { 
		// args: METHOD(1,2,3) BOARDSIZE(5..20) NUMSTEPS STRATEGY(Score first(1), chocolate first (2) five first (3), four first (4))
		int METHOD = Integer.parseInt(args[0]);
		int BOARDSIZE = Integer.parseInt(args[1]);
		int NUMSTEP = Integer.parseInt(args[2]) ;
		int STRATEGY = Integer.parseInt(args[3]) ;
		int TARGET = NUMSTEP*600 ;
		Game game = new Game(METHOD, BOARDSIZE, NUMSTEP, TARGET, STRATEGY);
		simpleAI simple = new simpleAI(game);
		testAI(game, simple, METHOD, BOARDSIZE, NUMSTEP, TARGET, STRATEGY);
	}
		
		public static void testAI(Game game, simpleAI simple, int METHOD, int BOARDSIZE, int NUMSTEP, int TARGET, int STRATEGY) {
			while(game.NUMSTEP  > 0 && game.getBoard().getScore() <  TARGET) {
				System.out.println(game.NUMSTEP +"      ffffffffffff    " + game.getBoard().getScore());
				simple.play();
				game.upateStep(game.NUMSTEP-1);
			}
			if( game.NUMSTEP  == 0 && game.getBoard().getScore() <  TARGET) {
				game.reDrawBoard();
				int result = JOptionPane.showConfirmDialog(null, "Sorry! You Lose the Game. Would you like to continue the game?", "YES", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE); 
				if(result != JOptionPane.OK_OPTION){
				    System.exit(0);
				}else {
					try {
						game = new Game(METHOD, BOARDSIZE, NUMSTEP, TARGET, STRATEGY);
						simple = new simpleAI(game);
						testAI(game,simple, METHOD, BOARDSIZE, NUMSTEP, TARGET, STRATEGY);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(game.getBoard().getScore() >= TARGET && game.NUMSTEP   >= 0) {
				int result = JOptionPane.showConfirmDialog(null, "Congratulations! Continue the game?", "YES", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE); 
				if(result != JOptionPane.OK_OPTION){
				    System.exit(0);
				}else {
					try {
						game = new Game(METHOD, BOARDSIZE,NUMSTEP, TARGET, STRATEGY);
						simple = new simpleAI(game);
						testAI(game,simple, METHOD, BOARDSIZE, NUMSTEP, TARGET, STRATEGY);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
	}

}
