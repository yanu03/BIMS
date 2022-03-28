package kr.tracom.cm.domain.Crs;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CrsMapper {
	
	public List<Map<String, Object>> selectCrsItem();
	
	public List<Map<String, Object>> selectGrpCrsItem();

}
