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
	
	public Map VD0204G2S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_DVC_PARAM_CFG_INFO");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += vd0204Mapper.VD0204G2I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += vd0204Mapper.VD0204G2U0(data);
				} else if (rowStatus.equals("D")) {
					dCnt += vd0204Mapper.VD0204G2D0(data);
				} 
			}			
		} catch(Exception e) {
			if (e instanceof DuplicateKeyException)
			{
				throw new MessageException(Result.ERR_KEY, "중복된 키값의 데이터가 존재합니다.");
			}
			else
			{
				throw e;
			}		
		}

		
		Map result = saveResult(iCnt, uCnt, dCnt);
		
		return result;		
		
		
	}
	
	

}
