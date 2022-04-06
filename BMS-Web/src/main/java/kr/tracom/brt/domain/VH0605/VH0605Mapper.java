package kr.tracom.brt.domain.VH0605;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0605Mapper {
	
	public List<Map> VH0605G0R0(Map param);
	
}
