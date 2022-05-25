package kr.tracom.cm.domain.Intg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IntgMapper {
	
	public List<Map<String, Object>> selectIntgList(Map param);
	
}
