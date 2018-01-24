package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import game.player.*;
import game.player.Color;
import game.*;

public class HumanUI {
	
	// GUI handling
	private final JFrame frameGame = new JFrame();
	JTextArea jTextArea = new JTextArea();
	JButton[][] boardButtons = new JButton[Board.SIZE][Board.SIZE];
	Image buttonImage;
	ColorUI colorUI;
	Container c = frameGame.getContentPane();
	
	// Player Handling
	Player[] players;
	
	public HumanUI() {
		init();
		frameGame.pack();
		frameGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameGame.setSize(700, 700);
		frameGame.setTitle("Ringzz");
		frameGame.setResizable(false);
		frameGame.setVisible(true);
	}
	
	public void init() {
		c.setLayout(new GridLayout(5, 5));
		
		for (int y = Board.SIZE - 1; y >= 0; y--) {	
			for (int x = 0; x < Board.SIZE; x++) {
						
				boardButtons[x][y] = new JButton();
								
				// button size to match with image
				boardButtons[x][y].setPreferredSize(new Dimension(128, 128));				
				boardButtons[x][y].setBorder(null);
				boardButtons[x][y].setContentAreaFilled(false);
				boardButtons[x][y].setMargin(new Insets(0, 0, 0, 0));
								
				// Default Empty board
				colorUI = new ColorUI(null, false, 0);
        		buttonImage = colorUI.getColorUI();
				updateButton(x, y, buttonImage);
				c.add(boardButtons[x][y]);	
				boardButtons[x][y].addActionListener(new RingPlacement());
			}
		}
	}
	
	public class RingPlacement implements ActionListener {
		
		public RingPlacement() {

			
		}
		
		public void actionPerformed(ActionEvent ev) {
			
			
		}
		
	}
		
	public void updateButton(int x, int y, Image merged) {
		
		ImageIcon rings = new ImageIcon(merged);
		
		// Image manager
		boardButtons[x][y].setIcon(rings);
	}
}
