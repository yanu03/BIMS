package kr.tracom.bms.domain.SI0408;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class SI0408Service extends ServiceSupport {

	@Autowired
	private SI0408Mapper SI0408Mapper;
	

	public Map SI0408G1S0() throws Exception {
		int iCnt = 0;
		int uCnt = 0;
		int dCnt = 0;
		
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		
		List<Map<String, Object>> param = getSimpleList("BMS_ROUT_NODE_DISP_CMPSTN");
		List<Map<String, Object>> routNodeListByRepRout = null;
		
		try {
			Map<String, Object> map2 = new HashMap<String, Object>();
			
			if(param.size()>0) { //동일 대표노선의 다른 노선 갱신 용 삭제해도 됨
				map2.put("REP_ROUT_ID",param.get(0).get("REP_ROUT_ID"));
				map2.put("ROUT_ID",param.get(0).get("ROUT_ID"));
				map2.put("WAY_DIV",param.get(0).get("WAY_DIV"));
				routNodeListByRepRout = SI0408Mapper.selectRoutNodeAllByRepRout(map2);
			}
			
			if(routNodeListByRepRout!=null) { //동일 대표노선의 다른 노선 갱신 용 삭제해도 됨 //동일한 대표노선의 다른 노선의 정보 삭제
				String oldRoutId = ""; 
				for(Map<String, Object> routNode : routNodeListByRepRout) {
					/*String nodeId = "";
					String edNodeId = "";*/
					for (int i = 0; i < param.size(); i++) {
						Map data = (Map) param.get(i);
						
						String rowStatus = (String)data.get("rowStatus");
						
						if (rowStatus.equals("D")) {
							
							String cOrgGpsX = (String)CommonUtil.bigDecimalToString(routNode.get("ORG_GPS_X"));
							String cOrgGpsY = (String)CommonUtil.bigDecimalToString(routNode.get("ORG_GPS_Y"));
							String cNodeType = (String)CommonUtil.bigDecimalToString(routNode.get("NODE_TYPE"));
							String orgGpsX = (String)CommonUtil.bigDecimalToString(data.get("ORG_GPS_X"));
							String orgGpsY = (String)CommonUtil.bigDecimalToString(data.get("ORG_GPS_Y"));
							String nodeType = (String)CommonUtil.bigDecimalToString(data.get("NODE_TYPE"));
							
							if(cOrgGpsX.equals(orgGpsX)&&cOrgGpsY.equals(orgGpsY)&&cNodeType.equals(nodeType)) {
								routNode.put("NODE_CHILD_SN", data.get("NODE_CHILD_SN"));
								dCnt += SI0408Mapper.SI0408G1D1(routNode);
							}
						}
					}
				}
			}
			
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				
				String rowStatus = (String)data.get("rowStatus");
				
				if (rowStatus.equals("D")) {
					dCnt += SI0408Mapper.SI0408G1D0(data);
				}
			}
			
			
			if(routNodeListByRepRout!=null) { //동일 대표노선의 다른 노선 갱신 용 삭제해도 됨 //동일한 대표노선의 다른 노선의 정보 갱신
				String oldRoutId = "";
				String routId = "";
				boolean isRouteStart = false;
				for(Map<String, Object> routNode : routNodeListByRepRout) {
					routId = (String)routNode.get("ROUT_ID");
					for (int i = 0; i < param.size(); i++) {
						Map data = (Map) param.get(i);
						
						String rowStatus = (String) data.get("rowStatus");
						
						
						
						String cOrgGpsX = (String)CommonUtil.bigDecimalToString(routNode.get("ORG_GPS_X"));
						String cOrgGpsY = (String)CommonUtil.bigDecimalToString(routNode.get("ORG_GPS_Y"));
						String cNodeType = (String)CommonUtil.bigDecimalToString(routNode.get("NODE_TYPE"));
						String orgGpsX = (String)CommonUtil.bigDecimalToString(data.get("ORG_GPS_X"));
						String orgGpsY = (String)CommonUtil.bigDecimalToString(data.get("ORG_GPS_Y"));
						String nodeType = (String)CommonUtil.bigDecimalToString(data.get("NODE_TYPE"));						
						
						if(oldRoutId.equals(routId)==false) {
							isRouteStart = false;
							if(cOrgGpsX.equals(orgGpsX)&&cOrgGpsY.equals(orgGpsY)/*&&cNodeType.equals(nodeType)*/) {
								isRouteStart = true;
								oldRoutId = routId;
							}
						}
						else {
							isRouteStart = true;
						}
						
						boolean isUpdate = false;
						if(isRouteStart) {
							Map cLinkGps = SI0408Mapper.selectGpsLink((String)routNode.get("LINK_ID"));

							if(cLinkGps!=null) {
								String cStGpsX = (String)CommonUtil.bigDecimalToString(cLinkGps.get("ST_GPS_X"));
								String cStGpsY = (String)CommonUtil.bigDecimalToString(cLinkGps.get("ST_GPS_Y"));
								String cEdGpsX = (String)CommonUtil.bigDecimalToString(cLinkGps.get("ED_GPS_X"));
								String cEdGpsY = (String)CommonUtil.bigDecimalToString(cLinkGps.get("ED_GPS_Y"));
								String stGpsX = (String)CommonUtil.bigDecimalToString(data.get("ST_GPS_X"));
								String stGpsY = (String)CommonUtil.bigDecimalToString(data.get("ST_GPS_Y"));
								String edGpsX = (String)CommonUtil.bigDecimalToString(data.get("ED_GPS_X"));
								String edGpsY = (String)CommonUtil.bigDecimalToString(data.get("ED_GPS_Y"));
								
								if(cStGpsX.equals(stGpsX)
									&&cStGpsY.equals(stGpsY)
									&&cEdGpsX.equals(edGpsX)
									&&cEdGpsY.equals(edGpsY)
								)
								{
									isUpdate = true;
								}
							}
						}
						
						//String cNodeId = (String)routNode.get("NODE_ID");
						//String cEdNodeId = (String)routNode.get("ED_NODE_ID");
						if(isUpdate) {
							String nodeChildSn = (String)data.get("NODE_CHILD_SN");
							if (rowStatus.equals("C")) {
																
								if(CommonUtil.empty(nodeChildSn)==false&&"-1".equals(nodeChildSn)){
									routNode.put("NODE_CHILD_SN", "0");
									iCnt += SI0408Mapper.SI0408G1I0(routNode);
								}
								else {
									Map<String, Object> input = new HashMap<String, Object>();
									input.put("ROUT_ID", routNode.get("ROUT_ID"));
									input.put("NODE_SN", routNode.get("NODE_SN"));
									input.put("NODE_CHILD_SN", data.get("NODE_CHILD_SN"));
									input.put("NODE_TYPE", data.get("NODE_TYPE"));
									input.put("NODE_NM", data.get("NODE_NM"));
									input.put("GPS_X", data.get("GPS_X"));
									input.put("GPS_Y", data.get("GPS_Y"));

									iCnt += SI0408Mapper.SI0408G1I0(input);
								}
								
							} else if (rowStatus.equals("U")) {
								if(cOrgGpsX.equals(orgGpsX)&&cOrgGpsY.equals(orgGpsY)&&cNodeType.equals(nodeType)) { //원 gps 좌표가 동일한것만 갱신
									if(CommonUtil.empty(nodeChildSn)==false&&"-1".equals(nodeChildSn)){
										routNode.put("NODE_CHILD_SN", "0");
										iCnt += SI0408Mapper.SI0408G1I0(routNode);
									}
									else {
										Map<String, Object> input = new HashMap<String, Object>();
										input.put("ROUT_ID", routNode.get("ROUT_ID"));
										input.put("NODE_SN", routNode.get("NODE_SN"));
										input.put("NODE_CHILD_SN", data.get("NODE_CHILD_SN"));
										input.put("NODE_TYPE", data.get("NODE_TYPE"));
										input.put("NODE_NM", data.get("NODE_NM"));
										input.put("GPS_X", data.get("GPS_X"));
										input.put("GPS_Y", data.get("GPS_Y"));
										
										/*String oldNodeChildSn = (String)data.get("OLD_NODE_CHILD_SN");
										if(nodeChildSn.equals(oldNodeChildSn)==false) {
											iCnt += SI0408Mapper.SI0408G1I0(data);
										}
										else*/ 
										{
											uCnt += SI0408Mapper.SI0408G1U1(data);
										}
									}
								}
							}
						}
					}
					//oldRoutId = routId;
				}
			}
			
			for (int i = 0; i < param.size(); i++) {
				Map data = (Map) param.get(i);
				
				String rowStatus = (String) data.get("rowStatus");
				
				String nodeChildSn = (String)data.get("NODE_CHILD_SN");
				if (rowStatus.equals("C")) {
					
					if(CommonUtil.empty(nodeChildSn)==false&&"-1".equals(nodeChildSn)){
						data.put("NODE_CHILD_SN", "0");
					}
					iCnt += SI0408Mapper.SI0408G1I0(data);
				} else if (rowStatus.equals("U")) {
					if(CommonUtil.empty(nodeChildSn)==false&&"-1".equals(nodeChildSn)){
						data.put("NODE_CHILD_SN", "0");
						iCnt += SI0408Mapper.SI0408G1I0(data);
					}
					else {
						/*String oldNodeChildSn = (String)data.get("OLD_NODE_CHILD_SN");
						if(nodeChildSn.equals(oldNodeChildSn)==false) {
							iCnt += SI0408Mapper.SI0408G1I0(data);
						}
						else*/ 
						{
							uCnt += SI0408Mapper.SI0408G1U0(data);
						}
					}
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
	
	public List SI0408G1R0() throws Exception {
		// TODO Auto-generated method stub
		Map param = getSimpleDataMap("dma_sub_search");
		return SI0408Mapper.SI0408G1R0(param);
	}
}
