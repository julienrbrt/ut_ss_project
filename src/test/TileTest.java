package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Tile;
import game.player.Color;

public class TileTest {

	Tile tile;
	
	@Before
	public void setUp() {
		tile = new Tile();
	}
	
	@Test
	public void test() {
		assertTrue(tile.isTileEmpty());
		assertTrue(tile.isSpotEmpty(0));
		tile.change(true, 0, Color.REDDD);
		assertFalse(tile.isTileEmpty());
		assertFalse(tile.isSpotEmpty(0));
		assertEquals(Color.Group.BASE, tile.getColor(0).getGroup());
		assertTrue(tile.contains(Color.ColGroup.RED));
		assertFalse(tile.contains(Color.ColGroup.BLUE));
		assertTrue(tile.hasBase(Color.REDDD));
		assertFalse(tile.hasBase(Color.GREEN));
	}

}
