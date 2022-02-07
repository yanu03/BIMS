package kr.tracom.bms.domain.PI1100;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PI1100Mapper {
	
	public List PI1100G0R0(Map param);
	
	public List PI1100SHI0();
	
	public List PI1100P0R0(Map param);
	
	public Map PI1100G0K0();
	
	public int PI1100G0I0(Map param);
	
	public int PI1100G0D0(Map param);
	
	public int PI1100G0U0(Map param);
}
