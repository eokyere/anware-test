package net.hutspace.anware.test;

import net.hutspace.anware.Board;
import net.hutspace.anware.GameActivity;
import net.hutspace.anware.R;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.TextView;

public class GameActivityTest extends ActivityInstrumentationTestCase2<GameActivity> {
	GameActivity activity;
	Board board;
	
	public GameActivityTest() {
		super("net.hutspace.anware", GameActivity.class);
	}

	@Override
	protected void setUp() throws Exception { 
		super.setUp();
		activity = getActivity();		
		board = (Board) activity.findViewById(R.layout.board);
	}
	
	protected void testPreConditions() {
		assertNotNull(activity);
		assertNotNull(board);
	}
	
	public void testNewGame() {
		assertToPlayIs("Player 1 to play");
		play(0);
		assertToPlayIs("Player 2 to play");
	}

	// XXX: takes 12 secs to run
	public void testCompleteGame() {
		int[] moves = NamNamTest.COMPLETE_GAME;
		for (int i = 0; i < moves.length; ++i) {
			final int p0 = i % 2 + 1;
			assertToPlayIs(p0);
			play(moves[i]);
//			final int p1 = (i + 1) % 2 + 1;
//			assertToPlayIs(p1);
		}
		assertToPlayIs("Player 2 has won!!");
	}

	private void assertToPlayIs(final int lbl) {
		assertToPlayIs(String.format("Player %s to play", lbl));
	}
	
	private void assertToPlayIs(final String s) {
		final TextView txtTurn = (TextView) activity.findViewById(R.id.txt_turn);
		assertNotNull(txtTurn);
		assertEquals(s, txtTurn.getText().toString());
	}
	
	private void play(int i) {
		TouchUtils.clickView(this, activity.getPit(i));
	}
}
