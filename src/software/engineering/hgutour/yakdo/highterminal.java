package software.engineering.hgutour.yakdo;

import software.engineering.hgutour.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class highterminal extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.image_webview);
	    // TODO Auto-generated method stub
	    
	    WebView webView = (WebView)findViewById(R.id.image_webview);
	    webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
	    
	    WebSettings set = webView.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
	    
        
	    webView.loadDataWithBaseURL(
	    		null, 
	    		"<img src=\"file:///android_res/drawable/yakdo_highterminal_comb.png\" />", 
	    		"text/html", 
	    		"UTF-8", 
	    		null);
	    }

}
