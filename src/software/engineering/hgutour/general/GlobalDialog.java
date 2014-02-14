package software.engineering.hgutour.general;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

public class GlobalDialog {

	// make dialog which says bad internet environment and return builder of the dialog
	public static AlertDialog.Builder createBadInternetDialog(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Network unavailable");	
		builder.setMessage("Please check Wifi or 3/4G connection environment.")
		.setPositiveButton("OK", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
			}
		});
		return builder;
	}


	// make update dialog
	public static ProgressDialog createUpdateProgressDialog(Context context){
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("Update");	
		progressDialog.setMessage("I'm doing Update...");
		progressDialog.setIndeterminate(true);
		return progressDialog;
	}
}
