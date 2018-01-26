package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.Game;
import game.player.*;
import game.player.Color;

// Start Offline Game Session
public class ALGSStartGame implements ActionListener {
	
	JFrame frameGS;
	JPanel aiSelect;
	String playerName;
	HumanUI gui = new HumanUI();
	
	public ALGSStartGame(JFrame frame, JPanel panel, String name) {
		this.frameGS = frame;
		this.aiSelect = panel;
		this.playerName = name;
	}
	
	Player[] aiPlayers;
	Player[] players;
	
	public void actionPerformed(ActionEvent e) {
	
		GameSessionAISelect selectAI = (GameSessionAISelect) aiSelect;
		GameSessionAISelect.AI ai = selectAI.new AI();
		
		Thread threadAI = new Thread(ai);
		threadAI.start();

		ai.addObserver(new Observer() {
            public void update(Observable obj, Object arg) {
            		            	
                aiPlayers = (Player[]) arg;
               
    			players = new Player[aiPlayers.length + 1];
    			
    			if (players.length == 2 || players.length == 3) {
    				players[0] = new HumanPlayerUI(playerName, Color.REDDD, Color.YELLO, 0, gui);
    			} else if (players.length == 4) {
    				players[0] = new HumanPlayerUI(playerName, Color.REDDD, 0, gui);
    			}
    			
    			if ((players.length == 2 || players.length == 3) && playerName.equals("SMART")) {
    				players[0] = new ComputerPlayer(new SmartStrategy(0), Color.REDDD, Color.YELLO, 0);
    			} else if (players.length == 4 && playerName.equals("SMART")) {
    				players[0] = new ComputerPlayer(new SmartStrategy(0), Color.REDDD, 0);
    			}
    			
    			if ((players.length == 2 || players.length == 3) && playerName.equals("RANDOM")) {
    				players[0] = new ComputerPlayer(new RandomStrategy(0), Color.REDDD, Color.YELLO, 0);
    			} else if (players.length == 4 && playerName.equals("RANDOM")) {
    				players[0] = new ComputerPlayer(new RandomStrategy(0), Color.REDDD, 0);
    			}
    			
    			for (int x = 1; x < aiPlayers.length + 1; x++) {
    				players[x] = aiPlayers[x - 1];
    			}
    			
            }
        });
		
		if (players == null) {
			// do nothing
		} else if (players.length < 2) { // Check Minimum players
        	JOptionPane.showMessageDialog(null,
        			"A minimum of two players is required to play offline.");
		} else {
			// Close Window
			frameGS.dispose();
			Game game = new Game(players, gui);
			game.play();
		}
	}
}