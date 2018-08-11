import java.awt.*;
import java.io.*;
import java.util.Random;

import javax.swing.*;

public class Board {

	protected final static int BOARDSIZE = 9;
	private Candy[][] candies; 
	private Game game;
	private boolean selectedFirst;
	private Candy FirstCandy;
	public int threeNUM=0, fourXNUM=0, fourYNUM=0, fiveNUM=0, chocoNUM=0;
	private Five five;
	private FourX fourX;
	private FourY fourY;
	private Three three;
	private Chocolate choco;
	private int score = 0;
	Random random;
	
	public Board(Board b) {
		this.game = b.game;
		this.random = b.random;
		this.selectedFirst = b.selectedFirst;
		this.FirstCandy = b.FirstCandy;
		this.score = b.score;
		this.choco = b.choco;
		this.five = b.five;
		this.fourX = b.fourX;
		this.fourY = b.fourY;
		this.three = b.three;
		this.threeNUM = b.threeNUM;
		this.fourXNUM = b.fourXNUM;
		this.fourYNUM = b.fourYNUM;
		this.fiveNUM = b.fiveNUM;
		this.chocoNUM = b.chocoNUM;
		this.candies = new Candy[BOARDSIZE][BOARDSIZE];
		for(int i = 0; i < BOARDSIZE; i++) {
			for(int j = 0; j < BOARDSIZE; j++) {
				Candy oldcandy = b.candies[i][j];
				if (oldcandy instanceof RegularCandy){
					Candy newcandy = new RegularCandy(oldcandy.color, this, i, j);
					this.candies[i][j] = newcandy;
				}else if(oldcandy instanceof XCandy) {
					Candy newcandy = new XCandy(oldcandy.color, this, i, j);
					this.candies[i][j] = newcandy;
				}else if(oldcandy instanceof YCandy) {
					Candy newcandy = new YCandy(oldcandy.color, this, i, j);
					this.candies[i][j] = newcandy;
				}else if(oldcandy instanceof BombCandy) {
					Candy newcandy = new BombCandy(oldcandy.color, this, i, j);
					this.candies[i][j] = newcandy;
				}else if(oldcandy instanceof ChocolateCandy) {
					Candy newcandy = new ChocolateCandy(-1, this, i, j);
					this.candies[i][j] = newcandy;
				}
			}
		}
		
	}
	
	
	public void setSelectedFirst() {
		selectedFirst = false;
	}

	public Game getGame(){ //getters
		return this.game;
	}
	
	public Three getThree(){ //getters
		return this.three;
	}
	
	public FourX getFourX(){ //getters
		return this.fourX;
	}
	
	public FourY getFourY(){ //getters
		return this.fourY;
	}
	
	public Five getFive(){ //getters
		return this.five;
	}
	
	public Chocolate getChocolate(){ //getters
		return this.choco;
	}

	public Candy[][] getCandies(){
		return this.candies;
	}
	
	public void setCandy(Candy candy,int row,int column){ //setters
		this.candies[row][column]=candy;
	}

