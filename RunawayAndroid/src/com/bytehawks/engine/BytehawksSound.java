package com.bytehawks.engine;

import android.content.Context;

/** 
* @author Tobias Schult
*/
public class BytehawksSound extends BytehawksAudioObject
{
	private static final int sDefaultSimultaneousSounds = 3;

	public BytehawksSound (Context activity)
	{
		super(activity,sDefaultSimultaneousSounds);
	}
	
	public BytehawksSound(BytehawksActivity activity, int resId)
	{
		super(activity,sDefaultSimultaneousSounds);
		load(resId);
	}

	public BytehawksSound(BytehawksActivity activity, int resId, int simultaneousSounds)
	{
		super(activity,simultaneousSounds);
		load(resId);
	}

}
