package kr.tracom.bms.domain.PI0205;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.bms.ftp.FTPHandler;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.CommonUtil;
import kr.tracom.util.DateUtil;
import kr.tracom.util.Result;

@Service
public class PI0205Service extends ServiceSupport{

	@Autowired
	private PI0205Mapper PI0205Mapper;
	
	@Value("${fileupload.up.directory}")
	private String UPLOAD_DIR;
	
	@Value("${fileupload.route.audio.directory}")
	private String UPLOAD_ROUTE_AUDIO_DIR;
	
	@Value("${fileupload.base.path}")
	private String UPLOAD_BASE_PATH;
	
	@Autowired
	FTPHandler ftpHandler;
	
	public List<Map> PI0205G0R0() throws Exception{
		Map<String, Object> param = getSimpleDataMap("dma_search");
		List returnList = PI0205Mapper.PI0205G0R0(param);
		
		Map<String, Object> AUDIO_INFO = getSimpleDataMap("dma_AUDIO_INFO");
		for(Object obj:returnList) {
			
			Map<String, Object> temp = (Map<String, Object>)obj;
			//temp.put("VOC_PATH", "/fileUpload/audio/"+AUDIO_INFO.get("AUDIO_NM"));
			String routAudioDir = (UPLOAD_BASE_PATH + UPLOAD_ROUTE_AUDIO_DIR).replaceAll("//", "/");
			if("WAV".equals(temp.get("PLAY_TYPE"))){
				temp.put("VOC_PATH", routAudioDir+temp.get("ROUT_ID")+".wav");
			}
			else if("TTS".equals(temp.get("PLAY_TYPE"))){
				temp.put("VOC_PATH", routAudioDir+temp.get("ROUT_ID")+".wav");
			}
			else {
				temp.put("VOC_PATH", routAudioDir+temp.get("ROUT_ID")+"wav");
			}
		}
		
		return returnList;
		
	}
	
	public Map PI0205G0S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_VOC_INFO");
        Map<String, Object> AUDIO_INFO = getSimpleDataMap("dma_AUDIO_INFO");
        
        try {
        	for (int i = 0; i < param.size(); i++) {
    			Map<String, Object> data = param.get(i);
    			String rowStatus = (String) data.get("rowStatus");
    			// 데이터베이스 date 타입일때 공백으로 들어가면 에러나는 사항 임시 수정
    			for (String key : data.keySet()) {
    				if (data.get(key).equals("")) {
    					data.put(key, null);
    				}
    			}			
    			if (rowStatus.equals("U")) {
    				if((data.get("VOC_ID") != null)&&(data.get("VOC_ID").toString().isEmpty()==false))
    					{
    						ftpHandler.uploadVoice(data);
    						uCnt += PI0205Mapper.PI0205G0U0(data);
    					}
    				else if((data.get("VOC_ID") == null)||(data.get("VOC_ID").toString().isEmpty()==true)) 
    					{
    						ftpHandler.uploadVoice(data);
    						iCnt += PI0205Mapper.PI0205G0I0(data);
    					}
    				
   
    				/*if(CommonUtil.notEmpty(AUDIO_INFO.get("AUDIO_NM"))&&CommonUtil.notEmpty(AUDIO_INFO.get("VOC_ID")))
					{
						doMoveFile(UPLOAD_DIR, UPLOAD_AUDIO_DIR, AUDIO_INFO.get("AUDIO_NM").toString(), AUDIO_INFO.get("VOC_ID").toString());
					}*/
                    
    				
    			} else if (rowStatus.equals("D")) {
    				dCnt += PI0205Mapper.PI0205G0D0(data);
    				ftpHandler.deleteVoice(data);
    			}
    		}
		} catch (Exception e) {
			if (e instanceof DuplicateKeyException) {
				throw new MessageException(Result.ERR_KEY, "중복된 키값의 데이터가 존재합니다.");
			} else {
				throw e;
			}
		}
		
		Map result = saveResult(iCnt, uCnt, dCnt);
		
		return result;
	}
	
	
	public List PI0205SHI0() throws Exception {
		return PI0205Mapper.PI0205SHI0();
	}
	
	public Map PI0205G0K0() throws Exception {
		return PI0205Mapper.PI0205G0K0();
	}	
	
	
}