	public Board(Game game){ 
		random = new Random(1000);
		this.game = game;
		this.three = new Three();
		this.fourX = new FourX();
		this.fourY = new FourY();
		this.five = new Five();
		this.choco = new Chocolate();
		candies = setInitialBoard(game);
		
//		for (int i = 0; i < BOARDSIZE; i = i+1){ //randomly draw a board (without 3 in a row/column)
//			for (int j = 0; j < BOARDSIZE; j = j+1){
//				Candy candy = setRandomCandy(i,j);
//				while (isMatchThree(candy))//make sure there is no three candies connected...
//					candy= setRandomCandy(i,j);
//				candies[i][j] = candy;
//			}
//		}
		
		this.selectedFirst = true;
		this.FirstCandy = null;
	}
	
	
	Candy[][] setInitialBoard(Game game) {
		
		//read initialBoard.txt to make sure that the initial board is same.
		candies = new Candy[BOARDSIZE][BOARDSIZE];
		String fileName = "InitialBoard.txt";
		BufferedReader br = null;
		FileReader fr = null;
		try {
			int i = 0;
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				String[] str = currentLine.split("      ");
					for (int j = 0; j < BOARDSIZE; j = j+1){
						int num = Integer.parseInt(str[j]);
						Candy candy = new RegularCandy(num, this, i, j);
						candies[i][j] = candy;
					}
				i++;
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return candies;
	}
	

	public Candy setRandomCandy(int i,int j){ //decides which Candy will be set
		Candy candy = null;
		int ran = (int) (Math.random()*6); //get a candy 
		int mark = random.nextInt(6);
		candy = new RegularCandy(mark, this, i, j);
		System.out.println("color: " + mark);
		return candy;		
	}

	public void move(int i, int j) {
		Candy SecondCandy = this.getCandies()[i][j];
		
		System.out.println("              " + SecondCandy.color + "          " + i + "             " + j);
		//if there is no first selected candy, name this candy as the first selected candy.
		if (selectedFirst){
			this.FirstCandy = SecondCandy;
			selectedFirst = false;
//			System.out.println("1111111");
		}else {
			if(isValid(FirstCandy, SecondCandy)){
				System.out.println("22222222");
				swap(FirstCandy, SecondCandy);
				System.out.println("33333");
				if (isTwoBomb(FirstCandy, SecondCandy)){
					this.Bomb(candies, FirstCandy, SecondCandy);
					System.out.println("44444");
				}else if(choco.isMatchChocolate(this, candies, SecondCandy) || choco.isMatchChocolate(this, candies, this.FirstCandy)) {
					System.out.println("shddbdhashdhasdhadhashdhasdhad");
					choco.disappearChocolate(this, candies, SecondCandy);
					System.out.println("dddd");
					choco.disappearChocolate(this, candies, FirstCandy);
					System.out.println("fffff");
					updateScore(500);
				}else if(five.isMatchFiveCenter(this, candies, SecondCandy) || five.isMatchFiveCenter(this, candies, this.FirstCandy)) {
					five.disappearFiveCenter(this, candies, SecondCandy);
					five.disappearFiveCenter(this, candies, FirstCandy);
					updateScore(200);
				}else if(fourX.isMatchFourX(this, candies, SecondCandy) || fourX.isMatchFourX(this, candies, this.FirstCandy)) {
					fourX.disappearFourX(this, candies, SecondCandy);
					fourX.disappearFourX(this, candies, FirstCandy);
					updateScore(120);
				}else if(fourY.isMatchFourY(this, candies, SecondCandy) || fourY.isMatchFourY(this, candies, this.FirstCandy)) {
					fourY.disappearFourY(this, candies, SecondCandy);
					fourY.disappearFourY(this, candies, FirstCandy);
					updateScore(120);
				}else if (three.isMatchThree(this, candies, SecondCandy) || three.isMatchThree(this, candies, this.FirstCandy)){
					three.disappearThree(this, candies, SecondCandy);
					three.disappearThree(this, candies, FirstCandy);
					updateScore(60);
				}
				this.addRandomCandy();
				
				//make sure there is no more new "chocolate" in vertical line
				while (choco.isNoMatchChocolate(this, candies)){
					for (int row = 0; row < BOARDSIZE; row = row+1){ 
						for (int column = 0; column < BOARDSIZE; column = column+1){
							if(choco.isMatchChocolate(this, candies, candies[row][column])){
								choco.disappearChocolate(this, candies, candies[row][column]);
								this.addRandomCandy();
								updateScore(500);
							}
						}
					}
				}
				
				//make sure there is no more new "match-five" in vertical line
				while (five.isNoMatchFive(this, candies)){
					for (int row = 0; row < BOARDSIZE; row = row+1){ 
						for (int column = 0; column < BOARDSIZE; column = column+1){
							if(five.isMatchFiveCenter(this, candies, candies[row][column])){
								five.disappearFiveCenter(this, candies, candies[row][column]);
								this.addRandomCandy();
								updateScore(200);
							}
						}
					}
				}
				//make sure there is no more new "match-four" in vertical line
				while (fourX.isNoMatchFourX(this, candies)){
					for (int row = 0; row < BOARDSIZE; row = row+1){ 
						for (int column = 0; column < BOARDSIZE; column = column+1){
							if(fourX.isMatchFourX(this, candies,candies[row][column])){
								fourX.disappearFourX(this, candies, candies[row][column]);
								this.addRandomCandy();
								updateScore(120);
							}
						}
					}
				}
				//make sure there is no more new "match-four" in horizontal line
				while (fourY.isNoMatchFourY(this, candies)){
					for (int row = 0; row < BOARDSIZE; row = row+1){ 
						for (int column = 0; column < BOARDSIZE; column = column+1){
							if(fourY.isMatchFourY(this, candies, candies[row][column])){
								fourY.disappearFourY(this, candies, candies[row][column]);
								this.addRandomCandy();
								updateScore(120);
							}
						}
					}
				}
				//make sure there is no more new "match-three"
				while (three.isNoMatchThree(this, candies)){
					for (int row = 0; row < BOARDSIZE; row = row+1){ 
						for (int column = 0; column < BOARDSIZE; column = column+1){
							if(three.isMatchThree(this, candies, candies[row][column])){
								three.disappearThree(this, candies, candies[row][column]);
								updateScore(60);
								this.addRandomCandy();
							}
						}
					}
				}
				this.game.reDrawBoard(); 
			}
			reset();	
		}
	}
	
	public void setFourXNUM(int num) {
		this.fourXNUM = num;
	}
	
	public int getFourXNUM() {
		return this.fourXNUM;
	}
	
	public void setFourYNUM(int num) {
		this.fourYNUM = num;
	}
	
	public int getFourYNUM() {
		return this.fourYNUM;
	}
	
	public void setFiveNUM(int num) {
		this.fiveNUM = num;
	}
	
	public int getFiveNUM() {
		return this.fiveNUM;
	}
	
	public void setChocolateNUM(int num) {
		this.chocoNUM = num;
	}
	
	public int getChocolateNUM() {
		return this.chocoNUM;
	}
	
	
	public void updateScore(int newscore) {
		score += newscore;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setFirstCandy(int i, int j) {
		 FirstCandy = this.getCandies()[i][j];
	}
	
	public boolean isTwoBomb(Candy FirstCandy, Candy SecondCandy) {
		if (FirstCandy == null || SecondCandy == null) {
			return false;
		}
		if ((FirstCandy instanceof ChocolateCandy) || (SecondCandy instanceof ChocolateCandy)) {
			if(FirstCandy.getRow()!=SecondCandy.getRow() || FirstCandy.getColumn()!=SecondCandy.getColumn()) {
				return true; 
			}
		}
		
		if (!(FirstCandy instanceof RegularCandy) && !(SecondCandy instanceof RegularCandy)) {
			return true; 
		}
		return false;
	}
	
	public void Bomb(Candy[][] candies, Candy FirstCandy, Candy SecondCandy) {
		if (FirstCandy instanceof XCandy && SecondCandy instanceof XCandy) {
			fourX.disappearFourX(this, candies, FirstCandy);
			updateScore(120);
		}else if(FirstCandy instanceof XCandy && SecondCandy instanceof YCandy) {
			fourX.disappearFourX(this, candies, FirstCandy);
			fourY.disappearFourY(this, candies, SecondCandy);
			updateScore(1020);
		}else if (FirstCandy instanceof YCandy && SecondCandy instanceof YCandy) {
			fourY.disappearFourY(this, candies, FirstCandy);
			updateScore(120);
		}else if (FirstCandy instanceof YCandy && SecondCandy instanceof XCandy) {
			fourX.disappearFourX(this, candies, SecondCandy);
			fourY.disappearFourY(this, candies, FirstCandy);
			updateScore(1020);
		}else if(FirstCandy instanceof BombCandy && SecondCandy instanceof XCandy) {
			int row2 = SecondCandy.getRow();
			int column2 = SecondCandy.getColumn();
			if(row2 > 0) {
				Candy candy1 = candies[row2-1][column2];
				fourX.disappearFourX(this, candies, candy1);
			}
			fourX.disappearFourX(this, candies, SecondCandy);
			if(row2 < BOARDSIZE - 1) {
				Candy candy2 = candies[row2+1][column2];
				fourX.disappearFourX(this, candies, candy2);
			}
			updateScore(720*3);
		}else if(FirstCandy instanceof XCandy && SecondCandy instanceof BombCandy) {
			int row1 = FirstCandy.getRow();
			int column1 = FirstCandy.getColumn();
			if(row1 > 0) {
				Candy candy1 = candies[row1-1][column1];
				fourX.disappearFourX(this, candies, candy1);
			}
			fourX.disappearFourX(this, candies, FirstCandy);
			if(row1 < BOARDSIZE - 1) {
				Candy candy2 = candies[row1+1][column1];
				fourX.disappearFourX(this, candies, candy2);
			}
			updateScore(720*3);
		}else if(FirstCandy instanceof YCandy && SecondCandy instanceof BombCandy) {
			int row1 = FirstCandy.getRow();
			int column1 = FirstCandy.getColumn();
			if(column1 > 0) {
				Candy candy1 = candies[row1][column1-1];
				fourX.disappearFourX(this, candies, candy1);
			}
			fourX.disappearFourX(this, candies, FirstCandy);
			if(column1 < BOARDSIZE - 1) {
				Candy candy2 = candies[row1][column1+1];
				fourX.disappearFourX(this, candies, candy2);
			}
			updateScore(720*3);
		}else if(FirstCandy instanceof BombCandy && SecondCandy instanceof YCandy) {
			int row2 = SecondCandy.getRow();
			int column2 = SecondCandy.getColumn();
			if(column2 > 0) {
				Candy candy1 = candies[row2][column2-1];
				fourX.disappearFourX(this, candies, candy1);
			}
			fourX.disappearFourX(this, candies, FirstCandy);
			if(column2 < BOARDSIZE - 1) {
				Candy candy2 = candies[row2][column2+1];
				fourX.disappearFourX(this, candies, candy2);
			}
			updateScore(720*3);
		}else if(FirstCandy instanceof BombCandy && SecondCandy instanceof BombCandy) {
			
			five.disappearFiveCenter(this, candies, FirstCandy);
			five.disappearFiveCenter(this, candies, SecondCandy);
			updateScore(720*3);
		}else if(FirstCandy instanceof ChocolateCandy && SecondCandy instanceof ChocolateCandy) {
			
			choco.disappearChocolate(this, candies, FirstCandy);
			choco.disappearChocolate(this, candies, SecondCandy);
			choco.CombineWithChocolateCandy(this, FirstCandy, SecondCandy);
			updateScore(810*3*6);
		}else if(FirstCandy instanceof ChocolateCandy && SecondCandy instanceof BombCandy) {
			
			choco.disappearChocolate(this, candies, FirstCandy);
			choco.disappearChocolate(this, candies, SecondCandy);
			choco.CombineWithBombCandy(this, FirstCandy, SecondCandy);
			updateScore(810*3*2);
		}else if(FirstCandy instanceof ChocolateCandy && SecondCandy instanceof XCandy) {
			
			choco.disappearChocolate(this, candies, FirstCandy);
			choco.disappearChocolate(this, candies, SecondCandy);
			choco.CombineWithXCandy(this, FirstCandy, SecondCandy);
			updateScore(810*3);
		}else if(FirstCandy instanceof ChocolateCandy && SecondCandy instanceof YCandy) {
			
			choco.disappearChocolate(this, candies, FirstCandy);
			choco.disappearChocolate(this, candies, SecondCandy);
			choco.CombineWithYCandy(this, FirstCandy, SecondCandy);
			updateScore(810*3);
		}else if(FirstCandy instanceof ChocolateCandy && SecondCandy instanceof RegularCandy) {
			
			choco.disappearChocolate(this, candies, FirstCandy);
			choco.disappearChocolate(this, candies, SecondCandy);
			choco.CombineWithRegularCandy(this, FirstCandy, SecondCandy);
			updateScore(810*3);
		}else if(FirstCandy instanceof BombCandy && SecondCandy instanceof ChocolateCandy) {
			
			choco.disappearChocolate(this, candies, FirstCandy);
			choco.disappearChocolate(this, candies, SecondCandy);
			choco.CombineWithBombCandy(this, SecondCandy, FirstCandy);
			updateScore(810*3*2);
		}else if(FirstCandy instanceof XCandy && SecondCandy instanceof ChocolateCandy) {
			
			choco.disappearChocolate(this, candies, FirstCandy);
			choco.disappearChocolate(this, candies, SecondCandy);
			choco.CombineWithXCandy(this,SecondCandy, FirstCandy);
			updateScore(810*3);
		}else if(FirstCandy instanceof YCandy && SecondCandy instanceof ChocolateCandy) {
			
			choco.disappearChocolate(this, candies, FirstCandy);
			choco.disappearChocolate(this, candies, SecondCandy);
			choco.CombineWithYCandy(this, SecondCandy, FirstCandy);
			updateScore(810*3);
		}else if(FirstCandy instanceof RegularCandy && SecondCandy instanceof ChocolateCandy) {
			
			choco.disappearChocolate(this, candies, FirstCandy);
			choco.disappearChocolate(this, candies, SecondCandy);
			choco.CombineWithRegularCandy(this, SecondCandy, FirstCandy);
			updateScore(810*3);
		}
	}
	

	
	public boolean isValid(Candy FirstCandy, Candy SecondCandy) {
		int row1 = FirstCandy.getRow();
		int column1 = FirstCandy.getColumn();
		int row2 = SecondCandy.getRow();
		int column2 = SecondCandy.getColumn();
		boolean mark = false;
		if(isTwoBomb(FirstCandy, SecondCandy)) {
			return true;
		}
		if (row1 == row2){
			if (column1 == (column2-1) || column1 == (column2+1)) {
				mark = true;
			}
		}
		if (column1 == column2){
			if (row1 == (row2-1) || row1 == (row2+1)) {
				mark = true;
			}
		}
		if(!mark || (FirstCandy.getColor() == SecondCandy.getColor())) {
			return false;
		}
		
		swap(FirstCandy, SecondCandy);
//		System.out.println(this);
//		System.out.println(candies);
//		System.out.println(FirstCandy);
			if (three.isMatchThree(this, candies, FirstCandy) || three.isMatchThree(this, candies,SecondCandy)){
				swap(FirstCandy, SecondCandy);
				return true;
			
			}
		swap(FirstCandy, SecondCandy);
		return false;
	}
	

	
	public void addRandomCandy() { // candy should fall down....
		for (int i = BOARDSIZE - 1;i >= 0;i = i-1){
			for (int j = BOARDSIZE - 1;j >= 0;j = j-1){
				if(candies[i][j]==null){
					int m = i;
					while (m >= 0){
//						System.out.println(m + "  add random candies  " + j);
						if(candies[m][j] != null){
							candies[i][j] = candies[m][j];
							candies[i][j].setRow(i);
							candies[m][j] = null;
							break;
						}
						m = m - 1;
					}
				}
			}
		}
		for (int i = BOARDSIZE - 1;i >= 0;i = i-1){
			for (int j = BOARDSIZE - 1;j >= 0;j = j-1){
				if (candies[i][j] == null)
					candies[i][j] = this.setRandomCandy(i, j);
			}
		}
	}
	
	public void reset() {
		selectedFirst = true; 
		FirstCandy = null;
	}
	
	public void swap(Candy FirstCandy, Candy SecondCandy) {
		int firstI = FirstCandy.getRow();
		int firstJ = FirstCandy.getColumn();
		FirstCandy.setRow(SecondCandy.getRow());
		FirstCandy.setColumn(SecondCandy.getColumn());
		SecondCandy.setRow(firstI);
		SecondCandy.setColumn(firstJ);
//		System.out.println(FirstCandy.color + "       " +  FirstCandy.getRow() + "       " + FirstCandy.getColumn());
		this.setCandy(FirstCandy, FirstCandy.getRow(), FirstCandy.getColumn());
		this.setCandy(SecondCandy, SecondCandy.getRow(), SecondCandy.getColumn());
	}
	
	
	public void printBoard(){
		for (int i = 0; i < 9; i = i+1){
			System.out.print("i: " + i);
			for (int j = 0; j < 9; j = j+1){
				if (candies[i][j] == null) System.out.print("    null   ");
				else System.out.print( "    " + candies[i][j].getColor() + "  ");
			}
			System.out.println("");
		}
		System.out.println("");
	}

}
