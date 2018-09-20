
public class ChessPoint {  
    int x, y; 
    boolean notEmpty;
    ChessPiece piece = null; 
    ChessBoard board = null;  
  
    public ChessPoint(int x, int y, boolean boo) {  
        this.x = x;  
        this.y = y;  
        notEmpty = boo;  
    }  
  
    public boolean isPiece() {  
        return notEmpty;  
    }  
  
    public void setNotEmpty(boolean boo) {  
    	notEmpty = boo;  
    }  
  
    public int getX() {  
        return x;  
    }  
  
    public int getY() {  
        return y;  
    }  
  
  
    public void setPiece(ChessPiece piece, ChessBoard board) {  
        this.board = board;  
        this.piece = piece;  
        board.add(piece);  
        int w = (board.unitWidth);  
        int h = (board.unitHeight);  
        piece.setBounds(x - w / 2, y - h / 2, w, h); 
        notEmpty = true;  
        board.validate();  
    }  
  
    public ChessPiece getPiece() {  
        return piece;  
    }  
  
    public void reMovePiece(ChessPiece piece, ChessBoard board) {  
        this.board = board;  
        this.piece = piece;  
        board.remove(piece);  
        board.validate();  
        notEmpty = false;  
    }  
}  