package kr.tracom.brt.domain.VH0206;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0206Service extends ServiceSupport{
	
	@Autowired
	private VH0206Mapper vh0206Mapper;
	
	public List<Map> VH0206G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0206Mapper.VH0206G0R0(param);
	}
	
	public List<Map> selectDvcItem() throws Exception{
		return vh0206Mapper.selectDvcItem();
	}
	
	public List<Map> selectDvcParamItem() throws Exception{
		return vh0206Mapper.selectDvcParamItem();
	}
}
