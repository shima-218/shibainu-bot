package api;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class CallApi {

	public static CallApi getInstance() {
		return api;
	}
	private static CallApi api = new CallApi();
	private void CallApi() {}

	public String obtainShibainuImage() {
		//API接続の準備
		CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate rest = new RestTemplate(requestFactory);
		//API接続
		String apiUrl = "http://shibe.online/api/shibes?count=1&urls=true&httpsUrls=true";
		ResponseEntity<String> response = rest.getForEntity(apiUrl, String.class);
		//結果のreturn
		String responseBody = response.getBody();
		return responseBody.substring(2, responseBody.length()-2);
	}
}
