package kr.tracom.brt.domain.VH0206;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0206Mapper {
	
	public List<Map> VH0206G0R0(Map param);
	
	public List<Map> selectDvcItem();
	
	public List<Map> selectDvcParamItem();
	
}
