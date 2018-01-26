package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import game.ColorUI;
import game.player.Color;

/**
 * This class for lauching the Ringgz GUI to permit to host a game or play the game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class Ringzz {

	// GUI handling
	private final JFrame frameMain = new JFrame();
	JButton[] settingsButton = new JButton[2];
	Container c = frameMain.getContentPane();
	
	public Ringzz() {
		init();
		frameMain.pack();
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.setSize(700, 350);
		frameMain.setTitle("Ringzz");
		frameMain.setResizable(false);
		frameMain.setVisible(true);
		// Center Window
		frameMain.setLocationRelativeTo(null);
	}
	
	public void init() {
		c.setLayout(new GridBagLayout());
		
		// Generate and add buttons
		for (int x = 0; x < settingsButton.length; x++) {
			settingsButton[x] = new JButton();
			settingsButton[x].setPreferredSize(new Dimension(250, 250));
			// Image above and text centered
			settingsButton[x].setVerticalTextPosition(SwingConstants.BOTTOM);
			settingsButton[x].setHorizontalTextPosition(SwingConstants.CENTER);
			// Button flat and white
			settingsButton[x].setFocusPainted(false);
			settingsButton[x].setBackground(java.awt.Color.WHITE);
			c.add(settingsButton[x]);
		} 
	
		// Set labels and generate dummy icons and add listeners
		settingsButton[0].setText("Start Game");
		ColorUI colorUI = new ColorUI(Color.GREEN, false, 2);
   		ImageIcon buttonImage = new ImageIcon(colorUI.getColorUI());
   		settingsButton[0].setIcon(buttonImage);
   		settingsButton[0].setPressedIcon(buttonImage);
   		settingsButton[0].addActionListener(new GameSession());
   		
		settingsButton[1].setText("Launch Server");
		colorUI = new ColorUI(Color.YELLO, false, 2);
   		buttonImage = new ImageIcon(colorUI.getColorUI());
   		settingsButton[1].setIcon(buttonImage);
   		settingsButton[1].setPressedIcon(buttonImage);
   		settingsButton[1].addActionListener(new ServerSession());
	}
	
	public class GameSession implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			new gui.GameSession();
			// Close Window
			frameMain.dispose();
		}
	}
	
	public class ServerSession implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			// Close Window
			frameMain.dispose();
			new gui.ServerSession();
		}
	}
	
	public static void main(String[] arg0) {
		new Ringzz();
	}
}
