package com.bytehawks.engine;

/** 
* @author Tobias Schult
*/
public class BytehawksMusic extends BytehawksAudioObject
{
	public BytehawksMusic(BytehawksActivity activity)
	{
		super(activity,1);
	}

	public BytehawksMusic(BytehawksActivity activity, int resId)
	{
		super(activity,1);
		load(resId);
	}

}
