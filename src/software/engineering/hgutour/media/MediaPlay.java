package software.engineering.hgutour.media;
 
import software.engineering.hgutour.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.VideoView;
 
public class MediaPlay extends Activity {
    
	private String VideoURL = "";
	private String dialogTitle = "";
	private ProgressDialog pDialog;
    
	VideoView videoview;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.media_video_player);
        
        stopService(new Intent("software.engineering.hgutour.bgm.service"));
		
        Intent intent = getIntent();
    	VideoURL = intent.getStringExtra("LINK");
        dialogTitle = intent.getStringExtra("dialogTitle");
        Uri video = Uri.parse(VideoURL);
        
    
        try {
        	
            Intent intentAction_View = new Intent(Intent.ACTION_VIEW, video);
            intentAction_View.setDataAndType(video,"video/*");
            startActivity(intentAction_View);
            
        } catch (Exception e) {
            pDialog.dismiss();
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        
        
        

    }

}