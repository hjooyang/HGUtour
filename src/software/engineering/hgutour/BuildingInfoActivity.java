package software.engineering.hgutour;


import software.engineering.hgutour.general.GlobalVariables;
import software.engineering.hgutour.mealANDcoffee.hisbeansParser;
import software.engineering.hgutour.mealANDcoffee.mealsParser;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

public class BuildingInfoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	private int layouturl;
	
	//Create a dialog object, that will contain the WebView  
	private Dialog webViewDialog_stu_moreInfo;
	//a WebView object to display a web page  
	private WebView webView_stu;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    Intent intent = getIntent();
	    layouturl = intent.getIntExtra("LINK", 0);
	    
	   switch (layouturl) {
		case R.id.allnationshall:
			setContentView(R.layout.activity_main_anh);
			break;
		case R.id.ohseok:
			setContentView(R.layout.activity_main_ohseok);
			Button hisbeans = (Button)findViewById(R.id.hisbeans);
			hisbeans.setOnClickListener(this);
			break;
		case R.id.changbo:
			break;
			
		case R.id.dreamfield:
			break;
		case R.id.nt:
			setContentView(R.layout.activity_main_nth);
			break;
		case R.id.hyundong:
			setContentView(R.layout.activity_main_hyundong);
			break;
		case R.id.nh:
	//		setContentView(R.layout.ac);
			break;
		case R.id.chappel:
		//	setContentView(R.layout.ac);
			break;
		case R.id.chappel2:
			break;
		case R.id.glc:
			break;
		case R.id.vision_square:
			setContentView(R.layout.activity_main_visionsq);
			break;
		case R.id.school:
			setContentView(R.layout.activity_main_stu);
			ImageButton icon_meal_stunion = (ImageButton)findViewById(R.id.icon_meal_stunion);
			ImageButton icon_cafe_stunion = (ImageButton)findViewById(R.id.icon_cafe_stunion);
			ImageButton icon_stunion_moreinfo = (ImageButton)findViewById(R.id.icon_stunion_moreinfo);
			icon_meal_stunion.setOnClickListener(this);
			icon_cafe_stunion.setOnClickListener(this);
			icon_stunion_moreinfo.setOnClickListener(this);
			
			//Create a new dialog  
	        webViewDialog_stu_moreInfo = new Dialog(this);  
	        //Remove the dialog's title  
	        webViewDialog_stu_moreInfo.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        //Inflate the contents of this dialog with the Views defined at 'webviewdialog.xml' 
	        webViewDialog_stu_moreInfo.setContentView(R.layout.webview);  
	        //With this line, the dialog can be dismissed by pressing the back key  
	        webViewDialog_stu_moreInfo.setCancelable(true);
	        
	        LayoutParams params = webViewDialog_stu_moreInfo.getWindow().getAttributes();
	        params.width=LayoutParams.MATCH_PARENT;
	        params.height=LayoutParams.MATCH_PARENT;
	        webViewDialog_stu_moreInfo.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
	        
	        //Initialize the WebView object with data from the 'webviewdialog.xml' file  
	        webView_stu = (WebView) webViewDialog_stu_moreInfo.findViewById(R.id.webview_moreInfo);  
	        //Scroll bars should not be hidden  
	        webView_stu.setScrollbarFadingEnabled(false);  
	        //Disable the horizontal scroll bar  
	        webView_stu.setHorizontalScrollBarEnabled(false);  
	        //Enable JavaScript  
	        webView_stu.getSettings().setJavaScriptEnabled(true);  
	        //Set the user agent  
	        webView_stu.getSettings().setUserAgentString("AndroidWebView");  
	        //기존 웹뷰 정신 못 차리는 거 설정.
	        webView_stu.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
	        //Clear the cache 
	        webView_stu.clearCache(true);  
	        
	        
	        //Make the webview load the specified URL  
	        webView_stu.loadUrl(GlobalVariables.SERVER_ADDR + "/HGUtour/structure/SU/SU.html");
	        WebSettings set = webView_stu.getSettings();
	        set.setBuiltInZoomControls(true);
			set.setJavaScriptEnabled(true);
			//set.setBuiltInZoomControls(true);
	        set.setUseWideViewPort(true);
	        webView_stu.setInitialScale(1);
			break;
		case R.id.bt:
			break;
		case R.id.creation:
			break;
		case R.id.vision:
			break;
		case R.id.ld:
			break;
		case R.id.plant:
			break;
		case R.id.shalom:
			break;
		case R.id.grace:
			break;
		case R.id.nigodemo:
			break;
		case R.id.onnuri_house:
			break;
		case R.id.hgu_younger_dorm:
			break;
		case R.id.hgu_younger_school:
			break;
		case R.id.globalhouse:
			break;
		case R.id.international_dorm:
			break;
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	    
		switch(v.getId()) {
		case R.id.icon_meal_stunion:
			Intent i = new Intent(BuildingInfoActivity.this, mealsParser.class);
			startActivity(i);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			break;
		case R.id.icon_cafe_stunion:
			break;
		case R.id.hisbeans:
			Intent z = new Intent(BuildingInfoActivity.this, hisbeansParser.class);
			startActivity(z);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			break;
		case R.id.icon_stunion_moreinfo:
			
			webViewDialog_stu_moreInfo.show();
			break;
			
		}
	}
	
	
	
	
	
	
	
}
