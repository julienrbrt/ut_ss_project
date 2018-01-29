package game.player;

import game.*;

/**
 * Manages the random AI.
 * @author Richard 
 * @author Julien
 * @version 0.1
 */

public class RandomStrategy implements Strategy {
	
	int playerNumber;
	
	/**
	 * Constructor for RandomStrategy.
	 * @param playerNumber ID of this Player
	 */
	
	public RandomStrategy(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	/**
	 * Getter for name of this Strategy.
	 * @return Name of this Strategy
	 */
	
	public String getName() {
		return "Random";
	}
	
	/**
     * Generates coordinates for the starting base.
	 * <p>
	 * Should only be called once per game, at the beginning of the game.
	 * @param board Board to determine the base off of
	 * @return Array of coordinates to place the starting base on
     */
	
	public int[] determineBase(Board board) {
		int[] result = new int[2];
		result[0] = (int) Math.floor(Math.random() * 3) + 1;	//get a random int between 0 and 4
		result[1] = (int) Math.floor(Math.random() * 3) + 1;
		return result;
	}
	
	/**
	 * Generates a valid move, or an empty array if no moves are possible.
	 * @param board Board to determine move on
	 * @param colorAmount Amount of Colors this Player possesses
	 * @param firstColor Primary Color of this Player
	 * @param secondColor Secondary Color of this Player, if applicable. Otherwise null
	 * @return Array of possible moves
	 */
	
    public Object[] determineMove(Board board,
    		int colorAmount, Color firstColor, Color secondColor) {
    	 
    	boolean multipleColors = colorAmount == 2;
    	int firstArray;
    	int[] moves;
    	Object[] result;
    	
    	//setup
    	if (multipleColors) {
    		int[] tempMoves = board.getPossibleMoves(firstColor, playerNumber);
    		firstArray = tempMoves.length;
    		int[] tempMoves2 = board.getPossibleMoves(secondColor, playerNumber);
    		moves = new int[firstArray + tempMoves2.length];
    		for (int i = 0; i < moves.length; i++) {	//concatenate arrays
    			if (i < firstArray) {
    				moves[i] = tempMoves[i];
    			}
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
    	result[2] = move % 10 == 4;
    	result[3] = (move % 10 == 4) ? 3 : (move % 10);
    	
    	if (multipleColors && choice >= firstArray) {
    		result[4] = secondColor;
    	} else {
    		result[4] = firstColor;
    	}
    	board.hasRing((boolean) result[2], (int) result[3], (Color) result[4], playerNumber, true);
    	return result;
    	
    }
}