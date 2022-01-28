package kr.tracom.bms.domain.PI1102;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.tracom.bms.domain.PI1102.PI1102Mapper;
import kr.tracom.bms.ftp.FTPHandler;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.Constants;
import kr.tracom.util.Result;

@Service
public class PI1102Service extends ServiceSupport {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceSupport.class);


	@Autowired
	private PI1102Mapper pi1102Mapper;
	
	@Autowired
	private FTPHandler ftpHandler;
	
	
	public List PI1102G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return pi1102Mapper.PI1102G0R0(map);
	}
	
	public List PI1102SHI0() throws Exception {
		return pi1102Mapper.PI1102SHI0();
	}
	
	public List PI1102G1R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_subsearch");
		return pi1102Mapper.PI1102G1R0(map);
	}
	
	public List PI1102G2R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_subsearch2");		
		return pi1102Mapper.PI1102G2R0(map);
	}
	
	
	@Transactional
	public Map PI1102G1S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_DVC_INFO");
		Map<String, Object> map = getSimpleDataMap("dma_subsearch");
		
		List<String> impMngList = new ArrayList<>(); //관리아이디 목록
		
		try {		
			
			
			
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String)data.get("rowStatus");				
				if (rowStatus.equals("U")) {
					//iCnt = pi1102Mapper.PI1102G1I0(data);
					
					Map m = new HashMap();
					
					String orgaId = String.valueOf(map.get("ORGA_ID"));
					data.put("ORGA_ID", orgaId);
					
					int retCnt = pi1102Mapper.PI1102G1I0(data); //예약정보 insert
					iCnt += retCnt;		
					
					if(retCnt > 0) {						
						retCnt = pi1102Mapper.PI1102G1I1(data); //예약 결과정보 insert

						//영상 복사
						if(retCnt > 0) {
							String mngId = String.valueOf(data.get("MNG_ID"));
							String impId = mngId.substring(0, Constants.IMP_ID_DIGIT);
							String dvcId = mngId.substring(Constants.IMP_ID_DIGIT);
							
							data.put("ORGA_ID", orgaId);
							data.put("IMP_ID", impId);
							data.put("DVC_ID", dvcId);
							
							List<Map<String, Object>> playlist = pi1102Mapper.makePlayList(orgaId);
							
							ftpHandler.reserveVideo(data, playlist);
														
						}
						
					}
					
				} 
				/*else if (rowStatus.equals("D")) {
					dCnt += pi1102Mapper.PI1102G1D0(data);
				}*/ 
			}
			
			
			//FTP 서버와 영상 싱크
			for (int i = 0; i < param.size(); i++) {
				Map<String, Object> data = param.get(i);
					
				String mngId = String.valueOf(data.get("MNG_ID"));
				String impId = mngId.substring(0, Constants.IMP_ID_DIGIT);
				String dvcId = mngId.substring(Constants.IMP_ID_DIGIT);
				
				ftpHandler.syncVdoFile(impId, dvcId);
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
	
	public Map PI1102G1U0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_DVC_INFO");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("U")) {
					iCnt += pi1102Mapper.PI1102G1U0(data);
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
