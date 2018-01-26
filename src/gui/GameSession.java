package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import game.player.Color;

/**
 * This class for let the user set its game preferences in one place.
 * @author Richard
 * @author Julien
 * @version 0.1
 */
public class GameSession {
	
	// GUI handling
	private final JFrame frameGS = new JFrame();
	Container c = frameGS.getContentPane();

	JPanel aiSelect = new GameSessionAISelect();
	JPanel serverSettings = new GameSessionServerSettings();
	JCheckBox onlineOff = new JCheckBox("Offline");
	JPanel title = new GameSessionBasic(frameGS, aiSelect, serverSettings, onlineOff);
	
	// Player handling
	Color color = Color.NONEE;	//initiated to make static calls
	
	public GameSession() {
		init();
		frameGS.pack();
		frameGS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		onlineOff.addActionListener(new OnlineOff());
		c.add(onlineOff);
		
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