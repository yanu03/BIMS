package kr.tracom.tims.handler;

import kr.tracom.platform.attribute.BisAtCode;
import kr.tracom.platform.attribute.bis.AtFacilityParam;
import kr.tracom.platform.attribute.integration.bluemobile.AtBluemobileStatus;
import kr.tracom.platform.net.protocol.attribute.AtData;
import kr.tracom.tims.domain.BisMapper;

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
    
    public void handle(short attrId, AtData atData, String sessionId){
    	
        switch(attrId){
        
            case BisAtCode.FACILITY_PARAM:
            	AtFacilityParam facilityParam = (AtFacilityParam)atData;
            	updateFacilityParam(facilityParam);
                break;
            
            case BisAtCode.BLUEMOBILE_STATUS_INFO:
            	AtBluemobileStatus bluemobileStatus = (AtBluemobileStatus)atData;
                break;
                
            
        }
    }
    


    @Transactional
    protected void updateFacilityParam(AtFacilityParam atData) {

        try {
            logger.debug("updateFacilityParam {}",atData);
            
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
            
            
        } catch (Exception e) {
            logger.info("", e);
        }

    }








}
