package network;

import java.util.*;

import game.*;

/**
 * This class for defining all the method used in the Server of the game.
 * For comment see Protocol.java
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class ServerHandler implements Protocol {
	
    public ClientHandler client;
	List<String> players = client.getPlayer();
	Board board;
	

	public String acceptjoin() {
		return SERVER_ACCEPTJOIN + " 0 0 0 0";
	}
	
	public String denyjoin(String username) {
		return SERVER_DENYJOIN + " " + username;
	}
	
	public String startgame(String[] usernames) {
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int x = 0; x < usernames.length; x++) {
			stringBuilder.append(usernames[x] + " ");
		}
	
		return SERVER_STARTGAME + stringBuilder.toString();
	}
	
	public String moverequest(String username) {
		return SERVER_MOVEREQUEST + " " + username;
	}
	
	public String denymove(String username) {
		return SERVER_DENYMOVE + " " + username;
	}
	
	public String notifymove(int x, int y, int size, int color) {
		return SERVER_NOTIFYMOVE + " " + x + " " + y + " " + size + " " + color;
	}
	
	public String gameover(String[] winners) {
		
		// When tie
		if (winners.length > 1) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int x = 0; x < winners.length; x++) {
				stringBuilder.append(winners[x] + " ");
			}
		}

		return SERVER_GAMEOVER + " " + winners;
	}
	
	public String connectionlost(String username) {
		return SERVER_CONNECTIONLOST + " " + username;
	}
	
	public String invalidcommand() {
		return SERVER_INVALIDCOMMAND;
	}
	
}