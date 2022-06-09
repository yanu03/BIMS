package kr.tracom.brt.domain.VH0108;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class VH0108Service extends ServiceSupport {

	@Autowired
	private VH0108Mapper VH0108Mapper;
	
	public List VH0108G0R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_search");
		return VH0108Mapper.VH0108G0R0(param);
	}
	
	public List VH0108SHI0() throws Exception {
		return VH0108Mapper.VH0108SHI0();
		
	}
	
	 	
}
