package software.engineering.hgutour.yakdo;

import software.engineering.hgutour.R;
import software.engineering.hgutour.general.GlobalMethods;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class YakdoMain extends TabActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.common_tab);
	    // TODO Auto-generated method stub
	    
	    Resources res = getResources(); 
		TabHost tabHost = getTabHost(); 
	    
	    tabHost.addTab(tabHost.newTabSpec("고속버스")
				.setIndicator(GlobalMethods.createTabView(tabHost.getContext()
						, "고속버스"))
						.setContent(new Intent(YakdoMain.this, highterminal.class)));

		tabHost.addTab(tabHost.newTabSpec("시외버스")
				.setIndicator(GlobalMethods.createTabView(tabHost.getContext()
						, "시외버스"))
						.setContent(new Intent(YakdoMain.this, outterminal.class)));

		tabHost.addTab(tabHost.newTabSpec("기차")
				.setIndicator(GlobalMethods.createTabView(tabHost.getContext()
						, "기차"))
						.setContent(new Intent(YakdoMain.this, train.class)));

		tabHost.addTab(tabHost.newTabSpec("항공")
				.setIndicator(GlobalMethods.createTabView(tabHost.getContext()
						, "항공"))
						.setContent(new Intent(YakdoMain.this, air.class)));
		
		tabHost.addTab(tabHost.newTabSpec("자가운전")
				.setIndicator(GlobalMethods.createTabView(tabHost.getContext()
						, "자가운전"))
						.setContent(new Intent(YakdoMain.this, car.class)));
	    
	    
	}

}
