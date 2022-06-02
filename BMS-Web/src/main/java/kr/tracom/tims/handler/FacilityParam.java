package kr.tracom.tims.handler;

import kr.tracom.platform.attribute.bis.AtFacilityParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FacilityParam {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void handle(AtFacilityParam facilityParam, String sessionId){
        updateFacilityParam(facilityParam);
    }


    @Transactional
    protected void updateFacilityParam(AtFacilityParam atData) {

        try {
            logger.debug("updateFacilityParam {}",atData);
            //bisMapper.insertFacilityStatus(atData.toMap());

        } catch (Exception e) {
            logger.info("", e);
        }

    }








}
