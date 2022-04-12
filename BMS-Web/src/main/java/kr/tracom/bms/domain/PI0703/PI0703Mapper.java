package kr.tracom.bms.domain.PI0703;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PI0703Mapper {

	public List PI0703G0R0(Map param);
	
	public List PI0703SHI0();
	
	public List PI0703SHI1();
	
	public Map PI0703G1K0();
	
	public List PI0703G1R0(Map param);
	
	public int PI0703G0I0(Map param);
	
	public int PI0703G0U0(Map param);
	
	public int PI0703G0D0(Map param);
	
	public List PI0703G2R0(Map param);
	
	public List PI0703G1R1(); 
	
	
	//차량별 장치정보 
	List<Map<String, Object>> selectDvcCd(String vhcId);
	//노선정보
	Map<String, Object> selectRouteInfo(String vhcId);
	
}
