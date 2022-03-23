package kr.tracom.brt.domain.VH0502;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0502Service extends ServiceSupport{
	
	@Autowired
	private VH0502Mapper vh0502Mapper;
	
	public List<Map> VH0502G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0502Mapper.VH0502G0R0(param);
	}
	
}
