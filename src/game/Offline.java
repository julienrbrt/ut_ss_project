package game;

import game.player.Color;
import game.player.ComputerPlayer;
import game.player.HumanPlayer;
import game.player.Player;
import game.player.RandomStrategy;

public class Offline {

	public static void main(String[] args) {
		if (args.length >= 2) {
			Player p0, p1, p2, p3;
			
		    if (args[0].equals("-D")) {
		    	if (args.length == 2) {
		    		p0 = new ComputerPlayer(Color.REDDD, Color.YELLO);
		    	} else {
				    p0 = new ComputerPlayer(Color.REDDD, null);
		    	}
		    } else if (args[0].equals("-R")) {
		    	if (args.length == 2) {
		    		p0 = new ComputerPlayer(new RandomStrategy(), Color.REDDD, Color.YELLO);
		    	} else {
		    		p0 = new ComputerPlayer(new RandomStrategy(), Color.REDDD, null);
		    	}
		    } else {
		    	if (args.length == 2) {
				    p0 = new HumanPlayer(args[0], Color.REDDD, Color.YELLO);
		    	} else {
				    p0 = new HumanPlayer(args[0], Color.REDDD, null);
		    	}
		    }
			  
		    if (args[1].equals("-D")) {
		    	if (args.length == 2) {
		    		p1 = new ComputerPlayer(Color.GREEN, Color.BLUEE);
		    	} else {
			    	p1 = new ComputerPlayer(Color.GREEN, null);
		    	}
		    } else if (args[1].equals("-R")) {
		    	if (args.length == 2) {
		    		p1 = new ComputerPlayer(new RandomStrategy(), Color.GREEN, Color.BLUEE);
		    	} else {
		    		p1 = new ComputerPlayer(new RandomStrategy(), Color.GREEN, null);
		    	}
		    } else {
		    	if (args.length == 2) {
				    p1 = new HumanPlayer(args[0], Color.GREEN, Color.BLUEE);
		    	} else {
				    p1 = new HumanPlayer(args[0], Color.GREEN, null);
		    	}
		    }
		    
		    if (args.length >= 3) {
		    	if (args[2].equals("-D")) {
			    	p2 = new ComputerPlayer(Color.YELLO, null);
			    } else if (args[2].equals("-R")) {
		    		p2 = new ComputerPlayer(new RandomStrategy(), Color.YELLO, null);
			    } else {
			    	p2 = new HumanPlayer(args[2], Color.YELLO, null);
			    }
		    } else {
		    	p2 = null;
		    }
		    
		    if (args.length == 4) {
			    if (args[3].equals("-D")) {
			    	p3 = new ComputerPlayer(Color.BLUEE, null);
			    } else if (args[3].equals("-R")) {
		    		p3 = new ComputerPlayer(new RandomStrategy(), Color.BLUEE, null);
			    } else {
			    	p3 = new HumanPlayer(args[3], Color.BLUEE, null);
			    }
		    } else {
		    	p3 = null;
		    	
		    }
			  
		    Game game = new Game(p0, p1, p2, p3);
		    game.start();
		} else {
			System.out.println("This game needs a minimum of two players. Try again.");
		}
	}
}
