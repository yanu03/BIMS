package kr.tracom.brt.domain.VH0601;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0601Mapper {
	
	public List<Map> VH0601G0R0(Map param);
	
}
