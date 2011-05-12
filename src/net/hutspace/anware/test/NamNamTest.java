package net.hutspace.anware.test;

import junit.framework.TestCase;
import net.hutspace.anware.core.Game;
import net.hutspace.anware.core.IllegalMove;
import net.hutspace.anware.core.NamNamGame;

public class NamNamTest extends TestCase {
	private Game game;
	private static int[] COMPLETE_GAME = new int[] {0, 6, 3, 8, 2, 9, 4, 6, 0, 8};
	
	protected void setUp() throws Exception {
		game = new NamNamGame();
	}

	public void testBeginState() {
		for (int i = 0; i < 12; ++i)
			assertEquals(4, game.pit(i));
		
		for (int i = 0; i < 2; ++i)
			assertEquals(0, game.store(i));
		
		for (int i = 0; i < 6; ++i)
			assertTrue("Player 1 can make move > " + i, game.valid(i));
		for (int i = 6; i < 12; ++i)
			assertFalse("Player 1 cannot make move > " + i, game.valid(i));
	}
	
	public void testLegalMoveAtStart() {
		assertEquals(game.turn(), Game.PLAYER_ONE);
		try {
			game.move(0);
			validatePits(new int[]{2, 7, 1, 6, 1, 6, 6, 6, 0, 1, 6, 6});
			validateStores(new int[]{0, 0});
		} catch (IllegalMove e) {
			fail("Move should be valid");
		}
	}

	public void testAttemptToMoveFromEmptyPit() {
		try {
			game.move(2);
			validatePits(new int[]{6, 6, 2, 7, 1, 6, 1, 6, 6, 6, 0, 1});
		} catch (IllegalMove e) {
			fail("Should not throw exception for IllegalMove");
		}
		try {
			game.move(10);
			fail("Should throw exception for IllegalMove");
		} catch (Exception e) {
		}
	}

	public void testPlayOutOfTurn() {
		try {
			game.move(6);
			fail("Only player 2 can start from pit 6");
		} catch (IllegalMove e) {
		}
	}
	
	
	public void testAPairOfLegalMoves() {
		try {
			game.move(2);
			validatePits(new int[]{6, 6, 2, 7, 1, 6, 1, 6, 6, 6, 0, 1});
			game.move(6);
			validatePits(new int[]{1, 9, 2, 10, 0, 0, 3, 0, 1, 10, 0, 0});
		} catch (IllegalMove e) {
			fail("All moves should be valid");
		}
	}
	
	public void testCompleteGame() {
		try {
			makeMoves(COMPLETE_GAME);
			validatePits(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
			validateStores(new int[]{20, 28});
		} catch (IllegalMove e) {
			fail("All moves should be valid");
		}
	}

	public void testUndoAll() {
		try {
			makeMoves(COMPLETE_GAME);
			for (int i = 0; i < COMPLETE_GAME.length; ++i)
				game.undo();
			validatePits(new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4});
			validateStores(new int[]{0, 0});
		} catch (IllegalMove e) {
			fail("All moves should be valid");
		}
	}
	
	public void testCompleteGameWithUndoRedo() {
		try {
			makeMoves(COMPLETE_GAME);
			game.undo();
			game.redo();
			validatePits(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
			validateStores(new int[]{20, 28});
		} catch (IllegalMove e) {
			fail("All moves should be valid");
		}
	}
	
	public void testMoveThatCaptures() {
		try {
			game.move(2);
			validateStores(new int[]{0, 0});
			game.move(6);
			validateStores(new int[]{4, 8});
		} catch (IllegalMove e) {
			fail("All moves should be valid");
		}
	}
	
	private void validatePits(int[] pits) {
		for (int i = 0; i < pits.length; ++i)
			assertEquals(pits[i], game.pit(i));
	}
	
	private void validateStores(int[] stores) {
		for (int i = 0; i < stores.length; ++i)
			assertEquals(stores[i], game.store(i));
	}

	private void makeMoves(int[] moves) throws IllegalMove {
		for (int i = 0; i < moves.length; ++i)
			game.move(moves[i]);
	}
}
