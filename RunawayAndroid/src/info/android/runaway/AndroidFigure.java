package info.android.runaway;


import com.bytehawks.engine.BytehawksSprite;
import com.bytehawks.engine.BytehawksSpriteLayout;
import com.bytehawks.engine.BytehawksVector;


class AndroidFigure extends BytehawksSprite{
	BytehawksVector mDestination;
	float Speed; 
	public AndroidFigure(BytehawksSpriteLayout sprite)
	{
		super(sprite);
		mPosition.set(16,16);
		mDestination=new BytehawksVector(mPosition); 
		Speed=200;
	}
	@Override
	public void step(float secondsElapsed)
	{
		if ((int)mPosition.mX<(int)mDestination.mX)
			mPosition.mX+=Speed*secondsElapsed;
		if ((int)mPosition.mX>(int)mDestination.mX)
			mPosition.mX-=Speed*secondsElapsed;
		if ((int)mPosition.mY<(int)mDestination.mY)
			mPosition.mY+=Speed*secondsElapsed;
		if ((int)mPosition.mY>(int)mDestination.mY)
			mPosition.mY-=Speed*secondsElapsed;
		super.step(secondsElapsed);
	}
}