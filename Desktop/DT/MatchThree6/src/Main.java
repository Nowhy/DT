import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {
	
	
	public static void main(String args[]) throws IOException { 
		// args: METHOD(1,2,3)  STRATEGY(Score first(1), chocolate first (2) five first (3), four first (4)) Level
		int METHOD = Integer.parseInt(args[0]);
		int STRATEGY = Integer.parseInt(args[1]) ;
		int LEVEL = 0;
		Game game = new Game(METHOD, STRATEGY, LEVEL);
//		simpleAI simple = new simpleAI(game);
//		testAI(game, simple, METHOD, STRATEGY, LEVEL);
	}
		
		public static void testAI(Game game, simpleAI simple, int METHOD, int STRATEGY, int LEVEL) {
			long startTime = System.currentTimeMillis(); 
			while(game.getBoard().STEP  > 0) {
				System.out.println(game.getBoard().STEP +"      ffffffffffff    " + game.getBoard().getScore());
				simple.play();
				game.upateStep(game.getBoard().STEP-1);
			}
			long endTime = System.currentTimeMillis(); 
			long running = endTime - startTime;
			if( game.getBoard().STEP  == 0 && game.getBoard().getScore() <  game.getBoard().TARGET) {
//				game.reDrawBoard();
				int result = JOptionPane.showConfirmDialog(null, "Sorry! You Lose the Game. Your Score is " + game.getBoard().getScore() +". And time you spent is " + running +" ms\n"+ " Would you like to continue this level?", "YES", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE); 
				if(result != JOptionPane.OK_OPTION){
				    System.exit(0);
				}else {
					try {
						System.exit(0);
						game = new Game(METHOD, STRATEGY, LEVEL);
						simple = new simpleAI(game);
						testAI(game,simple, METHOD, STRATEGY, LEVEL);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(game.getBoard().STEP  == 0 && game.getBoard().getScore() >=  game.getBoard().TARGET) {
				int result = JOptionPane.showConfirmDialog(null, "Congratulations! Your Score is " + game.getBoard().getScore() +". And time you spent is "+running+" ms\n"+ "Continue the next Level " + (LEVEL+2) + "?" , "YES", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE); 
				if(result != JOptionPane.OK_OPTION){
				    System.exit(0);
				}else {
					try {
							game = new Game(METHOD, STRATEGY, LEVEL+1);
							simple = new simpleAI(game);
							testAI(game,simple, METHOD, STRATEGY, LEVEL+1);
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
	}

}
