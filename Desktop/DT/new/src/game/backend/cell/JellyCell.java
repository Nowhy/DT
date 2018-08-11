package game.backend.cell;

import game.backend.Grid;

public class JellyCell extends Cell {

	private boolean active;

	public JellyCell(Grid grid) {
		super(grid);
		setActive(true);
	}

	@Override
	public void clearContent() {
			super.clearContent();

			if(isEmpty() && isActive())
				this.setActive(false);
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}
}
