package kr.tracom.bms.domain.FM0101;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FM0101Mapper {

	
	public List FM0101SHI1();
	
	public List FM0101SHI2();
	
	public List FM0101G1R0(Map param);
	
	public List FM0101G2R0(Map param);
	
	public List FM0101G3R0(Map param);
	
	public int FM0101G2I0(Map param);
	
	public int FM0101G2U0(Map param);
	
	public int FM0101G2D0(Map param);
	
	public List FM0101P0SH();
	
	/** PLF 테이블 관련 20211018 jh **/
	public int FM0101G1I2(Map param);
	
	public int FM0101G1U2(Map param);
	
	public int FM0101G1D2(Map param);
	
	public List<Map> FM0101P0R0(Map param);
	
}
