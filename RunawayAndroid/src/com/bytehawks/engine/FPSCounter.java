package com.bytehawks.engine;

import android.util.Log;

/**
 * FPS displayed in LogCat
 * @author Tobias Schult
 *
 */
public class FPSCounter extends BytehawksObject
{
	private int frameCount;
	private long lCTM;
	public float mFPS;

	@Override
	public void step(float secondsElapsed)
	{
		// Add FPS record to log every 100 frames
		frameCount++;
		if (frameCount >= 100)
		{
			long CTM = System.currentTimeMillis();
			frameCount = 0;
			if (lCTM > 0)
			{
				mFPS=(100.f / ((CTM - lCTM) / 1000.f));
				Log.v("FPS","" + mFPS);
			}
			lCTM = CTM;
		}
		// --------------------------------------

		super.step(secondsElapsed);
	}
}
