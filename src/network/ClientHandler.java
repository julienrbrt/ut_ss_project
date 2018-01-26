package network;

import java.util.*;

/**
 * This class for defining all the method used in the Client of the game.
 * For comment see Protocol.java
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class ClientHandler implements Protocol  {
	
	public static List<String> players;

    public String joinrequest(String username) {
    	players.add(username);
    	return CLIENT_JOINREQUEST + " " + username + " 0 0 0 0";
    }
    
    public String gamerequest(int playersNum) {
    	return CLIENT_GAMEREQUEST + " " + playersNum;
    }
    
    public String setmove(int x, int y, int size, int color) {
    	return CLIENT_SETMOVE + " " + x + " " + y + " " + size + " " + color;
    }
    
    public String disconnect() {
    	return "disconnect";
    }
      
    public List<String> getPlayer() {
    	return players;
    }
    
}