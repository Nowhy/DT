package game.frontend.gui;

import game.backend.CandyGame;
import game.backend.level.ClassicLevel;
import game.backend.level.CreamLevel;
import game.backend.level.JellyLevel;
import game.backend.level.VoidLevel;
import game.frontend.CandyFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class LevelsFrame extends JFrame {
	
	private MainFrame parent;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	LevelsFrame(MainFrame main) throws IOException {
		this.setParent(main);
		// Seteo la dimensi�n y posici�n de la ventana
		setBounds(0, 0, 1024, 580);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		// Seteo el icono del frame
		BufferedImage icon = ImageIO.read(new File("resources/images/icon.png"));
		setIconImage(icon);

		// Seteo que no se pueda maximizar
		setResizable(false);

		// Seteo el fondo de la ventana
		BufferedImage myImage = ImageIO.read(new File("resources/images/background_map.png"));
		setContentPane(new ImagePanel(myImage));
		
		// Seteo boton level 1
		BufferedImage imageOne = ImageIO.read(new File("resources/images/one.png"));
		ImagePanel panelOne = new ImagePanel(imageOne, this, new CandyGame(ClassicLevel.class));
		panelOne.setBounds(115, 395, 35, 35);
		
		add(panelOne);
		
		
		// Seteo boton level 2
		BufferedImage imageTwo = ImageIO.read(new File("resources/images/two.png"));
		ImagePanel panelTwo = new ImagePanel(imageTwo, this, new CandyGame(VoidLevel.class));
		panelTwo.setBounds(395, 400, 35, 35);
		add(panelTwo);
		
		// Seteo boton level 3
		BufferedImage imageThree = ImageIO.read(new File("resources/images/three.png"));
		ImagePanel panelThree = new ImagePanel(imageThree, this, new CandyGame(JellyLevel.class));
		panelThree.setBounds(680, 405, 35, 35);
		add(panelThree);
		
		// Seteo boton level 4
		BufferedImage imageFour = ImageIO.read(new File("resources/images/four.png"));
		ImagePanel panelFour = new ImagePanel(imageFour, this, new CandyGame(CreamLevel.class));
		panelFour.setBounds(915, 320, 35, 35);
		add(panelFour);

		// Seteo boton level 5
		BufferedImage imageFive = ImageIO.read(new File("resources/images/five.png"));
		ImagePanel panelFive = new ImagePanel(imageFive);
		panelFive.setBounds(895, 115, 35, 35);
		add(panelFive);
		
		panelFive.addMouseListener(new MouseAdapter() { 
	          public void mouseClicked(MouseEvent me) {
	        	  System.out.println("Click en 5");  
	          }
		});
	}


	public void createGame(CandyGame game){
		
//		getParent().getOgg().stop();
		
		CandyFrame frame;
		try {
			frame = new CandyFrame(game);
			frame.setVisible(true);
			
			frame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				setVisible(true);
//				getParent().getOgg().play();
				}
			});
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public MainFrame getParent() {
		return parent;
	}


	private void setParent(MainFrame parent) {
		this.parent = parent;
	}
}
