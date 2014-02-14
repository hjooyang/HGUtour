package software.engineering.hgutour.mealANDcoffee;

public class hisbeansInfo{

		private String hisbeans_menu;
		private String hisbeans_price;
		
		public hisbeansInfo(){
			super();
		}
		
		public hisbeansInfo(String _menu, String _price){
			
			super();
			this.hisbeans_menu = _menu;
			this.hisbeans_price = _price;
			
		}
		
		public void sethisbeans_menu(String menu){
			this.hisbeans_menu = menu;
		}
		
		public String gethisbeans_menu(){
			return hisbeans_menu;
		}
		
		public void sethisbeans_price(String price){
			this.hisbeans_price = price;
		}
		
		public String gethisbeans_price(){
			return hisbeans_price;
		}
				
}