import java.awt.*;
import javax.swing.*;

public class GUI extends JPanel {
	private Game game;
	private Board board;
	private JButton[][] buttons;

	public GUI(Game game, Board board){ //constructor
		super(new GridLayout(board.BOARDSIZE, board.BOARDSIZE));
		this.board = board;
		this.game = game;
		buttons = new JButton[board.BOARDSIZE][board.BOARDSIZE];	
		
		for (int i = 0; i < board.BOARDSIZE; i = i+1){ 
			for (int j = 0; j < board.BOARDSIZE; j = j+1){
				JButton button = new JButton();
				Candy candy = this.board.getCandies()[i][j];
				if(candy instanceof Block) {
					button.setIcon(new ImageIcon("images//nothing.png"));
				}
				else if (candy instanceof RegularCandy){
					if (candy.getcolour()==0) button.setIcon(new ImageIcon("images//yellowCandy.png"));
					else if (candy.getcolour()==1) button.setIcon(new ImageIcon("images//greenCandy.png"));
					else if (candy.getcolour()==2) button.setIcon(new ImageIcon("images//purpleCandy.png"));
					else if (candy.getcolour()==3) button.setIcon(new ImageIcon("images//redCandy.png"));
					else if (candy.getcolour()==4) button.setIcon(new ImageIcon("images//orangeCandy.png"));
					else if (candy.getcolour()==5) button.setIcon(new ImageIcon("images//blueCandy.png"));
				
				}else if(candy instanceof YCandy) {
					if (candy.getcolour()==0) button.setIcon(new ImageIcon("images//yellowHStripped.png"));
					else if (candy.getcolour()==1) button.setIcon(new ImageIcon("images//greenHStripped.png"));
					else if (candy.getcolour()==2) button.setIcon(new ImageIcon("images//purpleHStripped.png"));
					else if (candy.getcolour()==3) button.setIcon(new ImageIcon("images//redHStripped.png"));
					else if (candy.getcolour()==4) button.setIcon(new ImageIcon("images//orangeHStripped.png"));
					else if (candy.getcolour()==5) button.setIcon(new ImageIcon("images//blueHStripped.png"));
					
				}else if(candy instanceof XCandy) {
					if (candy.getcolour()==0) button.setIcon(new ImageIcon("images//yellowVStripped.png"));
					else if (candy.getcolour()==1) button.setIcon(new ImageIcon("images//greenVStripped.png"));
					else if (candy.getcolour()==2) button.setIcon(new ImageIcon("images//purpleVStripped.png"));
					else if (candy.getcolour()==3) button.setIcon(new ImageIcon("images//redVStripped.png"));
					else if (candy.getcolour()==4) button.setIcon(new ImageIcon("images//orangeVStripped.png"));
					else if (candy.getcolour()==5) button.setIcon(new ImageIcon("images//blueVStripped.png"));
					
				}else if(candy instanceof BombCandy) {
					if (candy.getcolour()==0) button.setIcon(new ImageIcon("images//yellowWrapped.png"));
					else if (candy.getcolour()==1) button.setIcon(new ImageIcon("images//greenWrapped.png"));
					else if (candy.getcolour()==2) button.setIcon(new ImageIcon("images//purpleWrapped.png"));
					else if (candy.getcolour()==3) button.setIcon(new ImageIcon("images//redWrapped.png"));
					else if (candy.getcolour()==4) button.setIcon(new ImageIcon("images//orangeWrapped.png"));
					else if (candy.getcolour()==5) button.setIcon(new ImageIcon("images//blueWrapped.png"));
					
				}else if(candy instanceof ChocolateCandy) {
					button.setIcon(new ImageIcon("images//bomb.png"));
				}
				button.setOpaque(true);
				button.setHorizontalAlignment(getWidth()/2);
				button.setVerticalAlignment(getHeight()/2); 
				button.addActionListener(game);
				this.add(button);
				buttons[i][j]=button;
			}
		}
	}
	
	
	public Game getGame(){
		return this.game;
	}
	
	public Board getBoard(){
		return this.board;
	}
	public JButton[][] getButtons(){
		return this.buttons;
	}
	
}
	