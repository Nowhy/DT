package game.backend;

import game.backend.element.Element;

public interface GameListener {
	
	public void gridUpdated();
	
	public void cellExplosion(Element e);
	
}