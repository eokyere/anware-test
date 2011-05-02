package net.hutspace.anware.test;

import net.hutspace.anware.GameActivity;
import android.test.ActivityInstrumentationTestCase2;

public class GameActivityTest extends ActivityInstrumentationTestCase2<GameActivity> {
	public GameActivityTest() {
		super("net.hutspace.anware", GameActivity.class);
	}

	@Override
	protected void setUp() throws Exception { 
		super.setUp();
		final GameActivity activity = getActivity(); 
	}
}
