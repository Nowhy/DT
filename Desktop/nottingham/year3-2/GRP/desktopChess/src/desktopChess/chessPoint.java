package desktopChess;

public class chessPoint {  
    /** 棋子坐标 */  
    int x, y;  
      
    /** 该坐标 是否有子*/  
    boolean 有棋子;  
      
    /** 改坐标的棋子 */  
    chessPiece piece = null;  
      
    /** 坐标所属棋盘 */  
    chessBoard board = null;  
  
    public chessPoint(int x, int y, boolean boo) {  
        this.x = x;  
        this.y = y;  
        有棋子 = boo;  
    }  
  
    public boolean isPiece() {  
        return 有棋子;  
    }  
  
    public void set有棋子(boolean boo) {  
        有棋子 = boo;  
    }  
  
    public int getX() {  
        return x;  
    }  
  
    public int getY() {  
        return y;  
    }  
  
    // 设置改点棋子  
    public void setPiece(chessPiece piece, chessBoard board) {  
        this.board = board;  
        this.piece = piece;  
        board.add(piece);  
        int w = (board.unitWidth);  
        int h = (board.unitHeight);  
        piece.setBounds(x - w / 2, y - h / 2, w, h);// 棋子位置，宽度，高度  
        有棋子 = true;  
        board.validate();  
    }  
  
    public chessPiece getPiece() {  
        return piece;  
    }  
  
    public void reMovePiece(chessPiece piece, chessBoard board) {  
        this.board = board;  
        this.piece = piece;  
        board.remove(piece);  
        board.validate();  
        有棋子 = false;  
    }  
}  