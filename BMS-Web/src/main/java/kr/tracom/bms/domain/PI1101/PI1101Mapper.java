package kr.tracom.bms.domain.PI1101;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PI1101Mapper {

	public List PI1101G0R0(Map param);
	
	public Map PI1101G0K0();
	
	public List PI1101SHI0();
	
	public List PI1101G1R0();
	
	public List PI1101G2R0(Map param);
	
	public int PI1101G0I0(Map param);
	
	public int PI1101G0D0(Map param);
	
	public int PI1101G0U0(Map param);

	public int PI1101G2I0(Map param);
	
	public int PI1101G2U0(Map param);
	
	public int PI1101G2D0(Map param);
	
	
}
