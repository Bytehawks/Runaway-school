package com.bytehawks.engine;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

/**
 * Texture engine
 * 
 * @author Tobias Schult
 * 
 */
public class BytehawksTextureEngine
{
	private CopyOnWriteArrayList<BytehawksTexture> mTexturesX;

	private GL10 mGl;

	BytehawksTextureEngine()
	{
		mTexturesX = new CopyOnWriteArrayList<BytehawksTexture>();
	}

	public void destroy(GL10 gl)
	{
		mGl = gl;
		onContextLost();
	}

	public void onContextLost()
	{
		if (mGl != null)
		{
			int d = 0;
			int[] textures = new int[mTexturesX.size()];
			Iterator<BytehawksTexture> it = mTexturesX.iterator();
			while (it.hasNext())
				textures[d++] = it.next().mHWTextureID;

			mGl.glDeleteTextures(d, textures, 0);
			mTexturesX.clear();
		}
	}

	public void loadTextures(GL10 gl)
	{
		mGl = gl;
		if (mGl != null)
		{
			Iterator<BytehawksTexture> it = mTexturesX.iterator();
			while (it.hasNext())
				it.next().linkToGL(mGl);
		}
	}

	public BytehawksTexture createTextureFromFont(BytehawksFont font)
	{
		BytehawksTexture tex = null;
		Iterator<BytehawksTexture> it = mTexturesX.iterator();
		while (it.hasNext())
		{
			tex = it.next();
			if (tex instanceof BytehawksFontTexture)
			{
				// Texture already exists
				if (((BytehawksFontTexture) tex).mFont == font)
				{
					tex.mRefernces++;
					return tex;
				}
			}
		}

		tex = new BytehawksFontTexture(this, font);
		mTexturesX.add(tex);
		tex.linkToGL(mGl);
		return tex;
	}
	
	public BytehawksTexture createTextureFromResourceId(int resourceId)
	{
		BytehawksTexture tex = null;
		Iterator<BytehawksTexture> it = mTexturesX.iterator();
		while (it.hasNext())
		{
			tex = it.next();
			if (tex instanceof BytehawksResourceTexture)
			{
				// Texture already exists
				if (((BytehawksResourceTexture) tex).mResourceID == resourceId)
				{
					tex.mRefernces++;
					return tex;
				}
			}
		}

		tex = new BytehawksResourceTexture(this, resourceId);
		mTexturesX.add(tex);
		return tex;
	}

	public int generateTexture()
	{
		if (mGl != null)
		{
			int[] textureIDs = new int[1];

			mGl.glGenTextures(1, textureIDs, 0);

			int error = mGl.glGetError();
			if (error != GL10.GL_NO_ERROR)
				Log.e("BytehawksTexture", "generate GLError: " + error);
			else
				return textureIDs[0];
		}
		return -1;
	}

	public void deleteTexture(BytehawksTexture tex)
	{
		if (mTexturesX.indexOf(tex) > -1)
		{
			tex.mRefernces--;
			if (tex.mRefernces < 0)
				mTexturesX.remove(tex);
		}
		if (tex.mHWTextureID > -1)
		{
			int[] texture = new int[1];
			texture[0] = tex.mHWTextureID;
			mGl.glDeleteTextures(1, texture, 0);
		}
	}
}