package kr.tracom.brt.domain.ST0504;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ST0504Mapper {

	public List ST0504G0R0(Map param);
	public List ST0504G0R1(Map param);
	
	public List ST0504G1R0(Map param);
	public List ST0504G1R1(Map param);
}
