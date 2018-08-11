package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Cream;
import game.backend.element.Nothing;

public class CreamCell extends Cell {

	private boolean active;
	
	public CreamCell(Grid grid) {
		super(grid);
		setActive(true);
		super.setContent(new Cream());
	
	}
	
	public boolean isActive() {
		return active;
	}

	private void setActive(boolean active) {
		this.active = active;
		//nuevo nothing
		super.setContent(new Nothing());
	}
	
	@Override
	public void clearContent() {
		
		super.clearContent();
		
		if(isEmpty() && isActive())
			this.setActive(false);
		
	}

	@SuppressWarnings("unused")
	private void explosionMessage(){}
	
	@Override
	public void explosionNotice(){
		this.setActive(false);
	}
	
}
