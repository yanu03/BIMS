package kr.tracom.bms.domain.PI1101;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.bms.domain.PI1101.PI1101Mapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class PI1101Service extends ServiceSupport {
	@Autowired
	private PI1101Mapper pi1101Mapper;
	
	public List PI1101G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return pi1101Mapper.PI1101G0R0(map);
	}

	public Map PI1101G0K0() throws Exception {
		return pi1101Mapper.PI1101G0K0(); 
	}
	
	public List PI1101SHI0() throws Exception {
		return pi1101Mapper.PI1101SHI0();
	}	
	
	public Map PI1101G0S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_VDO_ORGA_INFO");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += pi1101Mapper.PI1101G0I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += pi1101Mapper.PI1101G0U0(data);
				} else if (rowStatus.equals("D")) {
					dCnt += pi1101Mapper.PI1101G0D0(data);
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
	
	public List PI1101G1R0() throws Exception {
		// TODO Auto-generated method stub
		return pi1101Mapper.PI1101G1R0();
	}
	
	
	public List PI1101G2R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_subsearch");
		return pi1101Mapper.PI1101G2R0(map);
	}
	
	public Map PI1101G2S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_VDO_ORGA_LIST");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += pi1101Mapper.PI1101G2I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += pi1101Mapper.PI1101G2U0(data);
				}
				else if (rowStatus.equals("D")) {
					dCnt += pi1101Mapper.PI1101G2D0(data);
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
