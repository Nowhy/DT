package UI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class ChessPiece extends JLabel{
	 String name;
	    Color backColor = null, foreColor;
	    String type = null;  
	    ChessBoard board = null;
	    int width, height;
	  
	    public ChessPiece(String name, Color fc, Color bc, int width, int height,  
	            ChessBoard board) {
	        this.name = name;  
	        this.board = board;  
	        this.width = width;  
	        this.height = height;  
	        foreColor = fc;  
	        backColor = bc;  
	        setSize(width, height);  
	        setBackground(bc);  
	        addMouseMotionListener(board);  
	        addMouseListener(board);  
	    }  
	    
	    
	    public void paint(Graphics g) {
	    	if(this == board.getMoveChess()){
	    		 g.drawImage(board.pieceImg, 3, 3, width-1, height-1, null);  
	             g.setColor(foreColor);  
                 g.setFont(new Font("楷体", Font.BOLD, 26));  
	             g.drawString(name, 7, height - 8);  
                 g.setColor(Color.blue);  
                 float lineWidthClicked = 3.9f;  
	             ((Graphics2D)g).setStroke(new BasicStroke(lineWidthClicked));  
	             ((Graphics2D)g).drawOval(2, 2, width-2, height-2); 
	        }
	    	else{
	    		 g.drawImage(board.pieceImg, 3, 3, width, height, null);  
	             g.setColor(foreColor);  
                 g.setFont(new Font("楷体", Font.BOLD, 26));  
	             g.drawString(name, 7, height - 8);  
                 g.setColor(Color.black);  
	             float lineWidth = 2.3f;  
	             ((Graphics2D)g).setStroke(new BasicStroke(lineWidth));  
	             ((Graphics2D)g).drawOval(2, 2, width-2, height-2); 
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
	        return foreColor;  
	    }  
	  
	    public void setPieceType(String color) {  
	        type = color;  
	    }  
	  
	    public String getType() {  
	        return type;  
	    } 

	    
	} 
