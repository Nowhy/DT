import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {
	
	
	public static void main(String args[]) throws IOException { 
		// args: Human or AI 
		// if AI: enter X Y
		// Y: STRATEGY(Score first(1), chocolate first (2) five first (3), four first (4)) Level
		int STRATEGY = Integer.parseInt(args[0]) ;
		int LEVEL = 0;
		Game game = new Game(STRATEGY, LEVEL);
		
		while(LEVEL<=9) {
			int m = 0;
			simpleAI simple = new simpleAI(game);
			testAI(game, simple, STRATEGY, LEVEL);
			for(int i = 0; i < 1000; i++) {
				System.out.println(game.getBoard().getScore());
				if(game.getBoard().getScore()>=game.getBoard().TARGET) {
					m++;
				}
				game = new Game(STRATEGY, LEVEL);
				simple = new simpleAI(game);
				testAI(game, simple, STRATEGY, LEVEL);
			}
			LEVEL++;
			System.out.println(LEVEL + "    " + m);
		}
	}
		
		public static void testAI(Game game, simpleAI simple, int STRATEGY, int LEVEL) {
			long startTime = System.currentTimeMillis(); 
			while(game.getBoard().STEP  > 0) {
//				simple.play();
				game.upateStep(game.getBoard().STEP-1);
			}
			long endTime = System.nanoTime(); 
			float running = (endTime - startTime)/1000000;//hao miao
			game.writeFile("LEVEL "+ LEVEL + " FINISH!" + "\r\nTIME: "+ endTime + "\r\n");
			game.writeFile("Score "+ game.getBoard().getScore() + " " + "    RUNNING TIME: "+ running+" ms\r\n\r\n");
	}

}
