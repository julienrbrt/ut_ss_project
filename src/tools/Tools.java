/**
 * This class is a tooling class for the Ringgz game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

package tools;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tools {
	
	// https://stackoverflow.com/questions/2318020/merging-two-images
	public static Image mergeImg(Image fore, Image back) {

		BufferedImage img1 = (BufferedImage) fore;
		BufferedImage img2 = (BufferedImage) back;
				
		// create the new image, canvas size is the max. of both image sizes
		int w = Math.max(img1.getWidth(), img2.getWidth());
		int h = Math.max(img1.getHeight(), img2.getHeight());
		
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		g.drawImage(img1, 0, 0, null);
		g.drawImage(img2, 0, 0, null);
		
		g.dispose();
		
		return (Image) combined;
	}
}