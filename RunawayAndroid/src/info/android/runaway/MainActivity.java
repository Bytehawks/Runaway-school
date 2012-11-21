package info.android.runaway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity  implements OnClickListener{

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
    	 setContentView(R.layout.activity_main);
 		 findViewById(R.id.menu_startgame).setOnClickListener(this);
 		 findViewById(R.id.menu_about).setOnClickListener(this);
 		 findViewById(R.id.menu_close).setOnClickListener(this);
    }
    
    @Override
	public void onClick(View v)
	{
		Intent intent;
		switch (v.getId())
		{
			case R.id.menu_startgame:
				intent = new Intent(this, GameActivity.class);
				startActivity(intent);
				break;
			case R.id.menu_about:
				intent = new Intent(this, AboutActivity.class);
				startActivity(intent);
				break;
			case R.id.menu_close:
				this.finish();
				break;		
		}
	}
    
}
