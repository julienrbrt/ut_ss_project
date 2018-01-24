/************************************************************
 * **********************************************************
 * ******************** NOT WORKING *************************
 * **********************************************************
 * **********************************************************
 */

package network;

import java.io.*;
import java.net.*;
import java.util.*;

import game.*;
import game.player.HumanPlayer;
import game.player.Mark;

/**
 * This class for the connection of the client-server of the game.
 * @author  Theo Ruys
 * @author Yordi Hazekamp 
 * @author Julien Robert
 * @version 0.1
 */

//MVC (View)
public class Peer implements Runnable {

    protected String name;
    protected Socket sock;
    protected BufferedReader in;
    protected BufferedWriter out;
    
    public ClientHandler client;
	public ServerHandler server;
	
	game.player.Player p1, p2;

    /*@
       requires (nameArg != null) && (sockArg != null);
     */
    /**
     * Constructor. creates a peer object based in the given parameters.
     * @param   nameArg name of the Peer-process
     * @param   sockArg Socket of the Peer-process
     */
    public Peer(String nameArg, Socket sockArg) throws IOException {
    	this.name = nameArg;
    	this.sock = sockArg;
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
				e.printStackTrace();
			}
    	}
    }

    /**
     * Manage command received in the client side.
     */
    public void clientside() {
		Scanner cmd = new Scanner(System.in);
		Boolean inside = true;
		
		while (inside) {
			try {
				System.out.println("Enter your command: ");
				String command = cmd.next().toUpperCase();
				if (command.contains("JOIN")) {
					out.write(client.join(cmd.next()));
					out.flush();
				} else if (command.equals("READY")) {
					out.write(client.ready());
					out.flush();
				} else if (command.equals("LEAVE")) {
					out.write(client.leave());
					out.flush();
				} else if (command.equals("DISCONNECT")) {
					inside = false;
					client.disconnect();
					out.flush();
					shutDown();
				} else {
					System.out.println("Commands:\n Join [username] \n Ready"
							+ "\n Disconnect \n Help \n");
				}
			} catch (IOException e) {
				System.out.println("ERROR: Socket is closed!");
			}
		}
		cmd.close();
	}
    
    /**
     * Manage command received in the server side.
     */
    public void serverside() {
//		while (connectionLost() != true) {
			try {
				String line = in.readLine();
				while (line != null) {
					List<String> players = client.getPlayer();
					String[] args = line.split(" ");
					if (args[0].contains("Join")) {
						if (players != null) {
					    	p1 = new HumanPlayer(players.get(0), Mark.BB);
						}
						if (!players.contains(args[1])) {
							players.add(args[1]);
					    	p2 = new HumanPlayer(players.get(1), Mark.YY);
						}
					} else if (args[0].contains("Ready")) {
						Game game = new Game(p1, p2);
					    game.start();
					} else if (args[0].contains("Move")) {
						server.notifyMove(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
					}
				}
			} catch (IOException e) {
				System.out.println("ERROR: Socket is closed!");
			}
//		}
    }
        
	/**
	* The server notifies the clients that a player lost connection or left the game.
	*/
	public boolean connectionLost() {
		boolean connectionLost = true;
		while (connectionLost) {
			for (int i = 0; i < client.getPlayer().size(); i++) {
				try {
		        	if (sock.getInetAddress().isReachable(300)) {
		        		connectionLost = false;
		        	}
		        } catch (IOException e) {
		        	return true;
		        }
	    	}
	    }    
		return true;
	}
    	
    /**
     * Closes the connection, the sockets will be terminated.
     */
    public void shutDown() {
		try {
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

   /**
    * returns name of the peer object.
    */
    public String getName() {
        return name;
    }
    

    /**
     * read a line from the default input.
     */
    static public String readString(String tekst) {
        System.out.print(tekst);
        String antw = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            antw = in.readLine();
        } catch (IOException e) {
        }

        return (antw == null) ? "" : antw;
    }
}