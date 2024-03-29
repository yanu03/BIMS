package kr.tracom.bms.domain.SI0200;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class SI0200Service extends ServiceSupport {

	@Autowired
	private SI0200Mapper si0200Mapper;
	
	public List SI0200G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return si0200Mapper.SI0200G0R0(map);
	}
	
	public List SI0200SHI0() throws Exception {
		return si0200Mapper.SI0200SHI0();
	}
	
	public List SI0200P0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return si0200Mapper.SI0200P0R0(map);
	}

	public Map SI0200G0K0() throws Exception {
		return si0200Mapper.SI0200G0K0(); 
	}
	
	public Map SI0200G0S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_VHC_MST");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += si0200Mapper.SI0200G0I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += si0200Mapper.SI0200G0U0(data);
				} else if (rowStatus.equals("D")) {
					si0200Mapper.SI0200G0D1(data);
					dCnt += si0200Mapper.SI0200G0D0(data);
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
	
	public List SI0200SHI1() throws Exception {
		return si0200Mapper.SI0200SHI1();
	}
}
