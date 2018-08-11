public abstract class Candy{
	 int color;
	 Board board;
	 int row;
	 int column;
	 boolean wasCrushed;
	
	//constructor
	public Candy(int color,Board board,int row,int column){//constructor
		this.board=board;
		this.color=color;
		this.row=row;
		this.column=column;
	}
	
	public Candy(Candy oldCandy, Board newboard)
	{
		this.board=newboard;
		this.color=oldCandy.color;
		this.row=oldCandy.row;
		this.column=oldCandy.column;
	}
		
	public abstract void crush();
	
	public void setColor(int color){
		this.color=color;
	}
	public int getColor(){
		return this.color;
	}
	public Board getBoard(){
		return this.board;
	}
	public void setRow(int row){
		this.row=row;
	}
	public void setColumn(int column){
		this.column=column;
	}
	public int getRow(){
		return this.row;
	}
	public int getColumn(){
		return this.column;
	}
	public void setWasCrushed(boolean bool){
		this.wasCrushed=bool;
	}
	
	
}