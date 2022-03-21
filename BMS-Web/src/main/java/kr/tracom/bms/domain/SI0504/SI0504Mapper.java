package kr.tracom.bms.domain.SI0504;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SI0504Mapper {
	
	public List SI0504G0R0(Map param);
	
	public Map SI0504G0K0();
	
	public List SI0504SHI0();
	
	public List SI0504G1R0(Map param);
	
	public List SI0504P0R0(Map param);
	
	public List SI0504P1R0(Map param);
	
	public Map SI0504P1K0();
	
	public int SI0504P1I0(Map param);
	
	public int SI0504P1U0(Map param);
	
	public int SI0504P1D0(Map param);
	
	public List SI0504P2R0(Map param);
	
	public List SI0504P3R0(Map param);
	
	public int SI0504G0I0(Map param);
	
	public int SI0504G0D0(Map param);
	
	public int SI0504G0U0(Map param);
	
	public int SI0504G0U1(Map param);

	public int SI0504G1I0(Map param);
	
	public int SI0504G1D0(Map param);
	
}
