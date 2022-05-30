package kr.tracom.bms.domain.VD0101;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VD0101Mapper {

	public List VD0101G0R0(Map param);
	
	public List VD0101SHI0();
	
	public List VD0101SHI1();
	
	public List VD0101G1R0(Map param);
	
	public List VD0101G2R0(Map param);
	
	public int VD0101G2I0(Map param);
	
	public int VD0101G2U0(Map param);
	
	public int VD0101G2D0(Map param);
	
	public List VD0101P0SH();
	
	public List<Map> VD0101P0R0(Map param);
	
	
}
