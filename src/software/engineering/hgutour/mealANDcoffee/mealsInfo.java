package software.engineering.hgutour.mealANDcoffee;

public class mealsInfo{

		private String korean_breakfast;
		private String korean_lunch;
		private String korean_dinner;
		private String fryfry;
		private String noodleroad;
		private String hao;
		private String gracegarden;
		private String mixrice;
		private String moms_breakfast;
		private String moms_lunch;
		private String moms_dinner;
		private String hoam_special;
		private String hoam_normal;
		
		public mealsInfo(){
			super();
		}
		
		public mealsInfo(String _korean_breakfast, String _korean_lunch, String _korean_dinner, String _fryfry, String _noodleroad, String _hao, String _gracegarden, String _mixrice, String _moms_breakfast, String _moms_lunch, String _moms_dinner, String _hoam_special, String _hoam_normal){
			
			super();
			this.korean_breakfast = _korean_breakfast;
			this.korean_lunch = _korean_lunch;
			this.korean_dinner = _korean_dinner;
			this.fryfry = _fryfry;
			this.noodleroad = _noodleroad;
			this.hao = _hao;
			this.gracegarden = _gracegarden;
			this.mixrice = _mixrice;
			this.moms_breakfast = _moms_breakfast;
			this.moms_lunch = _moms_lunch;
			this.moms_dinner = _moms_dinner;
			this.hoam_special = _hoam_special;
			this.hoam_normal = _hoam_normal;
		}
		
		public void setKoreanBreakfast(String menu){
			this.korean_breakfast = menu;
		}
		
		public String getKoreanBreakfast(){
			return korean_breakfast;
		}
		
		public void setKoreanLunch(String menu){
			this.korean_lunch = menu;
		}
		
		public String getKoreanLunch(){
			return korean_lunch;
		}
		
		public void setKoreanDinner(String menu){
			this.korean_dinner = menu;
		}
		
		public String getKoreanDinner(){
			return korean_dinner;
		}
		
		public void setfryfry(String menu){
			this.fryfry = menu;
		}
		
		public String getfryfry(){
			return fryfry;
		}
		
		public void setnoodleroad(String menu){
			this.noodleroad = menu;
		}
		
		public String getnoodleroad(){
			return noodleroad;
		}
		
		public void sethao(String menu){
			this.hao = menu;
		}
		
		public String gethao(){
			return hao;
		}
		
	
		public void setgracegarden(String menu){
			this.gracegarden = menu;
		}
		
		public String getgracegarden(){
			return gracegarden;
		}
		
		public void setmixrice(String menu){
			this.mixrice = menu;
		}
		
		public String getmixrice(){
			return mixrice;
		}
		
		public void setmoms_breakfast(String menu){
			this.moms_breakfast = menu;
		}
		
		public String getmoms_breakfast(String menu){
			return moms_breakfast;
		}
		  
		public void setmoms_lunch(String menu){
			this.moms_lunch = menu;
		}
		
		public String getmoms_lunch(String menu){
			return moms_lunch;
		}
		
		public void setmoms_dinner(String menu){
			this.moms_dinner = menu;
		}
		
		public String getmoms_dinner(String menu){
			return moms_dinner;
		}
		
		public void sethoam_special(String menu){
			this.hoam_special = menu;
		}
		
		public String gethoam_special(String menu){
			return hoam_special;
		}
		
		public void sethoam_normal(String menu){
			this.hoam_normal = menu;
		}
		
		public String gethoam_normal(String menu){
			return hoam_normal;
		}
		
}