package kr.tracom.bms.domain.PI0201;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.CommonUtil;
import kr.tracom.util.DateUtil;
import kr.tracom.util.Result;

@Service
public class PI0201Service extends ServiceSupport{

	@Autowired
	private PI0201Mapper PI0201Mapper;
		
	@Value("${fileupload.up.directory}")
	private String UPLOAD_DIR;

	@Value("${fileupload.audio.directory}")
	private String UPLOAD_AUDIO_DIR;

	public List<Map> PI0201G0R0() throws Exception{
		Map<String, Object> param = getSimpleDataMap("dma_search");
		List returnList = PI0201Mapper.PI0201G0R0(param);
		
		Map<String, Object> AUDIO_INFO = getSimpleDataMap("dma_AUDIO_INFO");
		for(Object obj:returnList) {
			
			Map<String, Object> temp = (Map<String, Object>)obj;
			//if(CommonUtil.notEmpty(temp.get("VOC_PATH"))) {
				if("WAV".equals(temp.get("PLAY_TYPE"))){
					temp.put("VOC_PATH", "/fileUpload/audio/"+AUDIO_INFO.get("AUDIO_NM")+".wav");
				}
				else if("TTS".equals(temp.get("PLAY_TYPE"))){
					temp.put("VOC_PATH", "/fileUpload/audio/"+AUDIO_INFO.get("AUDIO_NM")+".tts");
				}
				else {
					temp.put("VOC_PATH", "/fileUpload/audio/"+AUDIO_INFO.get("AUDIO_NM")+".wav");
				}
			//}
		}
		
		return returnList;
		
	}
	
	public List PI0201SHI0() throws Exception {
		return PI0201Mapper.PI0201SHI0();
	}
	
	public Map PI0201G0K0() throws Exception {
		return PI0201Mapper.PI0201G0K0();
	}	
	
	public Map PI0201G0S0() throws Exception {
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
    			if (rowStatus.equals("C")) {
    				iCnt += PI0201Mapper.PI0201G0I0(data);
    				
    				if(CommonUtil.notEmpty(AUDIO_INFO.get("AUDIO_NM"))&&CommonUtil.notEmpty(AUDIO_INFO.get("VOC_ID")))
					{
						doMoveFile(UPLOAD_DIR, UPLOAD_AUDIO_DIR, AUDIO_INFO.get("AUDIO_NM").toString(), AUDIO_INFO.get("VOC_ID").toString());
					}
    				
    			} else if (rowStatus.equals("U")) {
    				uCnt += PI0201Mapper.PI0201G0U0(data);
   
    				if(CommonUtil.notEmpty(AUDIO_INFO.get("AUDIO_NM"))&&CommonUtil.notEmpty(AUDIO_INFO.get("VOC_ID")))
					{
						doMoveFile(UPLOAD_DIR, UPLOAD_AUDIO_DIR, AUDIO_INFO.get("AUDIO_NM").toString(), AUDIO_INFO.get("VOC_ID").toString());
					}

    			} else if (rowStatus.equals("D")) {
    				dCnt += PI0201Mapper.PI0201G0D0(data);
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
}
