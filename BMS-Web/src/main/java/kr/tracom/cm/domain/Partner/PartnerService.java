package kr.tracom.cm.domain.Partner;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;

@Service
public class PartnerService extends ServiceSupport {

	@Autowired
	private PartnerMapper reproutMapper;

	public List<Map<String, Object>> selectPartnerList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return reproutMapper.selectPartnerList(map);
	}
	
	public List<Map<String, Object>> selectPartnerItem() throws Exception {
		return reproutMapper.selectPartnerItem();
	}
	
}
