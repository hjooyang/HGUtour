package software.engineering.hgutour.email;
 
//학교홍보자료 요청 이메일 보내기.. 문의해서 이메일이랑 필요사항을 알아내함
//현재 데모 버전.
//우편번호찾기는 - 우체국 API 이용해서 구현 가능 - 추후.


import software.engineering.hgutour.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
 
public class sendEmail extends Activity {
 
 Button buttonSend;
 EditText textAddress;
 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.email);
	 
		  buttonSend = (Button) findViewById(R.id.buttonSend);
		
		  buttonSend.setOnClickListener(new OnClickListener() {
		 
		   @Override
		   public void onClick(View v) {
		 
		     String to = "admission@handong.edu";//입학처 이메일.. 테스트하다간 낭패봄.//textTo.getText().toString();
		     String subject = "홍보자료요청";
		     String message = textAddress.getText().toString();
		 
		     Intent email = new Intent(Intent.ACTION_SEND);
		     email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
		     email.putExtra(Intent.EXTRA_SUBJECT, subject);
		     email.putExtra(Intent.EXTRA_TEXT, message);
		 
		     //need this to prompts email client only
		     email.setType("message/rfc822");
		 
		     startActivity(Intent.createChooser(email, "Choose an Email client :"));
		 
		   }
		  });
	 }
}
