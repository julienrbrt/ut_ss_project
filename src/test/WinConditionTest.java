package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import game.Board;
import game.WinConditions;
import game.player.Color;
import game.player.ComputerPlayer;
import game.player.Player;
import game.player.RandomStrategy;

public class WinConditionTest {

	WinConditions win2p;
	Board board2p;
	Player[] set2p;
	
	@Before
	public void setUp() {
		Player player4c2 = new ComputerPlayer(new RandomStrategy(0), Color.REDDD, Color.BLUEE, 0);
		Player player5c2 = new ComputerPlayer(new RandomStrategy(1), Color.GREEN, Color.YELLO, 1);
		set2p = new Player[2];
		set2p[0] = player4c2;
		set2p[1] = player5c2;
		board2p = new Board(2, set2p);
		win2p = new WinConditions(board2p);
	}
	
	@Test
	public void winConditionsTest() {
		int[] result = new int[4];
		result[0] = 0;
		result[1] = 0;
		result[2] = 0;
		result[3] = 0;
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		win2p.calculate();
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		board2p.canPlace(2, 2, true, 0, Color.REDDD, 0);
		board2p.addHome(2, 2);
		board2p.addRing(2, 3, false, 1, Color.REDDD);
		win2p = new WinConditions(board2p);
		result[0] = 1;
		win2p.calculate();
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		board2p.addRing(2, 3, false, 0, Color.REDDD);
		win2p = new WinConditions(board2p);
		win2p.calculate();
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		board2p.addRing(2, 3, false, 2, Color.REDDD);
		win2p = new WinConditions(board2p);
		win2p.calculate();
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		board2p.addRing(2, 3, false, 3, Color.REDDD);
		win2p = new WinConditions(board2p);
		win2p.calculate();
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		board2p = new Board(2, set2p);
		board2p.addHome(2, 2);
		board2p.addRing(2, 3, false, 0, Color.REDDD);
		board2p.addRing(2, 3, false, 1, Color.BLUEE);
		win2p = new WinConditions(board2p);
		win2p.calculate();
		result[0] = 0;
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		board2p.addRing(2, 3, false, 2, Color.GREEN);
		win2p = new WinConditions(board2p);
		win2p.calculate();
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		board2p.addRing(2, 3, false, 3, Color.YELLO);
		win2p = new WinConditions(board2p);
		win2p.calculate();
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		board2p = new Board(2, set2p);
		board2p.addHome(2, 2);
		board2p.addRing(2, 3, false, 0, Color.REDDD);
		board2p.addRing(2, 3, false, 1, Color.REDDD);
		board2p.addRing(2, 3, false, 2, Color.BLUEE);
		board2p.addRing(2, 3, false, 3, Color.BLUEE);
		win2p = new WinConditions(board2p);
		win2p.calculate();
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
	}

}
