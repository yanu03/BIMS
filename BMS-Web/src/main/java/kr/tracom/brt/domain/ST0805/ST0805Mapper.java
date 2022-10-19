package kr.tracom.brt.domain.ST0805;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ST0805Mapper {

	public List ST0805G0R0(Map param);
	
	public List ST0805G1R0(Map param);
	
	public List ST0805G1R1(Map param);
	
	public List ST0805G2R0(Map param);
	
	public List<Map> selectStatHItem();
}
