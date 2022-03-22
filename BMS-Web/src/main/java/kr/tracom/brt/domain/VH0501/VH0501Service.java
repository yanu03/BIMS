package kr.tracom.brt.domain.VH0501;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0501Service extends ServiceSupport {
	
	@Autowired
	private VH0501Mapper vh0501Mapper;
	
	public List<Map> VH0501G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0501Mapper.VH0501G0R0(param);
	}
	
	public List<Map> selectIntgNmItem() throws Exception{
		return vh0501Mapper.selectIntgNmItem();
	}
	
	public List<Map> selectIntgStsItem() throws Exception{
		return vh0501Mapper.selectIntgStsItem();
	}
	
}
