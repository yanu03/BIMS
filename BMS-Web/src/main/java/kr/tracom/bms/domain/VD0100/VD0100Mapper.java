package kr.tracom.bms.domain.VD0100;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VD0100Mapper {

	//public List VD0100G0R0(Map param);
	
	public List VD0100SHI0();
	
	public List VD0100SHI1();
	
	public List VD0100G0R0(Map param);
	
	public Map VD0100G0K0();

	public int VD0100G0I0(Map param);
	
	public int VD0100G0U0(Map param);
	
	public int VD0100G0D0(Map param);
	
	/** PLF 테이블 관련 20211018 jh **/
	public int VD0100G0I2(Map param);
	
	public int VD0100G0U2(Map param);
	
	public int VD0100G0D2(Map param);
	
}
