package info.android.runaway.controls;


import java.util.ArrayList;

import com.bytehawks.engine.BytehawksObject;
import com.bytehawks.engine.BytehawksVector;

import info.android.runaway.GameActivity;
import info.android.runaway.AndroidFigure;
import info.android.runaway.GameUI;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Controlbar extends BytehawksObject{
	
	
	private Sequence sequence;
	private AndroidFigure figure;
	private GameUI context;
	private boolean running;
	
	public Controlbar(Sequence sequence, ImageButton down, ImageButton right, Button delete, Button start, AndroidFigure figure, GameUI context) {
		this.sequence = sequence;
		this.figure = figure;
		this.context = context;
		
		down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateSequence(GameActivity.DOWN);
            }
        });
		
		right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateSequence(GameActivity.RIGHT);
            }
        });
		
		delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateSequence();
            }
        });
		
		start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startSequence();
			}
		});
		
		
	}

	protected void startSequence(){
		ArrayList<Boolean> currentsequence = sequence.getCurrentSequence();
		if(currentsequence.size() < sequence.getMinLength()){
			new AlertDialog.Builder(this.sequence.getContext())
		      .setMessage("Minimum moves "+ sequence.getMinLength() )
		      .setTitle("Info")
		      .setCancelable(false)
		      .setNeutralButton(android.R.string.ok,
		         new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton){
		        	 figure.mPosition.set(figure.mScale.mX*32,figure.mScale.mX*32);
		         }
		         })
		      .show();
		}
		else{
			this.running = true;
			figure.mPosition.set(figure.mScale.mX*32,figure.mScale.mX*32);		
			int i = 0;
			int desX = 0;
			int desY = 0;
			while(this.running == true){
				if(currentsequence.size() == i){
					i = 0;
				}
				if(figure.mPosition.mX < desX && figure.mPosition.mY < desY && desX != 0 && desY != 0){
					if(figure.hasCollided() == true){
						this.explode();	
						this.running = false;
						break;
					}
					if(figure.isOutside() == true){
						this.win();	
						this.running = false;
						break;
					}
					i++;
				}
				else{
					if(currentsequence.get(i) == true){
						desX = (int)(figure.mScale.mX*64+figure.mPosition.mX)-2;
						this.figure.moveToDestination(new BytehawksVector(figure.mScale.mX*64,0));
					}
					else{
						desY = (int)(figure.mScale.mY*64+figure.mPosition.mY)-2;
						this.figure.moveToDestination(new BytehawksVector(0,figure.mScale.mY*64));
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}
			}			
		}
		
	}

	protected void updateSequence() {
		this.sequence.removeLast();
		
	}

	private void updateSequence(boolean direction) {
		this.sequence.add(direction);
	}
	
	private void explode(){		
		new AlertDialog.Builder(this.sequence.getContext())
	      .setMessage("BOOM!!!")
	      .setTitle("Info")
	      .setCancelable(false)
	      .setNeutralButton(android.R.string.ok,
	         new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int whichButton){
	        	 figure.mPosition.set(figure.mScale.mX*32,figure.mScale.mX*32);
	        	 figure.moveToDestination(new BytehawksVector(0,0));
	         }
	         })
	      .show();		
	}
	
	private void win(){		
		new AlertDialog.Builder(this.sequence.getContext())
	      .setMessage("WIN!!!")
	      .setTitle("Info")
	      .setCancelable(false)
	      .setNeutralButton(android.R.string.ok,
	         new DialogInterface.OnClickListener() {
	         public void onClick(DialogInterface dialog, int whichButton){
	        	 figure.mPosition.set(figure.mScale.mX*32,figure.mScale.mX*32);
	        	 figure.moveToDestination(new BytehawksVector(0,0));
	        	 context.incDifficulty();
	         }
	         })
	      .show();		
	}
}
