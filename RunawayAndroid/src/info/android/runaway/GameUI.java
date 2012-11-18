package info.android.runaway;

import info.android.runaway.controls.Controlbar;
import info.android.runaway.controls.Sequence;


import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bytehawks.engine.BytehawksActivity;
import com.bytehawks.engine.BytehawksObject;
import com.bytehawks.engine.BytehawksSpriteLayout;
import com.bytehawks.engine.BytehawksTileBank;
import com.bytehawks.engine.BytehawksUI;
import com.bytehawks.engine.BytehawksVector;

public class GameUI extends BytehawksUI {
	
	private Level levelObject;
	private BytehawksTileBank levelGround;
	
	private int height;
	private int width;
	
	private BytehawksObject field;
	private int difficulty = 4;
	@SuppressWarnings("unused")
	private Controlbar controlbar;
	private Sequence sequencebar;
	private  BytehawksSpriteLayout figureLayout;
	private  AndroidFigure figure;
	
	
    
    public GameUI(BytehawksActivity activity) {
		super(activity);
        DisplayMetrics displaymetrics = new DisplayMetrics();
    	activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    	this.height = displaymetrics.heightPixels-150;
    	this.width = displaymetrics.widthPixels;
        field=addObject(new BytehawksObject());
        levelGround = new BytehawksTileBank(activity.mGLSurfaceView,R.drawable.tilemap,5,5,32,32);
        levelObject = new Level(levelGround, this.width, this.height,this.difficulty );
        levelObject.createLevel(levelGround);       
        field.addObject(levelObject);
        figureLayout = new BytehawksSpriteLayout(activity.mGLSurfaceView,32, 32, R.drawable.android);
		figure = new AndroidFigure(figureLayout);		
		field.addObject(figure);
      
        this.sequencebar = new Sequence(
    		levelObject.getMinAllowedMoves(), 
    		levelObject.getMaxAllowedMoves(),
    		activity,
    		(LinearLayout)activity.findViewById(R.id.seqeunce_container)
		);
        this.controlbar = new Controlbar(
    			this.sequencebar,
    			(Button) activity.findViewById(R.id.c_down),
    			(Button) activity.findViewById(R.id.c_right),
    			(Button) activity.findViewById(R.id.c_delete),
    			(Button) activity.findViewById(R.id.c_start)
        	); 
    }
    

}
