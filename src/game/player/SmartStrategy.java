package game.player;

import game.*;

/**
 * This class manage the random IA of the Ringgz game.
 * @author Richard 
 * @author Julien
 * @version 0.1
 */

public class SmartStrategy implements Strategy {
	
	int playerNumber;
	
	public SmartStrategy(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	/**
	 * @see game.player.Strategy#getName()
	 * @return the name of the Strategy
	 */
	public String getName() {
		return "Smart";
	}
	
	/**
	 * @see game.player.Strategy#determineMove(game.Board, game.player.Color)
	 * @return the randomly generated choice of the move of the IA 
	 */
	
	public int[] determineBase(Board board) {
		int[] result = new int[2];
		result[0] = (int) Math.floor(Math.random() * 3) + 1;	//get a random int between 0 and 4
		result[1] = (int) Math.floor(Math.random() * 3) + 1;
		return result;
	}
	
    public Object[] determineMove(Board board, int colorAmount, Color firstColor, Color secondColor) {
    	 
    	boolean multipleColors = (colorAmount == 2);
    	int firstArray;
    	int[] moves;
    	Object[] result;
    	
    	//setup
    	if (multipleColors) {
    		int[] tempMoves = board.getPossibleMoves(firstColor, playerNumber);
    		firstArray = tempMoves.length;
    		int[] tempMoves2 = board.getPossibleMoves(secondColor, playerNumber);
    		moves = new int[firstArray + tempMoves2.length];
    		for (int i = 0; i < firstArray; i++) {	//concatenate arrays
    			moves[i] = tempMoves[i];
    			if (i < tempMoves2.length) {
    				moves[firstArray + i] = tempMoves2[i];
    			}
    		}
    	} else {
    		moves = board.getPossibleMoves(firstColor, playerNumber);
    		firstArray = moves.length;
    	}
    	
    	if (moves.length == 0) {	//return empty array if no moves possible
    		result = new Object[0];
    		return result;
    	}
    	
    	int choice = (int) Math.floor(Math.random() * moves.length);
    	int move = moves[choice];
    	result = new Object[5];
    	result[0] = (int) move / 100;
    	result[1] = (int) (move % 100) / 10;
    	result[2] = (move % 10 == 4);
    	result[3] = (move % 10 == 4) ? 3 : (move % 10);
    	
    	if (multipleColors && choice >= firstArray) {
    		result[4] = secondColor;
    	} else {
    		result[4] = firstColor;
    	}
    	
    	return result;
    
    }
}