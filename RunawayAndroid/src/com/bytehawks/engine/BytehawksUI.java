package com.bytehawks.engine;

import android.view.KeyEvent;
import android.view.MotionEvent;

import com.bytehawks.engine.BytehawksObject;

/**
 * Base user interface
 * @author Tobias Schult
 *
 */
public class BytehawksUI extends BytehawksObject
{
	protected BytehawksActivity mActivity;

	public BytehawksUI (BytehawksActivity activity)
	{
		mActivity=activity;
	}
	
	public boolean onTouchEvent(MotionEvent event) 
	{
		return false;
	}

	public boolean onTrackballEvent(MotionEvent event) 
	{ 
		return false;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{ 
		return false;
	}
	
	public void onPause() {}

	public void onResume() {}

	public void onActivate() {}

	public void onDeactivate() {}

}
