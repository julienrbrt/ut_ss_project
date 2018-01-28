package network;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

import game.player.*;
import gui.*;

/**
 * This class for the connection of the client-server of the game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */
public class ClientPeer implements Runnable, Protocol {

    protected String name;
    protected Socket sock;
    protected int gameWith;
    protected HumanUI gui;
    protected ClientGame game;
    protected String[] playerList;
    
    protected BufferedReader in;
    protected BufferedWriter out;
        
    public ClientPeer(String name, int gameWith, Socket sock) throws IOException {
    	this.name = name;
    	this.gameWith = gameWith;
    	this.sock = sock;
    	in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    	out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
    }

    /**
     * Reads strings of the stream of the socket-connection and
     * writes the characters to the default output.
     */
    public void run() {
    	while (!sock.isClosed()) {
    		try {
				if (in.ready()) {
					System.out.println(in.readLine());
				}
			} catch (IOException e) {
				System.out.println("Whoosh " + e);
			}
    		clientside();
    	}
    }

    /**
     * Manage command received in the client side and to send to server.
     * No error handling, the client is dumb and accept everything.
     */
    public void clientside() {
		Boolean playing = true;
		
		while (playing) {
			try {
				// Join and ask for game
				out.flush();
				out.write(CLIENT_JOINREQUEST + " " + name + " 0 0 0 0");
				out.flush();
				out.write(CLIENT_GAMEREQUEST + " " + gameWith);
				out.flush();
				
				// Handle Game
                String input = in.readLine();
                System.out.println(input);
                
                if (input != null) {
                	String[] cmd = input.split(" ");
                	switch (cmd[0]) {
                		case SERVER_ACCEPTJOIN:
                			if (cmd[1] == name) {
                				JOptionPane.showMessageDialog(null, "You are now connected");
                			}
                			break;
                		case SERVER_DENYJOIN:
                			if (cmd[1] == name) {
                				JOptionPane.showMessageDialog(null, "Username invalid. Try again.");
                				shutdown();
                			}
                			break;
                		case SERVER_STARTGAME:
                			int playerNum = -1;
                			playerList = new String[cmd.length - 1];
                			for (int x = 1; x < cmd.length; x++) {
                				playerList[x - 1] = cmd[x];
                				// Check if message  and if server respect preferences & create GUI
                				if (cmd[x].contains(name) && (cmd.length - 1) == gameWith && (cmd.length - 1) < 5) {
                					playerNum = x;
                					gui = new HumanUI();
                				}
                			}
                			
                			if (playerNum != -1) {
                				game = new ClientGame(name, playerNum, cmd.length - 1, gui);
                				new Thread(game).start();
                			}
                			break;
                		case SERVER_MOVEREQUEST:
                			if (cmd[1] == name) {
                				Object[] setmove = game.makeMove();
                				// main base placement
                				if (setmove.length == 2) {
                					out.write(CLIENT_SETMOVE + " " + (Integer) setmove[0] + " " + (Integer) setmove[1] + " " + 0 + " " + 0);
                				} else {
                					// get color number
                					int colorNum = 1;
                					if ((Color) setmove[4] == Color.YELLO) {
                						colorNum = 2;
                					}
                					// base placement
                					if ((Boolean) setmove[2]) {
                    					out.write(CLIENT_SETMOVE + " " + (Integer) setmove[0] + " " + (Integer) setmove[1] + " " + 5 + " " + colorNum);
                					} else {
                						out.write(CLIENT_SETMOVE + " " + (Integer) setmove[0] + " " + (Integer) setmove[1] + " " + (Integer) setmove[3] + " " + colorNum);
                					}
                				}
                				out.flush();
                			}
                			break;
                		case SERVER_DENYMOVE:
                			shutdown(); // :))
                			break;
                		case SERVER_NOTIFYMOVE:
                			if (cmd[1] != name) { 
                				// get player number name
                	  			for (int x = 0; x < playerList.length; x++) {
                    				if (playerList[x].contains(name)) {
                        				game.setMove(Integer.parseInt(cmd[2]), Integer.parseInt(cmd[3]), Integer.parseInt(cmd[4]), Integer.parseInt(cmd[5]), x + 1);
                    				}                				
                	  			}
                			}
                			break;
                		case SERVER_GAMEOVER:
                			if (cmd.length > 2) {
                	    		JOptionPane.showMessageDialog(null, "It is a tie");
                			} else {
                	    		JOptionPane.showMessageDialog(null, "The winner is " + cmd[1]);
                			}
                			shutdown(); //  nicer handling to be done
                			break;
                		case SERVER_CONNECTIONLOST:
            	    		JOptionPane.showMessageDialog(null, cmd[1] + " have been disconected.");
            	    		break;
                		case SERVER_INVALIDCOMMAND:
                			System.out.println("Should NEVER happen.");
                			// shutdown();
                			break;
                		default:
                			System.out.println("Server info - " + input);
                			break;
                	}
                }
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "You have been disconnected.");
				shutdown();
			}
		}
	}
    	
    /**
     * Closes the connection, the sockets will be terminated.
     */
    public void shutdown() {
		try {
			sock.close();
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