package kr.tracom.bms.domain.SI0408;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SI0408Mapper {
	
	public List SI0408G1R0(Map param); 
	
	public int SI0408G1I0(Map param);
	
	public int SI0408G1D0(Map param);
	
	public int SI0408G1U0(Map param);
	
	public int SI0408G1D1(Map param);
	
	public int SI0408G1U1(Map param);
	
	public int SI0408G1U2(Map param);
	
	
	public List selectRoutNodeAllByRepRout(Map param);
	
	public Map<String, Object> selectGpsLink(String linkID);	
	
}
