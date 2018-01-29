package network;

import java.io.*;
import java.net.*;

import exception.*;

/**
 * This class for the connection of the client-server of the game.
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class ServerPeer implements Runnable, Protocol {

    protected Socket sock;
    protected ServerGame game;
    protected ServerLobby lobby;
    
    protected BufferedReader in;
    protected BufferedWriter out;
    
    protected String playerName;
    protected String opponents;
    
    public ServerPeer(Socket sock, ServerLobby lobby) throws IOException {
    	this.lobby = lobby;
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
    
    /**
     * Manage command received in the server side.
     */
    public void serverside() {
		while (!connectLost()) {
			try {
				String input = in.readLine();
                System.out.println("Client info - " + input);
                
                // Setup game if can play
                if (game != null && opponents != null) {
                	out.write(startgame(opponents));
                	out.newLine();
                	out.flush();
                }
                
                if (input != null) {
                	String[] cmd = input.split(" ");
                	switch (cmd[0]) {
                		case CLIENT_JOINREQUEST:
                			
                			if (cmd[1] != null) {
                				playerName = cmd[1];
                			} else {
                				out.write(invalidcommand());
                				out.newLine();
                				out.flush();
                				break;
                			}
                			
                			try {
                				lobby.addPlayer(playerName, this);
                				out.write(acceptjoin(playerName));
                				out.newLine();
                				out.flush();
                			} catch (NameSpaceException e) {
                				out.write(denyjoin(playerName));
                				out.newLine();
                				out.flush();
                			} catch (NameUsedException e) {
                				out.write(denyjoin(playerName));
                				out.newLine();
                				out.flush();
                			}
                			break;
                		case CLIENT_GAMEREQUEST:
                			if (cmd.length < 2 || Integer.parseInt(cmd[1]) < 2 ||
                					Integer.parseInt(cmd[1]) > 4) {
                				out.write(invalidcommand());
                				out.newLine();
                				out.flush();
                				break;
                			} else {
                				lobby.addLobby(Integer.parseInt(cmd[1]), this);
                    			break;
                			}               			
                		case CLIENT_SETMOVE:
                			if (cmd.length < 2 || game == null) {
                				out.write(invalidcommand());
                				out.newLine();
                				out.flush();
                				break;
                			} else {
        
                    			break;
                			}
                		default:
                			System.out.print("Client info - ignored - " + input);
                	}
                }
			} catch (IOException e) {
				shutdown();
			}

		}
		shutdown();
		// notify client disconnected
    }
        
    
    /**
     * Assign game to player.
     */
    public void start(ServerGame gameSession) {
    	this.game = gameSession;
    }
    
    /**
     * Set opponents list.
     */
    public void setOpponents(String opponent) {
    	this.opponents = opponent;
    }
    
    
    /**
     * Get the player name.
     */
    public String getPlayerName() {
    	return playerName;
    }
    
	/**
	* The server check if client still connected.
	*/
    
    // todo remove that guy from lobby
    
	public boolean connectLost() {
		boolean connectLost = true;
		
		while (connectLost) {
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
		
		return connectLost;
	}
	
	public void shutdown() {
		try {
			lobby.deletePlayer(playerName);
			sock.close();
			out.close();
			in.close();
			System.out.println("Someone have been disconnected.");
		} catch (IOException e) {
			
		}
	}
    
	/**
	 * 
	 * Protocol commands.
	 */
	public String acceptjoin(String username) {
		return SERVER_ACCEPTJOIN + " " + username + " 0 0 0 0";
	}
	
	public String denyjoin(String username) {
		return SERVER_DENYJOIN + " " + username;
	}
	
	public String startgame(String usernames) {	
		return SERVER_STARTGAME + usernames;
	}
	
	public String invalidcommand() {
		return SERVER_INVALIDCOMMAND;
	}
	
	public String connectionlost(String username) {
		return SERVER_CONNECTIONLOST + " " + username;
	}
	
	public void moverequest(String username) {
		try {
			out.write(SERVER_MOVEREQUEST + " " + username);
			out.newLine();
			out.flush();
		} catch (IOException e) {
			// todo
		}
		
	}
	
	public void denymove(String username) {
		try {
			out.write(SERVER_DENYMOVE + " " + username);
			out.newLine();
			out.flush();
		} catch (IOException e) {
			// todo
		}
	}
	
	public void notifymove(int x, int y, int size, int color) {
		try {
			out.write(SERVER_NOTIFYMOVE + " " + x + " " + y + " " + size + " " + color);
			out.newLine();
			out.flush();
		} catch (IOException e) {
			// todo
		}
	}
	
	public void gameover(String[] winners) {
		// When tie
		if (winners.length > 1) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int x = 0; x < winners.length; x++) {
				stringBuilder.append(winners[x] + " ");
			}
		}

		try {
			out.write(SERVER_GAMEOVER + " " + winners);
			out.newLine();
			out.flush();
		} catch (IOException e) {
			// todo
		}		
	}

}