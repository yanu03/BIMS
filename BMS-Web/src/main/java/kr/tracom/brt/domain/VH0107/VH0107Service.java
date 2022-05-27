package kr.tracom.brt.domain.VH0107;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0107Service extends ServiceSupport{
	
	@Autowired
	private VH0107Mapper vh0107Mapper;
	
	public List<Map> VH0107G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0107Mapper.VH0107G0R0(param);
	}
	
}
