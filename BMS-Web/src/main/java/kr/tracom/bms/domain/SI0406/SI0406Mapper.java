package kr.tracom.bms.domain.SI0406;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SI0406Mapper {
	
	public List SI0406G0R0(Map param);
	
	
	public List SI0406SHI0();
	
	public List SI0406G1R0(Map param);
	
	public List SI0406G2R0();
	
	public List SI0406P0R0();
	
	public int SI0406G1I0(Map param);
	public int SI0406G1U0(Map param);
	public int SI0406G1D0(Map param);
	
	public int SI0406G1DA0(Map param); //노선과노드구성 테이블 삭제
	public int SI0406G1DA1(Map param); //노선과링크구성 테이블 삭제

}
