package info.android.runaway.controls;


import java.io.Serializable;
import java.util.ArrayList;

import com.bytehawks.engine.BytehawksObject;

import info.android.runaway.GameActivity;
import info.android.runaway.R;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Sequence extends BytehawksObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 449891242255814728L;
	private int minLength;
	private int maxLength;
	private Activity context;
	private LinearLayout container;
	
	private ArrayList<Boolean> currentSequence = new ArrayList<Boolean>();
	
	public Sequence(int minLength, int maxLength, Activity context, LinearLayout container) {
	    this.minLength = minLength;
	    this.maxLength = maxLength;
	    this.context = context;
	    this.container = container;
	}
	
	public void add(boolean direction) {
		System.out.println(direction);
		if(currentSequence.size() < maxLength) {
					    
			ImageView image = new ImageView(context);
			image.setImageResource(
					GameActivity.DOWN == direction ? R.drawable.c_down: R.drawable.c_right
			);
			
			currentSequence.add(direction);
			container.addView(image); 
		}
	}
	
	public void removeLast() {
		int i = currentSequence.size()-1;
		if(i >= 0) {
			container.removeViews(i, 1);
			currentSequence.remove(i);
		}
	}
	
	public ArrayList<Boolean> getCurrentSequence() {
		return this.currentSequence;
	}
}
