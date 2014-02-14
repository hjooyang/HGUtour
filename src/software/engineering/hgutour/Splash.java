package software.engineering.hgutour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.splash);
		
		Handler handler = new Handler();
		handler.postDelayed(new splashhandler(), 1000);
	    // TODO Auto-generated method stub
	}
	
	class splashhandler implements Runnable {
		public void run() {
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
			Splash.this.finish();
		}
	}

}
