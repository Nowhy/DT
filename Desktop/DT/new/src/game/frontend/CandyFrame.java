package game.frontend;

import game.backend.CandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.cell.CreamCell;
import game.backend.cell.JellyCell;
import game.backend.element.Element;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

//import org.newdawn.easyogg.OggClip;

public class CandyFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int CELL_SIZE = 65;
	private static final int SCORE_HEIGHT = 80;

	private BoardPanel bp;
	private ScorePanel sp;
	private ImageManager images;
	private GameListener listener;
	private Point lastPoint;
	private CandyGame game;

//	private OggClip ogg;
	
	public CandyFrame(CandyGame game) throws IOException {
		this.game = game;
		
		images = new ImageManager();

		setLayout(null);
		setResizable(false);
		setSize(game.getSize() * CELL_SIZE + 20, game.getSize() * CELL_SIZE + SCORE_HEIGHT);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		// Seteo el icono
		BufferedImage icon = ImageIO.read(new File("resources/images/icon.png"));
		setIconImage(icon);
		
		// Seteo el sonido de fondo
		try
		{
//			setOgg(new OggClip("sounds/level.ogg"));
//			getOgg().loop();
		}catch(IllegalArgumentException ex){
			System.err.println(ex.getMessage());
		}

		
		bp = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		add(bp);

		sp = new ScorePanel(bp.getWidth(), SCORE_HEIGHT);
		sp.setLocation(1, bp.getHeight());
		add(sp);

		game.initGame();
		game.addGameListener(listener = new GameListener() {
			@Override
			public void gridUpdated() {
				try {
					for (int i = 0; i < game().getSize(); i++) {
						for (int j = 0; j < game().getSize(); j++) {
							setImageCell(i, j, false);
						}
					}
					bp.paintImmediately(bp.getBounds());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}

			@Override
			public void cellExplosion(Element e) {
			}
		});

		listener.gridUpdated();

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if (lastPoint == null) {
					lastPoint = translateCoords(event.getX(), event.getY());
					System.out.println("Get first = " + lastPoint);
				} else {
					Point newPoint = translateCoords(event.getX(), event.getY());
					if (newPoint != null) {
						System.out.println("Get second = " + newPoint);
						game().tryMove(lastPoint.x, lastPoint.y, newPoint.x,
								newPoint.y);

						String message = ((Long) game().getScore()).toString();
						if (game().isFinished()) {
							if (game().playerWon()) {
								message = message + " Finished - Player Won!";
							} else {
								message = message + " Finished - Loser !";
							}
						}
						sp.updateScore(message);
						lastPoint = null;
					}
				}
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if (event.getKeyChar() == 'h') {
					Point[] points = game().hint();
					if (points != null && points.length > 0) {
						for (Point p : points)
							changeImage(p.y, p.x);
					}
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
//				ogg.stop();
			}
		});
	}

	// Nuevo
	private void setImageCell(int i, int j, boolean hint) {
		try {
			Cell cell = CandyFrame.this.game.get(i, j);
			Element element = cell.getContent();
			
			bp.clearImage(i, j);
			
			Image image;
			if (hint)
				image = images.getImageHint(cell.getColor());
			else
				image = images.getImage(element);
			
			Image nothing= ImageIO.read(new File("resources/images/nothing.png"));
			//bp.setImage(i, j, nothing);
			
			bp.appendImage(i, j, nothing);
			
			if (cell instanceof JellyCell ) {
				JellyCell jc = (JellyCell) cell;
				Image jelly = ImageIO.read(new File("resources/images/jelly.png"));
				if (jc.isActive())
					bp.setImage(i, j, jelly);
			}
			
			bp.appendImage(i, j, image);
			
			if (cell instanceof CreamCell ) {
				CreamCell cc = (CreamCell) cell;
				Image cream = ImageIO.read(new File("resources/images/block.png"));
				if (cc.isActive())
					//bp.setImage(i, j, cream);
					bp.appendImage(i, j, cream);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void changeImage(int x, int y) {
		try {
			setImageCell(x, y, true);
			bp.paintImmediately(bp.getBounds());
		} catch (Exception e) {
		}
	}

	private CandyGame game() {
		return game;
	}

	private Point translateCoords(int x, int y) {
		int i = x / CELL_SIZE;
		int j = y / CELL_SIZE;
		return (i >= 0 && i < game.getSize() && j >= 0 && j < game.getSize()) ? new Point(
				j, i) : null;
	}

//	private OggClip getOgg() {
//		return ogg;
//	}

//	private void setOgg(OggClip ogg) {
//		this.ogg = ogg;
//	}
}
