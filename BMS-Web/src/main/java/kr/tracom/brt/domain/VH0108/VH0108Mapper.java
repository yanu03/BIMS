package kr.tracom.brt.domain.VH0108;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0108Mapper {
	
	public List VH0108G0R0(Map param);
	
	public List VH0108G1R0(Map param);
	
	public List VH0108P0R0(Map param);
	
	public int VH0108G1I0(Map param);
	
	public int VH0108G1U0(Map param);
	
	public int VH0108G1D0(Map param);
	
	public List VH0108SHI0();
}
