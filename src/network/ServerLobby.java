package network;

import exception.*;
import java.util.*;

/**
 * Class managing how are started the game (by setting up lobbies). 
 * @author Richard
 * @author Julien
 *
 */
public class ServerLobby {
	
	// info LinkedHashMap preserver order, so first come, first serve
    private Map<String, ServerPeer> playerList;
    private LinkedHashMap<Integer, List<ServerPeer>> lobby;
	
    /**
     * Constructor managing the distant users and lobbies.
     */
	public ServerLobby() {
		// Create 4 lobby
		lobby = new LinkedHashMap<>();
		lobby.put(2, new ArrayList<>());
		lobby.put(3, new ArrayList<>());
		lobby.put(4, new ArrayList<>());
		
		// Client list
		playerList = new LinkedHashMap<>();
	}
	
	/**
	 * Add a player to a lobby.
	 * @param playerName, the name of the player
	 * @param server, it's connection peer
	 * @throws NameSpaceException if the name contains a space (invalid)
	 * @throws NameUsedException if the name is already existing in the server.
	 */
	// Should not happen at the same time
	public synchronized void addPlayer(String playerName, ServerPeer server)
			throws NameSpaceException, NameUsedException {
		if (playerName.contains(" ")) {
			throw new NameSpaceException();
		} else if (playerList.containsKey(playerName)) {
			throw new NameUsedException();
		} else {
			playerList.put(playerName, server);
		}
	}
	
	/**
	 * Delete a player from the list.
	 * @param name, name of the player.
	 */
	public synchronized void deletePlayer(String name) {
		playerList.remove(name);
	}
	
	/**
	 * Add a player to a lobby.
	 * @param gameWith, the number of people he wants to play with.
	 * @param server, it's connection peer.
	 */
	public synchronized void addLobby(int gameWith, ServerPeer server) {
		// Assign people to lobby
		lobby.get(gameWith).add(server);
		
		if (lobby.get(gameWith).size() >= gameWith) {
			ServerGame game = new ServerGame(lobby.get(gameWith));
			
			StringBuilder opponents = new StringBuilder();
			
			// Get opponents list
			for (ServerPeer player : lobby.get(gameWith)) {
				for (Map.Entry<String, ServerPeer> playerName : playerList.entrySet()) {
					if (playerName.getValue().equals(player)) {
						opponents.append(playerName + " ");
					}
				}
			}
		
			// Set the game with every people from that lobby
			for (ServerPeer player : lobby.get(gameWith)) {
				player.start(game);
				player.setOpponents(opponents.toString());
			}
			
			// remove those people from queue
			lobby.get(gameWith).clear();
			game.play();
		}
	}
	
	/**
	 * Delete a player from a lobby.
	 * @param server, it's connection peer.
	 */
	public synchronized void deleteLobby(ServerPeer server) {
		
	}

}
