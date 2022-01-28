package kr.tracom.bms.domain.SI0601;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SI0601Mapper {

	public List SI0601G0R0(Map param);
	
	public Map SI0601G0K0();
	
	public List SI0601SHI0();
	
	public int SI0601G0I0(Map param);
	
	public int SI0601G0D0(Map param);
	
	public int SI0601G0U0(Map param);
	
}
