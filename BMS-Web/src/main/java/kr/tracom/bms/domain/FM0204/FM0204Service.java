package kr.tracom.bms.domain.FM0204;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import sun.misc.BASE64Encoder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.core.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.FM0204.FM0204Mapper;
import kr.tracom.cm.domain.Intg.IntgMapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.tims.domain.HistoryMapper;
import kr.tracom.util.CommonUtil;
import kr.tracom.util.Result;
import kr.tracom.ws.WsClient;

@Service
public class FM0204Service extends ServiceSupport {
	
	@Value("${api.gateway.url}")
	private String apiGatewayUrl;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FM0204Mapper FM0204Mapper;
	
	@Autowired
	private IntgMapper intgMapper;
	
	@Autowired
	private HistoryMapper historyMapper;
	
	@Autowired
	WsClient webSocketClient;
	
	public List FM0204G0R0() throws Exception {
		Map param = getSimpleDataMap("dma_search");
		return FM0204Mapper.FM0204G0R0(param);
	}
	
	public List FM0204G0R1() throws Exception {
		Map searchParam = getSimpleDataMap("dma_search");
		String fcltKind = (String) searchParam.get("FCLT_KIND");
		
		//scheduler.schedule_10sec();
		
		//텝이 에어컨 일때만 에어컨 정보 UPDATE
		/*if(fcltKind.equals("FK005")) {
			List<Map<String, Object>> param = getSimpleList("dlt_airconItem");
			
			Map<String, Object> paramSr = new HashMap();
			paramSr.put("INTG_TYPE", "SR");
			
			List<Map<String, Object>> token = intgMapper.selectIntgMstList(paramSr);
			String key = (String) token.get(0).get("INTG_API_KEY");
			String intgUrl = (String) token.get(0).get("INTG_URL");
			
			HttpURLConnection conn = null;
			String result = "";
			
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String api = apiGatewayUrl + intgUrl + key;
				api = api + "&deviceId=" + data.get("INTG_FCLT_ID");
				
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
							
							Gson gson = new Gson();
							Type resultType = new TypeToken<List<Map<String, Object>>>(){}.getType();
							List<Map<String, Object>> jsonList = gson.fromJson(result, resultType);
							
							for (int j = 0; j < jsonList.size(); j++) {
								Map<String, Object> data2 = (Map) jsonList.get(j);
								
								data2.put("COOL_SET", data2.get("coolingSetpoint"));
								data2.put("TEMP", data2.get("temperature"));
								//data2.put("SWITCH", data.get("switch"));
								data2.put("FCLT_ID", data.get("FCLT_ID"));
								
								if(data2.get("switch").equals("on")) {
									data2.put("SWITCH", "1");
								}else if(data2.get("switch").equals("off")) {
									data2.put("SWITCH", "0");
								}
								
								historyMapper.updateFcltCondParamInfo(data2);
								
							}			
							
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
			}			
			
		}*/
		
		return FM0204Mapper.FM0204G0R1(searchParam);
	}
	
	public List FM0204G0R2() throws Exception {
		Map param = getSimpleDataMap("dma_subsearch");
		return FM0204Mapper.FM0204G0R2(param);
	}

	public List FM0204G1R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_subsearch");
		return FM0204Mapper.FM0204G1R0(param);
	}
	
	public List FM0204G2R0() throws Exception {
		return FM0204Mapper.FM0204G2R0();
	}
	
	public List FM0204G2R1() throws Exception {
		return FM0204Mapper.FM0204G2R1();
	}
	
	public List FM0204G2R2() throws Exception {
		Map param = getSimpleDataMap("dma_subsearch");
		return FM0204Mapper.FM0204G2R2(param);
	}
	
	//에어컨 제어
	public List airconControl() throws Exception {
		List<Map<String, Object>> param = getSimpleList("dlt_airconControl");
		
		for(int x = 0; x < param.size(); x++) {
			Map data = (Map) param.get(x);
			String intgFcltId = (String) data.get("INTG_FCLT_ID");
			String power = (String) data.get("POWER");
			String degree = (String) data.get("DEGREE");
			
			//전원제어
			if(power.isEmpty()==false) {
				data.put("INTG_TYPE", "PC");
				
				List<Map<String, Object>> token = intgMapper.selectIntgMstList(data);
				String key = (String) token.get(0).get("INTG_API_KEY");
				String intgUrl = (String) token.get(0).get("INTG_URL");
				
				String api = apiGatewayUrl + intgUrl + key + "&deviceId=" + intgFcltId + "&value=" + power;
				
				BufferedReader in = null;
				
				try {
					URL url = new URL(api);
					try {
						HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 접속 
						conn.setRequestMethod("GET"); // 전송 방식은 GET
						
						in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//온도제어
			if(degree.isEmpty()==false) {
				data.put("INTG_TYPE", "DC");
				
				List<Map<String, Object>> token = intgMapper.selectIntgMstList(data);
				String key = (String) token.get(0).get("INTG_API_KEY");
				String intgUrl = (String) token.get(0).get("INTG_URL");
				
				String api = apiGatewayUrl + intgUrl + key + "&deviceId=" + intgFcltId + "&value=" + degree;
				
				BufferedReader in = null;
				
				try {
					URL url = new URL(api);
					try {
						HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 접속 
						conn.setRequestMethod("GET"); // 전송 방식은 GET
						
						in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		
		
		return null;
	}
	
	
	

}
