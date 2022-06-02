package kr.tracom.brt.domain.ST0804;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ST0804Mapper {

	public List ST0804G0R0(Map param);
	public List ST0804G0R1(Map param);
	
	public List ST0804G1R0(Map param);
	public List ST0804G1R1(Map param);
}
