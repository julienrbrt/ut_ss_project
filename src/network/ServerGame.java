package network;

import java.util.List;

import game.*;

/**
 * Class managing the game for a Server interaction with clients. 
 * @author Richard
 * @author Julien
 *
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
	
	// TODO
	/*
	 * Ask for move
	 * determine if move correct - send if not and wait
	 * notify everyone
	 * if game over send it
	 * manage color here too
	 */
	
	public void play() {
    	while (!board.gameOver(gotSkipped)) {
    	
    		
    	}
		
	}
}