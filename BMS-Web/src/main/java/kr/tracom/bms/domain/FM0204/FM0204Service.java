package kr.tracom.bms.domain.FM0204;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sun.misc.BASE64Encoder;
import com.sun.jersey.core.util.Base64;

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
import kr.tracom.util.Result;

@Service
public class FM0204Service extends ServiceSupport {
	
	@Value("${api.gateway.url}")
	private String apiGatewayUrl;
	
	@Autowired
	private FM0204Mapper FM0204Mapper;
	
	@Autowired
	private IntgMapper intgMapper;
	
	public List FM0204G0R0() throws Exception {
		Map param = getSimpleDataMap("dma_search");
		return FM0204Mapper.FM0204G0R0(param);
	}
	
	public List FM0204G0R1() throws Exception {
		return FM0204Mapper.FM0204G0R1();
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
	
	//에어컨 제어 테스트!!!
	public List sendtest() throws Exception {
		Map param = getSimpleDataMap("dma_sendtest");
		String intgFcltId = (String) param.get("INTG_FCLT_ID");
		String power = (String) param.get("POWER");
		String degree = (String) param.get("DEGREE");
		
		List<Map<String, Object>> token = intgMapper.selectIntgMstList(param);
		String key = (String) token.get(0).get("INTG_API_KEY");
		String intgUrl = (String) token.get(0).get("INTG_URL");
		
		//String api = apiGatewayUrl + intgUrl + key + "&deviceId=" + intgFcltId + "&value=" + power;
		
		//intgUrl - "local/smartthings/getDevices?token=" => local/smartthings/까지만 연계관리에 두고 뒷부분은 파라미터 속성1에서 가져오기!! 작업해야함.
		String[] api = {apiGatewayUrl + intgUrl + key + "&deviceId=" + "18e8acbb-f959-119f-9cfd-000001200001" + "&value=" + power
						,apiGatewayUrl + "local/smartthings/setCoolingSetpoint?token=" + key + "&deviceId=" + "18e8acbb-f959-119f-9cfd-000001200001" + "&value=" + degree};
		
		BufferedReader in = null;
		
		for(int i=0; i<api.length; i++) {
			try {
				URL url = new URL(api[i]);
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
		
		
		
		
		
		return null;
	}
	
	
	

}
