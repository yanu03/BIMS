package kr.tracom.brt.domain.ST0503;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ST0503Mapper {

	public List ST0503G0R0(Map param);
	public List ST0503G0R1(Map param);
	
	public List ST0503G1R0(Map param);
	public List ST0503G1R1(Map param);
}
