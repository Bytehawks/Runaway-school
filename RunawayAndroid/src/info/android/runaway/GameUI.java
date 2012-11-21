package info.android.runaway;

import info.android.runaway.controls.Controlbar;
import info.android.runaway.controls.Sequence;


import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageButton;
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
	private int difficulty = 1;
	@SuppressWarnings("unused")
	private Controlbar controlbar;
	private Sequence sequencebar;
	private  BytehawksSpriteLayout figureLayout;
	private  AndroidFigure figure;
	private BytehawksActivity activity;
	public static final float dp = 155f;
	
    
    public GameUI(BytehawksActivity activity) {
		super(activity);
		this.activity = activity;
        DisplayMetrics displaymetrics = new DisplayMetrics();
    	activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
    	this.height = (int) (displaymetrics.heightPixels-(displaymetrics.density * dp + 0.5f));
    	this.width = displaymetrics.widthPixels;
        field=addObject(new BytehawksObject());
        levelGround = new BytehawksTileBank(activity.mGLSurfaceView,R.drawable.tilemap,5,5,32,32);
        levelObject = new Level(levelGround, this.width, this.height,this.difficulty);
        levelObject.createLevel(levelGround);       
        field.addObject(levelObject);
        figureLayout = new BytehawksSpriteLayout(activity.mGLSurfaceView,64, 64, R.drawable.android);
		figure = new AndroidFigure(figureLayout,levelObject);	
		field.addObject(figure);
      
        this.sequencebar = new Sequence(
    		levelObject.getMinAllowedMoves(), 
    		levelObject.getMaxAllowedMoves(),
    		activity,
    		(LinearLayout)activity.findViewById(R.id.seqeunce_container)
		);
        this.controlbar = new Controlbar(
    			this.sequencebar,
    			(ImageButton) activity.findViewById(R.id.c_down),
    			(ImageButton) activity.findViewById(R.id.c_right),
    			(Button) activity.findViewById(R.id.c_delete),
    			(Button) activity.findViewById(R.id.c_start),
    			figure,
    			this
        );         
        activity.setTitle("Level:"+levelObject.getDifficulty()+" Max moves:"+ levelObject.getMaxAllowedMoves());
    }
    
    public void incDifficulty(){	
    	DisplayMetrics displaymetrics = new DisplayMetrics();
		this.difficulty++; 	
		field.removeAll();
	    	activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
	    	this.height = (int) (displaymetrics.heightPixels-(displaymetrics.density * dp + 0.5f));
	    	this.width = displaymetrics.widthPixels;
	    	levelObject = new Level(levelGround, this.width, this.height,this.difficulty);
	        levelObject.createLevel(levelGround);       
	        field.addObject(levelObject);
	        figure.setScale(new BytehawksVector(levelObject.mScale, levelObject.mScale));
	        figure.mPosition.set(figure.mScale.mX*32,figure.mScale.mX*32);
       	 	figure.moveToDestination(new BytehawksVector(0,0));
	        figureLayout = new BytehawksSpriteLayout(activity.mGLSurfaceView,64, 64, R.drawable.android);
			figure = new AndroidFigure(figureLayout,levelObject);	
			field.addObject(figure);			
			this.sequencebar.setMaxLength(levelObject.getMaxAllowedMoves());
			this.sequencebar.setMinLength(levelObject.getMinAllowedMoves());	      
	        this.controlbar = new Controlbar(
	    			this.sequencebar,
	    			(ImageButton) activity.findViewById(R.id.c_down),
	    			(ImageButton) activity.findViewById(R.id.c_right),
	    			(Button) activity.findViewById(R.id.c_delete),
	    			(Button) activity.findViewById(R.id.c_start),
	    			figure,
	    			this
	        );       
	        this.sequencebar.clearSequence();
	        activity.setTitle("Level:"+levelObject.getDifficulty()+" Max moves:"+ levelObject.getMaxAllowedMoves());
	}
    

}
