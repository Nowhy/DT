package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Element;
import game.backend.move.Direction;

public class VoidCell extends Cell {

	public VoidCell(Grid grid) {
		super(grid);
	}
	
	@Override
	public boolean isEmpty(){
		return getAround()[Direction.UP.ordinal()].isEmpty();
	}
	
	@Override
	public boolean isMovable(){
		return true;
	}
	
	@Override
	public Element getAndClearContent() {
		Element elem = getAround()[Direction.UP.ordinal()].getAndClearContent();
		return elem;
	}
	
}
