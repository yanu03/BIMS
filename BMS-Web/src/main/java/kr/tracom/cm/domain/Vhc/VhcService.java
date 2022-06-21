package kr.tracom.cm.domain.Vhc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.tracom.cm.domain.Intg.IntgMapper;
import kr.tracom.cm.support.ServiceSupport;
import kr.tracom.util.Constants;
import kr.tracom.util.DataInterface;


@Service
public class VhcService extends ServiceSupport {

	@Autowired
	private VhcMapper vhcMapper;
	
	@Autowired
	private IntgMapper intgMapper;

	public List<Map<String, Object>> selectVhcList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return vhcMapper.selectVhcList(map);
	}
	
	public List<Map<String, Object>> selectAllocVhcList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		String temp[] = map.get("VHC_ID").toString().replace("[","").replace("]","").replace(" ","").split(",");
		map.put("VHC_ID", temp);
		return vhcMapper.selectAllocVhcList(map);
	}
	
	public List<Map<String, Object>> selectCurOperVhcList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return vhcMapper.selectCurOperVhcList(map);
	}
	
	public List<Map<String, Object>> selectVhcItem() throws Exception {
		return vhcMapper.selectVhcItem();
	}
	
	/*public List<Map<String, Object>> selectRoutList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return routMapper.selectRoutList(map);
	}
	
	public List<Map<String, Object>> selectRoutItem() throws Exception {
		return routMapper.selectRoutItem();
	}
	
	public List<Map<String, Object>> selectNodeListByRouts() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		String temp[] = map.get("ROUT_IDS").toString().replace("[","").replace("]","").replace(" ","").split(",");
		map.put("ROUT_IDS", temp);
		return routMapper.selectNodeListByRouts(map);
	}
	
	public List<Map<String, Object>> selectNodeListByRout() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_sub_search");
		return routMapper.selectNodeListByRout(map);
	}
	
	public List<Map<String, Object>> selectNodeListByRepRout() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return routMapper.selectNodeListByRepRout(map);
	}

	public List<Map<String, Object>> selectSttnList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return routMapper.selectSttnList(map);
	}
	
	public List<Map<String, Object>> selectSttnItem() throws Exception {
		return routMapper.selectSttnItem();
	}*/
	
	public List selectVhcBit() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");	
		Map<String, Object> param = new HashMap<>();
		List<Map<String, Object>> returnList = null;
		List<Map<String, Object>> nodeList = null;
		List<Map<String, Object>> staList = null;
		
		String sttnId = (String) map.get("PUB_STTN_ID");
		String cityCode = (String) map.get("AREA");
		param.put("INTG_ID", Constants.OPENAPI_BIT_ID);
		
		List<Map<String, Object>> list = intgMapper.selectIntg(param);
		String baseUrl = (String) list.get(0).get("INTG_URL");
		String apiKey = (String) list.get(0).get("INTG_API_KEY");
		
		String url = baseUrl + "serviceKey=" + apiKey + "&pageNo=1&numOfRows=999&_type=xml&cityCode="+cityCode+"&nodeId="+ sttnId;
		
		staList = new ArrayList<>();
		NodeList tempList = DataInterface.interface_XML(url);
		
		for(int i = 0; i < tempList.getLength(); i++) {
			Map<String, Object> tmp = new HashMap<String, Object>();
			Node child = tempList.item(i);

			//한 정류장의 bit parse
			if(child.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element)child;
				tmp.put("REMAIN_STTN",DataInterface.getTagValue("arrprevstationcnt", eElement)); //남은 정거장 수
				tmp.put("REMAIN_TM",DataInterface.getTagValue("arrtime", eElement)); // 남은 도착 시간(초)
				tmp.put("NODE_ID",DataInterface.getTagValue("nodeid", eElement).substring(3));
				tmp.put("NODE_NM",DataInterface.getTagValue("nodenm", eElement));
				tmp.put("ROUT_ID",DataInterface.getTagValue("routeid", eElement).substring(3));
				tmp.put("ROUT_NM",DataInterface.getTagValue("routeno", eElement));
				tmp.put("VHC_KIND",DataInterface.getTagValue("routetp", eElement));
				tmp.put("VHC_TYPE",DataInterface.getTagValue("vehicletp", eElement));
				
				staList.add(tmp);
			}
		}
		
		/*if(staList!=null && staList.size() > 0) {
			returnList = DataInterface.generalNode2(staList,nodeList);
			DataInterface.insertNodeToNode(returnList, staList);
			
			map.put("NODE_TYPE",Constants.NODE_TYPE_CROSS);
			List<Map<String, Object>> crsList = routMapper.selectNodeListByRout(map);
			if(crsList!=null && crsList.size() > 0) {
				returnList = DataInterface.generalNode2(crsList,returnList);
				DataInterface.insertNodeToNode(returnList, crsList);
			}
			map.put("NODE_TYPE",Constants.NODE_TYPE_SOUND);
			List<Map<String, Object>> sndList = routMapper.selectNodeListByRout(map);
			if(sndList!=null && sndList.size() > 0) {
				DataInterface.insertNodeToNode(returnList, sndList);
			}
		}*/
		
		return staList;
	}
}
