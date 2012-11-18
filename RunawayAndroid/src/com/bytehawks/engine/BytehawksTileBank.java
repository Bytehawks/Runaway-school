package com.bytehawks.engine;

import javax.microedition.khronos.opengles.GL10;

/**
 * Bank with tiles for tile map
 * @author Tobias Schult
 *
 */
public class BytehawksTileBank
{
	public int mTilesCount;
	public int mTilesColumns;

	private BytehawksTextureEngine mTextureEngine;
	public BytehawksTexture mTexture;
	public int mTileWidth;
	public int mTileHeight;

	public BytehawksTileBank (BytehawksSurfaceView view, int resourceId, int tilesCount, int tilesColumns, int tileWidth, int tileHeight)
	{
		mTextureEngine=view.getTextureEngine();
		mTexture = mTextureEngine.createTextureFromResourceId(resourceId);
		mTilesCount=tilesCount;
		mTilesColumns=tilesColumns;
		mTileWidth=tileWidth;
		mTileHeight=tileHeight;
		
	}

	public void onDestroy(GL10 gl)
	{
		mTextureEngine.deleteTexture(mTexture);
	}
}
