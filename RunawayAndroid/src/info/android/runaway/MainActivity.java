package info.android.runaway;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    	ImageView imgview = (ImageView)findViewById(R.id.imageView1);
    	createBitmap(imgview.getWidth(), imgview.getHeight());
    	imgview.setImageBitmap(createBitmap(imgview.getWidth(), imgview.getHeight()));
    	super.onWindowFocusChanged(hasFocus);
    }
    
    public Bitmap createBitmap(int width, int height) {
    	Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
    	int color = 0xff00ffff;
    	Paint paint = new Paint();
    	Rect rect = new Rect(0, 0, width, height);
    	RectF rectF = new RectF(rect);
    	canvas.drawARGB(0, 0, 0, 0);
    	paint.setColor(color);
    	canvas.drawRect(rectF, paint);
    	Bitmap bombfield = BitmapFactory.decodeFile("bombfield.png");
    	return bombfield;
//    	canvas.drawBitmap(bombfield, 10, 10);
//    	return bitmap;
    }
}
