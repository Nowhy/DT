package game.backend.move;

import game.backend.Grid;

public class NothingMove extends Move {

	public NothingMove(Grid grid) {
		super(grid);
	}

	@Override
	public void removeElements() {
		
	}

	@Override
	public boolean internalValidation(){
		return false;
	}
}