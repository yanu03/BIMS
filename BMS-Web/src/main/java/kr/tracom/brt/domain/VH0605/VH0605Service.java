package kr.tracom.brt.domain.VH0605;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0605Service extends ServiceSupport{
	
	@Autowired
	private VH0605Mapper vh0605Mapper;
	
	public List<Map> VH0605G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0605Mapper.VH0605G0R0(param);
	}
	
}
