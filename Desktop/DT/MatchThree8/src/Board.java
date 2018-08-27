import java.awt.*;
import java.io.*;
import java.util.Random;

public class Board {
	
	private Candy[][] candies; 
	private Game game;
	private boolean selectedFirst;
	public Candy FirstCandy;
	public int threeNUM=0, fourXNUM=0, fourYNUM=0, fiveNUM=0, chocoNUM=0;
	private Five five;
	private FourX fourX;
	private FourY fourY;
	private Block block;
	private Three three;
	private Chocolate choco;
	private int score = 0;
	int[] RAN = new int[2000];
	int mark = 0;
	public final int BOARDSIZE = 9;
	public int STEP;
	public int TARGET;
	public int LEVEL;
	
	public Board(Board b) {
		this.game = b.game;
		this.RAN = b.RAN;
		this.mark = b.mark;
		this.selectedFirst = b.selectedFirst;
		this.FirstCandy = b.FirstCandy;
		this.score = b.score;
		this.TARGET = b.TARGET;
		this.LEVEL = b.LEVEL;
		this.choco = b.choco;
		this.five = b.five;
		this.block = b.block;
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
				}else if(oldcandy instanceof Block) {
					Candy newcandy = new Block(-10, this, i, j);
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

	public Board(Game game, int LEVEL){ 
		this.game = game;
		this.three = new Three();
		this.fourX = new FourX();
		this.fourY = new FourY();
		this.five = new Five();
		this.choco = new Chocolate();
		this.LEVEL = LEVEL;
		try {
			checkExist(LEVEL);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		candies = setInitialBoard(LEVEL);
		
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
	
	public void checkExist(int LEVEL) throws Exception{

		File file = new File("Level"+LEVEL+".txt");
		if(!file.exists())    
		{    
			try {    
				file.createNewFile();    
				writeRandomLevel(LEVEL);
			} catch (IOException e) {    
				// TODO Auto-generated catch block    
				e.printStackTrace();    
			}    
	    }
	}
	
	
	void writeRandomLevel(int LEVEL) {
		try {
			File writename = new File("Level"+LEVEL+".txt");
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write("STEP:" + "\r\n");
			int seed = (int) System.currentTimeMillis();
			Random random = new Random(seed);
			int m = Math.abs(random.nextInt(seed) % 21);
			int a = m+5;
			out.write(a + "\r\n");
			out.write("TARGET:" + "\r\n");
			m = Math.abs(random.nextInt(seed) % 10);
			int b = (m+1)*100*a;
			out.write(b + "\r\n");
			out.write("BOARD:" + "\r\n");
			for(int i = 0; i < BOARDSIZE; i++) {
				for(int j = 0; j < BOARDSIZE; j++) {
					m = Math.abs(random.nextInt(seed) % 7);
					int n = Math.abs(random.nextInt(seed) % seed);
					if(n == (seed-1)) out.write( m + "B" + "      ");
					if(n == (seed-2)) out.write( m + "V" + "      ");
					if(n == (seed-3)) out.write( m + "H" + "      ");
					else if(m == 6) out.write( "block      ");
					else out.write( m + "      ");
				}
				out.write( "\r\n");
			}
			
			out.flush(); 
			out.close(); 
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	Candy[][] setInitialBoard(int LEVEL){
		
		//read initialBoard.txt to make sure that the initial board is same.
		candies = new Candy[BOARDSIZE][BOARDSIZE];
		String fileName = "Level"+LEVEL+".txt";
		BufferedReader br = null;
		FileReader fr = null;
		try {
			int i = 0;
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String currentLine;
			CharSequence cs1 = "B"; // BombCandy
			CharSequence cs2 = "H"; // YCandy
			CharSequence cs3 = "V"; // XCandy
			
			if ((currentLine = br.readLine()) != null && currentLine.equals("STEP:")) {
				this.STEP =  Integer.parseInt(br.readLine());
			}
			if ((currentLine = br.readLine()) != null && currentLine.equals("TARGET:")) {
				this.TARGET =  Integer.parseInt(br.readLine());
			}
			if ((currentLine = br.readLine()) != null && currentLine.equals("BOARD:")) {
				while ((currentLine = br.readLine()) != null && i < BOARDSIZE) {
					String[] str = currentLine.split("      ");
					for (int j = 0; j < BOARDSIZE; j ++){
						if(str[j].equals("block")) {
							Candy candy = new Block(-10, this, i, j);
							candies[i][j] = candy;
						}else if(str[j].equals("C")) {
							Candy candy = new ChocolateCandy(-1, this, i, j);
							candies[i][j] = candy;
						}else if(str[j].contains(cs1)) {
							Candy candy = new BombCandy(Integer.parseInt(str[j].charAt(0)+""), this, i, j);
							candies[i][j] = candy;
						}else if(str[j].contains(cs2)) {
							Candy candy = new YCandy(Integer.parseInt(str[j].charAt(0)+""), this, i, j);
							candies[i][j] = candy;
						}else if(str[j].contains(cs3)) {
							Candy candy = new XCandy(Integer.parseInt(str[j].charAt(0)+""), this, i, j);
							candies[i][j] = candy;
						}else {
							int num = Integer.parseInt(str[j]);
							Candy candy = new RegularCandy(num, this, i, j);
							candies[i][j] = candy;
						}
					}
					i++;
				}
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
		
		fileName = "Random.txt";
		br = null;
		fr = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			int i = 0;
			while (br.readLine() != null) {
						int num = Integer.parseInt(br.readLine());
						RAN[i] = num;
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
	
	
	public Candy setRandomCandy( int i , int j) {//decides which Candy will be set
		Candy candy = null;
//			if(METHOD == 1) {
//				if(mark >=2000) {
//					mark = 0;
//				}
//				int color = RAN[mark];
//				mark ++;
//				candy = new RegularCandy(color, this, i, j);
//			}else if(METHOD == 2) {
//				int ran = (int) (Math.random()*6); //get a random number to get a random candy 
//				candy = new RegularCandy(ran, this, i, j);
//			}else if(METHOD == 3) {
			int ran = (int) (Math.random()*6); //get a random number to get a random candy 
			candy = new RegularCandy(ran, this, i, j);
//			}
		return candy;		
			
	}
	
	public boolean checkValidMoves(){
		for(int i = BOARDSIZE-1; i >= 0; i--) {
			for(int j = BOARDSIZE-1; j >= 0; j--) {
				if(this.getCandies()[i][j] != null && this.getCandies()[i][j].getColor()!=-10) {
					this.setFirstCandy(i, j);
					this.setSelectedFirst();
					if(i > 0 && this.getCandies()[i-1][j] != null && this.getCandies()[i-1][j].getColor()!=-10) {
						Candy SecondCandy = this.getCandies()[i-1][j];
						if(isValid(FirstCandy, SecondCandy)) return true;
					}
					if(j > 0 && this.getCandies()[i][j-1] != null && this.getCandies()[i][j-1].getColor()!=-10) {
						Candy SecondCandy = this.getCandies()[i][j-1];
						if(isValid(FirstCandy, SecondCandy)) return true;
					}
				}
				
			}
		}
		this.reset();
		return false;
	}
	

	
	
	// score rule:
	// 1. three match: 60
	// 2. stripe: 120
	// 3. wrapped bump: 200
	// 4. strip a line: 60 for each candy
	// 5. match a wrapped bump: 540 + 60 for each candy
	// 6. chocolate: 200
	// 7. regular X chocolate: 1200 + 60 * each candy
	public void move(int i, int j) {
		Candy SecondCandy = this.getCandies()[i][j];
//		System.out.println(FirstCandy.color + "  11111111111            " + SecondCandy.color);
		//if there is no first selected candy, name this candy as the first selected candy.
		if (selectedFirst){
			this.FirstCandy = SecondCandy;
			selectedFirst = false;
		}else {
			if(isValid(FirstCandy, SecondCandy)){
				swap(FirstCandy, SecondCandy);
				if (isTwoBomb(FirstCandy, SecondCandy)){
					this.Bomb(candies, FirstCandy, SecondCandy);
				}else if(choco.isMatchChocolate(this, candies, SecondCandy) || choco.isMatchChocolate(this, candies, this.FirstCandy)) {
					choco.disappearChocolate(this, candies, SecondCandy);
					choco.disappearChocolate(this, candies, FirstCandy);
					updateScore(200);
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
				while(!this.isNONullCandy()) {
					this.addRandomCandy();
					this.checkChainReaction(0);
				}

				this.game.reDrawBoard(); 
				this.printBoard();
				reset();
			}else {
				this.FirstCandy = SecondCandy;
				selectedFirst = false;
			}
		}
	}

	public void checkChainReaction(int i) {
		if(choco.isNoMatchChocolate(this, candies)) {
			this.ChainreactionChocolate(i);
			while(!this.isNONullCandy()) {
				this.addRandomCandy();
			}
		}else if(five.isNoMatchFive(this, candies)) {
			this.ChainreactionFive(i);
			while(!this.isNONullCandy()) {
				this.addRandomCandy();
			}
		}else if(fourX.isNoMatchFourX(this, candies)) {
			this.ChainreactionFourX(i);
			while(!this.isNONullCandy()) {
				this.addRandomCandy();
			}
		}else if(fourY.isNoMatchFourY(this, candies)) {
			this.ChainreactionFourY(i);
			while(!this.isNONullCandy()) {
				this.addRandomCandy();
			}
		}else if(three.isNoMatchThree(this, candies)) {
			this.ChainreactionThree(i);
		}
	}
	
	//make sure there is no more new "match-chocolate"
	public void ChainreactionChocolate(int i) {
		for (int row = 0; row < BOARDSIZE; row = row+1){ 
			for (int column = 0; column < BOARDSIZE; column = column+1){
				if(choco.isMatchChocolate(this, candies, candies[row][column])){
					choco.disappearChocolate(this, candies, candies[row][column]);
					i = i + 1;
					while(!this.isNONullCandy()) {
						this.addRandomCandy();
						this.checkChainReaction(i);
					}
					updateScore(200*(i+1));
				}
			}
		}
	}
	
	//make sure there is no more new "match-five" in vertical line
	public void ChainreactionFive(int i) {
		for (int row = 0; row < BOARDSIZE; row = row+1){ 
			for (int column = 0; column < BOARDSIZE; column = column+1){
				if(five.isMatchFiveCenter(this, candies, candies[row][column])){
					five.disappearFiveCenter(this, candies, candies[row][column]);
					i = i + 1;
					while(!this.isNONullCandy()) {
						this.addRandomCandy();
						this.checkChainReaction(i);
					}
					updateScore(200*(i+1));
				}
			}
		}
	}
	
	//make sure there is no more new "match-four" in vertical line
		public void ChainreactionFourX(int i) {
			for (int row = 0; row < BOARDSIZE; row = row+1){ 
				for (int column = 0; column < BOARDSIZE; column = column+1){
					if(fourX.isMatchFourX(this, candies,candies[row][column])){
						fourX.disappearFourX(this, candies, candies[row][column]);
						i = i + 1;
						while(!this.isNONullCandy()) {
							this.addRandomCandy();
							this.checkChainReaction(i);
						}
						updateScore(120*(i+1));
					}
				}
			}
		}
		
		
		//make sure there is no more new "match-four" in horizontal line
		public void ChainreactionFourY(int i) {
			for (int row = 0; row < BOARDSIZE; row = row+1){ 
				for (int column = 0; column < BOARDSIZE; column = column+1){
					if(fourY.isMatchFourY(this, candies, candies[row][column])){
						fourY.disappearFourY(this, candies, candies[row][column]);
						i = i + 1;
						while(!this.isNONullCandy()) {
							this.addRandomCandy();
							this.checkChainReaction(i);
						}
						updateScore(120*(i+1));
					}
				}
			}
		}
		
		//make sure there is no more new "match-three"
		public void ChainreactionThree(int i) {
			for (int row = 0; row < BOARDSIZE; row++){
				for (int column = 0; column < BOARDSIZE; column++){
					if(three.isMatchThree(this, candies, candies[row][column])){
						three.disappearThree(this, candies, candies[row][column]);
						i = i + 1;
						while(!this.isNONullCandy()) {
							this.addRandomCandy();
							this.checkChainReaction(i);
						}
						updateScore(60*(i+1));
					}
				}
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
//		System.out.println(newscore);
		score += newscore;
	}
	
	public void resetScore() {
		score = 0;
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
//			System.out.println("two special candy");
			return true; 
		}
		return false;
	}
	
	
	
	public void Bomb(Candy[][] candies, Candy FirstCandy, Candy SecondCandy) {
		if ((FirstCandy instanceof XCandy && SecondCandy instanceof XCandy) || (FirstCandy instanceof XCandy && SecondCandy instanceof YCandy) || (FirstCandy instanceof YCandy && SecondCandy instanceof YCandy) || (FirstCandy instanceof YCandy && SecondCandy instanceof XCandy)) {
			int firstColor = FirstCandy.getColor();
			int firstRow = FirstCandy.getRow();
			int firstColumn = FirstCandy.getColumn();
			Candy newFirst = new XCandy(firstColor, this, firstRow, firstColumn);
			this.getCandies()[firstRow][firstColumn] = null;
			this.getCandies()[firstRow][firstColumn] = newFirst;
			this.getCandies()[firstRow][firstColumn].setRow(firstRow);
			this.getCandies()[firstRow][firstColumn].setColumn(firstColumn);
			this.getCandies()[firstRow][firstColumn].crush();
			
			int secondColor = SecondCandy.getColor();
			int secondRow = SecondCandy.getRow();
			int secondColumn = SecondCandy.getColumn();
			Candy newSecond = new YCandy(secondColor, this, secondRow, secondColumn);
			this.getCandies()[secondRow][secondColumn] = null;
			this.getCandies()[secondRow][secondColumn] = newSecond;
			this.getCandies()[secondRow][secondColumn].setRow(secondRow);
			this.getCandies()[secondRow][secondColumn].setColumn(secondColumn);
			this.getCandies()[secondRow][secondColumn].crush();
			updateScore(240);
		}else if((FirstCandy instanceof BombCandy && SecondCandy instanceof XCandy) || (FirstCandy instanceof BombCandy && SecondCandy instanceof YCandy) || (FirstCandy instanceof XCandy && SecondCandy instanceof BombCandy) || (FirstCandy instanceof YCandy && SecondCandy instanceof BombCandy)) {
			int row1 = FirstCandy.getRow();
			int column1 = FirstCandy.getColumn();
			int row2 = SecondCandy.getRow();
			int column2 = SecondCandy.getColumn();
			this.getCandies()[row1][column1] = null;
			this.getCandies()[row2][column2] = null;
			
			for (int i = 0; i < BOARDSIZE - 1; i ++){
				if (this.getCandies()[i][column2] != null && i != row2) {
					this.getCandies()[i][column2].crush();
					updateScore(120);
				}
				if (column2 < BOARDSIZE-1 && this.getCandies()[i][column2+1] != null) {
					this.getCandies()[i][column2+1].crush();
					updateScore(120);
				}
					
				if (column2 > 0 && this.getCandies()[i][column2-1] != null) {
					this.getCandies()[i][column2-1].crush();
					updateScore(120);
				}

			}
			for (int i = 0; i < BOARDSIZE - 1; i ++){
				if (this.getCandies()[row2][i] != null && i != column2) {
					this.getCandies()[row2][i].crush();
					updateScore(120);
				}
				if (row2 > 0 && this.getCandies()[row2-1][i] != null) {
					this.getCandies()[row2-1][i].crush();
					updateScore(120);
				}
				if (row2 < BOARDSIZE-1 && this.getCandies()[row2+1][i] != null) {
					this.getCandies()[row2+1][i].crush();
					updateScore(120);
				}
					

			}
			
		}else if(FirstCandy instanceof BombCandy && SecondCandy instanceof BombCandy) {
			int row1 = FirstCandy.getRow();
			int column1 = FirstCandy.getColumn();
			int row2 = SecondCandy.getRow();
			int column2 = SecondCandy.getColumn();
			this.getCandies()[row1][column1] = null;
			this.getCandies()[row2][column2] = null;
			if(row2 >= 2 && row2 < BOARDSIZE-3) {
				for (int i = row2-2; i <= row2+2; i ++){
					if (this.getCandies()[i][column2] != null && i != row2 && this.getCandies()[i][column2]!=null) {
						this.getCandies()[i][column2].crush();
					}

				}
			}else if(row2 < 2) {
				for (int i = 0 ; i <= row2+2; i ++){
					if (this.getCandies()[i][column2] != null && i != row2 && this.getCandies()[i][column2]!=null) {
						this.getCandies()[i][column2].crush();
					}

				}
			}
			else if(row2 >= BOARDSIZE-3) {
				for (int i = row2 - 2 ; i < BOARDSIZE - 1; i ++){
					if (this.getCandies()[i][column2] != null && i != row2 && this.getCandies()[i][column2]!=null) {
						this.getCandies()[i][column2].crush();
					}

				}
			}
			
			if(column2 >= 2 && column2 < BOARDSIZE-3) {
				for (int i = column2-2; i <= column2+2; i ++){
					if (this.getCandies()[row2][i] != null && i != column2 && this.getCandies()[row2][i]!=null) {
						this.getCandies()[row2][i].crush();
					}

				}
			}else if(column2 < 2) {
				for (int i = 0 ; i <= column2+2; i ++){
					if (this.getCandies()[row2][i] != null && i != column2 && this.getCandies()[row2][i]!=null) {
						this.getCandies()[row2][i].crush();
					}

				}
			}
			else if(column2 >= BOARDSIZE-3) {
				for (int i = column2 - 2 ; i < BOARDSIZE - 1; i ++){
					if (this.getCandies()[row2][i] != null && i != column2 && this.getCandies()[row2][i]!=null) {
						this.getCandies()[row2][i].crush();
					}

				}
			}
			
			updateScore(540);

			if(row1 >= 2 && row1 < BOARDSIZE-3) {
				for (int i = row1-2; i <= row1+2; i ++){
					if (this.getCandies()[i][column1] != null && i != row1 && this.getCandies()[i][column1]!=null) {
						this.getCandies()[i][column1].crush();
					}

				}
			}else if(row1 < 2) {
				for (int i = 0 ; i <= row1+2; i ++){
					if (this.getCandies()[i][column1] != null && i != row1 && this.getCandies()[i][column1]!=null) {
						this.getCandies()[i][column1].crush();
					}

				}
			}
			else if(row1 >= BOARDSIZE-3) {
				for (int i = row1 - 2 ; i < BOARDSIZE - 1; i ++){
					if (this.getCandies()[i][column1] != null && i != row1 && this.getCandies()[i][column1]!=null) {
						this.getCandies()[i][column1].crush();
					}

				}
			}
			
			if(column1 >= 2 && column1 < BOARDSIZE-3) {
				for (int i = column1-2; i <= column1+2; i ++){
					if (this.getCandies()[row1][i] != null && i != column1 && this.getCandies()[row1][i]!=null) {
						this.getCandies()[row1][i].crush();
					}

				}
			}else if(column1 < 2) {
				for (int i = 0 ; i <= column1+2; i ++){
					if (this.getCandies()[row1][i] != null && i != column1 && this.getCandies()[row1][i]!=null) {
						this.getCandies()[row1][i].crush();
					}

				}
			}
			else if(column1 >= BOARDSIZE-3) {
				for (int i = column1 - 2 ; i < BOARDSIZE - 1; i ++){
					if (this.getCandies()[row1][i] != null && i != column1 && this.getCandies()[row1][i]!=null) {
						this.getCandies()[row1][i].crush();
					}

				}
			}
			
			updateScore(540);
			
			
		}else if(FirstCandy instanceof ChocolateCandy && SecondCandy instanceof ChocolateCandy) {
			choco.CombineWithChocolateCandy(this, FirstCandy, SecondCandy);
		}else if(FirstCandy instanceof ChocolateCandy && SecondCandy instanceof BombCandy) {
			choco.CombineWithBombCandy(this, FirstCandy, SecondCandy);
		}else if(FirstCandy instanceof ChocolateCandy && SecondCandy instanceof XCandy) {
			choco.CombineWithXCandy(this, FirstCandy, SecondCandy);
		}else if(FirstCandy instanceof ChocolateCandy && SecondCandy instanceof YCandy) {
			choco.CombineWithYCandy(this, FirstCandy, SecondCandy);
		}else if(FirstCandy instanceof ChocolateCandy && SecondCandy instanceof RegularCandy) {
			choco.CombineWithRegularCandy(this, FirstCandy, SecondCandy);
		}else if(FirstCandy instanceof BombCandy && SecondCandy instanceof ChocolateCandy) {
			choco.CombineWithBombCandy(this, SecondCandy, FirstCandy);
		}else if(FirstCandy instanceof XCandy && SecondCandy instanceof ChocolateCandy) {
			choco.CombineWithXCandy(this,SecondCandy, FirstCandy);
		}else if(FirstCandy instanceof YCandy && SecondCandy instanceof ChocolateCandy) {
			choco.CombineWithYCandy(this, SecondCandy, FirstCandy);
		}else if(FirstCandy instanceof RegularCandy && SecondCandy instanceof ChocolateCandy) {
			choco.CombineWithRegularCandy(this, SecondCandy, FirstCandy);
		}
	}
	

	
	public boolean isValid(Candy FirstCandy, Candy SecondCandy) {
		if(FirstCandy.color == -10 || SecondCandy.color == -10) {
			return false;
		}
		int row1 = FirstCandy.getRow();
		int column1 = FirstCandy.getColumn();
		int row2 = SecondCandy.getRow();
		int column2 = SecondCandy.getColumn();
		boolean mark = false;
		if(row1 == row2 && column1 == column2) return false;
		
		if (row1 == row2){
			if (column1 == (column2-1) || column1 == (column2+1)) {
				mark = true;
			}
		}else if (column1 == column2){
			if (row1 == (row2-1) || row1 == (row2+1)) {
				mark = true;
			}
		}else {
			return false;
		}
		
		if(isTwoBomb(FirstCandy, SecondCandy)) {
			return true;
			
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
	

	public boolean isNONullCandy() {
		for (int i = BOARDSIZE - 1;i >= 0;i--){
			for (int j = BOARDSIZE - 1;j >= 0;j--){
				if(this.getCandies()[i][j] == null) {
					return false;
				}
				
			}
		}
		return true;
	}
	
	public void moveCandyTo(int from_i, int from_j, int to_i, int to_j)
	{
		if(isValidMove(from_i, from_j, to_i, to_j)) {
			candies[to_i][to_j] = candies[from_i][from_j];
			candies[from_i][from_j] = null;
			candies[to_i][to_j].setRow(to_i);
			candies[to_i][to_j].setColumn(to_j);
		}
	
	}
	
	public boolean isValidMove(int from_i, int from_j, int to_i, int to_j) {
		if(candies[to_i][to_j] != null) {
//			System.out.println("Oh no a candy at "+to_i+","+to_j);
//			System.exit(1);
			return false;
		}
		
		if(candies[from_i][from_j] == null) {
//			System.out.println("Oh there is a candy at "+from_i+","+from_j);
//			System.exit(1);
			return false;
		}
		return true;
		
	}
	
	public void addRandomCandy() { // candy should fall down....
		for (int i = BOARDSIZE - 1;i >= 0;i--){
			for (int j = BOARDSIZE - 1;j >= 0;j--){
				if(candies[i][j]==null){
					if(i == 0) {
						candies[0][j] = this.setRandomCandy(0, j);
						break;
					}else {
						if(candies[i-1][j] != null && candies[i-1][j].getColor() != -10){
							moveCandyTo(i-1,j,i,j);
						}else if(candies[i-1][j] != null && candies[i-1][j].getColor() == -10){
							int m = i-1;
							while(m >= 0) {
								if(candies[m][j] != null && candies[m][j].getColor() != -10) {
									moveCandyTo(m,j,i,j);
									break;
								}
								m--;
							}
							if(m == -1) {
								candies[i][j] = this.setRandomCandy(i, j);
							}
							break;
						}
//							if(i >= 1 && candies[i-1][j] instanceof Block) {
//								if(j >= 0 && j < BOARDSIZE/2) {
//									moveCandyTo(i-1,j+1, i, j);
//									//candies[i][j] = candies[m-1][j+1];
//									//candies[m-1][j+1] = null;
//									if(i == 1) {
//										candies[i-1][j+1] = this.setRandomCandy(METHOD, 0, j+1);
//									}
//									break;
//								}else {
//									moveCandyTo(i-1, j-1, i, j);
//									//candies[i][j] = candies[m-1][j-1];
//									//candies[m-1][j-1] = null;
//									if(i == 1) {
//										candies[i-1][j-1] = this.setRandomCandy(METHOD, 0,j-1);
//									}
//									break;
//								}
//							}else {
//								moveCandyTo(i-1,j,i,j);
//								//candies[i][j] = candies[m][j];
//								//candies[m][j] = null;
//								break;
//							}
//						}
					}
				}
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
		System.out.print("     ");
		for (int i = 0; i < BOARDSIZE; i++){
			System.out.print("    j");
		}
		System.out.println("");
		System.out.print("     ");
		for (int i = 0; i < BOARDSIZE; i++){
			System.out.print("    "+i);
		}
		System.out.println("");
		System.out.println("======================================================");
		for (int i = 0; i < BOARDSIZE; i++){
			System.out.print("i: " + i + "|");
			for (int j = 0; j < BOARDSIZE; j++){
				if (candies[i][j] == null) System.out.print("    null   ");
				else if(candies[i][j].getColor() != -10) {
					System.out.print( "    " + candies[i][j].getColor());
				}else {
					System.out.print( "  " + candies[i][j].getColor());
				}
			}
			System.out.println("");
		}
		System.out.println("======================================================");
	}

}