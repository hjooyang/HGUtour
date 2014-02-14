package software.engineering.hgutour.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import software.engineering.hgutour.R;
import software.engineering.hgutour.general.GlobalMethods;
import software.engineering.hgutour.general.GlobalVariables;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MediaMain extends Activity{
	private Vector<MediaInfo> mediaList;
	
	private ListView listview;
	private MediaAdapter adapter;
	
	private AutoCompleteTextView autoView;
	private Spinner spin;
	private ArrayList<String> choicelist=null;
	private int choiceNum=0;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mediamain);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		
		listview = (ListView)findViewById(R.id.media_listview);
		
		TextView tv_title = (TextView)findViewById(R.id.textview_media_name);
		if(GlobalVariables.engOrkor.equals("ko")){
			tv_title.setText("Moving Handong");
		}
		
		spin = (Spinner)findViewById(R.id.spin_media);
		autoView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView3);

		if(GlobalVariables.engOrkor.equals("ko")){
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
					R.array.media_list, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spin.setAdapter(adapter);
		}else{
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, 
					R.array.media_list_eng, android.R.layout.simple_spinner_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spin.setAdapter(adapter);
		}
		
		mediaList = new Vector<MediaInfo>();
		
		String SDPath = ""+Environment.getExternalStorageDirectory();
		File file = new File(SDPath + "/HGUtour/media.xml");
		
		autoView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				String search = autoView.getText().toString();
				
				if(search.length()<1){
					//화면 업데이트
					updateDisplay();
				}
				else{
					updateDisplay(search);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				//nothing
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				//nothing
			}
		});
		
		//인터넷 연결 확인
		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		AlertDialog.Builder netConDlgBuilder = GlobalMethods.checkInternet(cm, MediaMain.this);
		
		//인터넷 연결 되면
		if(netConDlgBuilder==null){
			if(file.exists()){
				display();
			}else{
				new DoSaveAtSDcard().execute();
				display();
			}
		}
		//인터넷 안되면
		else{
			//파일이 없으면 다이얼로그 띄움
			if(!file.exists()){
				netConDlgBuilder.show().setOnDismissListener(new OnDismissListener() {
					
					@Override
					public void onDismiss(DialogInterface dialog) {
						// TODO Auto-generated method stub
						finish();
					}
				});
			}
			//파일 있으면
			else{
				display();
			}
		}
		
		//spinner 선택
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				choiceNum=arg2;
				updateDisplay();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//업데이트 버튼
		ImageButton btn = (ImageButton)findViewById(R.id.update_btn_phone);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DoSaveAtSDcard().execute();
				display();
			}
		});
		
	listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, final int position,
					long id) {
				if(GlobalVariables.engOrkor.equals("ko")){
					new AlertDialog.Builder(MediaMain.this)
					.setTitle("영상감상")
					.setMessage(mediaList.get(position).gettitle() + " 영상을 보시겠습니까?")
							.setPositiveButton( "예", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
											dialog.dismiss();
											try {
												
												Intent myIntent = new Intent(MediaMain.this, MediaPlay.class);
												myIntent.putExtra("LINK", mediaList.get(position).geturl());
												myIntent.putExtra("dialogTitle", mediaList.get(position).gettitle());
												startActivity(myIntent);
											
											} catch (Exception e) {
												Log.e("error", e.toString());
											}
										}
									})
									.setNegativeButton("아니오", null)
									.show();
				}else{
					new AlertDialog.Builder(MediaMain.this)
					.setTitle("Media Watching")
					.setMessage(mediaList.get(position).gettitle() + " Watch?")
							.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
											dialog.dismiss();
											
											try {
												
												Intent myIntent = new Intent(MediaMain.this, MediaPlay.class);
												myIntent.putExtra("LINK", mediaList.get(position).geturl());
												myIntent.putExtra("dialogTitle", mediaList.get(position).gettitle());
												startActivity(myIntent);
												
											} catch (Exception e) {
												Log.e("error", e.toString());
											}
										}
									})
									.setNegativeButton("No", null)
									.show();
				}
			}
		});
	}
	
	private class DoSaveAtSDcard extends AsyncTask<Void, Void, String>{
		private ProgressDialog doSaveDialog;
		
		protected void onPreExecute(){
			super.onPreExecute();
			if(GlobalVariables.engOrkor.equals("ko")){
				doSaveDialog = ProgressDialog.show(MediaMain.this, "", "동영상 목록 자동 업데이트 하고 있습니다\n 잠시 기다려 주세요.",true);
				doSaveDialog.setCancelable(true);
			}
			else{
				doSaveDialog = ProgressDialog.show(MediaMain.this, "", "Updating... Please wait.",true);
				doSaveDialog.setCancelable(true);
			}
		}

		@Override
		protected String doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			MediaManager phoneManager = new MediaManager();
			if(phoneManager.saveAtSDcard()){
				return "success";
			}
			else{
				return "network error";
			}
		}
		
		@Override
		protected void onPostExecute(String result) {
			doSaveDialog.dismiss();
			if(result.equals("success")){
				// 화면 display
				display();
			}
			else if(result.equals("network error")){
				showDialog(1);
			}
		}

		@Override
		protected void onCancelled() {
			super.onCancelled();
			doSaveDialog.dismiss();
		}
		
	}
	
	// 화면 업데이트
		private void updateDisplay(){
			// List 청소 후 전체 List 받기
			MediaManager mediaManager = new MediaManager();
			mediaManager.setting();
			mediaList = mediaManager.getList(choiceNum);
			Collections.sort(mediaList);

			// adapter setting
			adapter = new MediaAdapter(this, R.layout.media_row, mediaList);
			// adapter register
			listview.setAdapter(adapter);
		}
	
	// 화면 업데이트
		private void updateDisplay(String search){
			// List 청소 후 전체 List 받기
			MediaManager contactsManager = new MediaManager();
			contactsManager.setting();
			mediaList = contactsManager.getList(search, choiceNum);
			Collections.sort(mediaList);
			// adapter setting
			adapter = new MediaAdapter(this, R.layout.media_row, mediaList);
			// adapter register
			listview.setAdapter(adapter);
		}

		// 화면 display
		private void display(){
			MediaManager mediaManager = new MediaManager();
			mediaManager.setting();
			mediaList = mediaManager.getList();
			Collections.sort(mediaList);

			// adapter setting
			adapter = new MediaAdapter(this, R.layout.media_row, mediaList);
			// adapter register
			listview.setAdapter(adapter);
		}
}