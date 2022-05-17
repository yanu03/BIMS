package kr.tracom.brt.domain.ST0605;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.brt.domain.AL0103.AL0103Mapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class ST0605Service extends ServiceSupport {

	@Autowired
	private ST0605Mapper ST0605Mapper;
	
	public List ST0605G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return ST0605Mapper.ST0605G0R0(map);
	}
	
	
	public List ST0605SHI0() throws Exception {
		return ST0605Mapper.ST0605SHI0();
	}
	
	public List ST0605G1R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_sub_search");
		return ST0605Mapper.ST0605G1R0(param);
	}
	
	public List ST0605G1CNT() throws Exception {
		Map param = getSimpleDataMap("dma_sub_search");
		return ST0605Mapper.ST0605G1CNT(param);
	}
	
		
	
}
