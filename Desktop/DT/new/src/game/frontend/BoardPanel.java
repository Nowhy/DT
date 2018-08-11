package game.frontend;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private int cellSize;
	private int rows, columns;
	private Image[][] cells;
	
	public BoardPanel(final int rows, final int columns, final int cellSize) {
		this.rows = rows;
		this.columns = columns;
		this.cellSize = cellSize;
		this.cells = new Image[rows][columns];

		setSize(columns * cellSize + 1, rows * cellSize + 1);
		//setBackground(Color.DARK_GRAY);
		
	}

	public void clearImage(int row, int column) {
		cells[row][column] = null;
	}
	
	public void setImage(int row, int column, Image image) {
		cells[row][column] = new BufferedImage(cellSize, cellSize, BufferedImage.TYPE_INT_ARGB);
		cells[row][column].getGraphics().drawImage(image, 0, 0, null);
		repaint();
	}
	
	public void appendImage(int row, int column, Image image) {
		if (cells[row][column] == null) {
			cells[row][column] = new BufferedImage(cellSize, cellSize, BufferedImage.TYPE_INT_ARGB);
		}
		cells[row][column].getGraphics().drawImage(image, 0, 0, null);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		ImageIcon icon = new ImageIcon("resources/images/background_level.png");
		Image background = icon.getImage();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(background, 10, 10, null);
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (cells[i][j] != null) {
					g.drawImage(cells[i][j], j * cellSize, i * cellSize, null);
				}
			}
		}
	}
	
}