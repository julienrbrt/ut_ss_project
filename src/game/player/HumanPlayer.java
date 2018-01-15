package game.player;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.*;
import gui.Tools;

public class HumanPlayer extends Player {
	
	private Color firstColor;
	private Color secondColor;
	
	// GUI handling
	JFrame humanUI = new JFrame("Ringzz");
	JTextArea jTextArea = new JTextArea();
	JButton[] settingsButton = new JButton[4];
	JButton[] boardButtons = new JButton[Board.SIZE * Board.SIZE];
	
	// Constructor for one color
	public HumanPlayer(String name, Color firstColor) {
		super(name, firstColor);
		this.firstColor = firstColor;
		init();
		humanUI.pack();
		humanUI.setDefaultCloseOperation(humanUI.DISPOSE_ON_CLOSE);
		humanUI.setVisible(true);
		
	}
	
	// Constructor for two colors
	public HumanPlayer(String name, Color firstColor, Color secondColor) {
		super(name, firstColor, secondColor);
		this.firstColor = firstColor;
		this.secondColor = secondColor;
		init();
		humanUI.pack();
		humanUI.setDefaultCloseOperation(humanUI.DISPOSE_ON_CLOSE);
		humanUI.setVisible(true);
	}
	
	
	public void init() {
		Container c = humanUI.getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 5));
		
		for (int x = 0; x < boardButtons.length; x++) {	
			boardButtons[x] = new JButton();
			c.add(boardButtons[x]);
			c.add(panel);
			boardButtons[x].addActionListener(new UserBoard(x));
		}
	}
	
	private class UserBoard implements ActionListener {
		
		public int TileNum;
		
		public UserBoard(int x) {
			TileNum = x;
		}
		
		public void actionPerformed(ActionEvent ev) {
			// buttons actions to be defined
		}
	}
	
	public int[] determineBase(Board board) {
		boolean valid = false;
		int x = 0;
		int y = 0;
		
        while (!valid) {
			String startX = JOptionPane.showInputDialog(null, "Enter coords for home base (x first)");
	        String startY = JOptionPane.showInputDialog(null, "Now enter y");
	       	       	
	        if (Tools.validNum(startX) && Tools.validNum(startY)) {
	     	    x = Integer.parseInt(startX);
	     	    y = Integer.parseInt(startY);
	     
	     	    if (board.canPlace(x, y, true, 0, firstColor)) {
	     		    valid = true;
	     	    } else if (secondColor != null) {
	     		    valid = board.canPlace(x, y, true, 0, secondColor);
	     	    }
	     	   
	        } else {
	        	JOptionPane.showMessageDialog(null, "Error, fields invalid, please try-again.");
	        }
	        
	        if (!valid) {
	        	JOptionPane.showMessageDialog(null, "Values incorrect. Please try-again");
	        }
        }
        
  	    int[] choice = {x, y};
  	    return choice;
	} 
	
	public Object[] determineMove(Board board) {
	
        boolean valid = false;
        boolean base = false;
		int x = 0;
		int y = 0;
		int ringSize = 0;
        
        while (!valid) {
        	String giveX = JOptionPane.showInputDialog(null, "Give X");
            String giveY = JOptionPane.showInputDialog(null, "Give Y");
            String giveBase = JOptionPane.showInputDialog(null, "Give Base (0 or 1)");
            String giveRingSize = JOptionPane.showInputDialog(null, "Give Ring Size");

	        if (Tools.validNum(giveX) && Tools.validNum(giveY) && Tools.validNum(giveBase) && Tools.validNum(giveRingSize)) {
	        	x = Integer.parseInt(giveX);
	        	y = Integer.parseInt(giveY);
	        	
	        	if (Integer.parseInt(giveBase) == 0) {
	        		base = false;
	        	} else {
	        		base = true;
	        	}
	        	
	        	ringSize = Integer.parseInt(giveRingSize);

	        	if (x < board.getSize() && y < board.getSize() && ringSize < 4) {
					valid = true;
				} else {
		        	JOptionPane.showMessageDialog(null, "Error, fields invalid, please try-again.");
				}
	        }
	        
	        if (!valid) {
	        	JOptionPane.showMessageDialog(null, "Values incorrect. Please try-again");
	        }
        }
        
        Object[] choice = {x, y, base, ringSize};
        return choice;
        
	}
	
}