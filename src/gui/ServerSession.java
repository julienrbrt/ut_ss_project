package gui;

import java.io.*;
import java.net.*;
import java.util.*;
import tools.Tools;
import javax.swing.JOptionPane;

import network.Peer;

/**
 * This class for maintening the Server of the Ringgz game for playing in network.
 * @author Richard
 * @author Julien
 * @version 0.1
 */
public class ServerSession extends Observable {
	
	String name;
	int port;
	
	public ServerSession() {
		init();
        ServerSocket ssock = null;
        Socket sock = null;
        
        /**
         * try to open a Socket to the server
         */
        try {
            ssock = new ServerSocket(port);
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null,
        			"ERROR: could not create a server socket on port " + port);
        }
        
        try {
        	int i = 0;
        	while (sock == null) {
        		sock = ssock.accept();
//        		setChanged();
//        		notifyObservers("new");
        		System.out.println("Client connected: " + (++i));              
        	}
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null,
        			"ERROR: could not create socket.");
        }
        
        /**
         *  create Peer object and start the two-way communication
         */
        try {
            Peer server = new Peer(name, sock);
            Thread streamInputHandler = new Thread(server);
            streamInputHandler.start();
            server.serverside();
            server.shutDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void init() {

        boolean valid = false;
	    
	    name = JOptionPane.showInputDialog(null, "Set Server name");
        String askPort = JOptionPane.showInputDialog(null, "Set listening port");
	       
		while (!valid) {
			if (!Tools.validNum(askPort)) {
				JOptionPane.showMessageDialog(null, "Port invalid.");
				askPort = JOptionPane.showInputDialog(null, "Set listening port");
			}
		}
		
		port = Integer.parseInt(askPort);
	}
	
}