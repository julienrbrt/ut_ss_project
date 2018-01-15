package game;

import game.player.*;

public class Offline {

	public static void main(String[] args) {
		/*if (args.length > 1) {
			Player p0, p1, p2, p3;
			
		    if (args[0].equals("-S")) {
		    	if (args.length == 2) {
		    		p0 = new ComputerPlayer(new SmartStrategy(), Color.REDDD, Color.YELLO);
		    	} else {
				    p0 = new ComputerPlayer(new SmartStrategy(), Color.REDDD);
		    	}
		    } else if (args[0].equals("-R")) {
		    	if (args.length == 2) {
		    		p0 = new ComputerPlayer(new RandomStrategy(), Color.REDDD, Color.YELLO);
		    	} else {
		    		p0 = new ComputerPlayer(new RandomStrategy(), Color.REDDD);
		    	}
		    } else {
		    	if (args.length == 2) {
				    p0 = new HumanPlayer(args[0], Color.REDDD, Color.YELLO);
		    	} else {
				    p0 = new HumanPlayer(args[0], Color.REDDD);
		    	}
		    }
			  
		    if (args[1].equals("-S")) {
		    	if (args.length == 2) {
		    		p1 = new ComputerPlayer(new SmartStrategy(), Color.GREEN, Color.BLUEE);
		    	} else {
			    	p1 = new ComputerPlayer(new SmartStrategy(), Color.GREEN);
		    	}
		    } else if (args[1].equals("-R")) {
		    	if (args.length == 2) {
		    		p1 = new ComputerPlayer(new RandomStrategy(), Color.GREEN, Color.BLUEE);
		    	} else {
		    		p1 = new ComputerPlayer(new RandomStrategy(), Color.GREEN);
		    	}
		    } else {
		    	if (args.length == 2) {
				    p1 = new HumanPlayer(args[0], Color.GREEN, Color.BLUEE);
		    	} else {
				    p1 = new HumanPlayer(args[0], Color.GREEN);
		    	}
		    }
		    
		    if (args.length >= 3) {
		    	if (args[2].equals("-S")) {
			    	p2 = new ComputerPlayer(new SmartStrategy(), Color.YELLO);
			    } else if (args[2].equals("-R")) {
		    		p2 = new ComputerPlayer(new RandomStrategy(), Color.YELLO);
			    } else {
			    	p2 = new HumanPlayer(args[2], Color.YELLO);
			    }
		    } else {
		    	p2 = null;
		    }
		    
		    if (args.length == 4) {
			    if (args[3].equals("-S")) {
			    	p3 = new ComputerPlayer(new SmartStrategy(), Color.BLUEE);
			    } else if (args[3].equals("-R")) {
		    		p3 = new ComputerPlayer(new RandomStrategy(), Color.BLUEE);
			    } else {
			    	p3 = new HumanPlayer(args[3], Color.BLUEE);
			    }
		    } else {
		    	p3 = null;
		    }
			  
		    Game game = new Game(p0, p1, p2, p3);
		    game.start();
		} else {
			System.out.println("This game needs a minimum of two players. Try again.");
		}*/
		
		if (args.length > 1 && args.length < 5) {
			Player[] players = new Player[args.length];
			int j = 0;
			Color color = Color.NONEE;	//initiated to make static calls
			
			if (args.length == 2) {
				for (int i = 0; i < args.length; i++) {
					switch (args[i]) {
					case "-S":
						players[i] = new ComputerPlayer(new SmartStrategy(), color.getColor(j), color.getColor(j+1));
						j += 2;
						break;
					case "-R":
						players[i] = new ComputerPlayer(new RandomStrategy(), color.getColor(j), color.getColor(j+1));
						j += 2;
						break;
					default:
						players[i] = new HumanPlayer(args[i], color.getColor(j), color.getColor(j+1));
						j += 2;
						break;
					}
				}
			} else if (args.length == 3) {
				for (int i = 0; i < args.length; i++) {
					switch (args[i]) {
					case "-S":
						players[i] = new ComputerPlayer(new SmartStrategy(), color.getColor(i), Color.YELLO);
						break;
					case "-R":
						players[i] = new ComputerPlayer(new RandomStrategy(), color.getColor(i), Color.YELLO);
						break;
					default:
						players[i] = new HumanPlayer(args[i], color.getColor(i), Color.YELLO);
						break;
					}
				}
			} else {
				for (int i = 0; i < args.length; i++) {
					switch (args[i]) {
					case "-S":
						players[i] = new ComputerPlayer(new SmartStrategy(), color.getColor(i));
						break;
					case "-R":
						players[i] = new ComputerPlayer(new RandomStrategy(), color.getColor(i));
						break;
					default:
						players[i] = new HumanPlayer(args[i], color.getColor(i));
						break;
					}
				}
			}
			
			Game game = new Game(players);
			game.start();
		} else {
			System.out.println("This game needs a minimum of two players and a maximum of 4 players. Try again.");
		}
	}
}
