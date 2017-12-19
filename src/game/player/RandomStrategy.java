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
	
	public int[] determineBase(Board board, Color firstColor, Color secondColor) {
    	int x = (int) Math.round(Math.random() * (Board.SIZE - 1));
    	int y = 0;
    	
  	   if(board.canPlace(x, y, true, 0, firstColor)) {
	        int[] choice = {x, y};
	        return choice;
  	   }
	    else if (secondColor != null && board.canPlace(x, y, true, 0, secondColor)) {
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
	}
	
    public Object[] determineMove(Board board, Color firstColor, Color secondColor) {
    	        
    	int x = (int) Math.round(Math.random() * (Board.SIZE - 1));
    	int y = 0;
    	boolean base = false;
    	int ringSize = 0;
    	
  	   if(board.canPlace(x, y, base, ringSize, firstColor)) {
  	        Object[] choice = {x, y, base, ringSize};
	        return choice;
  	   }
	    else if (secondColor != null && board.canPlace(x, y, base, ringSize, secondColor)) {
	        Object[] choice = {x, y, base, ringSize};
	    	return choice;
        } else {
        	while (!(board.canPlace(x, y, true, 0, firstColor) || (secondColor != null && board.canPlace(x, y, true, 0, secondColor)))) {
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
    }
}