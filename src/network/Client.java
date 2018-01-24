package network;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * This class for maintening the Client of the PuissanceQuatre game for playing in network.
 * @author Yordi Hazekamp 
 * @author Julien Robert
 * @version 0.1
 */

public class Client implements Observer {
	
	/**
	 * Define usage of the client.
	 */
	private static final String USAGE = "usage: java Client <name> <address> <port>";
	
	/**
	 * @param args permits to checks whether the usage is followed.
	 */
	public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println(USAGE);
            System.exit(0);
        }
        
	    String name = args[0];
	    InetAddress addr = null;
	    int port = 0;
	    Socket sock = null;
	    
	    /**
	     *  check args[1] - the IP-address
	     */
        try {
            addr = InetAddress.getByName(args[1]);
        } catch (UnknownHostException e) {
            System.out.println(USAGE);
            System.out.println("ERROR: host " + args[1] + " unknown");
            System.exit(0);
        }

        /**
         *  parse args[2] - the port
         */
        try {
            port = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println(USAGE);
            System.out.println("ERROR: port " + args[2] + " is not an integer");
            System.exit(0);
        }
        
        /**
         *  try to open a Socket to the server
         */
        try {
            sock = new Socket(addr, port);
        } catch (IOException e) {
            System.out.println("Error: could connect on the server " + addr + ": " + port);
        }
        
        /**
         *  create Peer object and start the two-way communication
         */
        try {
            Peer client = new Peer(name, sock);
            Thread streamInputHandler = new Thread(client);
            streamInputHandler.start();
            client.clientside();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Someone have joinned");
	}
}