package kr.tracom.bms.domain.SI0402;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import kr.tracom.cm.domain.Rout.RoutMapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.cm.support.exception.MessageException;
import kr.tracom.util.CommonUtil;
import kr.tracom.util.Constants;
import kr.tracom.util.DataInterface;
import kr.tracom.util.Result;

@Service
public class SI0402Service extends ServiceSupport {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SI0402Mapper si0402Mapper;
	
	@Autowired
	private RoutMapper routMapper;
	
	public List SI0402G0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return si0402Mapper.SI0402G0R0(map);
	}

	public Map SI0402G0K0() throws Exception {
		return si0402Mapper.SI0402G0K0(); 
	}
	
	public List SI0402SHI0() throws Exception {
		return si0402Mapper.SI0402SHI0();
	}	
	
	public Map SI0402G1S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;
		boolean isLinkChange = false; //링크가 추가되거나, 변경되었는지 체크
		
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_ROUT_NODE_CMPSTN");
		
		try {
			String exl_update = (String) map.get("EXL_UPDATE");
			String reInstall = (String) map.get("RE_INSTALL");
			
			if(CommonUtil.notEmpty(exl_update)&&"true".equals(exl_update)) {
				if(param.size()>0) {
					Map delParam = new HashMap();
					delParam.put("ROUT_ID", param.get(0).get("ROUT_ID"));
					si0402Mapper.SI0402G1DA0(delParam);
				}
			}
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				
				//링크가 추가되거나, 변경되었는지 체크
				if(CommonUtil.notEmpty(data.get("NODE_SN"))&&CommonUtil.notEmpty(data.get("OLD_NODE_SN"))&&
						(data.get("NODE_SN").equals(data.get("OLD_NODE_SN"))==false) || CommonUtil.notEmpty(reInstall)&& "Y".equals(reInstall)) {
					isLinkChange = true;
				}
				
				String rowStatus = (String) data.get("rowStatus");
				String nodeType = (String) data.get("NODE_TYPE");
				String lastNodeId = (String) map.get("LAST_NODE_ID");
				
				
				String nodeType2 = null;
				
				if(i < param.size()-1) {
					Map data2 = (Map) param.get(i+1);
					nodeType2 = (String) data2.get("NODE_TYPE");
				}
				
				if (rowStatus.equals("C")) {
					Map key = null;
					
					if(CommonUtil.empty(data.get("NODE_ID"))){
						key = si0402Mapper.SI0402G1K0();
					}
					
					/*if(Constants.NODE_TYPE_VERTEX.equals(nodeType)==true){
						data.put("STTN_ID","");
						data.put("STTN_NO","");
						data.put("GRG_ID","");
					}*/
					
					if((Constants.NODE_TYPE_VERTEX.equals(nodeType)==false)
						&&(Constants.NODE_TYPE_SOUND.equals(nodeType)==false)
						&&(Constants.NODE_TYPE_GARAGE.equals(nodeType)==false)
						&&(Constants.NODE_TYPE_SIGNAL.equals(nodeType)==false)
					) {
						if(CommonUtil.empty(nodeType2)||(Constants.NODE_TYPE_GARAGE.equals(nodeType2)==false)
							&&(data.get("NODE_ID").equals(lastNodeId)==false))
						{
							Map linkKeyMap = si0402Mapper.SI0402G1K1();
							data.put("LINK_ID",linkKeyMap.get("SEQ"));	
						}
						else if(data.get("NODE_ID").equals(lastNodeId)){
							data.put("LINK_ID","");	
						}
						data.put("LINK_NODE_YN","Y");
					}
					else {
						data.put("LINK_ID","");	
					}
					
					if(key!=null)data.put("NODE_ID",key.get("SEQ"));
					if(CommonUtil.empty(data.get("OLD_NODE_SN"))||"0".equals(data.get("OLD_NODE_SN"))){
						iCnt += si0402Mapper.SI0402G1I0(data);
					}
					else {
						 iCnt += si0402Mapper.SI0402G1U0(data);
					}
					
					if((Constants.NODE_TYPE_BUSSTOP.equals(nodeType)||Constants.NODE_TYPE_CROSS.equals(nodeType))
						&&(CommonUtil.notEmpty(data.get("STTN_ID"))||CommonUtil.notEmpty(data.get("CRS_ID")))){
						if(Constants.NODE_TYPE_BUSSTOP.equals(nodeType)&&CommonUtil.notEmpty(data.get("STTN_ID"))){
							data.put("TYPE","STTN_ID");	
							//data.put("WAY_DIV",map.get("WAY_DIV"));	
							routMapper.updateSttn(data);
						
						}
						else if(Constants.NODE_TYPE_CROSS.equals(nodeType)&&CommonUtil.notEmpty(data.get("CRS_ID"))){
							data.put("TYPE","CRS_ID");
							routMapper.updateCrs(data);
						}
						
						routMapper.updateRoutNodeToAnotherRoute(data);
						routMapper.updateMainRoutNodeToAnotherRoute(data);
					}
				} else if (rowStatus.equals("U")) {
					
					/*if(Constants.NODE_TYPE_VERTEX.equals(nodeType)==true){
						data.put("STTN_ID","");
						data.put("STTN_NO","");
						data.put("GRG_ID","");
					}*/
					
					if((Constants.NODE_TYPE_VERTEX.equals(nodeType)==false)
						&&(Constants.NODE_TYPE_SOUND.equals(nodeType)==false)
						&&(Constants.NODE_TYPE_GARAGE.equals(nodeType)==false)
						&&(Constants.NODE_TYPE_SIGNAL.equals(nodeType)==false)
					) {
						if((CommonUtil.empty(nodeType2)||(Constants.NODE_TYPE_GARAGE.equals(nodeType2)==false))
							&&CommonUtil.empty(data.get("LINK_ID"))&&(data.get("NODE_ID").equals(lastNodeId)==false))
						{
							Map linkKeyMap = si0402Mapper.SI0402G1K1();
							data.put("LINK_ID",linkKeyMap.get("SEQ"));	
						}
						else if(data.get("NODE_ID").equals(lastNodeId)){
							data.put("LINK_ID","");	
						}
						data.put("LINK_NODE_YN","Y");
					}
					else {
						data.put("LINK_ID","");	
					}
					
					if((Constants.NODE_TYPE_BUSSTOP.equals(nodeType)||Constants.NODE_TYPE_CROSS.equals(nodeType))
						&&(CommonUtil.notEmpty(data.get("STTN_ID"))||CommonUtil.notEmpty(data.get("CRS_ID")))){
						
						if(Constants.NODE_TYPE_BUSSTOP.equals(nodeType)&&CommonUtil.notEmpty(data.get("STTN_ID"))){
							data.put("TYPE","STTN_ID");
							//data.put("WAY_DIV",map.get("WAY_DIV"));	
							routMapper.updateSttn(data);
						}
						else if(Constants.NODE_TYPE_CROSS.equals(nodeType)&&CommonUtil.notEmpty(data.get("CRS_ID"))){
							data.put("TYPE","CRS_ID");
							routMapper.updateCrs(data);
						}
						
						routMapper.updateRoutNodeToAnotherRoute(data);
						routMapper.updateMainRoutNodeToAnotherRoute(data);
						
						if(CommonUtil.notEmpty(data.get("OLD_NODE_ID"))){ //일반노드에서 정류소/교차로 노드로 변경시 NODE_ID를 변경해야함
							Map delParam = new HashMap();
							delParam.put("ROUT_ID", data.get("ROUT_ID"));
							delParam.put("NODE_ID", data.get("OLD_NODE_ID"));
							delParam.put("OLD_NODE_SN", data.get("OLD_NODE_SN"));
							si0402Mapper.SI0402G1D0(delParam);
							uCnt += si0402Mapper.SI0402G1I0(data);
						}
						else {
							uCnt += si0402Mapper.SI0402G1U0(data);
						}
					}
					else {
						data.put("TYPE","NODE_ID");
						routMapper.updateRoutNodeToAnotherRoute(data);
						routMapper.updateMainRoutNodeToAnotherRoute(data);
						uCnt += si0402Mapper.SI0402G1U0(data);
					}
					
				} else if (rowStatus.equals("D")) {
					dCnt += si0402Mapper.SI0402G1D0(data);
					
					//정류소,교차로 테이블의 NODE_ID 초기화
					//data.put("NODE_ID", "");
					//routMapper.updateSttn(data);
					//routMapper.updateCrs(data);
				}
			}
			
			//링크가 변경되었을때 링크를 재 구성함
			if((isLinkChange==true)&&(param.size()>0)) {
				int sttnCnt = 0;
				double routLen = 0;
				int sttnCrsCnt = 0;
				
				si0402Mapper.SI0402G1DA1(map);
				si0402Mapper.SI0402G1DA2(map);
				si0402Mapper.SI0402G1DA3(map);
				si0402Mapper.SI0402G1DA4(map);
				List<Map<String, Object>> routNodeList = si0402Mapper.SI0402G1R1(map);
				
				//List<Map<Object, Object>> routSttnLinkIdList = new ArrayList();
				List<Object> routSttnLinkIdKeysList = new ArrayList();
				List<Object> routSttnLinkIdValuesList = new ArrayList();
				
				if(routNodeList.size()>0) {
					
					//정류소별 링크
					for (int i = 0; i < routNodeList.size()-1; i++) {
						Map sttnData = routNodeList.get(i);
						sttnData.put("ROUT_LINK_ID", sttnData.get("LINK_ID"));
						String nodeType = (String) sttnData.get("NODE_TYPE");
						
						if(!Constants.NODE_TYPE_BUSSTOP.equals(nodeType)){
							continue;
						}						
						
						double len = 0;
						Map sttnData2 = null;
						
						Map beforeNode = sttnData;
						for(int j = i+1; j < routNodeList.size(); j++ ) {
							Map curNode = routNodeList.get(j);
							String nodeType2 = (String) curNode.get("NODE_TYPE");
							len += DataInterface.getDistanceBetween(CommonUtil.decimalToDouble(beforeNode.get("GPS_X")), CommonUtil.decimalToDouble(beforeNode.get("GPS_Y")), 
									CommonUtil.decimalToDouble(curNode.get("GPS_X")), CommonUtil.decimalToDouble(curNode.get("GPS_Y")));
							if(Constants.NODE_TYPE_BUSSTOP.equals(nodeType2)) {
								sttnData2 = curNode;
								break;
							}
							beforeNode = curNode;
						}
						
						if(sttnData2 == null) {
							continue;
						}
						
						
						
						//매핑링크 List 
						Map<Object, Object> routSttnLinkIdMap = new HashMap<>();
						//routSttnLinkIdMap.put(linkKeyMap.get("SEQ"), data.get("NODE_ID"));
						//routSttnLinkIdList.add(routSttnLinkIdMap);
						routSttnLinkIdValuesList.add(sttnData2.get("NODE_ID")); //도착 정류소ID
						
						//sttnData.put("LINK_ID",linkKeyMap.get("SEQ"));
						sttnData.put("LINK_SN",(i+1));
						sttnData.put("ST_NODE_ID",sttnData.get("NODE_ID"));
						sttnData.put("ED_NODE_ID",sttnData2.get("NODE_ID"));
						String linkNm = sttnData.get("NODE_NM") + "-" + sttnData2.get("NODE_NM");
						sttnData.put("LINK_NM",linkNm);
						
						
//						if(i==0) {
//							data.put("ACCRU_LEN",0);
//							si0402Mapper.updateLengthRoutNodeCmpstn(data);
//						}
						sttnData.put("LEN",CommonUtil.pointRound(len,3));
						//routLen += len;
						
						Map<Object, Object> sttnLink = si0402Mapper.getSttnLinkIdByNode(sttnData);
						
						
						
						if(sttnLink!=null&&CommonUtil.notEmpty(sttnLink.get("LINK_ID"))) { //링크가 있는 경우 기존 링크 사용함
							routSttnLinkIdKeysList.add(sttnLink.get("LINK_ID")); //ROUT_STTN_LINK_ID
							sttnData.put("STTN_LINK_ID",sttnLink.get("LINK_ID"));
							si0402Mapper.SI0402G1I2_2(sttnData); //링크 insert
						}
						else {
							Map linkKeyMap = si0402Mapper.SI0402G1K2();
							routSttnLinkIdKeysList.add(linkKeyMap.get("SEQ")); //ROUT_STTN_LINK_ID
							sttnData.put("STTN_LINK_ID",linkKeyMap.get("SEQ"));
							si0402Mapper.SI0402G1I2(sttnData); //링크 insert
						}
						//si0402Mapper.SI0402G1I2(sttnData); //링크 insert
						
//						data2.put("ACCRU_LEN",(int)routLen);
//						si0402Mapper.updateLengthRoutNodeCmpstn(data2);
					}					
					
					
					//BMS_ROUT_LINK_CMPSTN
					for (int i = 0; i < routNodeList.size()-1; i++) {
						
						Map data = routNodeList.get(i);
						String nodeType = (String) data.get("NODE_TYPE");
						
						Map data2 = routNodeList.get(i+1);
						if(Constants.NODE_TYPE_BUSSTOP.equals(nodeType)){
							sttnCnt++;
						}
						
						
						
						//if(data.get("NODE_ID") != routSttnLinkIdList.get(sttnCrsCnt).get("NODE_ID")) {
						
						//data.put("ROUT_STTN_LINK_ID", routSttnLinkIdList.get(sttnCrsCnt).get("SEQ"));
						if(sttnCrsCnt<routSttnLinkIdKeysList.size()) {
							data.put("ROUT_STTN_LINK_ID", routSttnLinkIdKeysList.get(sttnCrsCnt));
							if(data.get("NODE_ID") == routSttnLinkIdValuesList.get(sttnCrsCnt)) {
								
								sttnCrsCnt++;
							}
						}
						
						data.put("LINK_SN",(i+1));
						data.put("ST_NODE_ID",data.get("NODE_ID"));
						data.put("ED_NODE_ID",data2.get("NODE_ID"));
						String linkNm = data.get("NODE_NM") + "-" + data2.get("NODE_NM");
						data.put("LINK_NM",linkNm);
						double len = DataInterface.getDistanceBetween(CommonUtil.decimalToDouble(data.get("GPS_X")), CommonUtil.decimalToDouble(data.get("GPS_Y")), 
								CommonUtil.decimalToDouble(data2.get("GPS_X")), CommonUtil.decimalToDouble(data2.get("GPS_Y")));
						
						if(i==0) {
							data.put("ACCRU_LEN",0);
							si0402Mapper.updateLengthRoutNodeCmpstn(data);
						}
						data.put("LEN",CommonUtil.pointRound(len,3));
						routLen += len;
						si0402Mapper.SI0402G1I1(data); //링크 insert
						
						data2.put("ACCRU_LEN",(int)routLen);
						si0402Mapper.updateLengthRoutNodeCmpstn(data2);
					}
					
					//정류소/교차로별 링크
					for (int i = 0; i < routNodeList.size()-1; i++) {
						Map sttnCrsdata = routNodeList.get(i);
						String nodeType = (String) sttnCrsdata.get("NODE_TYPE");
						
						if(!Constants.NODE_TYPE_BUSSTOP.equals(nodeType) && !Constants.NODE_TYPE_CROSS.equals(nodeType)){
							continue;
						}
						double len = 0;
						Map sttnCrsdata2 = null;
						
						Map beforeNode = sttnCrsdata;
						for(int j = i+1; j < routNodeList.size(); j++ ) {
							Map curNode = routNodeList.get(j);
							String nodeType2 = (String) curNode.get("NODE_TYPE");
							len += DataInterface.getDistanceBetween(CommonUtil.decimalToDouble(beforeNode.get("GPS_X")), CommonUtil.decimalToDouble(beforeNode.get("GPS_Y")), 
									CommonUtil.decimalToDouble(curNode.get("GPS_X")), CommonUtil.decimalToDouble(curNode.get("GPS_Y")));							
							if(Constants.NODE_TYPE_BUSSTOP.equals(nodeType2) || Constants.NODE_TYPE_CROSS.equals(nodeType2)) {
								sttnCrsdata2 = curNode;
								break;
							}
							beforeNode = curNode;
						}	
						
						if(sttnCrsdata2 == null) {
							continue;
						}
						

						sttnCrsdata.put("LINK_SN",(i+1));
						sttnCrsdata.put("ST_NODE_ID",sttnCrsdata.get("NODE_ID"));
						sttnCrsdata.put("ED_NODE_ID",sttnCrsdata2.get("NODE_ID"));
						String linkNm = sttnCrsdata.get("NODE_NM") + "-" + sttnCrsdata2.get("NODE_NM");
						sttnCrsdata.put("LINK_NM",linkNm);
						
						
//						if(i==0) {
//							data.put("ACCRU_LEN",0);
//							si0402Mapper.updateLengthRoutNodeCmpstn(data);
//						}
						sttnCrsdata.put("LEN",CommonUtil.pointRound(len,3));
						//routLen += len;
						Map<Object, Object> sttnCrsLink = si0402Mapper.getSttnCrsLinkIdByNode(sttnCrsdata);
						if(sttnCrsLink!=null&&CommonUtil.notEmpty(sttnCrsLink.get("LINK_ID"))) {
							sttnCrsdata.put("STTN_LINK_CRS_ID",sttnCrsLink.get("LINK_ID"));
							si0402Mapper.SI0402G1I3_2(sttnCrsdata); //링크 insert
						}
						else {
							Map linkKeyMap = si0402Mapper.SI0402G1K3();
							sttnCrsdata.put("STTN_LINK_CRS_ID",linkKeyMap.get("SEQ"));
							si0402Mapper.SI0402G1I3(sttnCrsdata); //링크 insert
						}
						//si0402Mapper.SI0402G1I3(sttnCrsdata); //링크 insert
						
//						data2.put("ACCRU_LEN",(int)routLen);
//						si0402Mapper.updateLengthRoutNodeCmpstn(data2);
					}
					
					//정류소/집중모니터링 교차로별 링크
					for (int i = 0; i < routNodeList.size()-1; i++) {
						Map sttnMoCrsdata = routNodeList.get(i);
						String nodeType = (String) sttnMoCrsdata.get("NODE_TYPE");
						
						
						if(!Constants.NODE_TYPE_BUSSTOP.equals(nodeType) && !Constants.NODE_TYPE_CROSS.equals(nodeType)){
							continue;
						}
						
						//현시가 있는 교차로만 찾아서 넣기
						else if(Constants.NODE_TYPE_CROSS.equals(nodeType)) {
							String phaseNo = (String) sttnMoCrsdata.get("PHASE_NO");
							if("".equals(phaseNo)) {
								continue;
							}
						}
						double len = 0;
						Map sttnMoCrsdata2 = null;
						Map beforeNode = sttnMoCrsdata;
						
						for(int j = i+1; j < routNodeList.size(); j++ ) {
							Map curNode = routNodeList.get(j);
							String nodeType2 = (String) curNode.get("NODE_TYPE");
							len += DataInterface.getDistanceBetween(CommonUtil.decimalToDouble(beforeNode.get("GPS_X")), CommonUtil.decimalToDouble(beforeNode.get("GPS_Y")), 
												CommonUtil.decimalToDouble(curNode.get("GPS_X")), CommonUtil.decimalToDouble(curNode.get("GPS_Y")));
							
							if(Constants.NODE_TYPE_BUSSTOP.equals(nodeType2) || Constants.NODE_TYPE_CROSS.equals(nodeType2)) {
								if(Constants.NODE_TYPE_CROSS.equals(nodeType2)) {
									String phaseNo2 = (String) curNode.get("PHASE_NO");
									if("".equals(phaseNo2)) {
										beforeNode = curNode;
										continue;
									}
								}
								sttnMoCrsdata2 = curNode;
								break;
							}
							beforeNode = curNode;
						}	
						
						if(sttnMoCrsdata2 == null) {
							continue;
						}
						
						sttnMoCrsdata.put("LINK_SN",(i+1));
						sttnMoCrsdata.put("ST_NODE_ID",sttnMoCrsdata.get("NODE_ID"));
						sttnMoCrsdata.put("ED_NODE_ID",sttnMoCrsdata2.get("NODE_ID"));
						String linkNm = sttnMoCrsdata.get("NODE_NM") + "-" + sttnMoCrsdata2.get("NODE_NM");
						sttnMoCrsdata.put("LINK_NM",linkNm);
						
//						if(i==0) {
//							data.put("ACCRU_LEN",0);
//							si0402Mapper.updateLengthRoutNodeCmpstn(data);
//						}
						sttnMoCrsdata.put("LEN",CommonUtil.pointRound(len,3));
						//routLen += len;
						
						Map<Object, Object> sttnMoCrsLink = si0402Mapper.getSttnMoCrsLinkIdByNode(sttnMoCrsdata);
						if(sttnMoCrsLink!=null&&CommonUtil.notEmpty(sttnMoCrsLink.get("LINK_ID"))) {
							sttnMoCrsdata.put("STTN_LINK_MO_CRS_ID",sttnMoCrsLink.get("LINK_ID"));
							si0402Mapper.SI0402G1I4_2(sttnMoCrsdata); //링크 insert
						}
						else {
							Map linkKeyMap = si0402Mapper.SI0402G1K4();
							sttnMoCrsdata.put("STTN_LINK_MO_CRS_ID",linkKeyMap.get("SEQ"));							
							si0402Mapper.SI0402G1I4(sttnMoCrsdata); //링크 insert
						}
						//si0402Mapper.SI0402G1I4(sttnMoCrsdata); //링크 insert
						
//						data2.put("ACCRU_LEN",(int)routLen);
//						si0402Mapper.updateLengthRoutNodeCmpstn(data2);
					}
					
					Map routMap = new HashMap();
					double routStrtLen = DataInterface.getDistanceBetween(CommonUtil.decimalToDouble(routNodeList.get(0).get("GPS_X")), CommonUtil.decimalToDouble(routNodeList.get(0).get("GPS_Y")), 
							CommonUtil.decimalToDouble(routNodeList.get(routNodeList.size()-1).get("GPS_X")), CommonUtil.decimalToDouble(routNodeList.get(routNodeList.size()-1).get("GPS_Y")));
							
					routMap.put("ROUT_ID", (String)routNodeList.get(0).get("ROUT_ID"));
					
					routMap.put("ROUT_LEN", CommonUtil.pointRound(routLen,3));
					routMap.put("ROUT_STRT_LEN", CommonUtil.pointRound(routStrtLen,3));
					routMap.put("STTN_CNT", sttnCnt);
					routMapper.updateRout(routMap);
				}
			}

		} catch(Exception e) {
			if (e instanceof DuplicateKeyException)
			{
				throw new MessageException(Result.ERR_KEY, "중복된 키값의 데이터가 존재합니다.");
			}
			else
			{
				logger.error("Excetion {}", e);
				throw e;
			}		
		}

		
		Map result = saveResult(iCnt, uCnt, dCnt);
		
		return result;		
		
		
	}
	
	public List SI0402G1R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_sub_search");
		return si0402Mapper.SI0402G1R0(param);
	}
	
	public List SI0402P0R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return si0402Mapper.SI0402P0R0(map);
	}
	
	public List SI0402P1R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return si0402Mapper.SI0402P1R0(map);
	}
	
	public List SI0402P2R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return si0402Mapper.SI0402P2R0(map);
	}
		
	public Map SI0402P2S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_STTN_MST");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += si0402Mapper.SI0402P2I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += si0402Mapper.SI0402P2U0(data);
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
	
	public Map SI0402P2K0() throws Exception {
		return si0402Mapper.SI0402P2K0(); 
	}
	
	public List SI0402P3R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return si0402Mapper.SI0402P3R0(map);
	}
	
	public Map SI0402P3S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;		
		
		List<Map<String, Object>> param = getSimpleList("dlt_BMS_CRS_MST");
		try {
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				String rowStatus = (String) data.get("rowStatus");
				if (rowStatus.equals("C")) {
					iCnt += si0402Mapper.SI0402P3I0(data);
				} else if (rowStatus.equals("U")) {
					uCnt += si0402Mapper.SI0402P3U0(data);
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
	
	public Map SI0402P3K0() throws Exception {
		return si0402Mapper.SI0402P3K0(); 
	}
	
	public List SI0402P5R0() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return si0402Mapper.SI0402P5R0(map);
	}	
}
