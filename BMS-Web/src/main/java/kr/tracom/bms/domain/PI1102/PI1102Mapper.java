package kr.tracom.bms.domain.PI1102;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PI1102Mapper {

	public List PI1102G0R0(Map param);
	
	public List PI1102SHI0();
	
	public List PI1102G1R0(Map param);
	
	public List PI1102G2R0(Map param);
	
	public int PI1102G1I0(Map param);
	
	public int PI1102G1I1(Map param); //예약결과 정보 insert
	
	public int PI1102G1U0(Map param);
	
	/*public int PI1102G1D0(Map param);*/
	
	
	//영상 playlist 생성
	List<Map<String, Object>> makePlayList(String value);
}
