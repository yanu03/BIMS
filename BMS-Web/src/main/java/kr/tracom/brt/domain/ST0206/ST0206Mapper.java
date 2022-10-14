package kr.tracom.brt.domain.ST0206;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ST0206Mapper {

	public List ST0206G0R0(Map param);
	public List ST0206PROC();
	
	public List ST0206G1R0(Map param);
	public List ST0206G1R1(Map param);
	public List ST0206G1R2(Map param);
	
}
