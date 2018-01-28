package network;

import java.util.List;

import game.*;

/**
 * This class for defining all the method used in the Server of the game.
 * For comment see Protocol.java
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class ServerGame {
	
	private Board board;
    private int currentPlayer;
	private boolean[] gotSkipped;
	
	public ServerGame(List<ServerPeer> players) {
		
//        board = new Board(players.size(), players);
    	
        currentPlayer = 0;
     	gotSkipped = new boolean[players.size()];
    	// Nobody skipped at first
    	for (int i = 0; i < gotSkipped.length; i++) {
    		gotSkipped[i] = false;
    	}
	}
	
	public void play() {
    	while (!board.gameOver(gotSkipped)) {
    	
    		
    	}
		
	}
}