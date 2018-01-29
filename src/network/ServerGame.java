package network;

import java.util.List;

import game.*;
import game.player.*;

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
	private ClientGame color; // used only for getting the color.
	
	public ServerGame(List<ServerPeer> players) {
		
		int amountPlayer = players.size();
		Player[] playerList = new Player[amountPlayer];
		
		int amountColor = 2;
		int position = 0;

		if (amountPlayer < 4) {			
			amountColor = 1;
			for (ServerPeer player : players) {
				playerList[position] = new DistantPlayer(player.getPlayerName(),
						color.getColor(position, 1, amountPlayer),
						color.getColor(position, 2, amountPlayer), position);
				position++;
			}
		} else {
			for (ServerPeer player : players) {
				playerList[position] = new DistantPlayer(player.getPlayerName(),
						color.getColor(position, 0, amountPlayer), position);
				position++;
			}
		}
		
        board = new Board(amountPlayer, playerList);
    	
        currentPlayer = 0;
     	gotSkipped = new boolean[amountPlayer];
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
	 */
	
	public void play() {
    	while (!board.gameOver(gotSkipped)) {
    	
    		
    	}
		
	}
}