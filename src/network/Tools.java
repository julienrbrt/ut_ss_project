/**
 * This class is a tooling class for the Ringgz game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

package network;

import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.GraphicsConfiguration;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;

//import javax.swing.*;

public class Tools {
	
	public static boolean validIP(String ip) {
	    try {
	        if (ip == null || ip.isEmpty()) {
	            return false;
	        }

	        String[] parts = ip.split("\\.");
	        if (parts.length != 4) {
	            return false;
	        }

	        for (String s : parts) {
	            int i = Integer.parseInt(s);
	            if ((i < 0) || (i > 255)) {
	                return false;
	            }
	        }
	        if (ip.endsWith(".")) {
	            return false;
	        }
	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
	
	public static boolean validNum(String number) {
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
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