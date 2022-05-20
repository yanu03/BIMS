package kr.tracom.brt.domain.VH0202;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0202Mapper {
	
	public List<Map> VH0202G0R0(Map param);
	
}
