package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import javax.imageio.ImageIO;

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
			
			try {
				
				// Source https://www.polyvore.com/cgi/img-thing?.out=jpg&size=l&tid=20482705
				
				File buttonFile = new File("Resources/Empty.png");
				Image buttonImage = ImageIO.read(buttonFile);
				boardButtons[x].setIcon(new ImageIcon(buttonImage));
			} catch (IOException ex) {
				System.out.println(ex);
			}
			
			c.add(boardButtons[x]);
			boardButtons[x].addMouseListener(new UserBoard(x));
		}
	}

	private class UserBoard implements MouseListener {
		
		public int TileNum;
		
		public UserBoard(int x) {
			TileNum = x;
		}
		
		@Override
		public void mouseClicked(MouseEvent ev) {
			// buttons actions to be defined
			
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
