package kr.tracom.bms.domain.FM0101;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.FM0101.FM0101Mapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class FM0101Service extends ServiceSupport {
	
	@Autowired
	private FM0101Mapper FM0101Mapper;
	
	
	public List FM0101SHI1() throws Exception {
		return FM0101Mapper.FM0101SHI1();
	}
	
	public List FM0101SHI2() throws Exception {
		return FM0101Mapper.FM0101SHI2();
	}
	
	public List FM0101G1R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_subsearch");
		return FM0101Mapper.FM0101G1R0(param);
	}
	
	public List FM0101G2R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_subsearch");
		return FM0101Mapper.FM0101G2R0(param);
	}
	
	public Map FM0101G2S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_FCLT_PARAM_CFG_INFO");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += FM0101Mapper.FM0101G2I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += FM0101Mapper.FM0101G2U0(data);
				} else if (rowStatus.equals("D")) {
					dCnt += FM0101Mapper.FM0101G2D0(data);
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
	
	public List<Map> FM0101P0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return FM0101Mapper.FM0101P0R0(param);
	}
	
	public List FM0101P0SH() throws Exception {
		return FM0101Mapper.FM0101P0SH();
	}

	public List<Map> FM0101G3R0() throws Exception{
		Map param = getSimpleDataMap("dma_subsearch");
		return FM0101Mapper.FM0101G3R0(param);
	}
	

}
