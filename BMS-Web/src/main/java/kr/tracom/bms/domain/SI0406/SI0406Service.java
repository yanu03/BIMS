package kr.tracom.bms.domain.SI0406;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.tracom.cm.domain.Common.CommonMapper;
import kr.tracom.cm.domain.Rout.RoutMapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.CommonUtil;
import kr.tracom.util.Constants;
import kr.tracom.util.DataInterface;
import kr.tracom.util.Result;

@Service
public class SI0406Service extends ServiceSupport {

	@Autowired
	private SI0406Mapper si0406Mapper;

	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private RoutMapper routMapper;	
	
	public Map SI0406G1S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_ROUT_MOCK_LINK_CMPSTN");
		try {
			
			//기존 노선노드테이블, 노선링크테이블 삭제
//			if(param.size()>0) {
//				si0406Mapper.SI0406G1DA0(map);
//				si0406Mapper.SI0406G1DA1(map);
//			}
			
			for (int i = 0; i < param.size(); i++) { 
				
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += si0406Mapper.SI0406G1I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += si0406Mapper.SI0406G1U0(data);
				} else if (rowStatus.equals("D")) {
					dCnt += si0406Mapper.SI0406G1D0(data);
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
	
	public List SI0406G1R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_sub_search");
		return si0406Mapper.SI0406G1R0(param);
	}
	
	public List SI0406G2R0() throws Exception {
		// TODO Auto-generated method stub
		return si0406Mapper.SI0406G2R0();
	}
	

	public List SI0406P0R0() throws Exception {
		// TODO Auto-generated method stub
		return si0406Mapper.SI0406P0R0();		
	}
	
	
	public Map SI0406P0S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_MOCK_LINK");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += si0406Mapper.SI0406G1I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += si0406Mapper.SI0406G1U0(data);
				} else if (rowStatus.equals("D")) {
					dCnt += si0406Mapper.SI0406G1D0(data);
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
