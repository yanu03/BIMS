package kr.tracom.cm.domain.Intg;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.tracom.cm.support.ServiceSupport;


@Service
public class IntgService extends ServiceSupport {

	@Autowired
	private IntgMapper intgMapper;
	
	public List<Map<String, Object>> selectIntgList() throws Exception {
		Map<String, Object> map = getSimpleDataMap("dma_search");
		return intgMapper.selectIntgList(map);
	}
	
}
