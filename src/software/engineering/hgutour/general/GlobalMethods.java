package software.engineering.hgutour.general;

import software.engineering.hgutour.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class GlobalMethods {
	
	    // Custom Tab View 통일 Design
		public static View createTabView(final Context context, final String text) {  
			View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);  
			TextView tv = (TextView) view.findViewById(R.id.tabsText);  
			tv.setText(text);
			return view;  
		}
	
        // 인터넷 연결 확인
		public static AlertDialog.Builder checkInternet(ConnectivityManager cm, Context con){
			NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			boolean isWifiConn = ni.isConnected();
			ni = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			boolean isMobileConn = ni.isConnected();

			if(isWifiConn==false && isMobileConn==false){	
				AlertDialog.Builder builder = new AlertDialog.Builder(con);
				builder.setMessage("Network unavailable. Please check Wifi or 3/4G connection")
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int id){
						dialog.dismiss();
					}
				});
				return builder;
			}
			return null;
		}
			
		// 인터넷 연결 확인
		public static boolean checkInternet(Context context){
			// make ConnectivityManager
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

			// check internet
			NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			boolean isWifiConn = ni.isConnected();
			ni = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			boolean isMobileConn = ni.isConnected();

			// network is good, return true
			if(isWifiConn == true || isMobileConn == true)
				return true;

			// network is bad, return false
			else
				return false;
		}
		
}
