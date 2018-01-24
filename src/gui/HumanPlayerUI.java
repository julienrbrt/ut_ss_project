package gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.*;
import game.player.*;
import tools.Tools;

public class HumanPlayerUI extends Player {
	
	private Color firstColor;
	private Color secondColor;
	private final int playerNumber;
	
	// GUI handling
	private final JFrame frameHP = new JFrame();
	Container c = frameHP.getContentPane();
	
	// Constructor for one color
	public HumanPlayerUI(String name, Color firstColor, int playerNumber) {
		super(name, firstColor, playerNumber);
		this.firstColor = firstColor;
		this.playerNumber = playerNumber;
	}
	
	// Constructor for two colors
	public HumanPlayerUI(String name, Color firstColor, Color secondColor, int playerNumber) {
		super(name, firstColor, secondColor, playerNumber);
		this.firstColor = firstColor;
		this.secondColor = secondColor;
		this.playerNumber = playerNumber;
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
	     
	     	    if (board.canPlace(x, y, true, 0, firstColor, playerNumber)) {
	     		    valid = true;
	     	    } else if (secondColor != null) {
	     		    valid = board.canPlace(x, y, true, 0, secondColor, playerNumber);
	     	    }
	     	   
	        }
	        
	        if (!valid) {
	        	JOptionPane.showMessageDialog(null, "Values incorrect. Please try again");
	        }
        }
        
  	    int[] choice = {x, y};
  	    return choice;
	} 
	
	public Object[] determineMove(Board board, int colorAmount) {
	
        boolean valid = false;
        boolean base = false;
		int x = 0;
		int y = 0;
		int ringSize = 0;
		Color color = Color.NONEE;
        
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

	        	if (board.canPlace(x, y, base, ringSize, firstColor, playerNumber)) {
	        		color = firstColor;
					valid = true;
				} else if (secondColor != null && board.canPlace(x, y, base, ringSize, secondColor, playerNumber)) {
					color = secondColor;
					valid = true;
				}
	        	
	        }
	        
	        if (!valid) {
	        	JOptionPane.showMessageDialog(null, "Values incorrect. Please try again");
	        }
        }
        
        Object[] choice = {x, y, base, ringSize, color};
        return choice;
        
	}
	
}