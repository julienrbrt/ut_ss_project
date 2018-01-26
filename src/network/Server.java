package network;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This class for maintening the Server of the Ringgz game for playing in network.
 * @author  Theo Ruys
 * @author Richard
 * @author Julien
 * @version 0.1
 */

public class Server extends Observable {

	/**
	 * Define usage of the server.
	 */
    private static final String USAGE = "usage: <name> <port>";
    
    
	/**
	 * @param args permits to checks whether the usage is followed.
	 */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(0);
        }
        
        String name = args[0];
        int port = 0;
        ServerSocket ssock = null;
        Socket sock = null;
        
        /**
         *  parse args[1] - the port
         */
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println(USAGE);
            System.out.println("ERROR: port " + args[2] + " is not an integer");
            System.exit(0);
        }
        
        /**
         * try to open a Socket to the server
         */
        try {
            ssock = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("ERROR: could not create a server socket on port " + port);
        }
        
        try {
        	int i = 0;
        	while (sock == null) {
        		sock = ssock.accept();
//        		setChanged();
//        		notifyObservers("new");
        		System.out.println("client connected: " + (++i));              
        	}
        } catch (IOException e) {
            System.out.println("ERROR: could not create socket.");
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
	
}