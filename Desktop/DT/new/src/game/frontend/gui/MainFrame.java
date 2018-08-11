package game.frontend.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

//import org.newdawn.easyogg.OggClip;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private OggClip ogg;

	public MainFrame() throws IOException  {
		
		
		setBounds(0, 0, 1024, 580);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		// 
		BufferedImage icon = ImageIO.read(new File("resources/images/icon.png"));
		setIconImage(icon);
		
		// Seteo que no se pueda maximizar
		setResizable(false);
		
		// Seteo el fondo de la ventana
		BufferedImage myImage = ImageIO.read(new File("resources/images/background_intro.png"));
		setContentPane(new ImagePanel(myImage));
		
		PlayButton playButton = new PlayButton (this);
		add(playButton);
		
		
		// Seteo el sonido de fondo
		try
		{
	//		setOgg(new OggClip("sounds/menu.ogg"));
	//		getOgg().loop();
			
		}catch( IllegalArgumentException ex){
			System.err.println(ex.getMessage());
		}
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void play() throws IOException{
		LevelsFrame levelsFrame = new LevelsFrame(this);
		levelsFrame.setVisible(true);
		
		levelsFrame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				setVisible(true);
			}
		});
	}

	//public OggClip getOgg() {
	//	return ogg;
	//}

//	private void setOgg(OggClip ogg) {
//		this.ogg = ogg;
//	}
}

