package info.android.runaway.controls;

import info.android.runaway.MainActivity;
import info.android.runaway.R;
import android.view.View;
import android.widget.Button;

public class Controlbar {
	
	private Sequence sequence;
	
	public Controlbar(Sequence sequence, Button down, Button right, Button delete, Button start) {
		this.sequence = sequence;
		
		down.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateSequence(MainActivity.DOWN);
            }
        });
		
		right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateSequence(MainActivity.RIGHT);
            }
        });
		
		delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateSequence();
            }
        });
		
		
	}

	protected void updateSequence() {
		sequence.removeLast();
		
	}

	private void updateSequence(boolean direction) {
		sequence.add(direction);
	}
	
}
