package info.android.runaway;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.widget.ImageView;

public class MainActivity extends Activity {

	public static final boolean BOMB = true;
	public static final boolean NOBOMB = false;
	public static final boolean RIGHT = true;
	public static final boolean DOWN = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    	int level = 50;
    	ImageView imgview = (ImageView)findViewById(R.id.imageView1);
    	int[] levelInfo = levelCalculatorA(level);
    	boolean[] levelMap = levelGenerator(levelInfo);
    	Bitmap gamefield = drawMatrix(imgview.getWidth(), imgview.getHeight(), levelMap, levelInfo[0]);
    	imgview.setImageBitmap(gamefield);
    	super.onWindowFocusChanged(hasFocus);
    }
    
    public int[] levelCalculatorA(int level) {
    	int[] maxhelper = {1,1,2,2,2,2,3,3,3,3,4,4,5,5,5,5,5,5,7,7,7,7,7,7};
    	int[] ret = {0,0,0};
    	ret[0] = level / 2 + 3;
    	ret[1] = level / 6 + 2;
    	ret[2] = level / 24 * 7 + maxhelper[level%24]+1;
    	return ret;
    }
    
    public boolean[] levelGenerator(int[] info) {
    	int line = info[0];
    	int fullLen = line*line;
    	boolean[] ret = new boolean[info[0] * info[0]];
    	// Zufallskarte erstellen true = bombe!
    	Random rand = new Random();
    	for(int i = 0; i < fullLen; i++){
    		ret[i] = rand.nextBoolean() ? BOMB : NOBOMB;
    	}
    	// Schritte berechnen
    	boolean[] steps = new boolean[(int)(Math.random()*(info[2]-info[1]))+1+info[1]];
    	for(int i = 0; i < steps.length; i++){
    		steps[i] = rand.nextBoolean() ? RIGHT : DOWN;
    	}
    	// Weg auf der Karte freiräumen
    	int x = 0;
    	int y = 0;
    	int pos = 0;
    	while(x < line && y < line){
    		ret[x+(y*line)] = NOBOMB;
    		if(steps[pos%steps.length] == RIGHT)
    			x++;
    		else
    			y++;
    		pos++;
    	}
    	return ret;
    }
    
    public Bitmap drawMatrix(int width, int height, boolean[] fields, int line) {
    	Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    	Canvas canvas = new Canvas(bitmap);
    	Bitmap bombfield = BitmapFactory.decodeResource(getResources(), R.drawable.bombfield);
    	int fieldWidth = width/line;
    	bombfield = getResizedBitmap(bombfield, fieldWidth, fieldWidth);
    	for(int j = 0, i = 0; i < line*line; i++){
    		if(i > 0 && i % line == 0)
    			j++;
			if(fields[i])
				canvas.drawBitmap(bombfield, (i%line)*fieldWidth, j*fieldWidth, null);
    	}
    	return bitmap;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
    	int width = bm.getWidth();
    	int height = bm.getHeight();
    	float scaleWidth = ((float) newWidth) / width;
    	float scaleHeight = ((float) newHeight) / height;
    	// create a matrix for the manipulation
    	Matrix matrix = new Matrix();
    	// resize the bit map
    	matrix.postScale(scaleWidth, scaleHeight);
    	// recreate the new Bitmap
    	Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    	return resizedBitmap;
    }
}
