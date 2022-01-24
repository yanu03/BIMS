package kr.tracom.bms.domain.SI0801;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SI0801Mapper {
	
	public List<Map> SI0801G0R0(Map param);
	public List<Map> SI0801G1R0(Map param);
	public List SI0801SHI0();
	public int SI0801G0I0(Map param);
	public int SI0801G0D0(Map param);
	public int SI0801G0U0(Map param);
	
	public int SI0801G1I0(Map param);
	public int SI0801G1D0(Map param);
	public int SI0801G1U0(Map param);
	
	public int SI0801G0U1(Map param);
}