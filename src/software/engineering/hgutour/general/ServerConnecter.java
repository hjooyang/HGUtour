package software.engineering.hgutour.general;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class ServerConnecter {

	// server connect
	protected boolean serverConnect(String addr){
		try {
			// 서버 연결
			URL stuUrl = new URL(GlobalVariables.SERVER_ADDR + addr);
			HttpURLConnection con = (HttpURLConnection)stuUrl.openConnection();

			// 연결 참조 변수 선언
			int responseCode = con.getResponseCode();

			// 연결되면 do something
			if(responseCode == HttpURLConnection.HTTP_OK)
				doSomething(con);

			// otherwise,
			else
				return false;

		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	protected abstract void doSomething(HttpURLConnection con) throws IOException;
}
