package software.engineering.hgutour.aboutHGU;

import software.engineering.hgutour.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class aboutHGUmain extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.about_hgu_main);
	    // TODO Auto-generated method stub
	    
	    Button history = (Button)findViewById(R.id.history);
	    Button vision = (Button)findViewById(R.id.vision);
	    Button education = (Button)findViewById(R.id.education);
	    Button culture = (Button)findViewById(R.id.culture);
	    
	    history.setOnClickListener(new OnClickListener(){
	    	
	    	 public void onClick(View v)  
	            {  
	    		 Intent i = new Intent(aboutHGUmain.this, history.class);
					startActivity(i);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
	            }
	    	
	    });
	    
	    vision.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(aboutHGUmain.this, Vision.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			}
		});
	    
	    
	}

}
