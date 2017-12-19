/**
 * This class is a tooling class for the Ringgz game.
 * @author Theo Ruys en Arend Rensink
 * @author Richard
 * @author Julien
 * @version 0.1
 */

package gui;

public class Tools {
	public static boolean validIP(String ip) {
	    try {
	        if (ip == null || ip.isEmpty()) {
	            return false;
	        }

	        String[] parts = ip.split( "\\." );
	        if ( parts.length != 4 ) {
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
}