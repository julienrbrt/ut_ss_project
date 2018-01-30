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
	WinConditions win3p;
	WinConditions win4p;
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
		win2p = new WinConditions(board2p);
		win3p = new WinConditions(board3p);
		win4p = new WinConditions(board4p);
	}
	
	@Test
	public void winConditionsTest() {
		int[] result = new int[4];
		result[0] = 0;
		result[1] = 0;
		result[2] = 0;
		result[3] = 0;
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		assertEquals(Arrays.toString(result), Arrays.toString(win3p.getScores()));
		assertEquals(Arrays.toString(result), Arrays.toString(win4p.getScores()));
		win2p.calculate();
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		board2p.canPlace(2, 2, true, 0, Color.REDDD, 0);
		board2p.addHome(2, 2);
		board2p.addRing(2, 3, false, 1, Color.REDDD);
		win2p = new WinConditions(board2p);
		result[0] = 1;
		win2p.calculate();
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
		assertEquals(Arrays.toString(result), Arrays.toString(win2p.getScores()));
	}

}
