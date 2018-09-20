/**
 * 
 */
package UI;

public class ChessPoint {  
    int x, y; 
    boolean isExist;
    ChessPiece piece = null; 
    ChessBoard board = null;  
  
    public ChessPoint(int x, int y, boolean boo) {  
        this.x = x;  
        this.y = y;  
        isExist = boo;  
    }  
  
    public boolean isPiece() {  
        return isExist;  
    }  
  
    public void setNotEmpty(boolean boo) {  
    	isExist = boo;  
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
        isExist = true;  
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
        isExist = false;  
    }  
}  