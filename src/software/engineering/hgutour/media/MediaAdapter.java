//썸네일 받아오는 것. 서버통신 비동기화로 할 것.

package software.engineering.hgutour.media;

import java.util.Locale;
import java.util.Vector;

import software.engineering.hgutour.R;
import software.engineering.hgutour.image.ImageDownloader;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MediaAdapter extends ArrayAdapter<MediaInfo>{
	private Vector<MediaInfo> mediaList;
	private Context context;
	
	public MediaAdapter(Context context, int textViewResourceId,
			Vector<MediaInfo> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.mediaList = objects;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		if(v==null){
			LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.media_row, null);
		}

		MediaInfo media = mediaList.get(position);
		Locale lo = context.getResources().getConfiguration().locale;
	
		ImageView img = (ImageView)v.findViewById(R.id.thumbnail);
		TextView media_category = (TextView) v.findViewById(R.id.text_media_category);
		TextView title = (TextView) v.findViewById(R.id.text_media_title);
		
		img.setScaleType(ImageView.ScaleType.FIT_XY);
		
		
		if(lo.getLanguage().equals("ko")){
			
			int category=Integer.parseInt(media.getcategory());
			switch(category){
				case 1:
					media_category.setText("홍보영상");
					break;
				case 2:
					media_category.setText("학부합창대회");
					break;
				case 3:
					media_category.setText("동아리");
					break;
				}
			}
		
		else{
					
			int category=Integer.parseInt(media.getcategory());
			switch(category){
				case 1:
					media_category.setText("HGUpromotion");
					break;
				case 2:
					media_category.setText("MajorCeremony");
					break;
				case 3:
					media_category.setText("Club");
					break;
				}
		}
		if(media.gettitle().length()>20){
			title.setText(media.gettitle().substring(0, 20) + "...");
		}
		else{
			title.setText(media.gettitle());
		}
		
		ImageDownloader.download(media.getimg(), img);
			
		return v;
	}
		
}
