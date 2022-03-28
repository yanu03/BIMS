package kr.tracom.brt.domain.VH0501;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0501Mapper {
	
	public List<Map> VH0501G0R0(Map param);
	
	public List<Map> selectIntgNmItem();
	
	public List<Map> selectIntgStsItem();
	
}
