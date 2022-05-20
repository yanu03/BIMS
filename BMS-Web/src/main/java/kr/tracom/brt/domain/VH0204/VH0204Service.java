package kr.tracom.brt.domain.VH0204;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0204Service extends ServiceSupport{
	
	@Autowired
	private VH0204Mapper vh0204Mapper;
	
	public List<Map> VH0204G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0204Mapper.VH0204G0R0(param);
	}
	
}
