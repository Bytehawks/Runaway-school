package com.bytehawks.engine;;

/**
 * Sprite base class
 * @author Tobias Schult
 *
 */
public abstract class BytehawksAbstractSprite extends BytehawksObject
{
	public BytehawksSpriteLayout roLayout; //Sprite Layout with information about how to draw the sprite
	public int roFrame; //Frame number. (ReadOnly)
	public BytehawksVector mPosition; //Set to change the position of the sprite
	public BytehawksVector mScale; //Set to change the scale of the sprite
	protected boolean mFlipHorizontal;
	protected boolean mFlipVertical;
	public float mRed;   //Red tint (0 - 1)
	public float mGreen;	//Green tint (0 - 1)
	public float mBlue;	//Blue tint (0 - 1)
	public float mAlpha;	//Alpha channel (0 - 1)

	/**
	 * Every sprite needs an BytehawksSpriteLayout to know how to draw itself
	 * @param layout the BytehawksSpriteLayout
	 */
	public BytehawksAbstractSprite(BytehawksSpriteLayout layout)
	{
		mScale = new BytehawksVector(1, 1);
		mPosition = new BytehawksVector(0, 0);
		roLayout=layout;
		mRed=1;
		mGreen=1;
		mBlue=1;
		mAlpha=1;
	}

	/**
	 * Set current frame number
	 * @param frame frame number
	 */
	public abstract void setFrame(int frame);
	
	/**
	 * Set if sprite is flipped horizontally or vertically  
	 * @param flipHorizontal
	 * @param flipVertical
	 */
	public void setFlip(boolean flipHorizontal, boolean flipVertical)
	{
		mFlipHorizontal=flipHorizontal;
		mFlipVertical=flipVertical;
		setFrame(roFrame);
	}
	
	/**
	 * Change the BytehawksSpriteLayout
	 * @param layout
	 */
	public void setLayout(BytehawksSpriteLayout layout)
	{
		roLayout=layout;
	}
}
