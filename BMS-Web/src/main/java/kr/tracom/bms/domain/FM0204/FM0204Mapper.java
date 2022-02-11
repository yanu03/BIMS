package kr.tracom.bms.domain.FM0204;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FM0204Mapper {

	public List FM0204G0R0();
	
	public List FM0204G0R1();
	
	public List FM0204G0R2(Map param);
	
	public List FM0204SHI0();
	
	public List FM0204SHI1();
	
	public List FM0204G1R0(Map param);
	
	public List FM0204G2R0();
	
	public List FM0204G2R1();
	
	public int FM0204G2I0(Map param);
	
	public int FM0204G2U0(Map param);
	
	public int FM0204G2D0(Map param);
	
	/** PLF 테이블 관련 20211018 jh **/
	public int FM0204G1I2(Map param);
	
	public int FM0204G1U2(Map param);
	
	public int FM0204G1D2(Map param);
	
	public List<Map> FM0204P0R0(Map param);
	
}
