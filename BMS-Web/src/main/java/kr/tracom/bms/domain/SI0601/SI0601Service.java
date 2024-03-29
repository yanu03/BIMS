package kr.tracom.bms.domain.SI0601;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.bms.domain.SI0101.SI0101Mapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class SI0601Service extends ServiceSupport {
	
	@Autowired
	private SI0601Mapper si0601Mapper;
	
	public List SI0601G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return si0601Mapper.SI0601G0R0(map);
	}

	public Map SI0601G0K0() throws Exception {
		return si0601Mapper.SI0601G0K0(); 
	}
	
	public List SI0601SHI0() throws Exception {
		return si0601Mapper.SI0601SHI0();
	}	
	
	public Map SI0601G0S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_PARTNER_MST");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += si0601Mapper.SI0601G0I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += si0601Mapper.SI0601G0U0(data);
				} else if (rowStatus.equals("D")) {
					dCnt += si0601Mapper.SI0601G0D0(data);
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
