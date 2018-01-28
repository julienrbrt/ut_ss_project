package gui;

import java.io.*;
import java.net.*;
import java.util.*;
import tools.Tools;
import javax.swing.JOptionPane;

import network.ServerPeer;

/**
 * This class for maintening the Server of the Ringgz game for playing in network.
 * @author Richard
 * @author Julien
 * @version 0.1
 */
public class ServerSession extends Observable {
	
	int port;
	int serverOff = JOptionPane.NO_OPTION;
    ServerSocket ssock = null;
    Socket sock = null;
	
	public ServerSession() {
		
		init();
        /**
         * try to open a Socket to the server
         */
        try {
            serverOff = JOptionPane.showConfirmDialog(null,
            		"Do you want to close it? If no do not close this window.",
            		"Server Started", JOptionPane.YES_OPTION);
            ssock = new ServerSocket(port);

        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null,
        			"Error: could not create a server socket on port " + port);
        }
        
        int clientID = 0;
        while (serverOff == JOptionPane.NO_OPTION) {
        	start(clientID++);
        }

	}
	
    /**
     *  create Peer object and start the two-way communication.
     *  add unique identifier to client
     */
    public void start(int clientID) {
        try {
        	sock = ssock.accept();
    		System.out.println("Client accepted id!");

    		setChanged();
    		notifyObservers("new");
    		
    		ServerPeer server = new ServerPeer("" + clientID + "", sock);
            new Thread(server).start();
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null,
        			"Error: Something wrong happened.");
        }
    }
	
	public void init() {

        boolean valid = false;
	    String askPort = JOptionPane.showInputDialog(null, "Set listening port");
	       
		while (!valid) {
			if (!Tools.validNum(askPort)) {
				JOptionPane.showMessageDialog(null, "Port invalid.");
				askPort = JOptionPane.showInputDialog(null, "Set listening port");
			} else {
				valid = true;
			}
		}
		
		port = Integer.parseInt(askPort);
	}
	
}