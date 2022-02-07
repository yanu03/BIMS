package kr.tracom.cm.domain.Partner;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PartnerMapper {
	public List<Map<String, Object>> selectPartnerList(Map param);
	
	public List<Map<String, Object>> selectPartnerItem();
}
