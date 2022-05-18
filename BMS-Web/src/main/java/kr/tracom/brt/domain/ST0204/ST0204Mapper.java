package kr.tracom.brt.domain.ST0204;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ST0204Mapper {

	public List ST0204G0R0(Map param);
	
	public List ST0204G1R0(Map param);
	public List ST0204G1R1(Map param);
}
