package kr.tracom.brt.domain.ST0605;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ST0605Mapper {
	
	public List ST0605G0R0(Map param);
	
	public List ST0605SHI0();
	
	public List ST0605SHI1(Map param);
	
	public List ST0605G1R0(Map param);
	
	public List ST0605G1CNT(Map param);
}
