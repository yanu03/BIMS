package kr.tracom.brt.domain.ST0504;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class ST0504Service extends ServiceSupport {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceSupport.class);

	@Autowired
	private ST0504Mapper st0504Mapper;
	
	public List ST0504G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return st0504Mapper.ST0504G0R0(map);
	}
	
	public List ST0504G0R1() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return st0504Mapper.ST0504G0R1(map);
	}
	
	public List ST0504G1R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return st0504Mapper.ST0504G1R0(map);
	}
	
	public List ST0504G1R1() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return st0504Mapper.ST0504G1R1(map);
	}
}
