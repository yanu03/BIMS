package kr.tracom.bms.domain.FM0204;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FM0204Mapper {

	public List FM0204G0R0(Map param);
	
	public List FM0204G0R1(Map param);
	
	public List FM0204G0R2(Map param);
	
	public List FM0204G1R0(Map param);
	
	public List FM0204G2R0();
	
	public List FM0204G2R1();
	
	public List FM0204G2R2(Map param);
	
	public List<Map> FM0204P0R0(Map param);
	
}
