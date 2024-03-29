package kr.tracom.bms.domain.PI0204;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.tracom.bms.ftp.FTPHandler;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.util.DateUtil;

@Service
public class PI0204Service extends ServiceSupport {

	@Autowired
	private PI0204Mapper PI0204Mapper;
	
	@Autowired
	FTPHandler ftpHandler;
	
	@Value("${fileupload.selectedAudio.directory}")
	private String UPLOAD_SELECTED_AUDIO_DIR;
	
	@Value("${fileupload.base.path}")
	private String UPLOAD_BASE_PATH;

	public List<Map> PI0204G0R0() throws Exception {
		Map param = getSimpleDataMap("dma_search");
		List returnList = PI0204Mapper.PI0204G0R0(param);
	
		for(Object obj:returnList) {
			
			Map<String, Object> temp = (Map<String, Object>)obj;
			
			String selectecAudioDir = (UPLOAD_BASE_PATH + UPLOAD_SELECTED_AUDIO_DIR).replaceAll("//", "/");
			
			temp.put("VOC_PATH", selectecAudioDir+temp.get("VOC_ID")+".wav");			
		}
		
		return returnList;
	}

	public Map PI0204G0S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;
		List param = getSimpleList("dlt_BMS_VOC_INFO");
		Map param2 = new HashMap();
		List<Map> vocList = PI0204Mapper.PI0204G0R0(param2);
		
		for (int i = 0; i < param.size(); i++) {
			Map<String, Object> data = (Map) param.get(i);
			String rowStatus = (String) data.get("rowStatus");
			// 데이터베이스 date 타입일때 공백으로 들어가면 에러나는 사항 임시 수정
			for (String key : data.keySet()) {
				if (data.get(key).equals("")) {
					data.put(key, null);
				}
			}
			if (rowStatus.equals("U")) {
				ftpHandler.uploadSelectedAudio(data, vocList);
				uCnt += PI0204Mapper.PI0204G0U0(data);
			} else if (rowStatus.equals("D")) {
				dCnt += PI0204Mapper.PI0204G0D0(data);
				ftpHandler.deleteSelectedAudio(data, vocList);
			}
		}
		Map result = new HashMap();
		result.put("STATUS", "S");
		result.put("ICNT", String.valueOf(iCnt));
		result.put("UCNT", String.valueOf(uCnt));
		result.put("DCNT", String.valueOf(dCnt));


		return result;
	}

	/*
	 * public Map PI0201G0K0() throws Exception { return PI0201Mapper.PI0201G0K0();
	 * }
	 */

}
