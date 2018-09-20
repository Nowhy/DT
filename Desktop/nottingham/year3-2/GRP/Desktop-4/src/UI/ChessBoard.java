package UI;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import engine.*;

public class ChessBoard extends JPanel implements MouseListener,  
        MouseMotionListener {  
    public int unitWidth, unitHeight;  
    private int xAxis, yAxis,x,y;  
    public ChessPoint point[][];
    Rule rule;
    private Image img;  
    protected Image pieceImg; 
    public int clickedTime = 0;
    public String RedPiece = "Red", BlackPiece = "Black"; 
    
    AIBoard aiboard = new engine.AIBoard();
	Recommender r = new engine.Recommender();
	Search s = new engine.Search();
    
    ChessPiece moveChess;
    double sum[][] = new double[9][10];
    ChessPiece RCar1, RCar2, BCar1, BCar2,
               RHorse1, RHorse2, BHorse1, BHorse2,  
               RElephant1, RElephant2, BElephant1, BElephant2,
               ROfficer1, ROfficer2, BOfficer1, BOfficer2,
               RSoldier1, RSoldier2, RSoldier3, RSoldier4, RSoldier5,
               BSoldier1, BSoldier2, BSoldier3, BSoldier4, BSoldier5,
               RPao1, RPao2, BPao1, BPao2,
               RKing, BKing;
    int startX, startY;  
    int startI, startJ;
    public boolean isRed = true, isBlack = false;
	public Record record = null;
    
    public ChessBoard(int w, int h, int r, int c) {
    	aiboard.printBoard();
    	setLayout(null);  
        addMouseListener(this);  
        addMouseMotionListener(this);  
        Color bc = getBackground();  
        unitWidth = w;  
        unitHeight = h;  
        xAxis = r;  
        yAxis = c;  
            point = new ChessPoint[r + 1][c + 1]; 
            
            for (int i = 1; i <= r; i++) {  
                for (int j = 1; j <= c; j++) {  
                    point[i][j] = new ChessPoint(i * unitWidth, j * unitHeight,  
                            false);  
                }  
            }  
            
            rule = new Rule(this, point);  
            record = new Record(this, point);  
            
            s.getUIBoard(this);
        	Thread t = new Thread(s);
        	t.start();
        	
        	try {
        		Thread.sleep(100);
        	} catch (InterruptedException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
      
            img = Toolkit.getDefaultToolkit().getImage("board.jpg");  
            pieceImg = Toolkit.getDefaultToolkit().getImage("piece.gif"); 
            
            RCar1 = new ChessPiece("車", Color.red, bc, w - 4, h - 4, this);  
            RCar1.setPieceType(RedPiece); 
            RCar2 = new ChessPiece("車", Color.red, bc, w - 4, h - 4, this);  
            RCar2.setPieceType(RedPiece); 
            BCar1 = new ChessPiece("車", Color.black, bc, w - 4, h - 4, this);  
            BCar1.setPieceType(BlackPiece); 
            BCar2 = new ChessPiece("車", Color.black, bc, w - 4, h - 4, this);  
            BCar2.setPieceType(BlackPiece); 
            RHorse1 = new ChessPiece("馬", Color.red, bc, w - 4, h - 4, this);  
            RHorse1.setPieceType(RedPiece); 
            RHorse2 = new ChessPiece("馬", Color.red, bc, w - 4, h - 4, this);  
            RHorse2.setPieceType(RedPiece); 
            BHorse1 = new ChessPiece("馬", Color.black, bc, w - 4, h - 4, this);  
            BHorse1.setPieceType(BlackPiece); 
            BHorse2 = new ChessPiece("馬", Color.black, bc, w - 4, h - 4, this);  
            BHorse2.setPieceType(BlackPiece); 
            RElephant1 = new ChessPiece("相", Color.red, bc, w - 4, h - 4, this);  
            RElephant1.setPieceType(RedPiece); 
            RElephant2 = new ChessPiece("相", Color.red, bc, w - 4, h - 4, this);  
            RElephant2.setPieceType(RedPiece); 
            BElephant1 = new ChessPiece("象", Color.black, bc, w - 4, h - 4, this);  
            BElephant1.setPieceType(BlackPiece); 
            BElephant2 = new ChessPiece("象", Color.black, bc, w - 4, h - 4, this);  
            BElephant2.setPieceType(BlackPiece); 
            ROfficer1 = new ChessPiece("仕", Color.red, bc, w - 4, h - 4, this);  
            ROfficer1.setPieceType(RedPiece); 
            ROfficer2 = new ChessPiece("仕", Color.red, bc, w - 4, h - 4, this);  
            ROfficer2.setPieceType(RedPiece); 
            BOfficer1 = new ChessPiece("士", Color.black, bc, w - 4, h - 4, this);  
            BOfficer1.setPieceType(BlackPiece); 
            BOfficer2 = new ChessPiece("士", Color.black, bc, w - 4, h - 4, this);  
            BOfficer2.setPieceType(BlackPiece); 
            RSoldier1 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);  
            RSoldier1.setPieceType(RedPiece); 
            RSoldier2 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);  
            RSoldier2.setPieceType(RedPiece); 
            RSoldier3 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);  
            RSoldier3.setPieceType(RedPiece); 
            RSoldier4 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);  
            RSoldier4.setPieceType(RedPiece); 
            RSoldier5 = new ChessPiece("兵", Color.red, bc, w - 4, h - 4, this);  
            RSoldier5.setPieceType(RedPiece); 
            BSoldier1 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);  
            BSoldier1.setPieceType(BlackPiece); 
            BSoldier2 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);  
            BSoldier2.setPieceType(BlackPiece); 
            BSoldier3 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);  
            BSoldier3.setPieceType(BlackPiece); 
            BSoldier4 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);  
            BSoldier4.setPieceType(BlackPiece); 
            BSoldier5 = new ChessPiece("卒", Color.black, bc, w - 4, h - 4, this);  
            BSoldier5.setPieceType(BlackPiece); 
            RPao1 = new ChessPiece("炮", Color.red, bc, w - 4, h - 4, this);  
            RPao1.setPieceType(RedPiece); 
            RPao2 = new ChessPiece("炮", Color.red, bc, w - 4, h - 4, this);  
            RPao2.setPieceType(RedPiece); 
            BPao1 = new ChessPiece("炮", Color.black, bc, w - 4, h - 4, this);  
            BPao1.setPieceType(BlackPiece); 
            BPao2 = new ChessPiece("炮", Color.black, bc, w - 4, h - 4, this);  
            BPao2.setPieceType(BlackPiece);
            RKing = new ChessPiece("帅", Color.red, bc, w - 4, h - 4, this);  
            RKing.setPieceType(RedPiece); 
            BKing = new ChessPiece("将", Color.black, bc, w - 4, h - 4, this);  
            BKing.setPieceType(BlackPiece); 
     
     
            point[1][10].setPiece(RCar1, this);  
            point[2][10].setPiece(RHorse1, this);  
            point[3][10].setPiece(RElephant1, this);  
            point[4][10].setPiece(ROfficer1,this);  
            point[5][10].setPiece(RKing, this);  
            point[6][10].setPiece(ROfficer2, this);  
            point[7][10].setPiece(RElephant2, this);  
            point[8][10].setPiece(RHorse2, this);  
            point[9][10].setPiece(RCar2, this);  
            point[2][8].setPiece(RPao1, this);  
            point[8][8].setPiece(RPao2, this);  
            point[1][7].setPiece(RSoldier1, this);  
            point[3][7].setPiece(RSoldier2, this);  
            point[5][7].setPiece(RSoldier3, this);  
            point[7][7].setPiece(RSoldier4, this);  
            point[9][7].setPiece(RSoldier5, this);  
      
            point[1][1].setPiece(BCar1, this);  
            point[2][1].setPiece(BHorse1, this);  
            point[3][1].setPiece(BElephant1, this);  
            point[4][1].setPiece(BOfficer1, this);  
            point[5][1].setPiece(BKing,this);  
            point[6][1].setPiece(BOfficer2, this);  
            point[7][1].setPiece(BElephant2, this);  
            point[8][1].setPiece(BHorse2, this);  
            point[9][1].setPiece(BCar2, this);  
            point[2][3].setPiece(BPao1, this);  
            point[8][3].setPiece(BPao2,this);  
            point[1][4].setPiece(BSoldier1, this);  
            point[3][4].setPiece(BSoldier2, this);  
            point[5][4].setPiece(BSoldier3, this);  
            point[7][4].setPiece(BSoldier4, this);  
            point[9][4].setPiece(BSoldier5, this); 
              
        }

	
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        
        int imgWidth = img.getWidth(this);  
        int imgHeight = img.getHeight(this);
        int FWidth = getWidth();  
        int FHeight = getHeight();
        int x = (FWidth - imgWidth) / 2;  
        int y = (FHeight - imgHeight) / 2;  
        g.drawImage(img, x, y, null);  
  
        for (int j = 1; j <= yAxis; j++) {  
            g.drawLine(point[1][j].x, point[1][j].y, point[xAxis][j].x,  
                    point[xAxis][j].y);  
        }  
        for (int i = 1; i <= xAxis; i++) {  
            if (i != 1 && i != xAxis) {  
                g.drawLine(point[i][1].x, point[i][1].y, point[i][yAxis - 5].x,  
                        point[i][yAxis - 5].y);  
                g.drawLine(point[i][yAxis - 4].x, point[i][yAxis - 4].y,  
                        point[i][yAxis].x, point[i][yAxis].y);  
            } else {  
                g.drawLine(point[i][1].x, point[i][1].y, point[i][yAxis].x,  
                        point[i][yAxis].y);  
            }  
        }  
  
        g.drawLine(point[4][1].x, point[4][1].y, point[6][3].x, point[6][3].y);  
        g.drawLine(point[6][1].x, point[6][1].y, point[4][3].x, point[4][3].y);  
        g.drawLine(point[4][8].x, point[4][8].y, point[6][yAxis].x,  
                point[6][yAxis].y);  
        g.drawLine(point[4][yAxis].x, point[4][yAxis].y, point[6][8].x,  
                point[6][8].y);  
  
        for (int i = 1; i <= xAxis; i++) {  
            g.drawString("" + i, i * unitWidth, unitHeight / 2);  
        }  
        int j = 1;  
        for (char c = 'A'; c <= 'J'; c++) {  
            g.drawString("" + c, unitWidth / 4, j * unitHeight);  
            j++;  
        }  
  
    }


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {		
		ChessPiece piece = null;  
		Rectangle rect = null;  
        int m = 0,n = 0 ;
        System.out.println(clickedTime);
    if((clickedTime%2) == 1){ 

        if (e.getSource() instanceof ChessPiece) {  
            piece = (ChessPiece) e.getSource(); 
            e = SwingUtilities.convertMouseEvent(piece, e, this); 
            rect = piece.getBounds();  
            for (int i = 1; i <= xAxis; i++) {  
                for (int j = 1; j <= yAxis; j++) {  
                    int x = point[i][j].getX();  
                    int y = point[i][j].getY();  
                    if (rect.contains(x, y)) {  
                        m = i;  
                        n = j;  
                        break;  
                    }  
                }  
            }    
           if((piece != moveChess)){
            if (moveChess != null) { 
        	     Color pieceColor = moveChess.getColor();  
                   Color c = (point[m][n].getPiece()).getColor();  
                   if (pieceColor.getRGB() == c.getRGB()) {  
                	   moveChess.setLocation(startX, startY);  
                       (point[startI][startJ]).setNotEmpty(true);
                       clickedTime --; 
                    }else {  
                   boolean right = rule.movePieceRule(moveChess, startI, startJ, m, n);  
                   if (right) { 
                	   s.keepSearching = false;
                	   point[m][n].reMovePiece(piece, this);
                	   point[m][n].reMovePiece(moveChess, this);
                       point[m][n].setPiece(moveChess, this);  
                       (point[startI][startJ]).setNotEmpty(false);  
                       record.recordEatenPiece(piece);
                       record.recordChess(moveChess, startI, startJ, m, n,piece);  
                       clickedTime ++; 
                       s.getUIBoard(this);
                       try {
              				Thread.sleep(100);
              			} catch (InterruptedException ie) {
              				// TODO Auto-generated catch block
              				ie.printStackTrace();
              			}
                       aiboard.update(((startJ-1) * 9 + (startI - 1)) , ((n-1) * 9 + (m-1)));
                       aiboard.printBoard();
                       s.keepSearching = true;
                       Thread t = new Thread(s);
               			t.start();
                       record.text.append(r.getRecords());
                       if (moveChess.getType().equals(BlackPiece))  
                           record.text.append("Red turn: \n");
                       else record.text.append("Black turn: \n");
//                       ai.search();
                       if (moveChess.getType().equals(RedPiece)) {  
                           isRed = false;  
                           isBlack = true;  
                       }  
                       if (moveChess.getType().equals(BlackPiece)) {  
                       	   isBlack = false;  
                           isRed = true;  
                       }  
                       rule.isWin(moveChess,piece);
                   } else {   
                       	JOptionPane.showMessageDialog(null, "Invalid move,try again.", "alert", JOptionPane.ERROR_MESSAGE); 
                	   moveChess.setLocation(startX, startY);  
                       (point[startI][startJ]).setNotEmpty(true);  
                       clickedTime --; 
                   }  
               }  	     
           }  
           moveChess = null;
           setMoveChess(null);
	       }else{
	    	   JOptionPane.showMessageDialog(null, "Please move again and do not click the same piece.", "alert", JOptionPane.ERROR_MESSAGE);
	    	   moveChess = null;
	    	   setMoveChess(null);
	    	   clickedTime --;
	       }
        }else{
    		double clickedX = e.getX();
    		double clickedY = e.getY();
    		m = n = 1;
    		double min = Math.sqrt(Math.pow((clickedX - point[1][1].getX()),2) + Math.pow((clickedY-point[1][1].getY()), 2));
    		for (int i = 1; i <= xAxis; i++) {  
                for (int j = 1; j <= yAxis; j++) {
                    double x = point[i][j].getX();  
                    double y = point[i][j].getY();
                    sum[i-1][j-1] = Math.sqrt(Math.pow((clickedX -x),2) + Math.pow((clickedY-y), 2));
                    if(min > sum[i-1][j-1]){
                    	m = i;
                    	n = j;
                    	min = sum[i-1][j-1];
                    }
                 }   
            } 
    		boolean ok = rule.movePieceRule(moveChess, startI, startJ,m, n);  
            if (ok) {  
            	s.keepSearching = false;
                point[m][n].setPiece(moveChess, this); 
                (point[startI][startJ]).setNotEmpty(false); 
                //ai.search();
                record.recordChess(moveChess, startI, startJ, m, n,null);  
                if (moveChess.getType().equals(RedPiece)) {  
                	isRed = false;  
                	isBlack = true;  
                }  
                if (moveChess.getType().equals(BlackPiece)) {  
                	isBlack = false;  
                    isRed = true;  
                }      
                setMoveChess(null);
                clickedTime ++;
                s.getUIBoard(this);
    			try {
    				Thread.sleep(100);
    			} catch (InterruptedException ie) {
    				// TODO Auto-generated catch block
    				ie.printStackTrace();
    			}
                aiboard.update(((startJ-1) * 9 + (startI - 1)) , ((n-1) * 9 + (m-1)));
                aiboard.printBoard();
                s.keepSearching = true;
                Thread t = new Thread(s);
        		t.start();
                record.text.append(r.getRecords());
                if (moveChess.getType().equals(BlackPiece))  
                    record.text.append("Red turn: \n");
                else record.text.append("Black turn: \n");
                rule.isWin(moveChess,piece);
                validate();  
                repaint();
                moveChess = null;
    	     }
            else{
            	JOptionPane.showMessageDialog(null, "Invalid move,try again.", "alert", JOptionPane.ERROR_MESSAGE);   
            	 moveChess = null;
            	 setMoveChess(null);
            	 clickedTime--;
            }
       }
	}
   else{
	 if (e.getSource() instanceof ChessPiece) { 
           piece = (ChessPiece) e.getSource(); 
           e = SwingUtilities.convertMouseEvent(piece, e, this); 
       }  
	 if(moveChess == null){
     	if(piece == null){
     		JOptionPane.showMessageDialog(null, "Empty click,try again.", "alert", JOptionPane.ERROR_MESSAGE); 
         }else{
     	    if(isRed && piece.getType().equals(RedPiece) || isBlack && piece.getType().equals(BlackPiece)){
     	    	    moveChess = piece;
     	    	    setMoveChess(piece);
                    startX = moveChess.getBounds().x;  
                    startY = moveChess.getBounds().y;  
                    rect = moveChess.getBounds();  
                    for (int i = 1; i <= xAxis; i++) {  
                        for (int j = 1; j <= yAxis; j++) {  
                            int x = point[i][j].getX();  
                            int y = point[i][j].getY();  
                            if (rect.contains(x, y)) {  
                                startI = i;  
                                startJ = j; 
                                
                                break;  
                            }  
                        }  
                    }  
     	    	clickedTime ++;
               }
	        }
         }
	 }
    validate();  
    repaint();
 }


	@Override
	public void mousePressed(MouseEvent e) {
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
	
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}  
	
	public void setMoveChess(ChessPiece moveChess){
		moveChess = moveChess;
	}
	
    public ChessPiece getMoveChess(){
		return moveChess;
	}
    
    public ChessBoard getChessBoard(){
    	return this;
    }
    


    
    public void deleteString(){
    	aiboard.undo();	
    }
    
	public void undoFromButton(ChessPoint pStart, ChessPoint pEnd, ChessPiece move, ChessPiece end){
        s.keepSearching = false;
    	pStart.setNotEmpty(true);
    	if(end != null){
    		point[pEnd.x][pEnd.y].reMovePiece(move, this);
    		point[pStart.x][pStart.y].setPiece(move, this);
    		point[pEnd.x][pEnd.y].setPiece(end, this);
    	}else{
    		point[pEnd.x][pEnd.y].reMovePiece(move, this);
    		point[pStart.x][pStart.y].setPiece(move, this);
    	}
        deleteString();
        aiboard.printBoard();
    	validate();  
        repaint();
        isRed = !isRed;  
        isBlack = !isBlack;
        record.text.append("\nSuccessfully undo!\n");
        aiboard.printBoard();
        s.keepSearching = true;
        Thread t = new Thread(s);
		t.start();
        record.text.append(r.getRecords());
    	if(isRed){
    		record.text.append("Red turn: \n");  
       }else{
    	   record.text.append("Black turn: \n");  
       }
    }
	
	
        
}