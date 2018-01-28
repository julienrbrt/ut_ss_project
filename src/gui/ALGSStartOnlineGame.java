package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import network.ClientPeer;

/**
 * This is an Action Listener permitting to start an Online Game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */
public class ALGSStartOnlineGame implements ActionListener {
	
	JFrame frameGS;
	JPanel uiPanel;
	
	String playerName;
    Socket sock = null;
	
	public ALGSStartOnlineGame(JFrame frame, JPanel panel, String name) {
		this.frameGS = frame;
		this.uiPanel = panel;
		this.playerName = name;
	}

	public void actionPerformed(ActionEvent e) {
		
		GameSessionServerSettings uiClass = (GameSessionServerSettings) uiPanel;
		Object[] settings = uiClass.getServerSettings();
		
		InetAddress addr = (InetAddress) settings[0];
		int port = (Integer) settings[1];
		int gameWith = (Integer) settings[2];
		
		System.out.println(addr + " " + port + " " + gameWith);
		
		if (port != -1 && addr != null && gameWith != -1) {
			
			/**
	         *  try to open a Socket to the server
	         */
	        try {
	            sock = new Socket(addr.getHostAddress(), port);
	            System.out.println("Connected to server.");
	        } catch (IOException io) {
	        	JOptionPane.showMessageDialog(null,
	        			"Error: could connect on the server " + addr + ":" + port);
	        }
	        
	        /**
	         *  create Peer object and start the two-way communication
	         */
	        try {
	            ClientPeer client = new ClientPeer(playerName, gameWith, sock);
	            new Thread(client).start();
	        } catch (IOException io) {
	            io.printStackTrace();
	        }
	        
	        // Close Window
	        frameGS.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Please verify your parameters.");
		}
		
	}
	
}