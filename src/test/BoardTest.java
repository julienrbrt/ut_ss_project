package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Board;
import game.player.*;

public class BoardTest {
	
	Board board2p;
	Board board3p;
	Board board4p;

	@Before
	public void setUp() {
		Player player1 = new ComputerPlayer(new RandomStrategy(0), Color.REDDD, 0);
		Player player2 = new ComputerPlayer(new RandomStrategy(1), Color.BLUEE, 1);
		Player player3 = new ComputerPlayer(new RandomStrategy(2), Color.GREEN, 2);
		Player player4 = new ComputerPlayer(new RandomStrategy(3), Color.YELLO, 3);
		Player player1c2 = new ComputerPlayer(new RandomStrategy(0), Color.REDDD, Color.YELLO, 0);
		Player player2c2 = new ComputerPlayer(new RandomStrategy(1), Color.BLUEE, Color.YELLO, 1);
		Player player3c2 = new ComputerPlayer(new RandomStrategy(2), Color.GREEN, Color.YELLO, 2);
		Player player4c2 = new ComputerPlayer(new RandomStrategy(0), Color.REDDD, Color.BLUEE, 0);
		Player player5c2 = new ComputerPlayer(new RandomStrategy(1), Color.GREEN, Color.YELLO, 1);
		Player[] set2p = new Player[2];
		set2p[0] = player4c2;
		set2p[1] = player5c2;
		Player[] set3p = new Player[3];
		set3p[0] = player1c2;
		set3p[1] = player2c2;
		set3p[2] = player3c2;
		Player[] set4p = new Player[4];
		set4p[0] = player1;
		set4p[1] = player2;
		set4p[2] = player3;
		set4p[3] = player4;
		board2p = new Board(2, set2p);
		board3p = new Board(3, set3p);
		board4p = new Board(4, set4p);
	}
	
	@Test
	public void canPlaceTest() {
		assertTrue(board2p.canPlaceCheck(2, 2, true, 3, Color.REDDD, 0));	//check whether or not the starting base can be placed in the middle
		board2p.addHome(2, 2);	//actually place the base now, also tests functionality of addHome
		assertFalse(board2p.canPlaceCheck(2, 2, false, 2, Color.REDDD, 0));	//make sure there is something in the (2,2) spot
		assertFalse(board2p.canPlaceCheck(-1, 2, true, 2, Color.REDDD, 0));	//check invalid values
		assertFalse(board2p.canPlaceCheck(5, 2, true, 2, Color.REDDD, 0));
		assertFalse(board2p.canPlaceCheck(2, -1, true, 2, Color.REDDD, 0));
		assertFalse(board2p.canPlaceCheck(2,5, true, 2, Color.REDDD, 0));
		assertFalse(board2p.canPlaceCheck(2, 2, true, 2, null, 0));
		assertFalse(board2p.canPlaceCheck(2, 2, true, -1, Color.REDDD, 0));
		assertTrue(board2p.canPlaceCheck(2, 3, true, 1, Color.BLUEE, 1));	//test surrounding-color checking
		board3p.addHome(2, 2);
		assertTrue(board3p.canPlace(2, 3, false, 0, Color.YELLO, 0));	//canPlace() does not actually place the piece, but it does reduce the ring counter
		assertFalse(board3p.canPlace(2, 3, false, 0, Color.YELLO, 0));	//should return false, because player 0 only has 1 yellow ring of size 0 and it has already been counted down
		board4p.canPlace(2, 2, true, 1, Color.REDDD, 0);	//flip start boolean to false
		board4p.addHome(2, 2);
		assertFalse(board4p.canPlaceCheck(3, 3, true, 1, Color.BLUEE, 1));	//not next to starting base
		board4p.canPlace(2, 3, false, 0, Color.BLUEE, 1);
		board4p.addRing(2, 3, false, 0, Color.BLUEE);
		assertTrue(board4p.canPlaceCheck(3, 3, true, 1, Color.BLUEE, 1));	//next to just placed piece
		board4p.canPlace(3, 3, true, 1, Color.BLUEE, 1);
		board4p.addRing(3, 3, true, 1, Color.BLUEE);
		assertFalse(board4p.canPlaceCheck(3, 4, true, 0, Color.BLUEE, 1));	//check same-colored bases not being able to be placed next to each other
	}

}
