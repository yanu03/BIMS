package kr.tracom.tims.handler;

import kr.tracom.platform.attribute.BisAtCode;
import kr.tracom.platform.attribute.bis.AtFacilityParam;
import kr.tracom.platform.attribute.integration.bluemobile.AtBluemobileStatus;
import kr.tracom.platform.net.protocol.attribute.AtData;
import kr.tracom.tims.domain.BisMapper;
import kr.tracom.ws.WsClient;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FacilityParam {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BisMapper bisMapper;
    
    @Autowired
	WsClient webSocketClient;
    
    public void handle(short attrId, AtData atData, String sessionId){
    	
        switch(attrId){
        
        	//스크린도어
            case BisAtCode.FACILITY_PARAM:
            	AtFacilityParam facilityParam = (AtFacilityParam)atData;
            	updateFacilityParam(facilityParam);
                break;
            
            //태그리스
            case BisAtCode.BLUEMOBILE_STATUS_INFO:
            	//추후에 웹소켓 작업 해야함
//            	//AtBluemobileStatus bluemobileStatus = (AtBluemobileStatus)atData;
            	//updateBluemobileFacilityParam(bluemobileStatus);
                break;
        }
    }

    @Transactional
    protected void updateFacilityParam(AtFacilityParam atData) {

        try {
            logger.debug("updateFacilityParam {}",atData);
            /*
            //paramKind 코드값 select
            Map paramKind = bisMapper.selectDlCdParamKindInfo(atData.toMap());
            
            //paramDiv 코드값 select
            Map paramDiv = bisMapper.selectDlCdParamDivInfo(atData.toMap());
            
            Map param = null;
            param.putAll(paramKind);
            param.putAll(paramDiv);
            param.putAll((Map) atData);
            
            //스크린도어 현정보 insert
            bisMapper.insertFacilityStatus(param);
            */
            
            /*logger.debug("updateFacilityParam {}",atData);
            Map<String, Object> param = new HashMap<String, Object>();

            //paramKind 코드값 select
            Map paramKind = bisMapper.selectDlCdParamKindInfo(atData.toMap());

            //paramDiv 코드값 select
            Map paramDiv = bisMapper.selectDlCdParamDivInfo(atData.toMap());

            //param.putAll(paramKind);
            //param.putAll(paramDiv);

            param.putAll(atData.toMap());
            param.put("PARAM_KIND",paramKind.get("DL_CD"));
            param.put("PARAM_DIV",paramDiv.get("DL_CD"));

            //스크린도어 현정보 insert
            bisMapper.insertFacilityParam(param);*/
            
            //웹소켓 전송이 필요한 경우
	        /*if(param != null) {
	    		webSocketClient.sendMessage(param);
	        }*/
            
        } catch (Exception e) {
            logger.info("", e);
        }

    }
    
    /*@Transactional
    protected void updateBluemobileFacilityParam(AtBluemobileStatus atData) {
    	
    	try {
            logger.debug("updateBlumobileFacilityParam {}",atData);
            
            //paramKind 코드값 select
            Map paramKind = bisMapper.selectDlCdParamKindInfo(atData.toMap());
            
            //paramDiv 코드값 select
            Map paramDiv = bisMapper.selectDlCdParamDivInfo(atData.toMap());
            
            Map param = null;
            param.putAll(paramKind);
            param.putAll(paramDiv);
            param.putAll((Map) atData);
            
            //태그리스 현정보 insert
            bisMapper.insertBluemobileFacilityStatus(param);
            
            
        } catch (Exception e) {
            logger.info("", e);
        }
    	
    }*/








}
