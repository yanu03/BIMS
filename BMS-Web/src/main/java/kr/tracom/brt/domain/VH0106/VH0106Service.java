package kr.tracom.brt.domain.VH0106;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0106Service extends ServiceSupport{
	
	@Autowired
	private VH0106Mapper vh0106Mapper;
	
	public List<Map> VH0106G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0106Mapper.VH0106G0R0(param);
	}
	
}
