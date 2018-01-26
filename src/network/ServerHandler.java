package network;

import java.io.*;
import java.net.*;
import java.util.*;

import game.*;
import game.player.Color;

/**
 * This class for defining all the method used in the Server of the game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class ServerHandler implements Protocol {
	
    public ClientHandler client;
	List<String> players = client.getPlayer();
	Board board;
	
	/**
	* The server notifies the clients of the move that was performed.
	*/
	public String notifyMove(int x, int y) {
        if (x >= Board.SIZE || y >= Board.SIZE) {
        	return error();
        }
		boolean valid = board.isField(x, y, board.getY(x, y))
        		&& board.isEmpty(x, z, board.getY(x, y));
		if (valid) {
			return "NotifyMove " + x + " " + y;
		} else {
			return error();
		}
		
	}
	    
	/**
	* The server notifies the clients that the game has started.
	*/
	public String startGame() {
		return players.get(0) + " " + players.get(1);
	}
	    
	/**
	* The server notifies the clients that the game is over.
	*/
	public String gameOver() {
	    if (board.gameOver()) {
	    	if (board.isWinner(Mark.BB)) {
	    		return "Winner" + players.get(0);
	    	} else {
	    		return "Winner" + players.get(1);
	    	}
	    }
	    return error();
	}
	 
	/**
	* The server notifies the client of an error that occurred.
	*/
	public String error() {
		return "Something wrong happened";
	}
	
}