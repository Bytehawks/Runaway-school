package info.android.runaway.controls;

import info.android.runaway.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.widget.Button;
import android.widget.LinearLayout;

public class Sequence implements Serializable {
	
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
			Button btn = new Button(context);
			btn.setText((MainActivity.DOWN == direction) ? "v" : ">");
		    
			currentSequence.add(direction);
			container.addView(btn); 
		}
	}
	
	public void removeLast() {
		int i = currentSequence.size()-1;
		container.removeViews(i, 1);
		currentSequence.remove(i);
	}
	
	
	

}
