package kr.tracom.brt.domain.ST0202;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ST0202Mapper {

	public List ST0202G0R0(Map param);
	
	public List ST0202G1R0(Map param);
	/*
	public List PI0503G0R0(Map param);
	
	public List PI0503SHI0();
	
	public List PI0503G1R0();
	
	public List PI0503G2R0(Map param);
	
	public int PI0503G1I0(Map param);
	
	public int PI0503G1I1(Map param); //예약결과 정보 insert
	
	public int PI0503G1U0(Map param);
	
	/*public int PI0503G1D0(Map param);
	
	
	//영상 playlist 생성
	List<Map<String, Object>> makePlayList(String value);*/
}
