package kr.tracom.brt.domain.VH0502;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0502Mapper {
	
	public List<Map> VH0502G0R0(Map param);
	
	public List<Map> VH0502SHI0();
	
}
