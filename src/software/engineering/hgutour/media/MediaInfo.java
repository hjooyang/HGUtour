package software.engineering.hgutour.media;

import software.engineering.hgutour.general.GlobalVariables;

public class MediaInfo implements Comparable<MediaInfo>{

		private String category;
		private String url;
		private String title;
		private String img;
		
		public MediaInfo(){
			super();
		}
		
		public MediaInfo(String _category, String _url, String _title, String _img){
			super();
			this.category = _category;
			this.url = _url;
			this.title = _title;
			this.img = _img;
		}
		
		public void setcategory(String num){
			this.category = num;
		}
		
		public String getcategory(){
			return category;
		}
		
		public void seturl(String address){
			this.url = address;
		}
		
		public String geturl(){
			return GlobalVariables.SERVER_ADDR+"/HGUtour/introduction/media/"+url;
		}
		
		public void settitle(String title){
			this.title = title;
		}
		
		public String gettitle(){
			return title;
		}
				
		public void setimg(String address){
			
			this.img = address;
		}
		
		public String getimg(){
			
			return GlobalVariables.SERVER_ADDR+"/HGUtour/introduction/media/"+img;
		}
		
		
		
		@Override
		public int compareTo(MediaInfo o) {
			return category.compareTo(o.category);
		}
		
	}