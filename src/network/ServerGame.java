package network;

import java.util.List;

import javax.swing.JOptionPane;

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
	private Player[] playerList;
	private List<ServerPeer> players;
    private int currentPlayer;
    private boolean firstPlayer = true;
	private boolean[] gotSkipped;
	private ClientGame color; // used only for getting the color.
	
	public ServerGame(List<ServerPeer> players) {
		
		this.players = players;
		int amountPlayer = players.size();
		playerList = new Player[amountPlayer];
		
		int position = 0;

		if (amountPlayer < 4) {			
			for (ServerPeer player : players) {
				playerList[position] = new DistantPlayer(player.getPlayerName(),
						color.getColor(position, 1, amountPlayer),
						color.getColor(position, 2, amountPlayer), position, player);
				position++;
			}
		} else {
			for (ServerPeer player : players) {
				playerList[position] = new DistantPlayer(player.getPlayerName(),
						color.getColor(position, 0, amountPlayer), position, player);
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

	/**
	 * Play the game online.
	 */
	public void play() {
		while (!board.gameOver(gotSkipped)) {
			
    		int checkNo = (playerList[currentPlayer].getColor()[1] != null) ?
					board.getPossibleMoves(playerList[currentPlayer].getColor()[0],
							currentPlayer).length +
					board.getPossibleMoves(playerList[currentPlayer].getColor()[1],
							currentPlayer).length : 
					board.getPossibleMoves(playerList[currentPlayer].getColor()[0],
							currentPlayer).length;
			
			// Skip the current player if cannot not again.
			if (checkNo == 0) {
				gotSkipped[currentPlayer] = true;
			}
					
			if (!gotSkipped[currentPlayer]) {
				playerList[currentPlayer].getServerPeer().
					moverequest(playerList[currentPlayer].getName());
	    		currentPlayer = (currentPlayer + 1) % playerList.length;
			}
		}
		
		// add info about winner
    	WinConditions win = new WinConditions(board);
    	win.calculate();
    	int[] scores = win.getScores();
    	int winner = board.getWinner(scores);
    	 	
    	// Send corresponding winner
    	if (winner < 4) {
    		for (ServerPeer player : players) {
    			player.gameover(playerList[winner].getName());
    		}
    	} else {
    		StringBuilder tie = new StringBuilder();
    		for (int x = 0; x < playerList.length; x++) {
    			tie.append(playerList[x].getName() + " ");
    		}
    		for (ServerPeer player : players) {
    			player.gameover(tie.toString());
    		}
    	}
		
	}
	
	/**
	 * Check move validity.
	 * @param x, position x on the board
	 * @param y, position y on the board
	 * @param size, size of the ring
	 * @param color, color number of the user
	 * @return if the user can place or not
	 */
	public boolean askMove(int x, int y, int size, int colorUsed) {
		
		boolean base = false;
		int setSize = 0;
		Color setColor;
		if (size == 5) {
			base = true;
		} else {
			setSize = size;
		}
		
		if (colorUsed == 1) {
			setColor = playerList[currentPlayer].getColor()[0];
		} else {
			setColor = playerList[currentPlayer].getColor()[1];
		}
		
		return board.canPlace(x, y, base, setSize, setColor, currentPlayer);		
	}
	
	/**
	 * Send move to every player.
	 * @param x, position x on the board
	 * @param y, position y on the board
	 * @param size, size of the ring
	 * @param color, color number of the user
	 */
	public void makeMove(int x, int y, int size, int colorUsed) {
		
		boolean base = false;
		int setSize = 0;
		Color setColor;
		if (size == 5) {
			base = true;
		} else {
			setSize = size;
		}
		
		if (colorUsed == 1) {
			setColor = playerList[currentPlayer].getColor()[0];
		} else {
			setColor = playerList[currentPlayer].getColor()[1];
		}
		
		if (firstPlayer) {
			board.addHome(x, y);
			firstPlayer = false;
		} else {
			board.addRing(x, y, base, setSize, setColor);
		}

		for (ServerPeer player : players) {
			player.notifymove(x, y, size, colorUsed);
		}
	}
}