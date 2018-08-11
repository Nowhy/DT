package game.backend.element;

public class Cream extends Element {

	@Override
	public boolean isMovable() {
		return false;
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
	
	@Override
	public String getKey() {
		return "CREAM";
	}

}
