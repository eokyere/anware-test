package net.hutspace.anware.test;

import net.hutspace.anware.Anware;
import net.hutspace.anware.R;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;

public class AnwareTest extends ActivityInstrumentationTestCase2<Anware> {
	private Anware activity; 
	private Button newGameButton;

	public AnwareTest() {
		super("net.hutspace.anware", Anware.class);
	}

	@Override
	protected void setUp() throws Exception { 
		super.setUp();
		setActivityInitialTouchMode(true);
		activity = getActivity();
		newGameButton = (Button) activity.findViewById(R.id.new_game_button);
	}
	
//	public void testNewGame() throws Throwable {
//		TouchUtils.clickView(this, newGameButton);
//	}
	
}
