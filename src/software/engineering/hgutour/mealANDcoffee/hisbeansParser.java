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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import software.engineering.hgutour.R;
import software.engineering.hgutour.general.GlobalVariables;
import software.engineering.hgutour.mealANDcoffee.hisbeansParser.ListData;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class hisbeansParser extends Activity{

	private String url = GlobalVariables.SERVER_ADDR + "/HGUtour/hisbeans.jsp";
//	hisbeansInfo hisbeansData;
	ListData hisbeansData;
	URL text;
	
	private ListView listView;
	private ArrayList<ListData> arrayList;
	private DataAdapter adapter;
	
	
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hisbeans_listview);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
		
		//xml 파싱하기 위해서 풀 파서 쓴다.
		try {
		   
			text = new URL(url); // 파싱하고자하는 URL
		   XmlPullParserFactory parserCreator = XmlPullParserFactory .newInstance(); 
		   XmlPullParser parser = parserCreator.newPullParser(); // XMLPullParser 사용
		   parser.setInput(text.openStream(), "utf-8");   // 파싱하기위해서 스트림을 열어야한다.
		   int parserEvent = parser.getEventType();  // 파싱할 데이터의 타입을 알려준다.
		  
		   String tag;
		  
		   boolean b_hisbeans_menu = false, b_hisbeans_price = false;
		   String price = null;
		   String menu = null;
		   
		   while (parserEvent != XmlPullParser.END_DOCUMENT) { // xml 파일의 문서 끝인가? 
			
			//   if(hisbeansData!= null && hisbeansData.getPrice()!=null)
				   hisbeansData = new ListData();
		    
		   switch (parserEvent) {
		    
		    case XmlPullParser.START_DOCUMENT:
		    	arrayList = new ArrayList<ListData>();
				listView = (ListView)findViewById(R.id.listView1);
				
		    	break;
		    
		    case XmlPullParser.TEXT:
		    		
		     tag = parser.getName();
		     if (b_hisbeans_menu) {
			      menu = parser.getText();
			      hisbeansData.setMenu(menu);//////////////////
			      Log.i("menu",menu.toString()); 
			      b_hisbeans_menu=false;// ArrayList에 추가
		     }else if (b_hisbeans_price) {
		    	  price = parser.getText();
		    	  hisbeansData.setPrice(price);
		    	Log.i("price",price.toString());
		    	 b_hisbeans_price=false;
		     }
		   // if(b_hisbeans_menu==false && b_hisbeans_price==false)
		     arrayList.add(hisbeansData);
		   //  Log.i("list", arrayList.get(i).getMenu());
		     break;
		     
		    case XmlPullParser.END_TAG: // 나중에
			     tag = parser.getName();
			     if (tag.equalsIgnoreCase("hisbeans") && hisbeansData != null) {  // 태그가 address인지 비교
			    	//  arrayList.add(new ListData(getApplicationContext(), menu, price));///
			    	 arrayList.add(hisbeansData);
			     }
			     break;
		     
		    case XmlPullParser.START_TAG: // 먼저 
			     tag = parser.getName();
			     if (tag.compareTo("hisbeans") == 0) {
				      hisbeansData = new ListData();
				     } 
			     else if (tag.compareTo("menu") == 0) {
			    	 b_hisbeans_menu = true;
			     } else if (tag.compareTo("price") == 0) {
			    	 b_hisbeans_price = true;
			     } 
		     
			     break;
		    
		    }
		    
//      
		    parserEvent = parser.next();
		    
		   }
		   adapter = new DataAdapter(this, arrayList);
			listView.setAdapter(adapter);
		 
		    
		    
		   
		  } catch (Exception e) {
		   Log.e("dd", "Error in network call", e);
		  }
	}

	class ListData {

		private String menu=null;
		private String price=null;
		
		public ListData() {
			
		}
		public ListData(Context context, String menu, String price) {
			this.menu = menu;
			this.price = price;
		}
		
		public String getMenu() {
			return this.menu;
		}
		
		public String getPrice() {
			return this.price;
		}
		
		private void setMenu(String menu) {
			this.menu = menu;
		}

		private void setPrice(String price) {
			this.price = price;
		}
	}
}

class DataAdapter extends ArrayAdapter<ListData> {
	private LayoutInflater inflater; //to create a view instance
	
	public DataAdapter(Context context, ArrayList<ListData> arrayList) {
		super(context, 0, arrayList);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public View getView (int position, View v, ViewGroup parent ){
		View custom_list_view = inflater.inflate(R.layout.hisbeans_row, null);
		ListData data = this.getItem(position);
		
		TextView textView_menu = (TextView) custom_list_view.findViewById(R.id.hisbeans_menu);
		TextView textView_price = (TextView) custom_list_view.findViewById(R.id.hisbeans_price);
		
		textView_menu.setText(data.getMenu());
		textView_price.setText(data.getPrice());
		
		return custom_list_view;
	}
}

