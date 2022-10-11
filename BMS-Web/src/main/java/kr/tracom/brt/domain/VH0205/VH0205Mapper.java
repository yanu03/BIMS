package kr.tracom.brt.domain.VH0205;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VH0205Mapper {
	
	public List<Map> VH0205G0R0(Map param);
	
	public List<Map> selectFcltItem();
	
	public List<Map> selectParamItem();
	
}
