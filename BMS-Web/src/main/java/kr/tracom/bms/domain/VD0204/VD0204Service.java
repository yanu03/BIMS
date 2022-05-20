package kr.tracom.bms.domain.VD0204;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.VD0204.VD0204Mapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class VD0204Service extends ServiceSupport {
	
	@Autowired
	private VD0204Mapper vd0204Mapper;
	
	public List VD0204G0R0() throws Exception {
		return vd0204Mapper.VD0204G0R0();
	}
	
	public List VD0204G0R1() throws Exception {
		return vd0204Mapper.VD0204G0R1();
	}
	
	public List VD0204G0R2() throws Exception {
		Map param = getSimpleDataMap("dma_subsearch");
		return vd0204Mapper.VD0204G0R2(param);
	}

	public List VD0204SHI0() throws Exception {
		return vd0204Mapper.VD0204SHI0();
	}
	
	public List VD0204SHI1() throws Exception {
		return vd0204Mapper.VD0204SHI1();
	}
	
	public List VD0204G1R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_subsearch");
		return vd0204Mapper.VD0204G1R0(param);
	}
	
	public List VD0204G2R0() throws Exception {
		return vd0204Mapper.VD0204G2R0();
	}
	
	public List VD0204G2R1() throws Exception {
		return vd0204Mapper.VD0204G2R1();
	}
	
	
	

}
