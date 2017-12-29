package game.player;

import javax.swing.JOptionPane;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;

import game.*;
import gui.Tools;

public class HumanPlayer extends Player {
		
	Color firstColor;
	Color secondColor;
	
	public HumanPlayer(String name, Color firstColor, Color secondColor) {
		super(name, firstColor, secondColor);
		this.firstColor = firstColor;
		this.secondColor = secondColor;
	}
	
	public int[] determineBase(Board board) {
		boolean valid = false;
		int x = 0;
		int y = 0;
		
        while(!valid) {
			String startX = JOptionPane.showInputDialog(null, "Enter coords for home base (x first)");
	        String startY = JOptionPane.showInputDialog(null, "Now enter y");
	       	
	        if(Tools.validNum(startX) && Tools.validNum(startY)) {
	     	   x = Integer.parseInt(startX);
	     	   y = Integer.parseInt(startY);
	     
	     	   if(board.canPlace(x, y, true, 0, firstColor)) {
	     		   valid = true;
	     	   } else if (secondColor != null) {
	     		   valid = board.canPlace(x, y, true, 0, secondColor);
	     	   };
	     	   
	        } else {
	        	JOptionPane.showMessageDialog(null, "ERROR, fields invalid, please try-again.");
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
        
        while(!valid) {
        	String giveX = JOptionPane.showInputDialog(null, "Give X");
            String giveY = JOptionPane.showInputDialog(null, "Give Y");
            String giveBase = JOptionPane.showInputDialog(null, "Give Base (0 or 1)");
            String giveRingSize = JOptionPane.showInputDialog(null, "Give Ring Size");

	        if(Tools.validNum(giveX) && Tools.validNum(giveY) && Tools.validNum(giveBase) && Tools.validNum(giveRingSize)) {
	        	x = Integer.parseInt(giveX);
	        	y = Integer.parseInt(giveY);
	        	
	        	if(Integer.parseInt(giveBase) == 0) {
	        		base = false;
	        	} else {
	        		base = true;
	        	};
	        	
	        	ringSize = Integer.parseInt(giveRingSize);

	        	if(x < board.getSize() && y < board.getSize() && ringSize < 4) {
					valid = true;
				} else {
		        	JOptionPane.showMessageDialog(null, "ERROR, fields invalid, please try-again.");
				}
	        }
        }
        
        Object[] choice = {x, y, base, ringSize};
        return choice;
        
	}
}