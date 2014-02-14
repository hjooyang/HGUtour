package software.engineering.hgutour;


import java.util.Locale;

import software.engineering.hgutour.aboutHGU.aboutHGUmain;
//import software.engineering.hgutour.abutHGU.aboutHGUmain;
import software.engineering.hgutour.email.sendEmail;
import software.engineering.hgutour.general.GlobalVariables;
import software.engineering.hgutour.media.MediaMain;
import software.engineering.hgutour.yakdo.YakdoMain;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener{
	
	private boolean isInternetAvailable = true;
	
	private boolean isSlideOpen = false;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startService(new Intent("software.engineering.hgutour.bgm.service"));
		
		ImageButton allnationsBtn = (ImageButton)findViewById(R.id.allnationshall);
		ImageButton ohseokBtn = (ImageButton)findViewById(R.id.ohseok);
		ImageButton changboBtn = (ImageButton)findViewById(R.id.changbo);
		ImageButton dreamfieldBtn = (ImageButton)findViewById(R.id.dreamfield);
		ImageButton newtonHallBtn = (ImageButton)findViewById(R.id.nt);
		ImageButton hyundongHallBtn = (ImageButton)findViewById(R.id.hyundong);
		ImageButton nehemiahHallBtn = (ImageButton)findViewById(R.id.nh);
		ImageButton chapelBtn = (ImageButton)findViewById(R.id.chappel);
		ImageButton chapel2Btn = (ImageButton)findViewById(R.id.chappel2);
		ImageButton glcBtn = (ImageButton)findViewById(R.id.glc);
		ImageButton visionSquareBtn = (ImageButton)findViewById(R.id.vision_square);
		ImageButton schoolBtn = (ImageButton)findViewById(R.id.school);
		ImageButton btBtn = (ImageButton)findViewById(R.id.bt);
		ImageButton creationBtn = (ImageButton)findViewById(R.id.creation);
		ImageButton visionBtn = (ImageButton)findViewById(R.id.vision);
		ImageButton ldBtn = (ImageButton)findViewById(R.id.ld);
		ImageButton plantBtn = (ImageButton)findViewById(R.id.plant);
		ImageButton shalomBtn = (ImageButton)findViewById(R.id.shalom);
		ImageButton graceBtn = (ImageButton)findViewById(R.id.grace);
		ImageButton nigodemoBtn = (ImageButton)findViewById(R.id.nigodemo);
		ImageButton onnuriHouseBtn = (ImageButton)findViewById(R.id.onnuri_house);
		ImageButton hguYoungerDormBtn = (ImageButton)findViewById(R.id.hgu_younger_dorm);
		ImageButton globalhouseBtn = (ImageButton)findViewById(R.id.globalhouse);
		ImageButton internationalDormBtn = (ImageButton)findViewById(R.id.international_dorm);
		ImageButton hguYoungerSchoolBtn = (ImageButton)findViewById(R.id.hgu_younger_school);



		Locale lo = getResources().getConfiguration().locale;
		//한국어 영어 설정
		if(lo.getLanguage().equals("ko"))
			GlobalVariables.engOrkor = "ko";
		else
			GlobalVariables.engOrkor = "eng";
		
		allnationsBtn.setOnClickListener(this);
		ohseokBtn.setOnClickListener(this);
		changboBtn.setOnClickListener(this);
		changboBtn.setOnClickListener(this);
		dreamfieldBtn.setOnClickListener(this);
		newtonHallBtn.setOnClickListener(this);
		hyundongHallBtn.setOnClickListener(this);
		nehemiahHallBtn.setOnClickListener(this);
		chapelBtn.setOnClickListener(this);		
		chapel2Btn.setOnClickListener(this);
		glcBtn.setOnClickListener(this);
		visionSquareBtn.setOnClickListener(this);
		schoolBtn.setOnClickListener(this);
		btBtn.setOnClickListener(this);
		creationBtn.setOnClickListener(this);
		visionBtn.setOnClickListener(this);
		ldBtn.setOnClickListener(this);
		plantBtn.setOnClickListener(this);
		shalomBtn.setOnClickListener(this);
		graceBtn.setOnClickListener(this);
		nigodemoBtn.setOnClickListener(this);
		onnuriHouseBtn.setOnClickListener(this);
		hguYoungerDormBtn.setOnClickListener(this);
		globalhouseBtn.setOnClickListener(this);
		internationalDormBtn.setOnClickListener(this);
		hguYoungerSchoolBtn.setOnClickListener(this);

	
		ImageButton Media = (ImageButton)findViewById(R.id.bar_media);
		ImageButton Email = (ImageButton)findViewById(R.id.bar_email);
		ImageButton Tab = (ImageButton)findViewById(R.id.bar_tab);
		ImageButton Yakdo = (ImageButton)findViewById(R.id.bar_yakdo);
		
		Media.setOnClickListener(new OnClickListener(){

		   @Override
		   public void onClick(View v) {
					// TODO Auto-generated method stub
			   		Intent i = new Intent(MainActivity.this, MediaMain.class);
					startActivity(i);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				}
		});
		
		Email.setOnClickListener(new OnClickListener(){

			   @Override
			   public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i = new Intent(MainActivity.this, sendEmail.class);
						startActivity(i);
						overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
					}
				
				
			});
		
		Yakdo.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, YakdoMain.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				
			}
			
		});
		
		
		Tab.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, aboutHGUmain.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				
				
			}
		});
	
	}

	public void onClick(View v) {
		//건물 이미지 버튼 클릭시 BuildingInfoActivity로 연결
		Intent intentBuildingActivity = new Intent (MainActivity.this, BuildingInfoActivity.class);
		//각 건물 이미지 버튼의 id를 넘김
		intentBuildingActivity.putExtra("LINK", v.getId());
		startActivity(intentBuildingActivity);
	}
	
	
	//음악 사운드 볼륨키로 조절하기. audio 둘다 설정.
	public boolean onKeyDown(int keyCode, KeyEvent event) {                  
        AudioManager mAudioManager = 
            (AudioManager)getSystemService(AUDIO_SERVICE);
        switch (keyCode) {
        case KeyEvent.KEYCODE_VOLUME_UP :
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                                             AudioManager.ADJUST_RAISE, 
                                             AudioManager.FLAG_SHOW_UI);
                return true;
        case KeyEvent.KEYCODE_VOLUME_DOWN:
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
                                             AudioManager.ADJUST_LOWER, 
                                             AudioManager.FLAG_SHOW_UI);
                return true;
        case KeyEvent.KEYCODE_BACK:
            return true;
        }
 
        return false;
   }
 
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        AudioManager mAudioManager = 
           (AudioManager)getSystemService(AUDIO_SERVICE);
        switch (keyCode) {
        case KeyEvent.KEYCODE_VOLUME_UP :
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
                                             AudioManager.ADJUST_SAME, 
                                             AudioManager.FLAG_SHOW_UI);
                return true;
        case KeyEvent.KEYCODE_VOLUME_DOWN:
            mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, 
                                             AudioManager.ADJUST_SAME, 
                                             AudioManager.FLAG_SHOW_UI);
                return true;
        case KeyEvent.KEYCODE_BACK:
            this.finish();
            return true;
        }
        return false;
    }
	
		
	protected void onDestroy(){
		super.onDestroy();
		stopService(new Intent("software.engineering.hgutour.bgm.service"));
		clearApplicationCache(null);
	}
	
	private void clearApplicationCache(java.io.File dir){
		if(dir==null)
			dir=getCacheDir();
		if(dir==null)
			return;
		java.io.File[] children=dir.listFiles();
		
		try{
			for(int i=0;i<children.length;i++){
				if(children[i].isDirectory())
					clearApplicationCache(children[i]);
				else children[i].delete();
			}	
			
		}catch(Exception e){}
	}

	

	
	
}