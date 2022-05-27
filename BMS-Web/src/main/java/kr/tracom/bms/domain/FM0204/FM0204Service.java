package kr.tracom.bms.domain.FM0204;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.FM0204.FM0204Mapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class FM0204Service extends ServiceSupport {
	
	@Value("${api.gateway.url}")
	private String apiGatewayUrl;
	
	@Autowired
	private FM0204Mapper FM0204Mapper;
	
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
	
	
	

}
