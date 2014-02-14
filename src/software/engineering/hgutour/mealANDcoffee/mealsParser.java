//파싱 XMLPullParser로 해보기. //PullParser는 이벤트타입형 파서이다.
//학식 아침, 점심, 저녁
//믹스라이스
//프라이프라이
//누들로드
//하오
//저녁찜닭
//맘스 아침, 점심, 저녁
//또랑 특선, 일반메뉴들
//네트워크가 mainthread랑 겹치면 network exception 일어난다..
//지금은 static mode로 했지만- sync, thread로 해결하는 것이 정법.
package software.engineering.hgutour.mealANDcoffee;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import software.engineering.hgutour.R;
import software.engineering.hgutour.general.GlobalVariables;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

public class mealsParser extends Activity{

	private String url = GlobalVariables.SERVER_ADDR + "/HGUtour/food.jsp";
	List<mealsInfo> mealsList = null;	
	mealsInfo mealsData;
	URL text;
	
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meals_stu);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
		
		TextView kote_breakfast = (TextView)findViewById(R.id.kote_breakfast);
		TextView kote_lunch = (TextView)findViewById(R.id.kote_lunch);
		TextView kote_dinner = (TextView)findViewById(R.id.kote_dinner);
		TextView fryfry = (TextView)findViewById(R.id.fryfry);
		TextView noodleroad = (TextView)findViewById(R.id.noodleroad);
		TextView hao = (TextView)findViewById(R.id.hao);
		TextView gracegarden = (TextView)findViewById(R.id.gracegarden);
		
		
		//xml 파싱하기 위해서 풀 파서 쓴다.
		try {
		   text = new URL(url); // 파싱하고자하는 URL
		   XmlPullParserFactory parserCreator = XmlPullParserFactory .newInstance(); 
		   XmlPullParser parser = parserCreator.newPullParser(); // XMLPullParser 사용
		   parser.setInput(text.openStream(), "utf-8");   // 파싱하기위해서 스트림을 열어야한다.
		   int parserEvent = parser.getEventType();  // 파싱할 데이터의 타입을 알려준다.
		   String tag;
		   
		   boolean b_kote_breakfast = false, b_kote_lunch = false, b_kote_dinner = false, b_fryfry = false;
		   boolean b_noodleroad = false, b_hao = false, b_gracegarden = false, b_mixrice = false;
		   boolean b_moms_breakfast = false, b_moms_lunch = false, b_moms_dinner = false, b_hoam_special = false, b_hoam_normal = false;
		  
		   while (parserEvent != XmlPullParser.END_DOCUMENT) { // xml 파일의 문서 끝인가? 
		    switch (parserEvent) {
		    
		    case XmlPullParser.START_DOCUMENT:
		    	mealsList = new ArrayList<mealsInfo>();
		    	break;
		    
		    case XmlPullParser.TEXT:
		    	
		     tag = parser.getName();
		     if (b_kote_breakfast) {
			      mealsData.setKoreanBreakfast(parser.getText());
			      b_kote_breakfast=false;// ArrayList에 추가
		     }else if (b_kote_lunch) {
		    	 mealsData.setKoreanLunch(parser.getText());
		    	 b_kote_lunch=false;
		     }else if (b_kote_dinner){
		    	 mealsData.setKoreanDinner(parser.getText());
		    	 b_kote_dinner=false;
		     }else if (b_fryfry){
		    	 mealsData.setfryfry(parser.getText());
		    	 b_fryfry=false;
		     }else if (b_noodleroad){
		    	 mealsData.setnoodleroad(parser.getText());
		    	 b_noodleroad=false;
		     }else if (b_hao){
		    	 mealsData.sethao(parser.getText());
		    	 b_hao=false;
		     }else if (b_gracegarden){
		    	 mealsData.setgracegarden(parser.getText());
		    	 b_gracegarden=false;
		     }else if (b_mixrice){
		    	 mealsData.setmixrice(parser.getText());
		    	 b_mixrice=false;
		     }else if (b_moms_breakfast){
		    	 mealsData.setmoms_breakfast(parser.getText());
		    	 b_moms_breakfast=false;
		     }else if (b_moms_lunch){
		    	 mealsData.setmoms_lunch(parser.getText());
		    	 b_moms_lunch=false;
		     }else if (b_moms_dinner){
		    	 mealsData.setmoms_dinner(parser.getText());
		    	 b_moms_dinner=false;
		     }else if (b_hoam_special){
		    	 mealsData.sethoam_special(parser.getText());
		    	 b_hoam_special=false;
		     }else if (b_hoam_normal){
		    	 mealsData.sethoam_normal(parser.getText());
		    	 b_hoam_normal=false;
		     }
		     
		     break;
		     
		    case XmlPullParser.END_TAG: // 나중에
			     tag = parser.getName();
			     if (tag.equalsIgnoreCase("food") && mealsData != null) {  // 태그가 address인지 비교
			    	 mealsList.add(mealsData);
			     }
			     break;
		     
		    case XmlPullParser.START_TAG: // 먼저 
			     tag = parser.getName();
			     if (tag.compareTo("food") == 0) {
				      mealsData = new mealsInfo();
				     } 
			     else if (tag.compareTo("kote_breakfast") == 0) {
			    	 b_kote_breakfast = true;
			     } else if (tag.compareTo("kote_lunch") == 0) {
			    	 b_kote_lunch = true;
			     } else if (tag.compareTo("kote_dinner") == 0) {
			      b_kote_dinner = true;
			     }else if (tag.compareTo("fryfry") == 0) {
				      b_fryfry = true;
				 }else if (tag.compareTo("noodleroad") == 0) {
				      b_noodleroad = true;
				 }else if (tag.compareTo("hao") == 0) {
				      b_hao = true;
				 }else if (tag.compareTo("gracegarden") == 0) {
				      b_gracegarden = true;
				 }else if (tag.compareTo("mixrice") == 0) {
				      b_mixrice = true;
				 }else if (tag.compareTo("moms_breakfast") == 0) {
				      b_moms_breakfast = true;
				 }else if (tag.compareTo("moms_lunch") == 0) {
				      b_moms_lunch = true;
				 }else if (tag.compareTo("moms_dinner") == 0) {
				      b_moms_dinner = true;
				 }else if (tag.compareTo("hoam_special") == 0) {
				      b_hoam_special = true;
				 }else if (tag.compareTo("hoam_normal") == 0) {
				      b_hoam_normal = true;
				 }
		     
		     break;
		    
		    }
		   
		    parserEvent = parser.next();
		   
		   }
		   
		    kote_breakfast.setText(mealsData.getKoreanBreakfast());
			kote_lunch.setText(mealsData.getKoreanLunch());
			kote_dinner.setText(mealsData.getKoreanDinner());
		    fryfry.setText(mealsData.getfryfry());
		    noodleroad.setText(mealsData.getnoodleroad());
		    hao.setText(mealsData.gethao());
		    gracegarden.setText(mealsData.getgracegarden());
			
			
		  } catch (Exception e) {
		   Log.e("dd", "Error in network call", e);
		  }
		
	}
	
}
