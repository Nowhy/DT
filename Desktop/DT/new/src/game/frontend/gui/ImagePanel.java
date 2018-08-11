package game.frontend.gui;
import game.backend.CandyGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

class ImagePanel extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	private LevelsFrame parent;

	public ImagePanel(Image image) {
		this.image = image;
	}
	
	public ImagePanel(Image image, LevelsFrame frame, final CandyGame game){
		this.image = image;
		this.parent = frame;
		
		addMouseListener(new MouseAdapter() { 
	          public void mouseClicked(MouseEvent me) {
	        	  
	        	  parent.setVisible(false);
	        	  parent.createGame(game);
	          }
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}