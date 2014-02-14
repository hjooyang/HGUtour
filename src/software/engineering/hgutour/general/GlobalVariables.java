package software.engineering.hgutour.general;

public class GlobalVariables {
	
	//LOCAL SERVER 주소
	public static final String SERVER_ADDR = "http://203.252.99.154:8080";
	
	//연결 delay time
	public static final int DELAY_TIME = 8000;
	
	//한국어 or 영어 사용자 구분
	public static String engOrkor="0";
	
		
	/*DIALOG를 위한 변수들*/
	
	//progress Dialog
	public static final int PROGRESS = 0;
	
	//network가 불안정할 경우
	public static final int NETWORK_ERROR = 1;
	
	//system상의 에러로 종료되는 경우.
	public static final int SYSTEM_ERROR = 4;

		
}
