package desktopChess;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  

public class chessBoard extends JPanel {  
    public chessPoint point[][];  
    public int unitWidth, unitHeight;  
    private int xLine, yLine;  
    private int x, y;  
    private Image img;  
    protected Image pieceImg;  
    private boolean move = false;  
    public String Red = "Red", Black = "Black";  
    chessPiece BChe1, BChe2, BMa1, BMa2, BXiang1, BXiang2, BJiang, BShi1, BShi2, BZu1, BZu2, BZu3, BZu4,  
    BZu5, BPao1, BPao2;  
    chessPiece RChe1, RChe2, RMa1, RMa2, RXiang1, RXiang2, RShuai, RShi1, RShi2, RBin1, RBin2, RBin3, RBin4,  
    RBin5, RPao1, RPao2;   

int startX, startY;  
int startI, startJ; 
public boolean 红方走棋 = true, 黑方走棋 = false;  
//Rule rule = null;  
//public MakeChessManual record = null;  

public chessBoard(int w, int h, int r, int c) {  
    setLayout(null);  
//    addMouseListener(this);  
//    addMouseMotionListener(this);  
    Color bc = getBackground();  
    unitWidth = w;  
    unitHeight = h;  
    xLine = r;  
    yLine = c;  

    point = new chessPoint[r + 1][c + 1];  

    for (int i = 1; i <= r; i++) {  
        for (int j = 1; j <= c; j++) {  
            point[i][j] = new chessPoint(i * unitWidth, j * unitHeight,  
                    false);  
        }  
    }  

 //   rule = new Rule(this, point);  
//    record = new MakeChessManual(this, point);  

    img = Toolkit.getDefaultToolkit().getImage("6.jpg");  
    pieceImg = Toolkit.getDefaultToolkit().getImage("5.jpg");  
      
    RChe1 = new chessPiece("車", Color.red, bc, w - 4, h - 4, this);  
    RChe1.setColour(Red);  
    RChe2 = new chessPiece("車", Color.red, bc, w - 4, h - 4, this);  
    RChe2.setColour(Red);  
    RMa1 = new chessPiece("馬", Color.red, bc, w - 4, h - 4, this);  
    RMa1.setColour(Red);  
    RMa2 = new chessPiece("馬", Color.red, bc, w - 4, h - 4, this);  
    RMa2.setColour(Red);  
    RPao1 = new chessPiece("炮", Color.red, bc, w - 4, h - 4, this);  
    RPao1.setColour(Red);  
    RPao2 = new chessPiece("炮", Color.red, bc, w - 4, h - 4, this);  
    RPao2.setColour(Red);  
    RXiang1 = new chessPiece("相", Color.red, bc, w - 4, h - 4, this);  
    RXiang1.setColour(Red);  
    RXiang2 = new chessPiece("相", Color.red, bc, w - 4, h - 4, this);  
    RXiang2.setColour(Red);  
    RShi1 = new chessPiece("仕", Color.red, bc, w - 4, h - 4, this);  
    RShi1.setColour(Red);  
    RShi2 = new chessPiece("仕", Color.red, bc, w - 4, h - 4, this);  
    RShi2.setColour(Red);  
    RShuai = new chessPiece("帅", Color.red, bc, w - 4, h - 4, this);  
    RShuai.setColour(Red);  
    RBin1 = new chessPiece("兵", Color.red, bc, w - 4, h - 4, this);  
    RBin1.setColour(Red);  
    RBin2 = new chessPiece("兵", Color.red, bc, w - 4, h - 4, this);  
    RBin2.setColour(Red);  
    RBin3 = new chessPiece("兵", Color.red, bc, w - 4, h - 4, this);  
    RBin3.setColour(Red);  
    RBin4 = new chessPiece("兵", Color.red, bc, w - 4, h - 4, this);  
    RBin4.setColour(Red);  
    RBin5 = new chessPiece("兵", Color.red, bc, w - 4, h - 4, this);  
    RBin5.setColour(Red);  

    BJiang = new chessPiece("将", Color.black, bc, w - 4, h - 4, this);  
    BJiang.setColour(Black);  
    BShi1 = new chessPiece("士", Color.black, bc, w - 4, h - 4, this);  
    BShi1.setColour(Black);  
    BShi2 = new chessPiece("士", Color.black, bc, w - 4, h - 4, this);  
    BShi2.setColour(Black);  
    BChe1 = new chessPiece("车", Color.black, bc, w - 4, h - 4, this);  
    BChe1.setColour(Black);  
    BChe2 = new chessPiece("车", Color.black, bc, w - 4, h - 4, this);  
    BChe2.setColour(Black);  
    BPao1 = new chessPiece("炮", Color.black, bc, w - 4, h - 4, this);  
    BPao1.setColour(Black);  
    BPao2 = new chessPiece("炮", Color.black, bc, w - 4, h - 4, this);  
    BPao2.setColour(Black);  
    BXiang1 = new chessPiece("象", Color.black, bc, w - 4, h - 4, this);  
    BXiang1.setColour(Black);  
    BXiang2 = new chessPiece("象", Color.black, bc, w - 4, h - 4, this);  
    BXiang2.setColour(Black);  
    BMa1 = new chessPiece("马", Color.black, bc, w - 4, h - 4, this);  
    BMa1.setColour(Black);  
    BMa2 = new chessPiece("马", Color.black, bc, w - 4, h - 4, this);  
    BMa2.setColour(Black);  
    BZu1 = new chessPiece("卒", Color.black, bc, w - 4, h - 4, this);  
    BZu1.setColour(Black);  
    BZu2 = new chessPiece("卒", Color.black, bc, w - 4, h - 4, this);  
    BZu2.setColour(Black);  
    BZu3 = new chessPiece("卒", Color.black, bc, w - 4, h - 4, this);  
    BZu3.setColour(Black);  
    BZu4 = new chessPiece("卒", Color.black, bc, w - 4, h - 4, this);  
    BZu4.setColour(Black);  
    BZu5 = new chessPiece("卒", Color.black, bc, w - 4, h - 4, this);  
    BZu5.setColour(Black);  
    point[1][10].setPiece(RChe1, this);  
    point[2][10].setPiece(RMa1, this);  
    point[3][10].setPiece(RXiang1, this);  
    point[4][10].setPiece(RShi1, this);  
    point[5][10].setPiece(RShuai, this);  
    point[6][10].setPiece(RShi2, this);  
    point[7][10].setPiece(RXiang2, this);  
    point[8][10].setPiece(RMa2, this);  
    point[9][10].setPiece(RChe2, this);  
    point[2][8].setPiece(RPao1, this);  
    point[8][8].setPiece(RPao2, this);  
    point[1][7].setPiece(RBin1, this);  
    point[3][7].setPiece(RBin2, this);  
    point[5][7].setPiece(RBin3, this);  
    point[7][7].setPiece(RBin4, this);  
    point[9][7].setPiece(RBin5, this);  

    point[1][1].setPiece(BChe1, this);  
    point[2][1].setPiece(BMa1, this);  
    point[3][1].setPiece(BXiang1, this);  
    point[4][1].setPiece(BShi1, this);  
    point[5][1].setPiece(BJiang, this);  
    point[6][1].setPiece(BShi2, this);  
    point[7][1].setPiece(BXiang2, this);  
    point[8][1].setPiece(BMa2, this);  
    point[9][1].setPiece(BChe2, this);  
    point[2][3].setPiece(BPao1, this);  
    point[8][3].setPiece(BPao2, this);  
    point[1][4].setPiece(BZu1, this);  
    point[3][4].setPiece(BZu2, this);  
    point[5][4].setPiece(BZu3, this);  
    point[7][4].setPiece(BZu4, this);  
    point[9][4].setPiece(BZu5, this);  

}

public void paintComponent(Graphics g) {  
    super.paintComponent(g);  

    int imgWidth = img.getWidth(this);  
    int imgHeight = img.getHeight(this);// 获得图片的宽度与高度  
    int FWidth = getWidth();  
    int FHeight = getHeight();// 获得窗口的宽度与高度  
    int x = (FWidth - imgWidth) / 2;  
    int y = (FHeight - imgHeight) / 2;  
    g.drawImage(img, x, y, null);  

    for (int j = 1; j <= yLine; j++) {  
        g.drawLine(point[1][j].x, point[1][j].y, point[xLine][j].x,  
                point[xLine][j].y);  
    }  
    for (int i = 1; i <= xLine; i++) {  
        if (i != 1 && i != xLine) {  
            g.drawLine(point[i][1].x, point[i][1].y, point[i][yLine - 5].x,  
                    point[i][yLine - 5].y);  
            g.drawLine(point[i][yLine - 4].x, point[i][yLine - 4].y,  
                    point[i][yLine].x, point[i][yLine].y);  
        } else {  
            g.drawLine(point[i][1].x, point[i][1].y, point[i][yLine].x,  
                    point[i][yLine].y);  
        }  
    }  

    g.drawLine(point[4][1].x, point[4][1].y, point[6][3].x, point[6][3].y);  
    g.drawLine(point[6][1].x, point[6][1].y, point[4][3].x, point[4][3].y);  
    g.drawLine(point[4][8].x, point[4][8].y, point[6][yLine].x,  
            point[6][yLine].y);  
    g.drawLine(point[4][yLine].x, point[4][yLine].y, point[6][8].x,  
            point[6][8].y);  

    for (int i = 1; i <= xLine; i++) {  
        g.drawString("" + i, i * unitWidth, unitHeight / 2);  
    }  
    int j = 1;  
    for (char c = 'A'; c <= 'J'; c++) {  
        g.drawString("" + c, unitWidth / 4, j * unitHeight);  
        j++;  
    }  

}  
}