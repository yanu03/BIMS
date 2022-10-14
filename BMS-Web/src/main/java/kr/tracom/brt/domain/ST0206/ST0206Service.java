package kr.tracom.brt.domain.ST0206;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class ST0206Service extends ServiceSupport {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceSupport.class);

	@Autowired
	private ST0206Mapper st0206Mapper;
	
	public List ST0206G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return st0206Mapper.ST0206G0R0(map);
	}
	
	public List ST0206PROC() throws Exception {
		return st0206Mapper.ST0206PROC();
	}
	
	public List ST0206G1R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		String temp[] = map.get("NODE_ID").toString().replace("[","").replace("]","").replace(" ","").split(",");
		map.put("NODE_ID", temp);
		return st0206Mapper.ST0206G1R0(map);
	}
	
	public List ST0206G1R1() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		String temp[] = map.get("NODE_ID").toString().replace("[","").replace("]","").replace(" ","").split(",");
		map.put("NODE_ID", temp);
		return st0206Mapper.ST0206G1R1(map);
	}
	
	public List ST0206G1R2() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		String temp[] = map.get("NODE_ID").toString().replace("[","").replace("]","").replace(" ","").split(",");
		map.put("NODE_ID", temp);
		return st0206Mapper.ST0206G1R2(map);
	}
	
}
