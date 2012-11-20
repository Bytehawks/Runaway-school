package info.android.runaway;

import java.util.Random;

import com.bytehawks.engine.BytehawksTileBank;
import com.bytehawks.engine.BytehawksTileMap;

public class Level extends BytehawksTileMap {
	
	public static final boolean RIGHT = true;
	public static final boolean DOWN = false;
	
	private int difficulty = 1;
	private int maxAllowedMoves = 1;
	private int minAllowedMoves = 1;
	private static int[] maxhelper = {1,1,2,2,2,2,3,3,3,3,4,4,5,5,5,5,5,5,7,7,7,7,7,7};
	private BytehawksTileBank tileBank;
	
	public Level(BytehawksTileBank tileBank, int width, int height,
			int difficulty) {
		super(tileBank, width, height, (int)((difficulty/2)+4+.5f), (int)((difficulty/2)+4+.5f), false,
				false);
		this.difficulty = difficulty;
		this.setTileBank(tileBank);
	}


		public int getDifficulty() {
			return this.difficulty;
		}
		public int getMaxAllowedMoves() {
			return this.maxAllowedMoves;
		}
		public int getMinAllowedMoves() {
			return this.minAllowedMoves;
		}

		public void setDifficulty(int difficulty) {
			this.minAllowedMoves = (difficulty/ 6 + 3);
			this.maxAllowedMoves = (difficulty/ 24 * 7 + maxhelper[difficulty%24]+2);
			this.difficulty = difficulty;
		}

		public void createLevel(BytehawksTileBank bank) {
			this.setDifficulty(this.difficulty);
			int oneRowTiles = (int) Math.sqrt(this.mColumnsCount*this.mRowsCount);
			int newWidth = oneRowTiles*32;
			Random rand = new Random();
			for (int t=0;t<this.mColumnsCount*this.mRowsCount;t++)
				this.mMap[t]=(int) (Math.random()*bank.mTilesCount);
			// Put the bottom of the camera at the lowest part of the map
			this.mScale = (float) this.mWidth/newWidth;
			// Schritte berechnen
	    	boolean[] steps = new boolean[(int)(Math.random()*(minAllowedMoves-maxAllowedMoves))+1+difficulty/ 6 + 3];
	    	for(int i = 0; i < steps.length; i++){
	    		steps[i] = rand.nextBoolean() ? RIGHT : DOWN;
	    	}
	    	int x = 0;
	    	int y = 0;
	    	int pos = 0;
	    	for (int t=0;t<this.mColumnsCount*this.mRowsCount;t++){
	    		if(x < this.mColumnsCount && y < this.mRowsCount)
	    			this.mMap[((x)+(y*oneRowTiles))] = 0;
	    		if(steps[pos%steps.length] == RIGHT)
	    			x++;
	    		else
	    			y++;
	    		pos++;
	    	}
		}


		public BytehawksTileBank getTileBank() {
			return tileBank;
		}


		public void setTileBank(BytehawksTileBank tileBank) {
			this.tileBank = tileBank;
		}
	
}
