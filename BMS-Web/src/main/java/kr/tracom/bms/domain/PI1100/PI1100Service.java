package kr.tracom.bms.domain.PI1100;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class PI1100Service extends ServiceSupport {

	@Autowired
	private PI1100Mapper pi1100Mapper;
	
	public List PI1100G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		List returnList = pi1100Mapper.PI1100G0R0(map);
		
		for(Object obj:returnList) {
			Map<String, Object> temp = (Map<String, Object>)obj;
			temp.put("FILE_PATH", "/fileUpload/video/"+temp.get("FILE_NM"));			
		}
		
		return returnList;
	}
	
	public List PI1100SHI0() throws Exception {
		return pi1100Mapper.PI1100SHI0();
	}
	
	public List PI1100P0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return pi1100Mapper.PI1100P0R0(map);
	}

	public Map PI1100G0K0() throws Exception {
		return pi1100Mapper.PI1100G0K0(); 
	}
	
	public Map PI1100G0S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_VDO_INFO");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					if((data.get("FILE_NM")!=null)&&(data.get("FILE_NM").toString().isEmpty()==false)
							&&(data.get("VDO_ID").equals(data.get("FILE_NM"))==false))
						{
							doMoveFile("up/", "video/", data.get("FILE_NM").toString(), data.get("VDO_ID").toString()+ "."+ data.get("FILE_EXTENSION").toString());
							data.put("FILE_NM", data.get("VDO_ID").toString()+ "."+ data.get("FILE_EXTENSION").toString());
						}	
					iCnt += pi1100Mapper.PI1100G0I0(data);				
				} else if (rowStatus.equals("U")) {
					if((data.get("FILE_NM")!=null)&&(data.get("FILE_NM").toString().isEmpty()==false)
							&&(data.get("VDO_ID").equals(data.get("FILE_NM"))==false)) 
						{
							doMoveFile("up/","video/",data.get("FILE_NM").toString(),data.get("VDO_ID").toString()+ "." + data.get("FILE_EXTENSION").toString());
							data.put("FILE_NM", data.get("VDO_ID").toString()+ "."+ data.get("FILE_EXTENSION").toString());
						}	
					uCnt += pi1100Mapper.PI1100G0U0(data);				
				} else if (rowStatus.equals("D")) {
					dCnt += pi1100Mapper.PI1100G0D0(data);
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
