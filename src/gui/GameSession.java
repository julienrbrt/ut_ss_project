package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import game.player.Color;

public class GameSession {
	
	// GUI handling
	private final JFrame frameGS = new JFrame();
	Container c = frameGS.getContentPane();

	JPanel aiSelect = new GameSessionAISelect();
	JPanel title = new GameSessionBasic(frameGS, aiSelect);
	JPanel serverSettings = new GameSessionServerSettings();

	JCheckBox onlineOff = new JCheckBox();
	
	// Player handling
	Color color = Color.NONEE;	//initiated to make static calls
	
	public GameSession() {
		init();
		frameGS.pack();
		frameGS.setDefaultCloseOperation(frameGS.EXIT_ON_CLOSE);
		frameGS.setSize(700, 350);
		frameGS.setTitle("Ringzz");
		frameGS.setResizable(false);
		frameGS.setVisible(true);
		// Center Window
		frameGS.setLocationRelativeTo(null);
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
		
		JButton button = new JButton("Start");
		button.addActionListener(new StartGame());
		c.add(button);
	
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
				frameGS.revalidate();
				frameGS.repaint();
				
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
				frameGS.revalidate();
				frameGS.repaint();
			}
			
		}
		
	}
}