package kr.tracom.bms.domain.VD0101;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.tracom.bms.domain.VD0101.VD0101Mapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class VD0101Service extends ServiceSupport {
	
	@Autowired
	private VD0101Mapper vd0101Mapper;
	
	public List VD0101G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return vd0101Mapper.VD0101G0R0(map);
	}

	public List VD0101SHI0() throws Exception {
		return vd0101Mapper.VD0101SHI0();
	}
	
	public List VD0101SHI1() throws Exception {
		return vd0101Mapper.VD0101SHI1();
	}
	
	public List VD0101G1R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_subsearch");
		return vd0101Mapper.VD0101G1R0(param);
	}
	
	public List VD0101G2R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_subsearch");
		return vd0101Mapper.VD0101G2R0(param);
	}
	
	public Map VD0101G2S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_DVC_PARAM_CFG_INFO");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += vd0101Mapper.VD0101G2I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += vd0101Mapper.VD0101G2U0(data);
				} else if (rowStatus.equals("D")) {
					dCnt += vd0101Mapper.VD0101G2D0(data);
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
	
	public List<Map> VD0101P0R0() throws Exception{
		Map param = getSimpleDataMap("dma_search");
		return vd0101Mapper.VD0101P0R0(param);
	}
	
	public List VD0101P0SH() throws Exception {
		return vd0101Mapper.VD0101P0SH();
	}

	

}
