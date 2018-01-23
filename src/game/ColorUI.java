package game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import game.player.Color;

public class ColorUI {
	
	int size;
	String color;
	Boolean base;
		
	public ColorUI(Color colorName, boolean base, int size) {
		this.size = size;
		this.base = base;
		
		if (colorName == null) {
			color = null;
		} else {
			switch (colorName.getColGroup()) {
				case RED:
					color = "R";
					break;
				case BLUE:
					color = "B";
					break;
				case GREEN:
					color = "G";
					break;
				case YELLOW:
					color = "Y";
					break;
				default:
					color = null;
					break;
			}
		}
	}
	
	public Image getColorUI() {

		Image buttonImage = null;
		File buttonFile;
		
		if ((base || size == 4) && color != null) {
			// Color Base
			buttonFile = new File("Resources/" + color + "Base.png");
		} else if ((base || size == 4) && color == null) {
			// Default Base
			buttonFile = new File("Resources/SBase.png");
		} else if (!base && color != null) {
			// Color Ring 0-3
			buttonFile = new File("Resources/" + color + size + ".png");
		} else {
			// Empty Ring
			// Source https://www.polyvore.com/cgi/img-thing?.out=jpg&size=l&tid=20482705
			buttonFile = new File("Resources/Empty.png");
		}

		try {
			buttonImage = ImageIO.read(buttonFile);
		} catch (IOException ex) {
			System.out.println(ex);
		}
		
		return buttonImage;
	}
}
