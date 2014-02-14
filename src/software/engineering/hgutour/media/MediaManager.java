package software.engineering.hgutour.media;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import software.engineering.hgutour.general.GlobalVariables;
import android.os.Environment;

public class MediaManager {
	private Vector<MediaInfo> mediaList;

	public void setting(){
		// SD카드 Path 얻기
		String SDPath = ""+Environment.getExternalStorageDirectory();
		// SD카드 열기
		File file = new File(SDPath + "/HGUtour/media.xml");
		// List 열기
		mediaList = new Vector<MediaInfo>();

		try {
			// 파일 스트림 열기
			FileInputStream fis = new FileInputStream(file);
			// 인풋스트림 열기
			InputStream is = fis;
			// DOM Factory 선언
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// DOM Builder 선언
			DocumentBuilder db = dbf.newDocumentBuilder();
			// DOM 선언
			Document dom = db.parse(new InputSource(is)); 
			// Element 선언
			Element docEle = dom.getDocumentElement();
			// NodeList 선언
			NodeList nl = docEle.getElementsByTagName("mediaList");

			// NodeList가 정보를 받아오면
			if(nl != null && nl.getLength() > 0){
				// NodeList에서 받은 정보 infoList에 뿌리기
				for(int i = 0 ; i < nl.getLength() ; i++){
					Element media = (Element)nl.item(i);
					Element category = (Element)media.getElementsByTagName("category").item(0);
					Element url = (Element)media.getElementsByTagName("url").item(0);
					Element title = (Element)media.getElementsByTagName("title").item(0);
										
					
					String _category = category.getFirstChild().getNodeValue();
					String _url = url.getFirstChild().getNodeValue();
					String _title = title.getFirstChild().getNodeValue();
					String _img = _url.replace("mp4", "jpg");					
					
					
					mediaList.add(new MediaInfo(_category, _url, _title, _img));
					// for 종료
				}
				// if nl is not null 종료
			}

			// 인풋스트림 닫기
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 처음 getList
	public Vector<MediaInfo> getList(){
		return mediaList;
	}

	// 검색할때 getList
		public Vector<MediaInfo> getList(int category_num){
			// 검색 후 나올 returnList 생성
			Vector<MediaInfo> returnList = new Vector<MediaInfo>();
			
			// 검색해서 returnList 구성
			for(int i = 0 ; i < mediaList.size() ; i++){
				if(category_num==0){
						returnList.add(mediaList.get(i));
					}
				else{
					if(Integer.parseInt(mediaList.get(i).getcategory())==category_num){
						returnList.add(mediaList.get(i));
						}
					}			
				}
				return returnList;
			}

	
	// 검색할때 getList
	public Vector<MediaInfo> getList(String search,int category_num){
		// 검색 후 나올 returnList 생성
		Vector<MediaInfo> returnList = new Vector<MediaInfo>();
		search = search.toUpperCase();
		
		// 검색해서 returnList 구성
		for(int i = 0 ; i < mediaList.size() ; i++){
			if(category_num==0){
				if(mediaList.get(i).gettitle().toUpperCase().contains(search)){
					returnList.add(mediaList.get(i));
				}			
			}//홀 넘버가 0이 아닐때
			else{
				if((mediaList.get(i).gettitle().toUpperCase().contains(search))
						&&Integer.parseInt(mediaList.get(i).getcategory())==category_num){
					returnList.add(mediaList.get(i));
				}	
			}
		}
		return returnList;
	}

	// 업데이트 or 처음 정보 받아 SD카드에 저장
	public boolean saveAtSDcard(){
		// InputStream 객체 생성
		InputStream is = null;
		// FileOutputStream 객체 생성
		FileOutputStream fos = null;

		try {
			// SD카드 Path 얻기
			String SDPath = ""+Environment.getExternalStorageDirectory();
			// 폴더 생성
			File path = new File(SDPath + "/HGUtour/");
			path.mkdir();

			// 파일 생성
			File saveFile = new File(SDPath + "/HGUtour/media.xml");
			saveFile.createNewFile();

			// 서버 연결
			
			URL stuUrl = new URL(GlobalVariables.SERVER_ADDR + "/HGUtour/media.jsp");
			HttpURLConnection con = (HttpURLConnection)stuUrl.openConnection();
			// 연결 참조 변수 선언
			int responseCode = con.getResponseCode();

			
			// 연결되면
			if(responseCode == HttpURLConnection.HTTP_OK){
				// InputStream 변수 선언
				is = con.getInputStream();

				// FileOutputStream 변수 선언
				fos = new FileOutputStream(saveFile);

				// 파일 복사
				int c;
				while((c = is.read()) != -1){
					fos.write((char)c);
				}

				// 스트림 닫기
				is.close();
				fos.close();
			}
			else{
				return false;
			}
		}catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}