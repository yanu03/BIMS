package kr.tracom.brt.domain.VH0202;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0202Service extends ServiceSupport{
	
	@Autowired
	private VH0202Mapper vh0202Mapper;
	
	public List<Map> VH0202G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0202Mapper.VH0202G0R0(param);
	}
	
}
