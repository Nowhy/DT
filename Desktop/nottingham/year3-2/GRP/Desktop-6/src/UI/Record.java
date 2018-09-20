package UI;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;  
  
public class Record extends JPanel implements ActionListener {  
	private static final long serialVersionUID = 1L;
	 /**
	  * For the record board
	  * text is the content of board
	  * scroll is the panel of text
	  * 
	  */
	public JTextArea text = null;
    JScrollPane scroll = null; 
    
    ChessBoard board = null;  
    ChessPoint[][] point;  
    ArrayList<MoveStep> movements = null;  
  
    public Record(ChessBoard board, ChessPoint[][] point) {  
        this.board = board;  
        this.point = point;  
        text = new JTextArea(); 
        text.setCaretPosition(text.getText().length()); 
        text.append(board.r.getRecords());
        /**
         * Always start with red
         */
        text.append("Red turn: \n");
        scroll = new JScrollPane(text);  
        movements = new ArrayList<MoveStep>();  
        setLayout(new BorderLayout());  
        add(scroll, BorderLayout.CENTER);  
    }  
  
    

    public void recordChess(ChessPiece piece, int startI, int startJ, int endI,  
            int endJ,ChessPiece eaten) { 
    	/**
    	 * add the movement to record
    	 */
    	ChessPoint pStart = new ChessPoint(startI,startJ,false);
    	ChessPoint pEnd = new ChessPoint(endI,endJ,true); 
        MoveStep step = new MoveStep(piece,eaten,pStart, pEnd);  
        movements.add(step);  
        
        String color1,color2;  
        if(piece.getColor() == Color.BLACK){
        	color1 = "Black";
        	color2 = "Red";
        }else{
        	color1 = "Red";
        	color2 = "Black";
        }
        String name = piece.getName();  
        String m = "\nPiece: " + color1 + name + "    from " + startI + (char)(startJ+64)  
                + " to " + endI + (char)(endJ+64) + "\n";  
        text.append(m); 
        if(eaten != null){
        	String n = "Piece: " + color2 + eaten.getName() + " is caught. Careful! " + color2 +"!\n\n" ; 
        	text.append(n);
        }
     }  
  
    public ArrayList<MoveStep> getGameChess() {  
        return movements;  
    }  
  
    public ArrayList<MoveStep> get() {  
        return movements;  
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}  
}  