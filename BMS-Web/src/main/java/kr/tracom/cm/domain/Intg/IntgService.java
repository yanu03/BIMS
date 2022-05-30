package kr.tracom.cm.domain.Intg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import kr.tracom.cm.support.ServiceSupport;


@Service
public class IntgService extends ServiceSupport {

	@Value("${api.gateway.url}")
	private String apiGatewayUrl;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	@Autowired
	private IntgMapper intgMapper;
	
	public List<Map<String, Object>> selectIntgList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		
		//String api = apiGatewayUrl + "/local/smartthings/getDevices?token=58b54327-bbd0-4d13-9767-c9c29bd285e0";
		//URL url = new URL(api);
		
		return intgMapper.selectIntgList(map);
		
	}
	
	public String insertIntgList() throws Exception {
		List<Map<String, Object>> token = intgMapper.selectIntgMstList();
		String key = (String) token.get(0).get("INTG_API_KEY");
		String intgUrl = (String) token.get(0).get("INTG_URL");
		
		String api = apiGatewayUrl + intgUrl + key;
		HttpURLConnection conn = null;
		String result = "";

		try {
			URL url = new URL(api);
			try {
				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				try {
					result = sb.toString();
					
					String[] array = result.split("},");
					int len = array.length;
					array[0].replaceAll("[", "");
					array[len].replaceAll("[", "");
					
					for(int i = 0; i < array.length; i++) {
						array[i] = array[i] + "}";
							
						JSONParser parser = new JSONParser();
						Object obj = parser.parse(array[i]);
						JSONObject jsonObj = (JSONObject) obj;
					}
					
					//JSONArray jsonArr = new JSONArray(result);
					
				} catch (Exception e) {
					//logger.error("error");
					logger.error("error : {}", e );
				}
			} catch (IOException e) {
				logger.error("IOException");
			}
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException");
		}

		//return result;
		return null;
		
	}

	
}
