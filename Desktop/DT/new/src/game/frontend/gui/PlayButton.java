package game.frontend.gui;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class PlayButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PlayButton(final MainFrame main){
		setIcon(new ImageIcon("resources/images/playButton.png"));
		setBounds(385, 260, 278, 111);
		setBorderPainted(false);
		
		addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
		            try {
		            	main.setVisible(false);
						main.play();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}   
		        });
	}
}
