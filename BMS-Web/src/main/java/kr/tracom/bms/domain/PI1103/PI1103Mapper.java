package kr.tracom.bms.domain.PI1103;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PI1103Mapper {

	public List PI1103G0R0(Map param);
	
	public List PI1103SHI0();
	
	public Map PI1103G0K0();
	
	public int PI1103G0I0(Map param);
	
	public int PI1103G0D0(Map param);
	
	public int PI1103G0U0(Map param);
}
