package kr.tracom.brt.domain.ST0205;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ST0205Mapper {

	public List ST0205G0R0(Map param);
	public List ST0205G0R1(Map param);
	
	public List ST0205G1R0(Map param);
	public List ST0205G1R1(Map param);
}
