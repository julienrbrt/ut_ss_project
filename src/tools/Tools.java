/**
 * Various tools for our game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

package tools;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tools {
	
	/**
	 * Checks whether or not an int can be obtained from a given String.
	 * @param number String to get an int out of
	 * @return true if number can be converted to an int
	 */
		
	public static boolean validNum(String number) {
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	/**
	 * Merges the 2 images given
	 * @param front Image to go on the front
	 * @param back Image to go on the back
	 * @return Merged Image
	 */
	
	// https://stackoverflow.com/questions/2318020/merging-two-images
	public static Image mergeImg(Image front, Image back) {

		BufferedImage img1 = (BufferedImage) front;
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