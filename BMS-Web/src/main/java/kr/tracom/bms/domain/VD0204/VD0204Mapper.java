package kr.tracom.bms.domain.VD0204;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VD0204Mapper {

	public List VD0204G0R0(Map param);
	
	public List VD0204SHI0();
	
	public List VD0204SHI1();
	
	public List VD0204G1R0(Map param);
	
	public List VD0204G2R0(Map param);
	
	public int VD0204G2I0(Map param);
	
	public int VD0204G2U0(Map param);
	
	public int VD0204G2D0(Map param);
	
	public List VD0204P0SH();
	
	/** PLF 테이블 관련 20211018 jh **/
	public int VD0204G1I2(Map param);
	
	public int VD0204G1U2(Map param);
	
	public int VD0204G1D2(Map param);
	
	public List<Map> VD0204P0R0(Map param);
	
}
