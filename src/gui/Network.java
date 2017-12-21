package gui;

import game.*;
import gui.*;

import java.lang.Thread;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Network extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int port;
	String portUser;
	String address;
	
    // JPanel
    JPanel panel = new JPanel();
    
	public Network() {
			
		// Set port input
		address = JOptionPane.showInputDialog("Enter Server IP");
		portUser = JOptionPane.showInputDialog("Enter Server Port");
			
		// Check if correct IP
		if(!Tools.validIP(address) || !Tools.validPort(portUser)) {
		}
				
		// JFrame properties
        setSize(500, 500);
        setTitle("Ringgz");
        setLocationRelativeTo(null);
		// Ensure closing windows close app
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
		pack();
		
	}

	// Display the window
    public static void main(String[] args) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
        	new Network();
        }
    	});
    }
	
}