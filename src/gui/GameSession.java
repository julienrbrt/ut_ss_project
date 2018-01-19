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
	JTextField[] jTextField = new JTextField[3];
	JButton startGame = new JButton();
	JCheckBox onlineOff = new JCheckBox();
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
		c.setLayout(new SpringLayout());
		
		// Generate and add buttons
		for (int x = 0; x < jTextField.length; x++) {
			jTextField[x] = new JTextField();
			jTextField[x].setPreferredSize(new Dimension(200, 25));
			c.add(jTextField[x]);
		}
		
		// Labels user input
		
		// Set start button
		startGame = new JButton();
		startGame.setText("Start");
		startGame.addActionListener(new StartGame());
		c.add(startGame);
		
		// Set offline switch
		onlineOff = new JCheckBox("Offline");
		c.add(onlineOff);
	}
	
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
}