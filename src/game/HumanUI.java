package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import game.player.*;

public class HumanUI extends JFrame {

	
	/**
	 * Generated serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	// GUI handling
	JTextArea jTextArea = new JTextArea();
	JButton[] settingsButton = new JButton[4];
	JButton[][] boardButtons = new JButton[Board.SIZE][Board.SIZE];
	Image buttonImage;
	ColorUI colorUI;
	Container c = getContentPane();
	
	// Player Handling
	Player[] players;
	
	public HumanUI() {
		init();
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(700, 700);
		setTitle("Ringzz");
		setResizable(false);
		setVisible(true);
	}
	
	public void init() {
		c.setLayout(new GridLayout(5, 5));
		
		for (int y = Board.SIZE - 1; y >= 0; y--) {	
			for (int x = 0; x < Board.SIZE; x++) {
						
				boardButtons[x][y] = new JButton();
								
				// button size to match with image
				boardButtons[x][y].setPreferredSize(new Dimension(128, 128));
				
				boardButtons[x][y].setBorder(null);
				boardButtons[x][y].setBorder(null);
				boardButtons[x][y].setContentAreaFilled(false);
				boardButtons[x][y].setMargin(new Insets(0, 0, 0, 0));
				
				// Default Empty board
				colorUI = new ColorUI(null, false, 0);
        		buttonImage = colorUI.getColorUI();
				updateButton(x, y, buttonImage);
			}
		}
	}
		
	public void updateButton(int x, int y, Image merged) {
		
		ImageIcon rings = new ImageIcon(merged);
		
		// Image manager
		boardButtons[x][y].setIcon(rings);
		boardButtons[x][y].setRolloverIcon(rings);
		boardButtons[x][y].setPressedIcon(rings);
		boardButtons[x][y].setDisabledIcon(rings);
		c.add(boardButtons[x][y]);
		boardButtons[x][y].addMouseListener(new UserBoard(x, y));
		
	}

	private class UserBoard implements MouseListener {
		
		int buttonX;
		int buttonY;
		
		public UserBoard(int x, int y) {
			buttonX = x;
			buttonY = x;
		}
		
		@Override
		public void mouseClicked(MouseEvent ev) {
			boardButtons[buttonX][buttonY].setText("Test");
		}

		@Override
		public void mouseEntered(MouseEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent ev) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent ev) {
			// TODO Auto-generated method stub
			
		}	
	}
}
