package kr.tracom.brt.domain.VH0201;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0201Service extends ServiceSupport{
	
	@Autowired
	private VH0201Mapper vh0201Mapper;
	
	public List<Map> VH0201G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0201Mapper.VH0201G0R0(param);
	}
	
}
