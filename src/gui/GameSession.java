package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import game.Game;
import game.player.*;
import game.player.Color;

// Observer - Observable
import java.util.Observable;
import java.util.Observer;
import gui.GameSessionAISelect.AI;

public class GameSession extends JFrame {
	
	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = -3990482348703734954L;

	// GUI handling	
	Container c = getContentPane();

	JPanel title = new GameSessionBasic();
	JPanel aiSelect = new GameSessionAISelect();
	JPanel serverSettings = new GameSessionServerSettings();

	JCheckBox onlineOff = new JCheckBox();
	
	// Player handling
	Color color = Color.NONEE;	//initiated to make static calls
	
	public GameSession() {
		init();
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 350);
		setTitle("Ringzz");
		setResizable(false);
		setVisible(true);
		// Center Window
		setLocationRelativeTo(null);
	}
	
	public void init() {
		
		// Layout fix
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		serverSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		serverSettings.setPreferredSize(new Dimension(340, 340));
		serverSettings.setMaximumSize(new Dimension(340, 340));
		aiSelect.setAlignmentX(Component.CENTER_ALIGNMENT);
		aiSelect.setPreferredSize(new Dimension(340, 340));
		aiSelect.setMaximumSize(new Dimension(340, 340));
		
		// Set name text field
		title.setPreferredSize(new Dimension(300, 75));
		c.add(title);
		
		// Add Server settings and center
		c.add(serverSettings);
		
		// Set offline switch
		onlineOff = new JCheckBox("Offline");
		onlineOff.addActionListener(new OnlineOff());
		c.add(onlineOff);
	
	}
	
	// Starting game handler
	public class StartGame implements ActionListener {
		
		Player[] aiPlayers;
		Player[] players;
		
		public void actionPerformed(ActionEvent e) {
			
		
			GameSessionAISelect selectAI = (GameSessionAISelect) aiSelect;
			AI ai = selectAI.new AI();

			ai.addObserver(new Observer() {
	            public void update(Observable obj, Object arg) {
	            		            	
	                aiPlayers = (Player[]) arg;
	               
	    			players = new Player[aiPlayers.length + 1];
	    			
	    			if (players.length == 2) {
	    				players[0] = new HumanPlayer("Name", Color.REDDD, Color.YELLO, 0);
	    			} else if (players.length == 3) {
	    				players[0] = new HumanPlayer("Name", Color.REDDD, Color.YELLO, 0);
	    			} else {
	    				players[0] = new HumanPlayer("Name", Color.REDDD, 0);
	    			}
	    			
	    			for (int x = 1; x < aiPlayers.length + 1; x++) {
	    				players[x] = aiPlayers[x - 1];
	    			}
	    			
	            }
	        });
			
			new Thread(ai).start();
			
			if (players.length < 2) { // Check Minimum players
	        	JOptionPane.showMessageDialog(null, "A minimum of two players is required to play offline.");
			} else {
				// Close Window
				dispose();
				Game game = new Game(players);
				game.play();
			}
		}
	}

	// Online / Offline management
	public class OnlineOff implements ActionListener {

		public void actionPerformed(ActionEvent e) {
						
			if (onlineOff.isSelected()) {
				
				// Remove Server selection
				c.remove(serverSettings);
				
				// Add AI Selection
				c.add(aiSelect);
				
				// Offline always bottom-1
				c.remove(onlineOff);
				c.add(onlineOff);
								
				// Refresh the frame
				revalidate();
				repaint();
				
			} else {
				
				// Add Server selection
				c.remove(serverSettings); // remove to be sure
				c.add(serverSettings);
				
				// Remove AI selection
				c.remove(aiSelect);
				
				// Offline always bottom-1
				c.remove(onlineOff);
				c.add(onlineOff);
				
				// Refresh the frame
				revalidate();
				repaint();
			}
			
		}
		
	}
}