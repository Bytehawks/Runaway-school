package info.android.runaway;

import com.bytehawks.engine.BytehawksActivity;
import com.bytehawks.engine.BytehawksUI;
import com.bytehawks.engine.FPSCounter;


import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;


public class GameActivity extends BytehawksActivity {

	public static final boolean RIGHT = true;
	public static final boolean DOWN = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		DisplayMetrics displaymetrics = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		mGLSurfaceView.addObject(new FPSCounter());
		FrameLayout mMainLayout=new FrameLayout(this);
		mMainLayout.addView(mGLSurfaceView);
		setContentView(R.layout.activity_game);
		addContentView(mMainLayout,  new ViewGroup.LayoutParams(-1, displaymetrics.heightPixels-150));
		
		BytehawksUI mDemo = new GameUI(this);
		setUI(mDemo);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{		
		if ((keyCode == KeyEvent.KEYCODE_BACK)){
			mGLSurfaceView.getmGLThread().requestExitAndWait();			
		}
		return super.onKeyDown(keyCode, event);
	}	
	
}
