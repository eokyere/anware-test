package net.hutspace.anware.test;

import net.hutspace.anware.Board;
import net.hutspace.anware.GameActivity;
import net.hutspace.anware.R;
import android.test.ActivityInstrumentationTestCase2;
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
		TextView txtTurn = (TextView) activity.findViewById(R.id.txt_turn);
		assertNotNull(txtTurn);
		assertEquals("Player 1 to play", txtTurn.getText().toString());
	}
}
