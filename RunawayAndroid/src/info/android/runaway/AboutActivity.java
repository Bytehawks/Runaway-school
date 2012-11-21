package info.android.runaway;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class AboutActivity extends Activity  implements OnClickListener{

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 setContentView(R.layout.activity_about);
 		 findViewById(R.id.about_back).setOnClickListener(this);
    }
    
    @Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.about_back:
				this.finish();
				break;		
		}
	}
    
}
