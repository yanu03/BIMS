package kr.tracom.brt.domain.VH0104;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class VH0104Service extends ServiceSupport{
	
	@Autowired
	private VH0104Mapper vh0104Mapper;
	
	public List<Map> VH0104G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vh0104Mapper.VH0104G0R0(param);
	}
	
	public List<Map> VH0104SHI0() throws Exception{
		return vh0104Mapper.VH0104SHI0();
	}
	/*
	public List<Map> FM0201G1R0() throws Exception{
		Map param = getSimpleDataMap("dma_sub_search");
		return fm0201Mapper.FM0201G1R0(param);
	}
	
	
	
	public List FM0201SHI1() throws Exception {
		return fm0201Mapper.FM0201SHI1();
	}
	
	public List FM0201SHI2() throws Exception {
		return fm0201Mapper.FM0201SHI2();
	}
	
	public List FM0201SHI3() throws Exception {
		Map param = getSimpleDataMap("dma_search");		
		return fm0201Mapper.FM0201SHI3(param);
	}	*/
}
