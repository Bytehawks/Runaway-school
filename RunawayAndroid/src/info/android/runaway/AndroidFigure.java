package info.android.runaway;



import com.bytehawks.engine.BytehawksSprite;
import com.bytehawks.engine.BytehawksSpriteLayout;
import com.bytehawks.engine.BytehawksVector;


public class AndroidFigure extends BytehawksSprite{
	private BytehawksVector mDestination;
	private float Speed; 
	private Level level;
	public AndroidFigure(BytehawksSpriteLayout sprite, Level levelObject){
		super(sprite);
		this.setLevel(levelObject);
		mPosition.set(levelObject.mScale*16,levelObject.mScale*16);
		mDestination=new BytehawksVector(mPosition);
		this.setScale(new BytehawksVector(levelObject.mScale, levelObject.mScale));
		Speed=200;
	}
	@Override
	public void step(float secondsElapsed){
		
		if (mPosition.mX<mDestination.mX)
			mPosition.mX+=Speed*secondsElapsed;
		if (mPosition.mX>mDestination.mX)
			mPosition.mX-=Speed*secondsElapsed;
		if (mPosition.mY<mDestination.mY)
			mPosition.mY+=Speed*secondsElapsed;
		if (mPosition.mY>mDestination.mY)
			mPosition.mY-=Speed*secondsElapsed;
		super.step(secondsElapsed);
	}
	
	public void setScale(BytehawksVector vector){
		vector.div(2);
		this.mScale.set(vector);
	}
	
	public void moveToDestination(BytehawksVector vector){
		vector.add(this.mPosition);
		this.mDestination = vector;
	}
	
	public boolean hasCollided() {
		int oneRowTiles = (int) Math.sqrt(getLevel().mColumnsCount*getLevel().mRowsCount);
		int x = (int) (this.mPosition.mX/(this.mScale.mX*64));
		int y = (int) (this.mPosition.mY/(this.mScale.mY*64));
		if(getLevel().mMap[((x)+(y*oneRowTiles))] == 3 || getLevel().mMap[((x)+(y*oneRowTiles))] == 4){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean isOutside() {
		int oneRowTiles = (int) Math.sqrt(getLevel().mColumnsCount*getLevel().mRowsCount);
		int x = (int) (this.mPosition.mX/(this.mScale.mX*48));
		int y = (int) (this.mPosition.mY/(this.mScale.mY*48));		
		if(oneRowTiles <=  x || oneRowTiles <= y){
			return true;
		}
		else{
			return false;
		}
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
}