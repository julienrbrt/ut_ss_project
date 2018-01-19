package game.player;

import game.*;

/**
 * This class manage the random IA of the Ringgz game.
 * @author Richard 
 * @author Julien
 * @version 0.1
 */

public class RandomStrategy implements Strategy {
	
	/**
	 * @see game.player.Strategy#getName()
	 * @return the name of the Strategy
	 */
	public String getName() {
		return "Random";
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
	
	/*public int[] determineBase(Board board, Color firstColor) {
    	int x = (int) Math.round(Math.random() * (Board.SIZE - 1));
    	int y = 0;
    	
  	    if (board.canPlace(x, y, true, 0, firstColor)) {
	        int[] choice = {x, y};
	        return choice;
  	    } else {
        	while (!(board.canPlace(x, y, true, 0, firstColor))) {
        		if (y < Board.SIZE - 1) {
    				y++;
    			} else if (y == Board.SIZE) {
    				y--;
        		} else if (x == Board.SIZE) {
        			x--;
        		}
        	}
	        int[] choice = {x, y};
	        return choice;
        }
	}
	
	public int[] determineBase(Board board, Color firstColor, Color secondColor) {
    	int x = (int) Math.round(Math.random() * (Board.SIZE - 1));
    	int y = 0;
    	
  	    if (board.canPlace(x, y, true, 0, firstColor)) {
	        int[] choice = {x, y};
	        return choice;
  	    } else if (secondColor != null && board.canPlace(x, y, true, 0, secondColor)) {
	    	int[] choice = {x, y};
	    	return choice;
        } else {
        	while (!(board.canPlace(x, y, true, 0, firstColor) || (secondColor != null && board.canPlace(x, y, true, 0, secondColor)))) {
        		if (y < Board.SIZE - 1) {
    				y++;
    			} else if (y == Board.SIZE) {
    				y--;
        		} else if (x == Board.SIZE) {
        			x--;
        		}
        	}
	        int[] choice = {x, y};
	        return choice;
        }
	}*/
	
	/*public Object[] determineMove(Board board, Color firstColor) {
        
    	int x = (int) Math.round(Math.random() * (Board.SIZE - 1));
    	int y = 0;
    	boolean base = false;
    	int ringSize = 0;
    	
  	    if (board.canPlace(x, y, base, ringSize, firstColor)) {
  	        Object[] choice = {x, y, base, ringSize};
	        return choice;
  	    } else {
        	while (!(board.canPlace(x, y, true, 0, firstColor))) {
        		if (y < Board.SIZE - 1) {
    				y++;
    			} else if (y == Board.SIZE) {
    				y--;
        		} else if (x == Board.SIZE) {
        			x--;
        		} else if (ringSize == Board.SIZE) {
        			ringSize--;
        		} else if (base || !base) {
        			base = !base;
        		}
        	}
            Object[] choice = {x, y, base, ringSize};
	        return choice;
        }
    }*/
	
	
    public Object[] determineMove(Board board, int colorAmount, Color firstColor, Color secondColor) {
    	 
    	boolean multipleColors = (colorAmount == 2);
    	int firstArray;
    	int[] moves;
    	Object[] result;
    	
    	//setup
    	if (multipleColors) {
    		int[] tempMoves = board.getPossibleMoves(firstColor);
    		firstArray = tempMoves.length;
    		int[] tempMoves2 = board.getPossibleMoves(secondColor);
    		moves = new int[firstArray + tempMoves2.length];
    		for (int i = 0; i < firstArray; i++) {	//concatenate arrays
    			moves[i] = tempMoves[i];
    			if (i < tempMoves2.length) {
    				moves[firstArray + i] = tempMoves2[i];
    			}
    		}
    	} else {
    		moves = board.getPossibleMoves(firstColor);
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
    	result[3] = move % 10;
    	
    	if (multipleColors && choice >= firstArray) {
    		result[4] = secondColor;
    	} else {
    		result[4] = firstColor;
    	}
    	
    	return result;
    	
    	/*int x = (int) Math.random() * (Board.SIZE - 1);
    	int y = 0;
    	boolean base = false;
    	int ringSize = 0;
    	
  	    if (board.canPlace(x, y, base, ringSize, firstColor)) {
  	        Object[] choice = {x, y, base, ringSize};
	        return choice;
  	    } else if (secondColor != null && board.canPlace(x, y, base, ringSize, secondColor)) {
	        Object[] choice = {x, y, base, ringSize};
	    	return choice;
        } else {
        	while (!(board.canPlace(x, y, base, ringSize, firstColor) && !(secondColor != null && board.canPlace(x, y, base, ringSize, secondColor)))) {
        		base = Math.round(Math.random() * 100) <= 50;
            	x = (int) Math.abs(Math.random() * (Board.SIZE - 1));
            	y = (int) Math.abs(Math.random() * (Board.SIZE - 1));
            	ringSize = (int) Math.abs(Math.random() * (Board.SIZE - 2));
        	}
            Object[] choice = {x, y, base, ringSize};
	        return choice;
        }*/
    }
}