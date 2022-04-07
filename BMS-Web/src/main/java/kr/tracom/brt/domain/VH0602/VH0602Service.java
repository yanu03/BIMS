package kr.tracom.brt.domain.VH0602;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0602Service extends ServiceSupport{
	
	@Autowired
	private VH0602Mapper vh0602Mapper;
	
	public List<Map> VH0602G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0602Mapper.VH0602G0R0(param);
	}
	
}
