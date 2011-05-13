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
		assertInfoIs("Player 1 to play");
		play(0);
		assertInfoIs("Player 2 to play");
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
		assertInfoIs("Player 2 has won!!");
	}
	
	public void testUndo() {
		final String info = "Player 1 to play";
		assertInfoIs(info);
		play(0);
		undo();
		assertInfoIs(info);
	}
	
	public void testRedo() {
		assertInfoIs("Player 1 to play");
		play(0);
		undo();
		redo();
		assertInfoIs("Player 2 to play");
	}
	
	public void testUndoMoveUndoRedo() {
		assertInfoIs("Player 1 to play");
		play(0);
		undo();
		play(4);
		undo();
		redo();
		assertInfoIs("Player 2 to play");
	}

	private void assertToPlayIs(final int lbl) {
		assertInfoIs(String.format("Player %s to play", lbl));
	}
	
	private void assertInfoIs(final String s) {
		final TextView txtInfo = (TextView) activity.findViewById(R.id.txt_turn);
		assertNotNull(txtInfo);
		assertEquals(s, txtInfo.getText().toString());
	}
	
	private void play(int i) {
		TouchUtils.clickView(this, activity.getPit(i));
	}

	private void undo() {
		TouchUtils.clickView(this, activity.findViewById(R.id.btn_undo));
	}
	
	private void redo() {
		TouchUtils.clickView(this, activity.findViewById(R.id.btn_redo));
	}
}