package game.frontend;

import game.backend.element.Bomb;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;
import game.backend.element.HorizontalStripedCandy;
import game.backend.element.Nothing;
import game.backend.element.VerticalStripedCandy;
import game.backend.element.Wall;
import game.backend.element.WrappedCandy;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageManager {

	private Map<String, Image> images;

	public ImageManager() {
		
		WrappedCandy wc = new WrappedCandy();
		VerticalStripedCandy vc = new VerticalStripedCandy();
		HorizontalStripedCandy hc = new HorizontalStripedCandy();
		
		images = new HashMap<String, Image>();

		try {
			images.put(new Nothing().getKey(), ImageIO.read(new File("resources/images/nothing.png")));
			images.put(new Bomb().getKey(), ImageIO.read(new File("resources/images/bomb.png")));
			images.put(new Wall().getKey(), ImageIO.read(new File("resources/images/wall.png")));
			//images.put(new Jelly().getKey(), ImageIO.read(new File("resources/images/jelly.png")));
			
			
			for (CandyColor cc: CandyColor.values()) {
				images.put(new Candy(cc).getFullKey(), ImageIO.read(new File("resources/images/" + cc.toString().toLowerCase() + "Candy.png")));
			}
			
			for (CandyColor cc: CandyColor.values()) {
				wc.setColor(cc);
				images.put(wc.getFullKey(), ImageIO.read(new File("resources/images/" + cc.toString().toLowerCase() + "Wrapped.png")));
			}
			
			for (CandyColor cc: CandyColor.values()) {
				vc.setColor(cc);
				images.put(vc.getFullKey(), ImageIO.read(new File("resources/images/" + cc.toString().toLowerCase() + "VStripped.png")));
			}

			for (CandyColor cc: CandyColor.values()) {
				hc.setColor(cc);
				images.put(hc.getFullKey(), ImageIO.read(new File("resources/images/" + cc.toString().toLowerCase() + "HStripped.png")));
			}
			
			for (CandyColor cc: CandyColor.values()) {
				images.put(cc.toString().toLowerCase()+"Hint", ImageIO.read(new File("resources/images/" + cc.toString().toLowerCase() + "Hint.jpg")));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage(Element e) {
		return images.get(e.getFullKey());
	}
	
	public Image getImageHint(CandyColor cc) {
		return images.get(cc.toString().toLowerCase()+"Hint");
	}
}
