package kr.tracom.brt.domain.VH0300;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class VH0300Service extends ServiceSupport {

	@Autowired
	private VH0300Mapper vh0300Mapper;
	
	public List VH0300G0R0() throws Exception {
		Map param = getSimpleDataMap("dma_search");
		return vh0300Mapper.VH0300G0R0(param);
	}
	
	 	
}
