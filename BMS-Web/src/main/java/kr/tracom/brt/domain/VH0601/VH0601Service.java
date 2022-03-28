package kr.tracom.brt.domain.VH0601;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0601Service extends ServiceSupport{
	
	@Autowired
	private VH0601Mapper vh0601Mapper;
	
	public List<Map> VH0601G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0601Mapper.VH0601G0R0(param);
	}
	
}
