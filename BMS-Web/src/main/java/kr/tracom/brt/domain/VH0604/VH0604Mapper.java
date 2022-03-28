package kr.tracom.brt.domain.VH0604;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0604Mapper {
	
	public List<Map> VH0604G0R0(Map param);
	
}
