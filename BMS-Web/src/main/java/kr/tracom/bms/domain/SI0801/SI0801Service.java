package kr.tracom.bms.domain.SI0801;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.DateUtil;
import kr.tracom.util.Result;

@Service
public class SI0801Service extends ServiceSupport{

	@Autowired
	private SI0801Mapper si0801Mapper;
		
	public List<Map> SI0801G0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return si0801Mapper.SI0801G0R0(param);
	}
	
	public List<Map> SI0801G1R0() throws Exception{
		Map param = getSimpleDataMap("dma_sub_search");
		return si0801Mapper.SI0801G1R0(param);
	}
	
	public List SI0801SHI0() throws Exception {
		return si0801Mapper.SI0801SHI0();
	}
	
	public Map SI0801G0S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;
		List param = getSimpleList("dlt_BMS_FCLT_PARAM_CFG_INFO");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += si0801Mapper.SI0801G0I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += si0801Mapper.SI0801G0U0(data);
				} else if (rowStatus.equals("D")) {
					dCnt += si0801Mapper.SI0801G0D0(data);
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
	
	public Map SI0801G1S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;
		List param = getSimpleList("dlt_BMS_FCLT_SCH_INFO");
		Map sub_param = getSimpleDataMap("dma_sub_search");
		
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				data.put("FCLT_KIND", sub_param.get("FCLT_KIND"));
				data.put("PARAM_DIV", sub_param.get("PARAM_DIV"));
				data.put("PARAM_KIND", sub_param.get("PARAM_KIND"));
				data.put("SCHEDULE_YN", sub_param.get("SCHEDULE_YN"));
				
				
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += si0801Mapper.SI0801G1I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += si0801Mapper.SI0801G1U0(data);
					
				} else if (rowStatus.equals("D")) {
					dCnt += si0801Mapper.SI0801G1D0(data);
				} 	
				
				//스케쥴유무 업데이트
				uCnt += si0801Mapper.SI0801G0U1(data);
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
