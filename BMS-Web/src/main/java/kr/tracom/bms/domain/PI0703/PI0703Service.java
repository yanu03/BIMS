package kr.tracom.bms.domain.PI0703;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.bms.domain.PI0703.PI0703Mapper;
import kr.tracom.bms.ftp.FTPHandler;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Result;

@Service
public class PI0703Service extends ServiceSupport {

	@Autowired
	private PI0703Mapper pi0703Mapper;
	
	@Autowired
	private FTPHandler ftpHandler;
	
	public List PI0703G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return pi0703Mapper.PI0703G0R0(map);
	}
	
	public List PI0703SHI1() throws Exception {
		return pi0703Mapper.PI0703SHI1();
	}
	
	//예약
	public Map PI0703G0S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		//체크한 노선 리스트 (list_param)
		List<Map<String, Object>> list_param = getSimpleList("dlt_BIT_ROUT_MST");
		
		List<Map<String, Object>> param = getSimpleList("dlt_VHC_MST");
		Map<String, Object> map = getSimpleDataMap("dma_subsearch");
		try {
			
			//예약 정보, 예약결과 정보 insert
			Map<String, Object> paramMap = new HashMap<String, Object>();
			//iCnt = pi0703Mapper.PI0703G1I0(paramMap);
			
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("U")) {
					iCnt = pi0703Mapper.PI0703G0I0(data);
					//data.put("ORGA_ID",map.get("ORGA_ID"));
					
					//차량별 장치코드리스트
					String vhcId = String.valueOf(data.get("VHC_ID"));
					List<Map<String, Object>> dlist = pi0703Mapper.selectDvcCd(vhcId);
					
					//예약해야할노선리스트			
					for(Map<String, Object> route : list_param) {
						
						String routeId = String.valueOf(route.get("ROUT_ID"));
						Map<String, Object> routeInfo= pi0703Mapper.selectRouteInfo(routeId);
						if(route!=null && routeInfo!=null)
							route.put( "TXT_VAL1", String.valueOf(routeInfo.get("TXT_VAL1")) ); //U or D //list_param 의 값을 변경
	
						//local temp -> local vehicle 폴더로 복사
						ftpHandler.reserveDst(routeInfo, dlist);
		    		}
					
				} 
				else if (rowStatus.equals("D")) {
					dCnt += pi0703Mapper.PI0703G0D0(data);
				} 
			}			
			
			//list.csv파일 생성 및 ftp 싱크
			ftpHandler.makeDstConfig(param, list_param);
			
			
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
	
	//예약 취소
	public Map PI0703G0U0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_VHC_MST");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("U")) {
					iCnt += pi0703Mapper.PI0703G0U0(data);
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
	
	public List PI0703SHI0() throws Exception {
		return pi0703Mapper.PI0703SHI0();
	}
	
	public List PI0703G1R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_subsearch");		
		return pi0703Mapper.PI0703G1R0(map);
	}
	
	public Map PI0703G1K0() throws Exception {
		return pi0703Mapper.PI0703G1K0(); 
	}	
	
	public List PI0703G1R1() throws Exception {
		return pi0703Mapper.PI0703G1R1();
	}
	
	
	
	
	
	public List PI0703G2R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_subsearch2");		
		return pi0703Mapper.PI0703G2R0(map);
	}	
}
