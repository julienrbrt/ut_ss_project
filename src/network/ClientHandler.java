package network;

import java.io.*;
import java.net.*;
import java.util.*;

import game.*;
import game.player.*;

/**
 * This class for defining all the method used in the Client of the game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

// MVC (Controller)
public class ClientHandler implements Protocol  {
	
	public static List<String> players;
	Board board = new game.Board();

    /**
     * Connected clients use it to enter the lobby.
     * @return Join and the user username
     */
    public String join(String username) {
    	players.add(username);
    	return "Join " + username;
    }
    
    /**
     * Clients in the lobby use it to start a game. When the required number of
	players are “ready”, the game will begin.
	* @return Ready and the number of players
     */
    public String ready() {
    	if (players.size() % 2 != 0) {
    		return "Ready 2";
    	} else {
    		return "You have to be two to play the game";
    	}
    }
    
    /**
     * A client leaves the game. When this happens, all the players go to the lobby.
     * Not implemented, leaving quit the client
     */
    public String leave() {
    	disconnect();
    	return "Leave";
    }
    
    /**
     * A client can use this command to disconnect from the server.
     */
    public void disconnect() {
    }
    
    /**
     * The player notifies the server of the move he wishes to perform.
     * @return the move done by the user
     */
    public String move(int x, int z) {
    	return "Move " + x + " " + z;
    }    
    
    /**
     * @return list of players
     */
    public List<String> getPlayer() {
    	return players;
    }
    
}