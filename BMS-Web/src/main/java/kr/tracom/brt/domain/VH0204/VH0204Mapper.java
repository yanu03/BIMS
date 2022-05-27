package kr.tracom.brt.domain.VH0204;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0204Mapper {
	
	public List<Map> VH0204G0R0(Map param);
	
}
