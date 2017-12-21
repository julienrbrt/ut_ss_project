package gui;

import game.*;
import game.player.Color;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Control extends JFrame {
	
	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -6262772413997362382L;
	
	// Board
	Board board = new Board();
	Color c = Color.REDDD;	
	
    // JPanel
    JPanel panel = new JPanel();
    
    // Buttons
    JButton place = new JButton("PLACE");
    JButton change = new JButton("CHANGE");
    JButton exit = new JButton("EXIT");
    
    // Booleans
    boolean startBase = true;
    
    // GUI variables
    final double WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    final double HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    
    public Control() {
    	
    	// Adding to JPanel
    	panel.setPreferredSize(new Dimension((int)WIDTH, (int)HEIGHT));
    	panel.setLayout(null);
    	place.setBounds((int)(WIDTH/2)-100, (int)(HEIGHT/4)-50, 200, 100);
        panel.add(place);
        change.setBounds((int)(WIDTH/2)-100, (int)(HEIGHT/2)-50, 200, 100);
        panel.add(change);
        exit.setBounds((int)(WIDTH/2)-100, (int)(HEIGHT/4*3)-50, 200, 100);
        panel.add(exit);
        add(panel);
        
        // Manage button events
        place.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		place();
        	}
        });
        
        change.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		change();
        	}
        });

        exit.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		exit();
        		panel.setVisible(false);
        		dispose();
        	}
        });

        // JFrame properties
        setSize((int)WIDTH, (int)HEIGHT);
        setTitle("Ringgz");
        setLocation(0, 0);
		// Ensure closing windows close app
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
		pack();
		
    }

	public void place() {
		boolean Base;
		
		if(startBase) {
           String startX = JOptionPane.showInputDialog(null, "Enter coords for home base (x first)");
           String startY = JOptionPane.showInputDialog(null, "Now enter y");
   		   
           // Check for values
           if(Tools.validNum(startX) && Tools.validNum(startY)) {
        	   int X = Integer.parseInt(startX);
        	   int Y = Integer.parseInt(startY);
        	   board.addHome(X, Y);
        	   startBase = false;
           }

		}
		
		if(!startBase) {
			String giveX = JOptionPane.showInputDialog(null, "Give X");
	        String giveY = JOptionPane.showInputDialog(null, "Give Y");
	        String giveBase = JOptionPane.showInputDialog(null, "Give Base (0 or 1)");
	        String giveRingSize = JOptionPane.showInputDialog(null, "Give Ring Size");

	        // Check for values
	        if(Tools.validNum(giveX) && Tools.validNum(giveY) && Tools.validNum(giveBase) && Tools.validNum(giveRingSize)) {
	        	int X = Integer.parseInt(giveX);
	        	int Y = Integer.parseInt(giveY);
	        	if(Integer.parseInt(giveBase) == 0) {
	        		Base = false;
	        	} else {
	        		Base = true;
	        	};
	        	int RingSize = Integer.parseInt(giveRingSize);
	        	if(X < board.getSize() && Y < board.getSize() && RingSize < 4) {
					board.addRing(X, Y, Base, RingSize, c);
				} else {
					System.out.println("Invalid input, try again");
					place();
				}
	        }
	        
		}
		
		// Error handling
	    System.out.println(board.toString());

	}
	
	public void change() {
		String giveColor = JOptionPane.showInputDialog(null, "Give new color number (0=Red, 1=BLUEEe, 2=Green, 3=Yellow");
		
		if(Tools.validNum(giveColor)) {
			int colorNum = Integer.parseInt(giveColor);
			if (colorNum == 0) {
				c = Color.REDDD;
				System.out.println("Changed to Red");
			} else if (colorNum == 1) {
				c = Color.BLUEE;
				System.out.println("Changed to blue");
				
			} else if (colorNum == 2) {
				c = Color.GREEN;
				System.out.println("Changed to Green");
				
			} else if (colorNum == 3) {
				c = Color.YELLO;
				System.out.println("Changed to Yellow");
			}
			 // Error handling
		}
			
	  // Error handling
	}
	
	public void exit() {
		// Close the application properly
	}
    
    public static void main(String[] args) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        	new Control();
        }
    	});
    }
}