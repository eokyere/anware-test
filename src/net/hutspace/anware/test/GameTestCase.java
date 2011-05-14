package net.hutspace.anware.test;

import net.hutspace.anware.core.Game;
import net.hutspace.anware.core.IllegalMove;
import junit.framework.TestCase;

public class GameTestCase extends TestCase {
	Game game;

	void validatePits(int[] pits) {
		for (int i = 0; i < pits.length; ++i)
			assertEquals(pits[i], game.pit(i));
	}
	
	void validateStores(int[] stores) {
		for (int i = 0; i < stores.length; ++i)
			assertEquals(stores[i], game.store(i));
	}

	void makeMoves(Integer[] moves) throws IllegalMove {
		for (int i = 0; i < moves.length; ++i)
			game.move(moves[i]);
	}
	
	void makeMoves(int[] moves) throws IllegalMove {
		for (int i = 0; i < moves.length; ++i)
			game.move(moves[i]);
	}
}
