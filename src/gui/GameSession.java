package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import game.ColorUI;
import game.Game;
import game.player.*;
import game.player.Color;

public class GameSession extends JFrame {

	/**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = -3990482348703734954L;

	
	// GUI handling
	JTextField name;;
	JTextField[] serverSettings = new JTextField[2];
	JButton startGame = new JButton();
	JCheckBox onlineOff = new JCheckBox();
	JCheckBox[] aiSelect = new JCheckBox[6];
	Container c = getContentPane();
	
	
	// Player handling
	Player[] players;
	
	public GameSession() {
		init();
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 350);
		setTitle("Ringzz");
		setResizable(false);
		setVisible(true);
	}
	
	public void init() {
		c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
				
		// Set name text field
		JLabel l = new JLabel("Name: ", JLabel.TRAILING);
		c.add(l);
		name = new JTextField();
		name.setPreferredSize(new Dimension(100, 25));
		l.setLabelFor(name);
		c.add(name);

		// Set start button
		startGame = new JButton();
		startGame.setText("Start");
		startGame.addActionListener(new StartGame());
		c.add(startGame);
		
		// Set offline switch
		onlineOff = new JCheckBox("Offline");
		c.add(onlineOff);
		onlineOff.addActionListener(new OnlineOff());
	
		// Layout panel
	}
	
	// Starting game handler
	public class StartGame implements ActionListener {
		
		public StartGame() {
			
		}
		
		public void actionPerformed(ActionEvent e) {
			Game game = new Game(players);
			game.play();
			// Close Window
			dispose();
		}
	}

	// Online / Offline management
	public class OnlineOff implements ActionListener {
		
		JLabel l;
		String[] labels = {"IP: ", "Port: "};
		
		public OnlineOff() {
		}
		
		public void actionPerformed(ActionEvent e) {
			
			if (onlineOff.isSelected()) {
				// Generate Random AI buttons
				for (int x = 0; x < aiSelect.length / 2; x++) {
					aiSelect[x] = new JCheckBox();
					aiSelect[x].setText("" + (x + 1) + "");
					c.add(aiSelect[x]);
					startGame.addActionListener(new RandomAI());
				}
				
				// Generate Smart AI buttons
				for (int x = 3; x < aiSelect.length; x++) {
					aiSelect[x] = new JCheckBox();
					aiSelect[x].setText("" + (x - 2) + "");
					c.add(aiSelect[x]);
					startGame.addActionListener(new SmartAI());
				}
				
				// Remove IP and Port buttons
				for (int x = 0; x < serverSettings.length; x++) {
					if (serverSettings[x] != null) {
						c.remove(l);
						c.remove(serverSettings[x]);
					}
				}
								
				// Refresh the frame
				revalidate();
				repaint();
				
			} else {
				
				// Delete all AI buttons
				for (int x = 0; x < aiSelect.length; x++) {
					c.remove(aiSelect[x]);
				}
				
				// Add IP and Port buttons
				for (int x = 0; x < serverSettings.length; x++) {
					l = new JLabel(labels[x], JLabel.TRAILING);
					c.add(l);
					serverSettings[x] = new JTextField();
					serverSettings[x].setPreferredSize(new Dimension(100, 25));
		            l.setLabelFor(serverSettings[x]);
					c.add(serverSettings[x]);
				}
				
				// Refresh the frame
				revalidate();
				repaint();
			}
			
		}
		
	}
	
	// Random AI handler
	public class RandomAI implements ActionListener {
		public RandomAI() {
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	// Smart AI handler
	public class SmartAI implements ActionListener {
		
		public SmartAI() {
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}