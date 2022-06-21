package kr.tracom.bms.domain.FM0205;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FM0205Mapper {

	public List FM0205G0R0(Map param);
	
	public List FM0205G0R1();
	
	public List FM0205G0R2(Map param);
	
	public List FM0205G1R0(Map param);
	
	public List FM0205G2R0();
	
	public List FM0205G2R1();
	
	public List FM0205G2R2(Map param);
	
	public List<Map> FM0205P0R0(Map param);
	
}
