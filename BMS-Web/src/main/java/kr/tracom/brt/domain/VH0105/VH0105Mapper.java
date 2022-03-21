package kr.tracom.brt.domain.VH0105;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0105Mapper {
	
	public List<Map> VH0105G0R0(Map param);
	
	public List<Map> VH0105SHI0();
	/*
	public List<Map> FM0201G1R0(Map param);
	
	
	
	public List FM0201SHI1();
	
	public List FM0201SHI2();
	
	public List FM0201SHI3(Map param);	*/
	
}
