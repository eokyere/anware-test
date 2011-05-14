package net.hutspace.anware.test;

import java.util.ArrayList;
import java.util.List;

import net.hutspace.anware.ai.AI;
import net.hutspace.anware.ai.MiniMax;
import net.hutspace.anware.core.IllegalMove;
import net.hutspace.anware.core.NamNamGame;

public class AITest extends GameTestCase {
	private AI ai; 
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		ai = new MiniMax();
		game = new NamNamGame();
	}
	
	public void testWinGame() {
		final int[] v = NamNamTest.COMPLETE_GAME;
		final List<Integer> xs = new ArrayList<Integer>();
		for (int i = 0; i < v.length; ++i)
			xs.add(v[i]);
		
		try {
			makeMoves(xs.subList(0, xs.size() - 1).toArray(new Integer[]{}));
			assertEquals(1, game.turn());
			assertEquals(3, game.validMoves().size());
			assertEquals(8, ai.move(game));
			game.move(ai.move(game));
			assertEquals(1, game.getWinner());
		} catch (IllegalMove e) {
			fail("We should not get here");
		}
	}
}
