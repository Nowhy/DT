package UI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class ChessPiece extends JLabel{
	 /**
	 * record each piece information and draw the piece
	 */
	private static final long serialVersionUID = 1L;
		String name;
	    Color pieceColor;
	    ChessBoard board = null;
	    int width, height;
	  
	    public ChessPiece(String name, Color pieceColor, int width, int height,  
	            ChessBoard board) {
	        this.name = name;  
	        this.board = board;  
	        this.width = width;  
	        this.height = height;  
	        this.pieceColor = pieceColor;  
	        setSize(width, height);  
	        addMouseMotionListener(board);  
	        addMouseListener(board);  
	    }  
	    
	    
	    public void paint(Graphics g) {
	    	if(this == board.getMoveChess()){ 
	             g.setColor(pieceColor);  
	             /**
	              * if the piece is clicked ,the circle color would be changed to "BLUE"
	              */
                 g.setFont(new Font("楷体", Font.BOLD, 26));  
	             g.drawString(name, 7, height - 8);  
                 g.setColor(Color.blue);  
                 int lineWidthClicked = 3;  
	             ((Graphics2D)g).setStroke(new BasicStroke(lineWidthClicked));  
	             ((Graphics2D)g).drawOval(1, 1, width-2, height-2); 
	        }
	    	else{
	             g.setColor(pieceColor);  
                 g.setFont(new Font("楷体", Font.BOLD, 26));  
	             g.drawString(name, 7, height - 8);  
                 g.setColor(this.getColor());  
	             float lineWidth = 2.3f;  
	             ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));  
	             ((Graphics2D)g).drawOval(1, 1, width-2, height-2); 
	    	}
	    } 
	  
	    public int getWidth() {  
	        return width;  
	    }  
	  
	    public int getHeight() {  
	        return height;  
	    }  
	  
	    public String getName() {  
	        return name;  
	    }  
	  
	    public Color getColor() {  
	        return pieceColor;  
	    }  

	    
	} 
