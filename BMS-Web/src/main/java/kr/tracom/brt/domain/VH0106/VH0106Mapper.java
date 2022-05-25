package kr.tracom.brt.domain.VH0106;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0106Mapper {
	
	public List<Map> VH0106G0R0(Map param);
	
}
