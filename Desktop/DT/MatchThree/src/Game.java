import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Time;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Game extends JFrame implements ActionListener, MouseListener {
	
	private Board board;
	int i = 0;
	private GUI gui;
	private JPanel northPanel;
	private JLabel scoreLabel, targetLabel, limitStep;
	int target = 6000;
	int stepNUM = 10;
	
	Game() throws IOException {
		
		super("Match Three Game"); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setResizable(false);
		this.getContentPane().setLayout(new BorderLayout());
		this.board = new Board(this);
		this.gui = new GUI(this, board);
		this.gui.setBorder(new EmptyBorder(10,10,10,10)); 
		
		scoreLabel = new JLabel("Score: "+ 0);
		scoreLabel.setBorder(new EmptyBorder(3, 3, 3, 30));
		scoreLabel.setFont(new Font("New Times Roman", Font.BOLD, 20));
		scoreLabel.setOpaque(true);
		
		targetLabel = new JLabel("Target: "+ target);
		targetLabel.setBorder(new EmptyBorder(3, 30, 3, 3));
		targetLabel.setFont(new Font("New Times Roman", Font.BOLD, 20));
		targetLabel.setOpaque(true);
		
		limitStep = new JLabel("Step: "+ stepNUM);
		limitStep.setBorder(new EmptyBorder(3, 30, 3, 3));
		limitStep.setFont(new Font("New Times Roman", Font.BOLD, 20));
		limitStep.setOpaque(true);
		
		northPanel = new JPanel();
		northPanel.add(scoreLabel);
		northPanel.add(targetLabel);
		northPanel.add(limitStep);
		northPanel.setBorder(new EmptyBorder(10,10,10,10)); 
		this.getContentPane().add(northPanel,BorderLayout.NORTH);
		
		this.getContentPane().add(gui, BorderLayout.CENTER); 
		this.addMouseListener(this);
		this.setSize(1000,700); 
		this.setVisible(true);
		board.printBoard();

	}

	
	public GUI getBoardGui(){ //getters
		return this.gui;
	}
	
	public Board getBoard(){
		return this.board;
	}
	
	
	public void reDrawBoard(){
//		board.printBoard();
		int score = board.getScore();
		this.getContentPane().remove(gui);
		this.getContentPane().remove(northPanel);
		this.gui = new GUI(this,board);
		this.gui.setBorder(new EmptyBorder(10,10,10,10)); 
		scoreLabel = new JLabel("Score: "+ score);
		scoreLabel.setBorder(new EmptyBorder(3, 3, 3, 30));
		scoreLabel.setFont(new Font("New Times Roman", Font.BOLD, 20));
		scoreLabel.setOpaque(true);
		
		targetLabel = new JLabel("Target: "+ target);
		targetLabel.setBorder(new EmptyBorder(3, 30, 3, 3));
		targetLabel.setFont(new Font("New Times Roman", Font.BOLD, 20));
		targetLabel.setOpaque(true);
		
		limitStep = new JLabel("Step: "+ stepNUM);
		limitStep.setBorder(new EmptyBorder(3, 30, 3, 3));
		limitStep.setFont(new Font("New Times Roman", Font.BOLD, 20));
		limitStep.setOpaque(true);
		
		northPanel = new JPanel();
		northPanel.add(scoreLabel);
		northPanel.add(targetLabel);
		northPanel.add(limitStep);
		northPanel.setBorder(new EmptyBorder(10,10,10,10)); 
		this.getContentPane().add(northPanel,BorderLayout.NORTH);
//		System.out.println(board.getScore());
		this.getContentPane().add(gui,BorderLayout.CENTER);
		this.setVisible(true);
		this.repaint();
//		if( stepNUM  == 0 && score <  target) {
//			JOptionPane.showMessageDialog(null, "Sorry!", "You lose!", JOptionPane.PLAIN_MESSAGE); // 弹出消息框
//			try {
//				board = new Board(new Game());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if(score >= target && stepNUM  >= 0) {
//			JOptionPane.showMessageDialog(null, "Congratulations!", "You win!", JOptionPane.PLAIN_MESSAGE); // 弹出消息框
//			try {
//				board = new Board(new Game());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
			for (int i = 0; i < Board.BOARDSIZE; i = i+1){ //one of boardGui buttons was pressed
				for (int j = 0; j < Board.BOARDSIZE; j = j+1){
					if (e.getSource() == this.gui.getButtons()[i][j]){
						board.move(i,j);
						return;
					}
				}
			}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		i = i+1;
		System.out.println("click      " + i);
		board.printBoard();
		this.repaint();
		this.gui.repaint();		
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		i = i+1;
		System.out.println("press      " + i);
		board.printBoard();
		this.repaint();
		this.gui.repaint();		
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		i = i+1;
		System.out.println("release      " + i);
		board.printBoard();
		this.repaint();
		this.gui.repaint();		
		
	}

	public void upateStep(int j) {
		this.stepNUM = j;
		
	}
}
