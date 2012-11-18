package info.android.runaway.controls;


import com.bytehawks.engine.BytehawksObject;

import info.android.runaway.GameActivity;
import android.view.View;
import android.widget.Button;

public class Controlbar extends BytehawksObject{
	
	
	private Sequence sequence;
	
	public Controlbar(Sequence sequence, Button down, Button right, Button delete, Button start) {
		this.sequence = sequence;
		
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

	protected void startSequence() {
		//new Anim().start(this.sequence.getCurrentSequence());
	}

	protected void updateSequence() {
		sequence.removeLast();
		
	}

	private void updateSequence(boolean direction) {
		sequence.add(direction);
	}
	
}
