package kr.tracom.bms.domain.PI0801;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PI0801Mapper {
	
	public List PI0801G0R0(Map param);
	
	public List PI0801G1R0(Map param);
	
	public List PI0801G2R0(Map param);
	
	public List PI0801SHI0();
	
	public Map PI0801G0K0();
	
	public List PI0801P0R0(Map param);
	
	public int PI0801G0I0(Map param);
	
	public int PI0801G0D0(Map param);
	
	public int PI0801G0U0(Map param);
	
	public int PI0801G0U1(Map param);
	
	public List PI0801SHI1();
	
	public Map PI0801G1K0();
	
	public int PI0801G1I0(Map param);
	
	public int PI0801G1D0(Map param);
	
	public int PI0801G1U0(Map param);
	
	public List PI0801SHI2();
	
	public Map PI0801G2K0();
	
	public int PI0801G2I0(Map param);
	
	public int PI0801G2D0(Map param);
	
	public int PI0801G2U0(Map param);	
	
}
