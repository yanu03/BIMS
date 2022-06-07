package kr.tracom.brt.domain.ST0503;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class ST0503Service extends ServiceSupport {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceSupport.class);

	@Autowired
	private ST0503Mapper st0503Mapper;
	
	public List ST0503G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return st0503Mapper.ST0503G0R0(map);
	}
	
	public List ST0503G0R1() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return st0503Mapper.ST0503G0R1(map);
	}
	
	public List ST0503G1R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return st0503Mapper.ST0503G1R0(map);
	}
	
	public List ST0503G1R1() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return st0503Mapper.ST0503G1R1(map);
	}
}
