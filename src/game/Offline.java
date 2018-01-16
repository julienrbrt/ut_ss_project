package game;

import game.player.*;

public class Offline {

	public static void main(String[] args) {
		
		if (args.length > 1 && args.length < 5) {
			Player[] players = new Player[args.length];
			int j = 0;
			Color color = Color.NONEE;	//initiated to make static calls
			
			if (args.length == 2) {
				for (int i = 0; i < args.length; i++) {
					switch (args[i]) {
						case "-S":
							players[i] = new ComputerPlayer(new SmartStrategy(), color.getColor(j), color.getColor(j + 1));
							j += 2;
							break;
						case "-R":
							players[i] = new ComputerPlayer(new RandomStrategy(), color.getColor(j), color.getColor(j + 1));
							j += 2;
							break;
						default:
							players[i] = new HumanPlayer(args[i], color.getColor(j), color.getColor(j + 1));
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
			game.play();
		} else {
			System.out.println("This game needs a minimum of two players and a maximum of 4 players. Try again.");
		}
	}
}
