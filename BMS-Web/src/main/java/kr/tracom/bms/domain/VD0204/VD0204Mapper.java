package kr.tracom.bms.domain.VD0204;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VD0204Mapper {

	public List VD0204G0R0();
	
	public List VD0204G0R1();
	
	public List VD0204G0R2(Map param);
	
	public List VD0204SHI0();
	
	public List VD0204SHI1();
	
	public List VD0204G1R0(Map param);
	
	public List VD0204G2R0();
	
	public List VD0204G2R1();
	
	
}
