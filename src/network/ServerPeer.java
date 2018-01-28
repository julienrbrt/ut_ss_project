package network;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JOptionPane;

import game.player.*;

/**
 * This class for the connection of the client-server of the game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class ServerPeer implements Runnable, Protocol {

    protected String name;
    protected Socket sock;
    protected int gameWith;
    protected ServerGame game;
    protected List<Player> playerList = new ArrayList<>();
    
    protected BufferedReader in;
    protected BufferedWriter out;
    
    public ServerPeer(String name, Socket sock) throws IOException {
    	this.name = name;
    	this.sock = sock;
    	in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    	out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
    }

    /**
     * Reads strings of the stream of the socket-connection and
     * writes the characters to the default output.
     */
    public void run() {
    	serverside();
    }

    
    // don't forget to handle space name
    
    /**
     * Manage command received in the server side.
     */
    public void serverside() {
		while (!connectLost()) {

		}
    }
        
	/**
	* The server notifies the clients that a player lost connection or left the game.
	*/
	public boolean connectLost() {
		boolean connectLost = true;
		
		while (connectLost) {
			for (int i = 0; i < playerList.size(); i++) {
				try {
		        	if (sock.isClosed()) {
		        		connectLost = true;
		        	}
					
					if (sock.getInetAddress().isReachable(300)) {
		        		connectLost = false;
		        	}
		        } catch (IOException e) {
		        	return true;
		        }
	    	}
	    }    
		return connectLost;
	}
    	
    
	public String acceptjoin(String username) {
		return SERVER_ACCEPTJOIN + " " + username + " 0 0 0 0";
	}
	
	public String denyjoin(String username) {
		return SERVER_DENYJOIN + " " + username;
	}
	
	public String startgame(String[] usernames) {
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int x = 0; x < usernames.length; x++) {
			stringBuilder.append(usernames[x] + " ");
		}
	
		return SERVER_STARTGAME + stringBuilder.toString();
	}
	
	public String moverequest(String username) {
		return SERVER_MOVEREQUEST + " " + username;
	}
	
	public String denymove(String username) {
		return SERVER_DENYMOVE + " " + username;
	}
	
	public String notifymove(int x, int y, int size, int color) {
		return SERVER_NOTIFYMOVE + " " + x + " " + y + " " + size + " " + color;
	}
	
	public String gameover(String[] winners) {
		
		// When tie
		if (winners.length > 1) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int x = 0; x < winners.length; x++) {
				stringBuilder.append(winners[x] + " ");
			}
		}

		return SERVER_GAMEOVER + " " + winners;
	}
	
	public String connectionlost(String username) {
		return SERVER_CONNECTIONLOST + " " + username;
	}
	
	public String invalidcommand() {
		return SERVER_INVALIDCOMMAND;
	}
    
    /**
     * Closes the connection, the sockets will be terminated.
     */
    public void shutdown() {
		try {
			sock.close();
			out.close();
			in.close();
		} catch (IOException e) {
			System.out.println("Whoosh " + e);
		}
    }

   /**
    * Return name of the peer object.
    */
    public String getName() {
        return name;
    }
}