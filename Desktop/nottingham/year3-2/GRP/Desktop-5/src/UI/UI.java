package UI;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;  

public class UI extends JFrame implements ActionListener {  
	ChessBoard board = null;
	Container con = null;
    Record record = null;
    JMenuBar bar;  
    JMenu fileMenu;  
    JMenuItem NewChess, SaveChess, Exit;  
    JFileChooser fileChooser = null;  
    ArrayList<MoveStep> gameChess = null;  
    JButton button4;
    public UI() {  
        bar = new JMenuBar();  
        fileMenu = new JMenu("Chinese Chess Game");  
        NewChess = new JMenuItem("NewChess");   
        Exit = new JMenuItem("Exit");  
        fileMenu.add(NewChess);  
        fileMenu.add(Exit);  
        bar.add(fileMenu);  
        setJMenuBar(bar);  
        setTitle(NewChess.getText());  
        NewChess.addActionListener(this);  
        Exit.addActionListener(this);  
        board = new ChessBoard(45, 45, 9, 10);  
        record = board.record;  
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true,board, record); 
        split.setDividerSize(5);  
        split.setDividerLocation(460);  
        con = getContentPane();
        con.add(split, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent e) {  
                System.exit(0);  
            }  
        }); 
        
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 5));
//        button3 = new JButton("Search");
//        button3.addActionListener(this);
        button4 = new JButton("Undo");
        button4.addActionListener(this);
//        p2.add(button3);
        p2.add(button4);
        con.add(p2, BorderLayout.SOUTH);
        
        setVisible(true);  
        setBounds(60, 20, 900, 600);  
        fileChooser = new JFileChooser();  
        con.validate();  
        validate();  
    }  
  
    public void actionPerformed(ActionEvent e) {  
        if (e.getSource() == NewChess) {  
            con.removeAll();  
            NewChess.setEnabled(true);  
            this.setTitle(NewChess.getText());  
            board.s.keepSearching = false;
            board.r = null;
            board.s = null;
            board.aiboard = null;
            board = new ChessBoard(45, 45, 9, 10);  
            record = board.record;  
            JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, board, record);  
            split.setDividerSize(5);  
            split.setDividerLocation(460);  
            JPanel p2 = new JPanel();
            p2.setLayout(new GridLayout(1, 5));
//            button3 = new JButton("Search");
//            button3.addActionListener(this);
            button4 = new JButton("Undo");
            button4.addActionListener(this);
//            p2.add(button3);
            p2.add(button4);
            con.add(p2, BorderLayout.SOUTH);
            con.add(split, BorderLayout.CENTER);  
            validate();  
        }  
        if (e.getSource() == Exit) {  
          System.exit(0);
        }  
//        if(e.getSource() == button3){
//        	String str = null;
//        	str = "tips: "+ board.getSearchString() + "\n";
//        	System.out.println(str);
//        	record.text.append(str);
//        }
        if(e.getSource() == button4){
        	if(record.gameChess.isEmpty()){
        		JOptionPane.showMessageDialog(null, "Empty record,cannot use undo.", "alert", JOptionPane.ERROR_MESSAGE); 
        	}else{
        	int size = record.gameChess.size();
        	ChessPoint pStart = record.gameChess.get(size-1).pStart;
        	ChessPoint pEnd = record.gameChess.get(size-1).pEnd;
        	ChessPiece move = record.gameChess.get(size-1).move;
        	ChessPiece end = record.gameChess.get(size-1).eaten;
        	board.undoFromButton(pStart,pEnd,move,end);
        	record.gameChess.remove(size-1);
        	}
        }
    }  
  
    public static void main(String args[]) {  
       UI n = new UI(); 
    }  
}  