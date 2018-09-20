import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;  
  
public class MakeChessManual extends JPanel implements ActionListener {  
    JTextArea text = null;
    JScrollPane scroll = null;  
    ChessBoard board = null;  
    ChessPoint[][] point;  
    ArrayList<MoveStep> gameChess = null;  
    ArrayList eatenPiece = null;  
    JButton buttonUndo;  
    int i = 0;
  
    public MakeChessManual(ChessBoard board, ChessPoint[][] point) {  
        this.board = board;  
        this.point = point;  
        text = new JTextArea(); 
        String a = board.getSearchString();
        text.append(a);
        text.append("Red turn: \n");
        scroll = new JScrollPane(text);  
        gameChess = new ArrayList();  
        eatenPiece = new ArrayList();   
        setLayout(new BorderLayout());  
        add(scroll, BorderLayout.CENTER);  
    }  
  
    public char numberToLetter(int n) {  
        char c = '\0';  
        switch (n) {  
        case 1:  
            c = 'A';  
            break;  
        case 2:  
            c = 'B';  
            break;  
        case 3:  
            c = 'C';  
            break;  
        case 4:  
            c = 'D';  
            break;  
        case 5:  
            c = 'E';  
            break;  
        case 6:  
            c = 'F';  
            break;  
        case 7:  
            c = 'G';  
            break;  
        case 8:  
            c = 'H';  
            break;  
        case 9:  
            c = 'I';  
            break;  
        case 10:  
            c = 'J';  
            break;  
        }  
        return c;  
    }  

    public void recordChess(ChessPiece piece, int startI, int startJ, int endI,  
            int endJ,ChessPiece eaten) { 
    	ChessPoint pStart = new ChessPoint(startI,startJ,false);
    	ChessPoint pEnd = new ChessPoint(endI,endJ,true); 
        MoveStep step = new MoveStep(piece,eaten,pStart, pEnd);  
        gameChess.add(step);  
        
        String pieceType = piece.getType();  
        String name = piece.getName();  
        String m = "Piece: " + pieceType + name + "    from " + startI + numberToLetter(startJ)  
                + " to " + endI + numberToLetter(endJ);  
        text.append(m); 
        text.append("\n");
        if(eatenPiece.size() != 0){
        	 int index = eatenPiece.size();
            if(eaten == (ChessPiece) eatenPiece.get(index-1)){
        	   String n = "Piece: " + eaten.getType() + eaten.getName() + " is caught. Careful! " + eaten.getType() ; 
        	   text.append(n); 
               text.append("\n");
             }
        }
     }  
  
    public void recordEatenPiece(ChessPiece piece) {  
    	eatenPiece.add(piece);  
    }  
  
    public ArrayList getGameChess() {  
        return gameChess;  
    }  
  
    public ArrayList get() {  
        return gameChess;  
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}  
}  