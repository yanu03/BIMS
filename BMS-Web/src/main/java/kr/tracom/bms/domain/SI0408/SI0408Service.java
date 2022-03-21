package kr.tracom.bms.domain.SI0408;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.cm.domain.Rout.RoutMapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.CommonUtil;
import kr.tracom.util.Constants;
import kr.tracom.util.DataInterface;
import kr.tracom.util.Result;

@Service
public class SI0408Service extends ServiceSupport {

	@Autowired
	private SI0408Mapper SI0408Mapper;
	

	public Map SI0408G1S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;
		
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		
		List<Map<String, Object>> param = getSimpleList("BMS_ROUT_NODE_DISP_CMPSTN");
		
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				
				String rowStatus = (String) data.get("rowStatus");
				
				if (rowStatus.equals("C")) {
					iCnt += SI0408Mapper.SI0408G1I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += SI0408Mapper.SI0408G1U0(data);
				} else if (rowStatus.equals("D")) {
					dCnt += SI0408Mapper.SI0408G1D0(data);
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
	
	public List SI0408G1R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_sub_search");
		return SI0408Mapper.SI0408G1R0(param);
	}
}
