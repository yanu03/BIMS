package kr.tracom.brt.domain.ST0600;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ST0600Mapper {

	public List ST0600G0R0(Map param);
	public List ST0600G0R1(Map param);
	
	public List ST0600G1R0(Map param);
	public List ST0600G1R1(Map param);
}
