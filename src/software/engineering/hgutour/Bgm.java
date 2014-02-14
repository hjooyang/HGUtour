package software.engineering.hgutour;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
public class Bgm extends Service{
	
	 public MediaPlayer mp;
	 
	 public IBinder onBind(Intent arg0) {
	  return null;
	 }
	 
	 public void onStart(Intent intent, int startId) {
	  Log.i("Example", "Service onStart()"); 
	  super.onStart(intent, startId);
	  mp = MediaPlayer.create(this, R.raw.handonglogo);
	  mp.setLooping(true); // 반복 재생 설정 (true와 false로 조정 가능)
	  mp.start(); //음악 재생
	 }
	 public void onDestroy() {
	  Log.i("Example", "Service onDestroy()");
	  super.onDestroy();
	  mp.stop(); //음악 정지
	 }
}
