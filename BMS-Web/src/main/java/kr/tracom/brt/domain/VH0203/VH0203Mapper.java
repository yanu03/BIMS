package kr.tracom.brt.domain.VH0203;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0203Mapper {
	
	public List<Map> VH0203G0R0(Map param);
	
}
