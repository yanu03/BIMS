package kr.tracom.cm.domain.Intg;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;


@Service
public class IntgService extends ServiceSupport {

	@Value("${api.gateway.url}")
	private String apiGatewayUrl;
		
	@Autowired
	private IntgMapper intgMapper;
	
	public List<Map<String, Object>> selectIntgList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return intgMapper.selectIntgList(map);
		
		/*String api = apiGatewayUrl + "/local/smartthings/getDevices?token=58b54327-bbd0-4d13-9767-c9c29bd285e0";
		URL url = new URL(api);
		return url;*/
	}
	
}
