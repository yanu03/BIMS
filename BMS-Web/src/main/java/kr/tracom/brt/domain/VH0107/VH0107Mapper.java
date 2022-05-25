package kr.tracom.brt.domain.VH0107;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0107Mapper {
	
	public List<Map> VH0107G0R0(Map param);
	
}
