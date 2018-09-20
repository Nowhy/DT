import java.awt.Point;  
 
public class MoveStep implements java.io.Serializable {  
    public ChessPoint pStart, pEnd;
    public ChessPiece move, eaten;
  
    public MoveStep(ChessPiece c1, ChessPiece c2, ChessPoint p1, ChessPoint p2) {  
    	move = c1;  
        eaten = c2;
        pStart = p1;  
        pEnd = p2;  
    }  
}  