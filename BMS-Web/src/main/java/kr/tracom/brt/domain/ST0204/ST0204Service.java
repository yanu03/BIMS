package kr.tracom.brt.domain.ST0204;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class ST0204Service extends ServiceSupport {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceSupport.class);

	@Autowired
	private ST0204Mapper st0202Mapper;
	
	/*public List ST0202G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return st0202Mapper.ST0202G0R0(map);
	}
	
	public List ST0202G1R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return st0202Mapper.ST0202G1R0(map);
	}*/
}
