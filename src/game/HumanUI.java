package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class HumanUI extends JFrame {

	
	// GUI handling
	JTextArea jTextArea = new JTextArea();
	JButton[] settingsButton = new JButton[4];
	JButton[] boardButtons = new JButton[Board.SIZE * Board.SIZE];
	
	public HumanUI() {
		init();
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(665, 665);
		setTitle("Ringzz");
		setResizable(false);
		setVisible(true);
	}
	
	public void init() {
		Container c = getContentPane();
		c.setLayout(new GridLayout(5, 5));
		
		for (int x = 0; x < boardButtons.length; x++) {	
			boardButtons[x] = new JButton();
			
			// button size to match with image
			boardButtons[x].setPreferredSize(new Dimension(128, 128));
			
			boardButtons[x].setBorder(null);
			boardButtons[x].setBorder(null);
			boardButtons[x].setContentAreaFilled(false);
			boardButtons[x].setMargin(new Insets(0, 0, 0, 0));
			
			// Image Manager - Default
			ColorUI colorUI = new ColorUI(null, false, 0);
			Image buttonImage = colorUI.getColorUI();
			boardButtons[x].setIcon(new ImageIcon(buttonImage));
			boardButtons[x].setRolloverIcon(new ImageIcon(buttonImage));
			boardButtons[x].setPressedIcon(new ImageIcon(buttonImage));
			boardButtons[x].setDisabledIcon(new ImageIcon(buttonImage));
			
			c.add(boardButtons[x]);
			boardButtons[x].addMouseListener(new UserBoard(x));
		}
	}

	private class UserBoard implements MouseListener {
		
		public int tileNum;
		
		public UserBoard(int x) {
			tileNum = x;
		}
		
		@Override
		public void mouseClicked(MouseEvent ev) {
			boardButtons[tileNum].setVisible(false);
			
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
	
	static public void main(String[] arg0) {
		new HumanUI();
	}
}
