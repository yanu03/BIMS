package kr.tracom.bms.domain.PI1104;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PI1104Mapper {

	public List PI1104G0R0(Map param);
	
	public List PI1104SHI0();
	
	public Map PI1104G1K0();
	
	public List PI1104G1R0(Map param);
	
	public int PI1104G1I0(Map param);
	
	public int PI1104G1U0(Map param);
	
	public int PI1104G1D0(Map param);
	
	public List PI1104G2R0(Map param);
	
	public List PI1104G1R1();
	
}
